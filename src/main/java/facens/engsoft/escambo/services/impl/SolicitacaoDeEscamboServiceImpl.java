package facens.engsoft.escambo.services.impl;

import facens.engsoft.escambo.enums.StatusDaSolicitacao;
import facens.engsoft.escambo.models.InformacoesDeContato;
import facens.engsoft.escambo.models.Item;
import facens.engsoft.escambo.models.SolicitacaoDeEscambo;
import facens.engsoft.escambo.services.SolicitacaoDeEscamboService;
import org.springframework.stereotype.Service;

@Service
public class SolicitacaoDeEscamboServiceImpl implements SolicitacaoDeEscamboService {

    @Override public SolicitacaoDeEscambo solicitarEscambo(Item itemDoSolicitador, Item itemSolicitado) {
        SolicitacaoDeEscambo solicitacaoDeEscambo = SolicitacaoDeEscambo.builder()
                .itemDoSolicitador(itemDoSolicitador)
                .itemSolicitado(itemSolicitado)
                .statusDaSolicitacao(StatusDaSolicitacao.PENDENTE)
                .build();

        // TODO: Salvar solicitação
        // TODO: Gerar notificação

        return solicitacaoDeEscambo;
    }

    @Override public InformacoesDeContato aceitarSolicitacaoDeEscambo(SolicitacaoDeEscambo solicitacaoDeEscambo) {
        return new InformacoesDeContato();
    }
}
