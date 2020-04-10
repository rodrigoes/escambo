package facens.engsoft.escambo.models;

import facens.engsoft.escambo.enums.Nota;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class Usuario {
    private Integer Id;
    private String nome;
    private InformacoesDeContato informacoesDeContato;
    private Set<Item> itens = new HashSet<>();
    private List<NotificacaoDeInteresse> notificacoes = new ArrayList<>();
    private List<Nota> notas = new ArrayList<>();

    public Double getMedia() {
        return 0d;
    }
}
