package flavia.dev.delivery_restaurantes.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import flavia.dev.delivery_restaurantes.model.Carrinho;

public interface CarrinhoRepository extends JpaRepository<Carrinho, Long> {

	 Optional<Carrinho> findByCustomer_Id(Long userId);
}