package facens.engsoft.escambo.services;

import facens.engsoft.escambo.models.*;
import org.springframework.stereotype.Service;

@Service
public class NotificacaoService {

    public NotificacaoDeInteresse notificarInteresse(Usuario usuarioInteressado, Item itemDeInteresse) {
        // TODO: disparar notificação para o usuário
        return null;
    }

    public NotificacaoDeEscambo notificarEscambo(SolicitacaoDeEscambo solicitacaoDeEscambo) {
        // TODO: disparar notificação para o usuário
        return new NotificacaoDeEscambo();
    }
}
