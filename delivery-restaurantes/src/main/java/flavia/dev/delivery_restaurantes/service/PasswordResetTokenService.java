package flavia.dev.delivery_restaurantes.service;

import flavia.dev.delivery_restaurantes.model.PasswordResetToken;

public interface PasswordResetTokenService {

	public PasswordResetToken findByToken(String token);

	public void delete(PasswordResetToken resetToken);

}