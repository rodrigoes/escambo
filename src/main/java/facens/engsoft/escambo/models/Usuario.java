package facens.engsoft.escambo.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Usuario {
    private Integer Id;
    private String nome;
    private List<Item> itens;
    private List<NotificacaoDeInteresse> notificacoes;
    private InformacoesDeContato informacoesDeContato;
}
