package flavia.dev.delivery_restaurantes.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngredientCategoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nome;
	
	@JsonIgnore
	@ManyToOne
	private Restaurant restaurant;
	
	@JsonIgnore
	@OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
	private List<IngredientsItem> ingredients= new ArrayList<>();

}
