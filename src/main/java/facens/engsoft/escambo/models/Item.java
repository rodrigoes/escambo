package facens.engsoft.escambo.models;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class Item {
    private Integer id;
    private String nome;
    private String descricao;
    private Usuario usuario;
    private Set<IntervaloDeDisponibilidade> intervalosDeDisponibilidade = new HashSet<>();
}
