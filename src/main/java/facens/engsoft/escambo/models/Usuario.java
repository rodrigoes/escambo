package facens.engsoft.escambo.models;

import lombok.Data;

import java.util.List;

@Data
public class Usuario {
    private Integer Id;
    private String name;
    private List<Item> itens;
}
