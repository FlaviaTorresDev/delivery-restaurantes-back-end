package flavia.dev.delivery_restaurantes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import flavia.dev.delivery_restaurantes.model.Categoria;


public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

	public List<Categoria> findByRestaurantId(Long id);
}
