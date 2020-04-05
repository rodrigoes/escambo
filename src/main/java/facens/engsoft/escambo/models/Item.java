package facens.engsoft.escambo.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Item {
    private Integer id;
    private String nome;
    private String descricao;
    private Usuario usuario;
}
