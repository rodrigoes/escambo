package facens.engsoft.escambo.services.impl;

import facens.engsoft.escambo.models.Usuario;
import facens.engsoft.escambo.repositories.UsuarioRepository;
import facens.engsoft.escambo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UsuarioRepository usuarioRepository;

    @Override public Boolean salvarUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);

        return true;
    }
}
