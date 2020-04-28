package facens.engsoft.escambo.services;

import facens.engsoft.escambo.enums.StatusDaSolicitacao;
import facens.engsoft.escambo.models.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NotificacaoServiceTest {

    private static final NotificacaoService notificacaoService = new NotificacaoService();
    private static final ItemService itemService = mock(ItemService.class);

    @BeforeAll
    public static void setUp() {
        Usuario usuarioA = new Usuario();
        usuarioA.setNome("Ford Prefect");

        Item itemA = new Item();
        itemA.setNome("Peixe Babel");
        itemA.setDescricao("é pequeno, amarelo e semelhante a uma sanguessuga, e é provavelmente a criatura mais " +
                "estranha em todo o Universo. Alimenta-se de energia mental, não daquele que o hospeda, mas das " +
                "criaturas ao redor dele. Absorve todas as frequências mentais inconscientes desta energia mental " +
                "e se alimenta delas, e depois expele na mente de seu hospedeiro uma matriz telepática formada pela " +
                "combinação das frequências mentais conscientes com os impulsos nervosos captados dos centros " +
                "cerebrais responsáveis pela fala do cérebro que os emitiu. Na prática, o efeito disto é o seguinte: " +
                "se você introduz no ouvido um peixe-babel, você compreende imediatamente tudo o que lhe for dito em " +
                "qualquer língua. Os padrões sonoros que você ouve decodificam a matriz de energia mental que o seu " +
                "peixe-babel transmitiu para a sua mente.");
        itemA.setUsuario(usuarioA);

        usuarioA.setItens(Collections.singleton(itemA));

        when(itemService.buscarItensPorNome(any())).thenReturn(Collections.singletonList(itemA));
    }

    @Test
    @DisplayName("QUANDO notificarInteresse ENTÃO deve criar uma notificação baseada no item de interesse")
    public void verificaNotificacaoDeInteresseDisparada() {
        Usuario usuarioB = new Usuario();
        usuarioB.setNome("Arthur Dent");

        Item itemBuscado = itemService.buscarItensPorNome(anyString()).stream()
                .findFirst().orElse(null);

        NotificacaoDeInteresse notificacaoEsperada = new NotificacaoDeInteresse();
        notificacaoEsperada.setUsuarioQueOriginou(usuarioB);
        notificacaoEsperada.setItemDeInteresse(itemBuscado);

        NotificacaoDeInteresse notificacaoCriada = notificacaoService.notificarInteresse(usuarioB, itemBuscado);

        assertThat(comparaNotificacoesDeInteresse(notificacaoEsperada, notificacaoCriada))
                .isTrue();
    }

    @Test
    @DisplayName("QUANDO notificarEscambo ENTÃO deve criar uma notificação baseada na solicitação de escambo")
    public void verificaNotificacaoDeSolicitacao() {
        SolicitacaoDeEscambo solicitacaoDeEscambo = mockSolicitacaoDeEscambo();
        NotificacaoDeEscambo notificacaoDeEscambo = notificacaoService.notificarEscambo(solicitacaoDeEscambo);

        assertThat(notificacaoDeEscambo.getSolicitacaoDeEscambo())
                .isEqualTo(solicitacaoDeEscambo);
    }

    private Boolean comparaNotificacoesDeInteresse(NotificacaoDeInteresse a, NotificacaoDeInteresse b) {
        if (a == null) return false;
        if (b == null) return false;

        if (!a.getItemDeInteresse().equals(b.getItemDeInteresse())) return false;
        if (!a.getUsuarioQueOriginou().equals(b.getUsuarioQueOriginou())) return false;
        return a.getMensagem().equals(b.getMensagem());
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

        return SolicitacaoDeEscambo.builder()
                .itemDoSolicitador(itemB)
                .itemSolicitado(itemSolicitado)
                .statusDaSolicitacao(StatusDaSolicitacao.PENDENTE)
                .build();
    }
}
