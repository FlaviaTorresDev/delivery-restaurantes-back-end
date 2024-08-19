package flavia.dev.delivery_restaurantes.request;

import lombok.Data;

@Data
public class ReviewRequest {

    private Long restaurantId;
    
    private double avaliacao;
    
    private String revisarTexto;

	
}
