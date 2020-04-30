package facens.engsoft.escambo.services.impl;

import facens.engsoft.escambo.models.*;
import facens.engsoft.escambo.services.NotificacaoService;
import org.springframework.stereotype.Service;

@Service
public class NotificacaoServiceImpl implements NotificacaoService {

    @Override public NotificacaoDeInteresse notificarInteresse(Usuario usuarioInteressado, Item itemDeInteresse) {
        // TODO: disparar notificação para o usuário
        return null;
    }

    @Override public NotificacaoDeEscambo notificarEscambo(SolicitacaoDeEscambo solicitacaoDeEscambo) {
        // TODO: disparar notificação para o usuário
        return new NotificacaoDeEscambo();
    }
}
