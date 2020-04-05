package facens.engsoft.escambo.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InformacoesDeContato {
    private Integer id;
    private String telefoneResidencial;
    private String telefoneComercial;
    private String emailResidencial;
    private String emailComercial;
    private String enderecoResidencial;
    private String enderecoComercial;
}
