package flavia.dev.delivery_restaurantes.model;

import java.util.List;
import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonIgnore;

import flavia.dev.delivery_restaurantes.domain.USER_ROLE;
import flavia.dev.delivery_restaurantes.dto.RestaurantDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String nomeCompleto;
	private String email;
	private String password;
	private String status;

	
	private USER_ROLE role;

	@JsonIgnore
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	private List<Pedido> ordens;

	@ElementCollection
	private List<RestaurantDto> favorites=new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Endereco> enderecos = new ArrayList<>();
	

}