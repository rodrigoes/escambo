package facens.engsoft.escambo.services;

import facens.engsoft.escambo.enums.Nota;
import facens.engsoft.escambo.models.Usuario;

public interface UsuarioService {
    boolean usuarioPodeSerAvaliadoPorOutroUsuario(Usuario avaliador, Usuario avaliado);

    boolean avaliarUsuario(Usuario avaliador, Usuario avaliado, Nota nota);
}
