package facens.engsoft.escambo.models;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class Usuario {
    private Integer Id;
    private String nome;
    private Set<Item> itens = new HashSet<>();
    private List<NotificacaoDeInteresse> notificacoes;
    private InformacoesDeContato informacoesDeContato;
}
