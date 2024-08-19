package flavia.dev.delivery_restaurantes.request;

import lombok.Data;

@Data
public class CriarIngredientCategoriaRequest {

    private Long restaurantId;
    private String nome;
}
