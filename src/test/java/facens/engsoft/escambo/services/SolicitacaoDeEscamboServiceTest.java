package facens.engsoft.escambo.services;

import facens.engsoft.escambo.enums.StatusDaSolicitacao;
import facens.engsoft.escambo.models.InformacoesDeContato;
import facens.engsoft.escambo.models.Item;
import facens.engsoft.escambo.models.SolicitacaoDeEscambo;
import facens.engsoft.escambo.models.Usuario;
import facens.engsoft.escambo.services.impl.ItemServiceMock;
import facens.engsoft.escambo.services.impl.SolicitacaoDeEscamboServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SolicitacaoDeEscamboServiceTest {

    private static final SolicitacaoDeEscamboService solicitacaoDeEscamboService = new SolicitacaoDeEscamboServiceImpl();

    private static final ItemService itemService = mock(ItemServiceMock.class);

    @BeforeAll
    public static void setUp() {
        Usuario usuarioA = new Usuario();
        usuarioA.setNome("Ford Prefect");

        Item itemA = new Item();
        itemA.setNome("Peixe Babel");
        itemA.setDescricao("é pequeno, amarelo e semelhante a uma sanguessuga, e é provavelmente a criatura mais " +
                "estranha em todo o Universo. Alimenta-se de energia mental, não daquele que o hospeda, mas das " +
                "criaturas ao redor dele. Absorve todas as frequências mentais inconscientes desta energia mental e " +
                "se alimenta delas, e depois expele na mente de seu hospedeiro uma matriz telepática formada pela " +
                "combinação das frequências mentais conscientes com os impulsos nervosos captados dos centros " +
                "cerebrais responsáveis pela fala do cérebro que os emitiu. Na prática, o efeito disto é o seguinte: " +
                "se você introduz no ouvido um peixe-babel, você compreende imediatamente tudo o que lhe for dito " +
                "em qualquer língua. Os padrões sonoros que você ouve decodificam a matriz de energia mental que o " +
                "seu peixe-babel transmitiu para a sua mente.");
        itemA.setUsuario(usuarioA);

        usuarioA.setItens(Collections.singleton(itemA));

        when(itemService.buscarItensPorNome(any())).thenReturn(Collections.singletonList(itemA));
    }

    @Test
    @DisplayName("DADO que outro usuário tem um item desejado " +
            "E possuo outro item de valor semelhante " +
            "QUANDO solicitar uma troca " +
            "ENTÃO uma solicitação com status Pendente deve ser criada")
    public void verificaSolicitacaoDeEscambo() {
        SolicitacaoDeEscambo solicitacaoDeEscambo = mockSolicitacaoDeEscambo();

        assertThat(solicitacaoDeEscambo.getStatusDaSolicitacao())
                .isEqualTo(StatusDaSolicitacao.PENDENTE);
    }

    @Test
    @DisplayName("DADO que tenho uma solicitação de troca " +
            "QUANDO aceito a solicitação " +
            "ENTÃO quero vizualizar as informações de contato do solicitador")
    public void verificaAceiteDeSolicitacaoDeEscambo() {
        // Dado que tenho uma solicitação de troca
        SolicitacaoDeEscambo solicitacaoDeEscambo = mockSolicitacaoDeEscambo();

        // Quando aceito a solicitação, então vizualizo as Informações de contato do solicitador
        InformacoesDeContato informacoesDeContatoRecebidas = solicitacaoDeEscamboService.aceitarSolicitacaoDeEscambo(solicitacaoDeEscambo);
        InformacoesDeContato informacoesDeContatoEsperadas = solicitacaoDeEscambo.getItemDoSolicitador().getUsuario().getInformacoesDeContato();

        assertThat(comparaInformacoesDeContato(informacoesDeContatoRecebidas, informacoesDeContatoEsperadas))
                .isTrue();
    }

    private SolicitacaoDeEscambo mockSolicitacaoDeEscambo() {
        Usuario usuarioB = new Usuario();
        usuarioB.setNome("Arthur Dent");

        Item itemB = new Item();
        itemB.setNome("Toalha");
        itemB.setDescricao("A toalha é um dos objetos mais úteis para um mochileiro interestelar");
        itemB.setUsuario(usuarioB);

        Item itemSolicitado = itemService.buscarItensPorNome(anyString()).stream()
                .findFirst().orElse(null);

        return solicitacaoDeEscamboService.solicitarEscambo(itemB, itemSolicitado);
    }

    private Boolean comparaInformacoesDeContato(InformacoesDeContato a, InformacoesDeContato b) {
        if (a == null) return false;
        if (b == null) return false;

        if (a.getEmailComercial().equals(b.getEmailComercial())) return false;
        if (a.getEmailResidencial().equals(b.getEmailResidencial())) return false;
        if (a.getTelefoneComercial().equals(b.getTelefoneComercial())) return false;
        if (a.getTelefoneResidencial().equals(b.getTelefoneResidencial())) return false;
        if (a.getEnderecoComercial().equals(b.getEnderecoComercial())) return false;
        return !a.getEnderecoResidencial().equals(b.getEnderecoResidencial());
    }
}
