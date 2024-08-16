package flavia.dev.delivery_restaurantes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import flavia.dev.delivery_restaurantes.model.Eventos;


public interface EventoRepository extends JpaRepository<Eventos, Long>{

	public List<Eventos> findEventsByRestaurantId(Long id);
}

