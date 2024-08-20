package flavia.dev.delivery_restaurantes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import flavia.dev.delivery_restaurantes.model.Notificacao;



public interface NotificacaoRepository extends JpaRepository<Notificacao, Long> {

	public List<Notificacao> findByClienteId(Long userId);
	public List<Notificacao> findByRestaurantId(Long restaurantId);

}