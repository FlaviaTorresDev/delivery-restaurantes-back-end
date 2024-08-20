package flavia.dev.delivery_restaurantes.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import flavia.dev.delivery_restaurantes.exception.ReviewException;
import flavia.dev.delivery_restaurantes.model.Restaurant;
import flavia.dev.delivery_restaurantes.model.Review;
import flavia.dev.delivery_restaurantes.model.User;
import flavia.dev.delivery_restaurantes.repository.RestaurantRepository;
import flavia.dev.delivery_restaurantes.repository.ReviewRepository;
import flavia.dev.delivery_restaurantes.request.ReviewRequest;
import flavia.dev.delivery_restaurantes.service.ReviewSerive;


@Service
public class ReviewServiceImpl implements ReviewSerive {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;

   @Override
    public Review submitReview(ReviewRequest reviewRequest, User user) {
        Review review = new Review();
        System.out.println(reviewRequest);
        
        System.out.println(reviewRequest.getRestaurantId());
         Optional<Restaurant> restaurant = restaurantRepository.findById(reviewRequest.getRestaurantId());
         if(restaurant.isPresent()) {
        	 review.setRestaurant(restaurant.get());
         }
        review.setCliente(user);
        review.setMensagem(reviewRequest.getRevisarTexto());
        review.setAvaliacao(reviewRequest.getAvaliacao());
        review.setCriadoEm(LocalDateTime.now());

        return reviewRepository.save(review);
    }

    
    @Override
    public void deleteReview(Long reviewId) throws ReviewException {
        Optional<Review> optionalReview = reviewRepository.findById(reviewId);

        if (optionalReview.isPresent()) {
            reviewRepository.deleteById(reviewId);
        } else {
            throw new ReviewException("Review with ID " + reviewId + " not found");
        }
    }

    @Override
    public double calculateAverageRating(List<Review> reviews) {
    	 double totalRating = 0;

         for (Review review : reviews) {
             totalRating += review.getAvaliacao();
         }

         if (reviews.size() > 0) {
             return totalRating / reviews.size();
         } else {
             return 0;
         }
    }
}

