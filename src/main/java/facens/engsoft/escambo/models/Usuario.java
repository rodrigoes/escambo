package facens.engsoft.escambo.models;

import facens.engsoft.escambo.enums.Nota;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    private String nome;

    @Transient
    private InformacoesDeContato informacoesDeContato;

    @OneToMany(mappedBy = "usuario")
    private Set<Item> itens = new HashSet<>();

    @Transient
    private List<NotificacaoDeInteresse> notificacoes = new ArrayList<>();

    @Transient
    private List<Nota> notas = new ArrayList<>();

    @Transient
    public Double getMedia() {
        return 0d;
    }
}
