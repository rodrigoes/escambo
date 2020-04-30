package facens.engsoft.escambo.services.impl;

import facens.engsoft.escambo.models.IntervaloDeDisponibilidade;
import facens.engsoft.escambo.models.Item;
import facens.engsoft.escambo.models.Usuario;
import facens.engsoft.escambo.services.ItemService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class ItemServiceMock implements ItemService {

    public Boolean addItemPara(Usuario usuario, Item item) {
        return true;
    }

    public Boolean addIntervaloDeDisponibilidadeParaItem(Item item, IntervaloDeDisponibilidade intervaloDeDisponibilidade) {
        return false;
    }

    public List<Item> buscarItensPorNome(String name) {
        Item itemA = new Item();
        itemA.setNome("Vaso de Petunias");

        Item itemB = new Item();
        itemB.setNome("Toalha");

        return Arrays.asList(itemA, itemB);
    }

    public List<Item> buscarItensPorNomeEIntervalo(String name, Date dataInicial, Date dataFinal) {
        Item itemA = new Item();
        itemA.setNome("Vaso de Petunias");

        Item itemB = new Item();
        itemB.setNome("Toalha");

        return Arrays.asList(itemA, itemB);
    }
}
