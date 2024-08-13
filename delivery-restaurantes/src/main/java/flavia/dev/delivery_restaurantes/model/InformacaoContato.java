package flavia.dev.delivery_restaurantes.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InformacaoContato {
	
    private String email;
    private String telefone;
    private String twitter;
    private String instagram;

   
}