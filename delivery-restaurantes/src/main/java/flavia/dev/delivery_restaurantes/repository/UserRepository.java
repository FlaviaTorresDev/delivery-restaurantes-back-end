package flavia.dev.delivery_restaurantes.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import flavia.dev.delivery_restaurantes.model.User;


public interface UserRepository extends JpaRepository<User, Long> {
	
	
	
	@Query("SELECT u FROM User u Where u.status='PENDING'")
	public List<User> listarproprietáriosRestaurantesPendentes();
	
	public User findByEmail(String username);

}