package facens.engsoft.escambo.services;

import facens.engsoft.escambo.models.InformacoesDeContato;
import facens.engsoft.escambo.models.Item;
import facens.engsoft.escambo.models.SolicitacaoDeEscambo;

public interface SolicitacaoDeEscamboService {
    SolicitacaoDeEscambo solicitarEscambo(Item itemDoSolicitador, Item itemSolicitado);

    InformacoesDeContato aceitarSolicitacaoDeEscambo(SolicitacaoDeEscambo solicitacaoDeEscambo);
}
