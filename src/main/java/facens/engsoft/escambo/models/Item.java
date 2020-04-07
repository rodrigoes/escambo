package facens.engsoft.escambo.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "usuario", foreignKey = @ForeignKey(name = "FK_Usuario"))
    private Usuario usuario;

    @Transient
    private Set<IntervaloDeDisponibilidade> intervalosDeDisponibilidade = new HashSet<>();
}
