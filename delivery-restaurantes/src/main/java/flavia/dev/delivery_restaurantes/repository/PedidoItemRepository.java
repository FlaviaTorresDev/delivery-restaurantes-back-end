package flavia.dev.delivery_restaurantes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import flavia.dev.delivery_restaurantes.model.PedidoItem;



public interface PedidoItemRepository extends JpaRepository<PedidoItem, Long> {

}
