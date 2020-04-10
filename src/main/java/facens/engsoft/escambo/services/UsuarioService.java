package facens.engsoft.escambo.services;

import facens.engsoft.escambo.enums.Nota;
import facens.engsoft.escambo.models.Usuario;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    public boolean usuarioPodeSerAvaliadoPorOutroUsuario(Usuario avaliador, Usuario avaliado) {
        return false;
    }

    public boolean avaliarUsuario(Usuario avaliador, Usuario avaliado, Nota nota) {
        return false;
    }
}
