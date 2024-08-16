package flavia.dev.delivery_restaurantes.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Eventos {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String imagem;
	
	private String comecouEm;
	
	private String acabouEm;
	
	private String nome;
	
	@ManyToOne
	private Restaurant restaurant;
	
	private String localização;

}
