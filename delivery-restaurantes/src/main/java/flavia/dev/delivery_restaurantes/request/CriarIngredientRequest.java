package flavia.dev.delivery_restaurantes.request;

import lombok.Data;

@Data
public class CriarIngredientRequest {

    private Long restaurantId;
    private String nome;
    private Long ingredientCategoriaId;
}
