package flavia.dev.delivery_restaurantes.model;

import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.stripe.model.OrderItem;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "pedido")
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long montanteTotal;
	private String statusPedido;
	private int totalItem;
	private int valorTotal;
	
	
	@ManyToOne
	private User cliente;

	@JsonIgnore
	@ManyToOne
	private Restaurant restaurant;


	@Temporal(TemporalType.TIMESTAMP)
	private Date criadoEm;

	@ManyToOne
	private Endereco deliveryEndereco;

//	@JsonIgnore
	@OneToMany
	private List<OrderItem> items;

	@OneToOne
	private Pagamento pagamento;
	
	
}

