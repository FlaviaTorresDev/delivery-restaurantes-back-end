package flavia.dev.delivery_restaurantes.request;

import lombok.Data;

@Data
public class UpdateCarrinhoItemRequest {
	
	private Long carrinhoItemId;
	private int quantidade;

}
