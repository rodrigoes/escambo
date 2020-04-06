package facens.engsoft.escambo.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class IntervaloDeDisponibilidade {
    private Integer id;
    private Date dataInicial;
    private Date dataFinal;
    private String observacoes;
}
