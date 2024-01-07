package com.example.movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private MongoTemplate mongoTemplate;
    public Review createReview(String reviewBody, String imdbId) {
        Review review = reviewRepository.insert(new Review(reviewBody)); //when you call insert, it returns the data you just pushed inside the db


        mongoTemplate.update(Movie.class) //updating movie db
                .matching(Criteria.where("imdbId").is(imdbId)) //update movie where imdbId matches
                .apply(new Update().push("reviewIds").value(review)) //apply this update by creating new update definition
                .first(); //getting a single movie and updating that first

        return review;
    }
}
