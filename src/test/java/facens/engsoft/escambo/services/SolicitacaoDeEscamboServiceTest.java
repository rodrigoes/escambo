package facens.engsoft.escambo.services;

import facens.engsoft.escambo.models.InformacoesDeContato;
import facens.engsoft.escambo.models.Item;
import facens.engsoft.escambo.models.SolicitacaoDeEscambo;
import facens.engsoft.escambo.models.Usuario;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SolicitacaoDeEscamboServiceTest {

    private static final SolicitacaoDeEscamboService solicitacaoDeEscamboService = new SolicitacaoDeEscamboService();

    private static final ItemService itemService = mock(ItemService.class);

    @BeforeAll
    public static void setUp() {
        Usuario usuarioA = new Usuario();
        usuarioA.setNome("Ford Prefect");

        Item itemA = new Item();
        itemA.setNome("Peixe Babel");
        itemA.setDescricao("é pequeno, amarelo e semelhante a uma sanguessuga, e é provavelmente a criatura mais estranha em todo o Universo. Alimenta-se de energia mental, não daquele que o hospeda, mas das criaturas ao redor dele. Absorve todas as frequências mentais inconscientes desta energia mental e se alimenta delas, e depois expele na mente de seu hospedeiro uma matriz telepática formada pela combinação das frequências mentais conscientes com os impulsos nervosos captados dos centros cerebrais responsáveis pela fala do cérebro que os emitiu. Na prática, o efeito disto é o seguinte: se você introduz no ouvido um peixe-babel, você compreende imediatamente tudo o que lhe for dito em qualquer língua. Os padrões sonoros que você ouve decodificam a matriz de energia mental que o seu peixe-babel transmitiu para a sua mente.");
        itemA.setUsuario(usuarioA);

        usuarioA.setItens(Collections.singleton(itemA));

        when(itemService.buscarItensPorNome(any())).thenReturn(Collections.singletonList(itemA));
    }

    @Test
    public void verificaSolicitacaoDeEscambo() {
        // Dado que outro usuário tem um item desejado
        SolicitacaoDeEscambo solicitacaoDeEscambo = mockSolicitacaoDeEscambo();

        // Quando possuir outro item de valor semelhante, quero solicitar uma troca
        assertTrue(solicitacaoDeEscamboService.solicitarEscambo(solicitacaoDeEscambo),
                "Uma solicitação deve ser criada");
    }

    @Test
    public void verificaAceiteDeSolicitacaoDeEscambo() {
        // Dado que tenho uma solicitação de troca
        SolicitacaoDeEscambo solicitacaoDeEscambo = mockSolicitacaoDeEscambo();

        // Quando aceito a solicitação, então vizualizo as Informações de contato do solicitador
        InformacoesDeContato informacoesDeContatoRecebidas = solicitacaoDeEscamboService.aceitarSolicitacaoDeEscambo(solicitacaoDeEscambo);
        InformacoesDeContato informacoesDeContatoEsperadas = solicitacaoDeEscambo.getItemDoSolicitador().getUsuario().getInformacoesDeContato();

        assertTrue(comparaInformacoesDeContato(informacoesDeContatoRecebidas, informacoesDeContatoEsperadas),
                "As informações de Contato retornadas devem ser do Usuário que criou a Solicitação");
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

        SolicitacaoDeEscambo solicitacaoDeEscambo = new SolicitacaoDeEscambo();
        solicitacaoDeEscambo.setItemDoSolicitador(itemB);
        solicitacaoDeEscambo.setItemSolicitado(itemSolicitado);

        return solicitacaoDeEscambo;
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
