package facens.engsoft.escambo.services;

import facens.engsoft.escambo.models.IntervaloDeDisponibilidade;
import facens.engsoft.escambo.models.Item;
import facens.engsoft.escambo.models.Usuario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ItemServiceTest {

    @Autowired
    private ItemService itemService;

    @Test
    @DisplayName("DADO que sou um usuário que possui item " +
            "QUANDO cadastrar " +
            "ENTÃO este item deve ser salvo com sucesso")
    public void verificaCadastroDeItem() {
        Usuario usuario = new Usuario();
        usuario.setNome("Zaphod Beeblebrox");
        usuario.setId(1);

        // Dado que tenho um item
        Item item = new Item();
        item.setNome("Coração de Ouro 2.0");
        item.setDescricao(any());

        // Quero adicioná-lo para trocas
        assertThat(itemService.addItemPara(usuario, item))
                .isTrue();
    }

    @Test
    @DisplayName("DADO que sou um usuário que está interessado em um item " +
            "QUANDO buscar por ele " +
            "E existirem itens de interesse " +
            "ENTÃO retornar resultados compatíveis")
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

        assertThat(!actualItems.isEmpty() && itensTemNomeEsperado)
                .isTrue();
    }

    @Test
    @DisplayName("DADO que sou um usuário que está interessado em um item " +
            "QUANDO buscar por ele " +
            "E não existirem itens de interesse " +
            "ENTÃO retornar lista vazia")
    public void verificaBuscaDeItensPorNome_quando_NaoEstiverCadastrado() {
        Item expectedItem = new Item();
        expectedItem.setNome("Nave Vógon");

        List<Item> actualItems = itemService.buscarItensPorNome(expectedItem.getNome());

        assertThat(actualItems).isEmpty();
    }

    @Test
    @DisplayName("DADO que sou um usuário " +
            "QUE possui itens cadastrados " +
            "ENTÃO quero poder adicionar intervalos de disponibilidade a este item")
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
        assertThat(itemService.addIntervaloDeDisponibilidadeParaItem(item, intervaloDeDisponibilidade))
                .isTrue();
    }

    @Test
    @DisplayName("DADO um usuário que possui itens QUANDO buscar itens por Nome e Intervalo ENTÃO retornar os respectivos itens")
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

        assertThat(!actualItems.isEmpty() && itensTemNomeEsperadoEPertencemAIntervalo)
                .isTrue();
    }

    @Test
    @DisplayName("DADO um usuário que não possui itens QUANDO buscar itens por Nome e Intervalo ENTÃO retornar lista vazia")
    public void verificaBuscaDeItensPorNomeEIntervalo_quando_NaoEstiverCadastrado() {
        Item expectedItem = new Item();
        expectedItem.setNome("Nave Vógon");

        Calendar cal = Calendar.getInstance();
        cal.set(1988, Calendar.JANUARY, 23);
        Date dataInicial = cal.getTime();

        cal.set(1988, Calendar.FEBRUARY, 3);
        Date dataFinal = cal.getTime();

        List<Item> actualItems = itemService.buscarItensPorNomeEIntervalo(expectedItem.getNome(), dataInicial, dataFinal);

        assertThat(actualItems)
                .isEmpty();
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
