package facens.engsoft.escambo.services.impl;

import facens.engsoft.escambo.models.IntervaloDeDisponibilidade;
import facens.engsoft.escambo.models.Item;
import facens.engsoft.escambo.models.Usuario;
import facens.engsoft.escambo.repositories.ItemRepository;
import facens.engsoft.escambo.repositories.UsuarioRepository;
import facens.engsoft.escambo.services.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final UsuarioRepository usuarioRepository;

    @Transactional
    public Boolean addItemPara(Usuario usuario, Item item) {
        usuario = usuarioRepository.findById(usuario.getId()).orElse(null);

        if (usuario == null) {
            return false;
        }

        usuario.getItens().add(item);
        itemRepository.save(item);

        usuario = usuarioRepository.save(usuario);

        log.info(String.format("Usuário %s salvo. Itens no inventário: %s", usuario.getNome(),
                String.join(",", usuario.getItens().stream().map(i -> i.getNome()).collect(Collectors.toList()))));


        return true;
    }

    public Boolean addIntervaloDeDisponibilidadeParaItem(Item item, IntervaloDeDisponibilidade intervaloDeDisponibilidade) {
        return false;
    }

    public List<Item> buscarItensPorNome(String name) {
        return null;
    }

    public List<Item> buscarItensPorNomeEIntervalo(String name, Date dataInicial, Date dataFinal) {
        return null;
    }
}
