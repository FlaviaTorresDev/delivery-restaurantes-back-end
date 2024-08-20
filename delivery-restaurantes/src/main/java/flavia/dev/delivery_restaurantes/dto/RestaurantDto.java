package flavia.dev.delivery_restaurantes.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.util.List;

@Data
@Embeddable
public class RestaurantDto {
	
	private String titulo;


	@Column(length = 1000)
	private List<String> images;

	private String descricao;
	private Long id;
	

}
