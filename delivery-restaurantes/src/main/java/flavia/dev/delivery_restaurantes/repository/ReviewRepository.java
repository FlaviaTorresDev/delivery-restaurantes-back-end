package flavia.dev.delivery_restaurantes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import flavia.dev.delivery_restaurantes.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

}
