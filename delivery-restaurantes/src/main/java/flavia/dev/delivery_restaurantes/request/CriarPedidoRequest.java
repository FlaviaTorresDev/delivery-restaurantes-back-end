package flavia.dev.delivery_restaurantes.request;



import flavia.dev.delivery_restaurantes.model.Endereco;
import lombok.Data;

@Data
public class CriarPedidoRequest {
 
	private Long restaurantId;
	
	private Endereco deliveryEndereco;
	
    
}
