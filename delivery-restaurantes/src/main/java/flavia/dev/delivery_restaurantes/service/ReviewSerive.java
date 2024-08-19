package flavia.dev.delivery_restaurantes.service;

import java.util.List;

import flavia.dev.delivery_restaurantes.exception.ReviewException;
import flavia.dev.delivery_restaurantes.model.Review;
import flavia.dev.delivery_restaurantes.model.User;
import flavia.dev.delivery_restaurantes.request.ReviewRequest;




public interface ReviewSerive {
	
    public Review submitReview(ReviewRequest review,User user);
    public void deleteReview(Long reviewId) throws ReviewException;
    public double calculateAverageRating(List<Review> reviews);
}

