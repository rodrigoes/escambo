package facens.engsoft.escambo.services;

import facens.engsoft.escambo.models.Item;
import facens.engsoft.escambo.models.Usuario;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ItemService {

    public boolean addItemPara(Usuario usuario, Item item) {
        return false;
    }

    public List<Item> buscarItensPorNome(String name) {
        Item itemA = new Item();
        itemA.setNome("Vaso de Petunias");

        Item itemB = new Item();
        itemB.setNome("Toalha");

        return Arrays.asList(itemA, itemB);
    }
}
