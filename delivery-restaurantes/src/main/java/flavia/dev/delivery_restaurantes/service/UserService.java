package flavia.dev.delivery_restaurantes.service;

import java.util.List;

import flavia.dev.delivery_restaurantes.exception.UserException;
import flavia.dev.delivery_restaurantes.model.User;



public interface UserService {

	public User findUserProfileByJwt(String jwt) throws UserException;
	
	public User findUserByEmail(String email) throws UserException;

	public List<User> findAllUsers();

	public List<User> getPenddingRestaurantOwner();

	void updatePassword(User user, String newPassword);

	void sendPasswordResetEmail(User user);

}
