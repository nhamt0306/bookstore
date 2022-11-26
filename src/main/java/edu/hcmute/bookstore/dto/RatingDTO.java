package edu.hcmute.bookstore.dto;

public class RatingDTO {
    private Long productId;
    private Long rating;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getRating() {
        return rating;
    }

    public void setRating(Long rating) {
        this.rating = rating;
    }

    public RatingDTO(Long productId, Long rating) {
        this.productId = productId;
        this.rating = rating;
    }
}
