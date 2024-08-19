package flavia.dev.delivery_restaurantes.request;

import java.util.List;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class AddCartItemRequest {
	
	private Long menuItemId;
	private int quantidade;
	private List<String> ingredients;

}
