package facens.engsoft.escambo.enums;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public enum StatusDaSolicitacao {
    PENDENTE,
    NEGADA,
    ACEITA,
    REMOVIDA
}
