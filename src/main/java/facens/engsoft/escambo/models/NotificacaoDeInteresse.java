package facens.engsoft.escambo.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificacaoDeInteresse {
    private Integer id;
    private String mensagem;
    private Item itemDeInteresse;
    private Usuario usuarioQueOriginou;
}
