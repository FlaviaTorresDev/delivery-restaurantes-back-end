package flavia.dev.delivery_restaurantes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import flavia.dev.delivery_restaurantes.model.Endereco;

public interface enderecoRepository extends JpaRepository<Endereco, Long> {

}

