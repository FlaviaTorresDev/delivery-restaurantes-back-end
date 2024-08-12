package flavia.dev.delivery_restaurantes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import flavia.dev.delivery_restaurantes.model.Pedido;


public interface PedidoRepository extends JpaRepository<Pedido,Long> {
	
	@Query("SELECT o FROM Pedido o WHERE o.cliente.id = :userId")
	List<Pedido> listarAllPedidosUser(@Param("userId")Long userId);
    
	@Query("SELECT o FROM Pedido o WHERE o.restaurant.id = :restaurantId")
	List<Pedido> listarPedidosRestaurant(@Param("restaurantId") Long restaurantId);
}