package com.mindflow.api.usuario;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.mindflow.api.auth.JwtTokenService;
import com.mindflow.api.auth.UserDetailsImpl;
import com.mindflow.api.auth.dto.CredenciaisDTO;
import com.mindflow.api.auth.dto.TokenDTO;
import com.mindflow.api.exception.CustomException;
import com.mindflow.api.exception.RecordNotFoundException;
import com.mindflow.api.role.Role;
import com.mindflow.api.role.RoleRepository;
import com.mindflow.api.usuario.dto.CriarUsuarioDTO;
import com.mindflow.api.usuario.dto.UsuarioDTO;
import com.mindflow.api.usuario.dto.UsuarioVisualizarDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UsuarioServiceImpl implements UsuarioService {


    private final UsuarioRepository repository;
    private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;


    public UsuarioDTO getById(Long id) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Usuário", id));

        return modelMapper.map(usuario, UsuarioDTO.class);
    }

    @Override
    public List<Usuario> getAll() {
        return repository.findAll();
    }

    @Override
    public List<UsuarioVisualizarDTO> getAllByIds(List<Long> ids) {
        List<Usuario> usuarios = repository.findAllByIdIn(ids);

        return usuarios.stream()
                .map(usuario -> modelMapper.map(usuario, UsuarioVisualizarDTO.class))
                .toList();
    }

    @Override
    public boolean save(CriarUsuarioDTO criarUsuarioDTO) {
        repository.findByCpf(criarUsuarioDTO.cpf()).ifPresent(u -> {
            throw new CustomException("CPF já cadastrado");
        });

        repository.findByEmail(criarUsuarioDTO.email()).ifPresent(u -> {
            throw new CustomException("E-mail já cadastrado");
        });

        String passwordHashred = BCrypt.withDefaults()
                .hashToString(12, criarUsuarioDTO.senha().toCharArray());

        Usuario usuario = new Usuario();
        usuario.setEmail(criarUsuarioDTO.email());
        usuario.setSenha(passwordHashred);
        usuario.setCpf(criarUsuarioDTO.cpf());
        if (criarUsuarioDTO.dataNascimento() != null && !criarUsuarioDTO.dataNascimento().isEmpty()) {
            usuario.setDataNascimento(LocalDate.parse(criarUsuarioDTO.dataNascimento()));
        }
        usuario.setNome(criarUsuarioDTO.nome());
        usuario.setCreatedAt(OffsetDateTime.now());
        usuario.setUpdatedAt(OffsetDateTime.now());

        Role userRole = roleRepository.findByName(criarUsuarioDTO.role())
                .orElseThrow(() -> new RuntimeException("Erro: Role não encontrada."));
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);
        usuario.setRoles(roles);

        repository.save(usuario);
        return true;
    }

    @Override
    public TokenDTO autenticarUsuario(CredenciaisDTO credenciaisDTO) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(credenciaisDTO.email(), credenciaisDTO.senha());

        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        UserDetailsImpl modelUserDetails = (UserDetailsImpl) authentication.getPrincipal();
        String token = jwtTokenService.generateToken(modelUserDetails);

        Usuario usuario = modelUserDetails.getUsuario();
        UsuarioDTO usuarioDTO = modelMapper.map(usuario, UsuarioDTO.class);
        usuarioDTO.setRoles(usuario.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toSet()));

        return new TokenDTO(token, usuarioDTO);
    }

}
