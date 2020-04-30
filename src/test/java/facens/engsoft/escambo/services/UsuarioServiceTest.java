package facens.engsoft.escambo.services;

import facens.engsoft.escambo.enums.Nota;
import facens.engsoft.escambo.models.Item;
import facens.engsoft.escambo.models.SolicitacaoDeEscambo;
import facens.engsoft.escambo.models.Usuario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class UsuarioServiceTest {

    private static final UsuarioService usuarioService = new UsuarioServiceImpl();
    private static final SolicitacaoDeEscamboService solicitacaoDeEscamboService = new SolicitacaoDeEscamboServiceImpl();

    @Test
    @DisplayName("DADO dois usuários QUE ainda não trocaram itens ENTÃO eles não podem se avaliar")
    public void verificaDisponibilidadeDeAvaliacaoDeUsuariosSemInteracao() {
        Usuario usuarioA = new Usuario();
        Usuario usuarioB = new Usuario();

        boolean resultadoA = usuarioService.usuarioPodeSerAvaliadoPorOutroUsuario(usuarioA, usuarioB);
        boolean resultadoB = usuarioService.usuarioPodeSerAvaliadoPorOutroUsuario(usuarioA, usuarioB);

        assertThat(resultadoA || resultadoB)
                .isFalse();
    }

    @Test
    @DisplayName("DADO dois usuários QUE ainda não trocaram itens MAS solicitaram escambo ENTÃO eles ainda não podem se avaliar")
    public void verificaDisponibilidadeDeAvaliacaoDeUsuariosComInteracaoESemTrocasRealizadas() {
        Usuario usuarioA = mockUsuario("Uhtred Ragnarson", "Hidromel e Churras");
        Item itemA = (Item) usuarioA.getItens().toArray()[0];
        Usuario usuarioB = mockUsuario("Leofric", "Navio");
        Item itemB = (Item) usuarioB.getItens().toArray()[0];

        solicitacaoDeEscamboService.solicitarEscambo(itemA, itemB);

        boolean resultadoA = usuarioService.usuarioPodeSerAvaliadoPorOutroUsuario(usuarioA, usuarioB);
        boolean resultadoB = usuarioService.usuarioPodeSerAvaliadoPorOutroUsuario(usuarioA, usuarioB);

        assertThat(resultadoA || resultadoB)
                .isFalse();
    }

    @Test
    @DisplayName("DADO dois usuários QUANDO aceitarem escambo ENTÃO eles já podem se avaliar")
    public void verificaDisponibilidadeDeAvaliacaoDeUsuariosComInteracaoEComTrocasRealizadas() {
        Usuario usuarioA = mockUsuario("Uhtred Ragnarson", "Hidromel e Churras");
        Item itemA = (Item) usuarioA.getItens().toArray()[0];
        Usuario usuarioB = mockUsuario("Leofric", "Navio");
        Item itemB = (Item) usuarioB.getItens().toArray()[0];

        SolicitacaoDeEscambo solicitacaoDeEscambo = solicitacaoDeEscamboService.solicitarEscambo(itemA, itemB);
        solicitacaoDeEscamboService.aceitarSolicitacaoDeEscambo(solicitacaoDeEscambo);

        boolean resultadoA = usuarioService.usuarioPodeSerAvaliadoPorOutroUsuario(usuarioA, usuarioB);
        boolean resultadoB = usuarioService.usuarioPodeSerAvaliadoPorOutroUsuario(usuarioA, usuarioB);

        assertThat(resultadoA && resultadoB)
                .isTrue();
    }

    @Test
    @DisplayName("DADO um novo usuário QUE ainda não realizou escambo ENTÃO média deve estar zerada")
    public void verificaNotaDeNovoUsuario() {
        Usuario usuarioA = new Usuario();

        assertThat(usuarioA.getMedia()).
                isEqualTo(0d);
    }

    @Test
    @DisplayName("DADO usuário QUE possui uma avaliação 'Pessimo' ENTÃO média deve ser 1")
    public void verificaNotaDeUsuarioComUmaAvaliacao() {
        Usuario usuarioA = new Usuario();
        usuarioA.setNotas(Collections.singletonList(Nota.PESSIMO));

        assertThat(usuarioA.getMedia())
                .isEqualTo(1d);
    }

    @Test
    @DisplayName("DADO usuário QUE possui avaliações ENTÃO média deve ser calculada")
    public void verificaMediaDeUsuarioComMultiplasAvaliacoes() {
        Usuario usuarioA = new Usuario();
        usuarioA.setNotas(Arrays.asList(Nota.PESSIMO, Nota.BOM, Nota.OTIMO, Nota.OTIMO));

        assertThat(usuarioA.getMedia())
                .isEqualTo(3.75d);
    }

    private Usuario mockUsuario(String nomeUsuario, String nomeItem) {
        Usuario usuarioA = new Usuario();
        usuarioA.setNome(nomeUsuario);
        Item itemA = new Item();
        itemA.setNome(nomeItem);
        usuarioA.setItens(Collections.singleton(itemA));

        return usuarioA;
    }
}
