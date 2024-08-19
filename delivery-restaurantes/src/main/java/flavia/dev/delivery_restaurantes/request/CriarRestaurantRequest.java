package flavia.dev.delivery_restaurantes.request;

import java.time.LocalDateTime;
import java.util.List;

import flavia.dev.delivery_restaurantes.model.Endereco;
import flavia.dev.delivery_restaurantes.model.InformacaoContato;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CriarRestaurantRequest {

	private Long id;
	private String nome;
	private String descricao;
	private String tipoCozinha;
	private Endereco endereco;
	private InformacaoContato informacaoContato;
	private String horarioAbertura;
	private List<String> imagens;
    private LocalDateTime dataRegistro;
}
