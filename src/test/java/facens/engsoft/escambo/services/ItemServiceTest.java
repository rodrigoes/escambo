package facens.engsoft.escambo.services;

import facens.engsoft.escambo.models.IntervaloDeDisponibilidade;
import facens.engsoft.escambo.models.Item;
import facens.engsoft.escambo.models.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ItemServiceTest {

    @Autowired
    private ItemService itemService;

    @Test
    public void verificaCadastroDeItem() {
        Usuario usuario = new Usuario();
        usuario.setNome("Zaphod Beeblebrox");
        usuario.setId(1);

        // Dado que tenho um item
        Item item = new Item();
        item.setNome("Coração de Ouro 2.0");
        item.setDescricao(any());

        // Quero adicioná-lo para trocas
        boolean actual = itemService.addItemPara(usuario, item);
        assertTrue(actual, "Testa que cadastro de item foi concretizado para usuário");
    }

    @Test
    public void verificaBuscaDeItensPorNome_quando_EstiverCadastrado() {
        // Dado que estou interessado em um item
        Item expectedItem = new Item();
        expectedItem.setNome("Toalha");

        // Quero buscar por ele
        List<Item> actualItems = itemService.buscarItensPorNome(expectedItem.getNome());

        // E saber quando houver algum resultado
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

    @Test
    public void verificaAdicaoDePeriodosDeDisponibilidadeParaItem() {
        // Dado que possuo itens cadastrados
        Item item = new Item();
        item.setNome("Gerador de Improbabilidade Infinita");

        Calendar cal = Calendar.getInstance();
        cal.set(1988, Calendar.JANUARY, 1);
        Date dataInicial = cal.getTime();

        cal.set(1988, Calendar.MARCH, 1);
        Date dataFinal = cal.getTime();


        // Quero adicionar intervalos de disponibilidade a este item
        IntervaloDeDisponibilidade intervaloDeDisponibilidade = new IntervaloDeDisponibilidade();
        intervaloDeDisponibilidade.setDataInicial(dataInicial);
        intervaloDeDisponibilidade.setDataFinal(dataFinal);
        intervaloDeDisponibilidade.setObservacoes("Período pode ser alterado, mas precisa combinar antes");

        boolean adicionado = itemService.addIntervaloDeDisponibilidadeParaItem(item, intervaloDeDisponibilidade);
        assertTrue(adicionado, "O intervalo deve ser adicionado ao item");
    }

    @Test
    public void verificaBuscaDeItensPorNomeEIntervalo_quando_EstiverCadastrado() {
        Item expectedItem = new Item();
        expectedItem.setNome("Toalha");

        Calendar cal = Calendar.getInstance();
        cal.set(1988, Calendar.JANUARY, 23);
        Date dataInicial = cal.getTime();

        cal.set(1988, Calendar.FEBRUARY, 3);
        Date dataFinal = cal.getTime();

        List<Item> actualItems = itemService.buscarItensPorNomeEIntervalo(expectedItem.getNome(), dataInicial, dataFinal);

        boolean itensTemNomeEsperadoEPertencemAIntervalo = actualItems
                .stream()
                .allMatch(item -> item.getNome().toLowerCase().contains(expectedItem.getNome().toLowerCase())
                        && item.getIntervalosDeDisponibilidade().stream()
                        .anyMatch(intervalo -> verificaSeIntervaloAPertenceAoIntervaloB(dataInicial, dataFinal, intervalo.getDataInicial(), intervalo.getDataFinal()))
                );

        assertTrue(!actualItems.isEmpty() && itensTemNomeEsperadoEPertencemAIntervalo,
                "Lista retornada deve conter elementos compatíveis, pelo menos um dos intervalos de disponibilidade do item deve contemplar o intervalo solicitado");
    }

    @Test
    public void verificaBuscaDeItensPorNomeEIntervalo_quando_NaoEstiverCadastrado() {
        Item expectedItem = new Item();
        expectedItem.setNome("Nave Vógon");

        Calendar cal = Calendar.getInstance();
        cal.set(1988, Calendar.JANUARY, 23);
        Date dataInicial = cal.getTime();

        cal.set(1988, Calendar.FEBRUARY, 3);
        Date dataFinal = cal.getTime();

        List<Item> actualItems = itemService.buscarItensPorNomeEIntervalo(expectedItem.getNome(), dataInicial, dataFinal);

        assertTrue(actualItems.isEmpty(), "A lista retornada deve ser vazia");
    }

    private Boolean verificaSeIntervaloAPertenceAoIntervaloB(Date dataInicialA, Date dataFinalA, Date dataInicialB, Date dataFinalB) {
        // Verifica se ambos os intervalos são consistentes
        if (!dataInicialA.before(dataFinalA)) return false;
        if (!dataInicialB.before(dataFinalB)) return false;

        // Verifica se data inicial e final fazem parte do intervalo B
        if (!(dataInicialA.before(dataInicialB) || dataInicialA.after(dataFinalB))) return false;
        return dataFinalA.before(dataInicialB) || dataFinalA.after(dataFinalB);
    }
}
