package facens.engsoft.escambo.services;

import facens.engsoft.escambo.models.Item;
import facens.engsoft.escambo.models.Usuario;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.any;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ItemServiceTest {

    private static final ItemService itemService = new ItemService();

    @Test
    public void verificaCadastroDeItem() {
        Usuario usuario = new Usuario();
        usuario.setName("Zaphod Beeblebrox");

        Item item = new Item();
        item.setNome("Coração de Ouro");
        item.setDescricao(any());

        boolean actual = !itemService.addItemPara(usuario, item);
        assertTrue(actual, "Testa que cadastro de item foi concretizado para usuário");
    }
}
