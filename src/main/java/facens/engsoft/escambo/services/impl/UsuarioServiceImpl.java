package facens.engsoft.escambo.services.impl;

import facens.engsoft.escambo.enums.Nota;
import facens.engsoft.escambo.models.Usuario;
import facens.engsoft.escambo.services.UsuarioService;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Override public boolean usuarioPodeSerAvaliadoPorOutroUsuario(Usuario avaliador, Usuario avaliado) {
        return false;
    }

    @Override public boolean avaliarUsuario(Usuario avaliador, Usuario avaliado, Nota nota) {
        return false;
    }
}
