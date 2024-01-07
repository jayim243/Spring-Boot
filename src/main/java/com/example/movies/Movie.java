package com.example.movies;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Document(collection = "movies") //let framework know that this class presents each document in movies collection
@Data //takes care of getters and setters for all attributes(comes from lombok)
@AllArgsConstructor //creating constructor for all private fields as argument
@NoArgsConstructor //constructor that takes no parameters
public class Movie {
    @Id //let framework know that ObjectId should be treated as unique identifier for each movie in db
    private ObjectId id;
    private String imdbId;
    private String title;
    private String releaseDate;
    private String trailerLink;
    private String poster;
    private List<String> genres;
    private List<String> backdrops;
    @DocumentReference //cause db to store only Ids of review and reviews will be in a separate collection(manual reference relationship)
    private List<Review> reviewIds;
}
