package facens.engsoft.escambo.services;

import facens.engsoft.escambo.models.Usuario;
import facens.engsoft.escambo.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UsuarioRepository usuarioRepository;

    public Boolean salvarUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);

        return true;
    }
}
