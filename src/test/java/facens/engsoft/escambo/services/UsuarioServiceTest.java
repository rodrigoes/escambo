package facens.engsoft.escambo.services;

import facens.engsoft.escambo.enums.Nota;
import facens.engsoft.escambo.models.Item;
import facens.engsoft.escambo.models.SolicitacaoDeEscambo;
import facens.engsoft.escambo.models.Usuario;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class UsuarioServiceTest {

    private static final UsuarioService usuarioService = new UsuarioService();
    private static final SolicitacaoDeEscamboService solicitacaoDeEscamboService = new SolicitacaoDeEscamboService();

    @Test
    public void verificaDisponibilidadeDeAvaliacaoDeUsuariosSemInteracao() {
        Usuario usuarioA = new Usuario();
        Usuario usuarioB = new Usuario();

        boolean resultadoA = usuarioService.usuarioPodeSerAvaliadoPorOutroUsuario(usuarioA, usuarioB);
        boolean resultadoB = usuarioService.usuarioPodeSerAvaliadoPorOutroUsuario(usuarioA, usuarioB);

        assertFalse(resultadoA || resultadoB,
                "Usuários não podem se avaliar entre si, dado que não trocaram items");
    }

    @Test
    public void verificaDisponibilidadeDeAvaliacaoDeUsuariosComInteracaoESemTrocasRealizadas() {
        Usuario usuarioA = mockUsuario("Uhtred Ragnarson", "Hidromel e Churras");
        Item itemA = (Item) usuarioA.getItens().toArray()[0];
        Usuario usuarioB = mockUsuario("Leofric", "Navio");
        Item itemB = (Item) usuarioB.getItens().toArray()[0];

        solicitacaoDeEscamboService.solicitarEscambo(itemA, itemB);

        boolean resultadoA = usuarioService.usuarioPodeSerAvaliadoPorOutroUsuario(usuarioA, usuarioB);
        boolean resultadoB = usuarioService.usuarioPodeSerAvaliadoPorOutroUsuario(usuarioA, usuarioB);

        assertFalse(resultadoA || resultadoB,
                "Usuários não podem se avaliar entre si, dado que não trocaram items");
    }

    @Test
    public void verificaDisponibilidadeDeAvaliacaoDeUsuariosComInteracaoEComTrocasRealizadas() {
        Usuario usuarioA = mockUsuario("Uhtred Ragnarson", "Hidromel e Churras");
        Item itemA = (Item) usuarioA.getItens().toArray()[0];
        Usuario usuarioB = mockUsuario("Leofric", "Navio");
        Item itemB = (Item) usuarioB.getItens().toArray()[0];

        SolicitacaoDeEscambo solicitacaoDeEscambo = solicitacaoDeEscamboService.solicitarEscambo(itemA, itemB);
        solicitacaoDeEscamboService.aceitarSolicitacaoDeEscambo(solicitacaoDeEscambo);

        boolean resultadoA = usuarioService.usuarioPodeSerAvaliadoPorOutroUsuario(usuarioA, usuarioB);
        boolean resultadoB = usuarioService.usuarioPodeSerAvaliadoPorOutroUsuario(usuarioA, usuarioB);

        assertTrue(resultadoA && resultadoB,
                "Usuários podem se avaliar entre si, dado que efeturam a troca de items");
    }

    @Test
    public void verificaNotaDeNovoUsuario() {
        Usuario usuarioA = new Usuario();

        assertEquals(0d, usuarioA.getMedia(), "Média de novo usuário deve estar zerada");
    }

    @Test
    public void verificaNotaDeUsuarioComUmaAvaliacao() {
        Usuario usuarioA = new Usuario();
        usuarioA.setNotas(Collections.singletonList(Nota.PESSIMO));

        assertEquals(1d, usuarioA.getMedia(),
                "Média de usuário com uma avaliação deve ser a nota da avaliação");
    }

    @Test
    public void verificaMediaDeUsuarioComMultiplasAvaliacoes() {
        Usuario usuarioA = new Usuario();
        usuarioA.setNotas(Arrays.asList(Nota.PESSIMO, Nota.BOM, Nota.OTIMO, Nota.OTIMO));

        assertEquals(3.75d, usuarioA.getMedia(),
                "Média de usuário com várias avaliações deve ser a média das avaliações");
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
