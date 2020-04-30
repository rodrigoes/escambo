package facens.engsoft.escambo.services;

import facens.engsoft.escambo.models.IntervaloDeDisponibilidade;
import facens.engsoft.escambo.models.Item;
import facens.engsoft.escambo.models.Usuario;

import java.util.Date;
import java.util.List;

public interface ItemService {

    Boolean addItemPara(Usuario usuario, Item item);

    Boolean addIntervaloDeDisponibilidadeParaItem(Item item, IntervaloDeDisponibilidade intervaloDeDisponibilidade);

    List<Item> buscarItensPorNome(String name);

    List<Item> buscarItensPorNomeEIntervalo(String name, Date dataInicial, Date dataFinal);
}
