package facens.engsoft.escambo.services;

import facens.engsoft.escambo.models.InformacoesDeContato;
import facens.engsoft.escambo.models.SolicitacaoDeEscambo;
import org.springframework.stereotype.Service;

@Service
public class SolicitacaoDeEscamboService {

    public Boolean solicitarEscambo(SolicitacaoDeEscambo solicitacaoDeEscambo) {
        return false;
    }

    public InformacoesDeContato aceitarSolicitacaoDeEscambo(SolicitacaoDeEscambo solicitacaoDeEscambo) {
        return null;
    }
}
