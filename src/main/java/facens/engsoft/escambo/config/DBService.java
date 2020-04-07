package facens.engsoft.escambo.config;

import facens.engsoft.escambo.models.Usuario;
import facens.engsoft.escambo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DBService {

    private final UserService userService;

    public void instanciaBancoDeDadosParaTestes() {
        Usuario usuario = new Usuario();
        usuario.setNome("Zaphod Beeblebrox");

        userService.salvarUsuario(usuario);
    }
}
