package facens.engsoft.escambo.services;

import facens.engsoft.escambo.models.Item;
import facens.engsoft.escambo.models.NotificacaoDeEscambo;
import facens.engsoft.escambo.models.NotificacaoDeInteresse;
import facens.engsoft.escambo.models.SolicitacaoDeEscambo;
import facens.engsoft.escambo.models.Usuario;

public interface NotificacaoService {
    NotificacaoDeInteresse notificarInteresse(Usuario usuarioInteressado, Item itemDeInteresse);

    NotificacaoDeEscambo notificarEscambo(SolicitacaoDeEscambo solicitacaoDeEscambo);
}
