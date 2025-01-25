package com.matchpointecv.matchpointecv.usuario;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.matchpointecv.matchpointecv.auth.JwtTokenService;
import com.matchpointecv.matchpointecv.auth.UserDetailsImpl;
import com.matchpointecv.matchpointecv.auth.dto.CredenciaisDTO;
import com.matchpointecv.matchpointecv.auth.dto.TokenDTO;
import com.matchpointecv.matchpointecv.exception.CustomException;
import com.matchpointecv.matchpointecv.exception.RecordNotFoundException;
import com.matchpointecv.matchpointecv.role.Role;
import com.matchpointecv.matchpointecv.role.RoleRepository;
import com.matchpointecv.matchpointecv.role.enuns.RoleName;
import com.matchpointecv.matchpointecv.security.SecurityConfig;
import com.matchpointecv.matchpointecv.usuario.dto.UsuarioDTO;
import com.matchpointecv.matchpointecv.usuario.dto.UsuarioVisualizarDTO;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private SecurityConfig securityConfig;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenService jwtTokenService;

    @Override
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
    public boolean save(UsuarioDTO usuarioDTO) {
        Usuario usrCpf = repository.findByCpf(usuarioDTO.getCpf());

        if (usrCpf == null) {
            String passwordHashred = BCrypt.withDefaults()
                    .hashToString(12, usuarioDTO.getSenha().toCharArray());

            Usuario usuario = new Usuario();
            usuario.setId(usuarioDTO.getId());
            usuario.setNome(usuarioDTO.getNome());
            usuario.setEmail(usuarioDTO.getEmail());
            usuario.setSenha(passwordHashred);
            usuario.setDataNascimento(usuarioDTO.getDataNascimento());
            usuario.setCpf(usuarioDTO.getCpf());

            Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Erro: Role não encontrada."));
            Set<Role> roles = new HashSet<>();
            roles.add(userRole);
            usuario.setRoles(roles);

            return Optional.of(repository.save(usuario)).isPresent();
        } else {
            throw new CustomException("CPF já cadastrado");
        }

    }

    public TokenDTO autenticarUsuario(CredenciaisDTO credenciaisDTO) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(credenciaisDTO.email(), credenciaisDTO.senha());

        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        UserDetailsImpl modelUserDetails = (UserDetailsImpl) authentication.getPrincipal();
        return new TokenDTO(jwtTokenService.generateToken(modelUserDetails));
    }

}
