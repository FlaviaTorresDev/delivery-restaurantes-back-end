package flavia.dev.delivery_restaurantes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import flavia.dev.delivery_restaurantes.model.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long>{

}
