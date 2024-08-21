package flavia.dev.delivery_restaurantes.response;


import flavia.dev.delivery_restaurantes.domain.USER_ROLE;
import lombok.Data;

@Data
public class AuthResponse {
	
	private String message;
	private String jwt;
	private USER_ROLE role;
	


}