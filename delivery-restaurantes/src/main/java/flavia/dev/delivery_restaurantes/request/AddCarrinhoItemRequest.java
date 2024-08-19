package flavia.dev.delivery_restaurantes.request;

import java.util.List;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class AddCarrinhoItemRequest {
	
	private Long menuItemId;
	private int quantidade;
	private List<String> ingredients;

}
