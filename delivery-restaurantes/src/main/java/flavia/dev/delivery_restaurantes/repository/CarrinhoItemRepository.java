package flavia.dev.delivery_restaurantes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import flavia.dev.delivery_restaurantes.model.CarrinhoItem;

public interface CarrinhoItemRepository extends JpaRepository<CarrinhoItem, Long>{

}
