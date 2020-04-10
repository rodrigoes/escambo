package facens.engsoft.escambo.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum Nota {
    PESSIMO(1, "Péssimo"),
    RUIM(2, "Ruim"),
    REGULAR(3, "Regular"),
    BOM(4, "Bom"),
    OTIMO(5, "Ótimo");

    private final int estrelas;
    private final String descricao;
}