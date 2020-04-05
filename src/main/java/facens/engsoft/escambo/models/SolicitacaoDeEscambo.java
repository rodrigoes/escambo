package facens.engsoft.escambo.models;

import facens.engsoft.escambo.enums.StatusDaSolicitacao;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SolicitacaoDeEscambo {
    private Integer id;
    private Item itemDoSolicitador;
    private Item itemSolicitado;
    private StatusDaSolicitacao statusDaSolicitacao;
}
