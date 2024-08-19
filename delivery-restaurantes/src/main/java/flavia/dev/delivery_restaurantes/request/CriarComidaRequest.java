package flavia.dev.delivery_restaurantes.request;

import java.util.List;

import flavia.dev.delivery_restaurantes.model.Categoria;
import flavia.dev.delivery_restaurantes.model.IngredientsItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CriarComidaRequest {
	

    
    private String nome;
    private String descricao;
    private Long valor;
    
  
    private Categoria categoria;
    private List<String> imagens;

   
    private Long restaurantId;
    
    private boolean vegetariano;
    private boolean sazonal;
    
    
    private List<IngredientsItem> ingredients;
	

}
