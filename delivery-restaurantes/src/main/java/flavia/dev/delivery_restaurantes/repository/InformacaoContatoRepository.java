package flavia.dev.delivery_restaurantes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import flavia.dev.delivery_restaurantes.model.InformacaoContato;

public interface InformacaoContatoRepository extends JpaRepository<InformacaoContato, Long>{

}
