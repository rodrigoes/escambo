package facens.engsoft.escambo.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificacaoDeEscambo {
    private Integer id;
    private String mensagem;
    private SolicitacaoDeEscambo solicitacaoDeEscambo;
}
