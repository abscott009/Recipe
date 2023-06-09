package com.github.recipe.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Review {

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public int getRating() {
        return rating;
    }

    public String getDescription() {
        return description;
    }

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String username;

    private int rating;

    @NotNull
    private String description;

    public void setRating(int rating) {
        if (rating <= 0 || rating > 10) {
            throw new IllegalStateException("Rating must be between 0 and 10.");
        }
        this.rating = rating;
    }
}
