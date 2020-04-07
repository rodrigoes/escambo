package facens.engsoft.escambo.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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

    @OneToMany(mappedBy = "usuario")
    private Set<Item> itens = new HashSet<>();

    @Transient
    private List<NotificacaoDeInteresse> notificacoes;
    @Transient
    private InformacoesDeContato informacoesDeContato;
}
