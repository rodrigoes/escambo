package facens.engsoft.escambo.services;

import facens.engsoft.escambo.models.Item;
import facens.engsoft.escambo.models.Usuario;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class ItemServiceTest {

    private static final ItemService itemService = new ItemService();

    @Test
    public void verificaCadastroDeItem() {
        Usuario usuario = new Usuario();
        usuario.setNome("Zaphod Beeblebrox");

        Item item = new Item();
        item.setNome("Coração de Ouro");
        item.setDescricao(any());

        boolean actual = itemService.addItemPara(usuario, item);
        assertTrue(actual, "Testa que cadastro de item foi concretizado para usuário");
    }

    @Test
    public void verificaBuscaDeItensPorNome_quando_EstiverCadastrado() {
        Item expectedItem = new Item();
        expectedItem.setNome("Toalha");

        List<Item> actualItems = itemService.buscarItensPorNome(expectedItem.getNome());

        boolean itensTemNomeEsperado = actualItems
                .stream()
                .allMatch(item -> item.getNome().toLowerCase().contains(expectedItem.getNome().toLowerCase()));

        assertTrue(!actualItems.isEmpty() && itensTemNomeEsperado,
                "Lista retornada deve conter elementos compatíveis");
    }

    @Test
    public void verificaBuscaDeItensPorNome_quando_NaoEstiverCadastrado() {
        Item expectedItem = new Item();
        expectedItem.setNome("Nave Vógon");

        List<Item> actualItems = itemService.buscarItensPorNome(expectedItem.getNome());

        assertTrue(actualItems.isEmpty(), "A lista retornada deve ser vazia");
    }
}
