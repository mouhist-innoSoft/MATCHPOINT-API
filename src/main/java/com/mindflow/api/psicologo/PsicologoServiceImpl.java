package com.mindflow.api.psicologo;

import com.mindflow.api.exception.RecordNotFoundException;
import com.mindflow.api.psicologo.dto.PsicologoDTO;
import com.mindflow.api.usuario.Usuario;
import com.mindflow.api.usuario.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PsicologoServiceImpl implements PsicologoService {

    private final PsicologoRepository psicologoRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    public PsicologoDTO createPsicologo(PsicologoDTO psicologoDTO) {
        Usuario usuario = usuarioRepository.findById(psicologoDTO.usuario_id())
                .orElseThrow(() -> new RecordNotFoundException("Usuário", psicologoDTO.usuario_id()));

        Psicologo psicologo = Psicologo.builder()
                .usuario(usuario)
                .crp(psicologoDTO.crp())
                .especialidade(psicologoDTO.especialidade())
                .build();

        psicologo = psicologoRepository.save(psicologo);
        return toDTO(psicologo);
    }

    @Override
    public PsicologoDTO getPsicologoById(Long id) {
        return psicologoRepository.findById(id).map(this::toDTO)
                .orElseThrow(() -> new RecordNotFoundException("Psicólogo", id));
    }

    @Override
    public List<PsicologoDTO> getAllPsicologos() {
        return psicologoRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public PsicologoDTO updatePsicologo(Long id, PsicologoDTO psicologoDTO) {
        Psicologo psicologo = psicologoRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Psicólogo", id));

        Usuario usuario = usuarioRepository.findById(psicologoDTO.usuario_id())
                .orElseThrow(() -> new RecordNotFoundException("Usuário", psicologoDTO.usuario_id()));

        psicologo.setUsuario(usuario);
        psicologo.setCrp(psicologoDTO.crp());
        psicologo.setEspecialidade(psicologoDTO.especialidade());

        psicologo = psicologoRepository.save(psicologo);
        return toDTO(psicologo);
    }

    @Override
    public void deletePsicologo(Long id) {
        psicologoRepository.delete(psicologoRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Psicólogo", id)));
    }

    private PsicologoDTO toDTO(Psicologo psicologo) {
        return PsicologoDTO.builder()
                .id(psicologo.getId())
                .usuario_id(psicologo.getUsuario().getId())
                .crp(psicologo.getCrp())
                .especialidade(psicologo.getEspecialidade())
                .build();
    }
}
