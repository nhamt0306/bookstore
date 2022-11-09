package edu.hcmute.bookstore.dto;

public class CommentDTO {
    private Long id;
    private String comContent;
    private Long comRating;
    private Long userId;
    private Long productId;

    public CommentDTO(Long id, String comContent, Long comRating, Long userId, Long productId) {
        this.id = id;
        this.comContent = comContent;
        this.comRating = comRating;
        this.userId = userId;
        this.productId = productId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComContent() {
        return comContent;
    }

    public void setComContent(String comContent) {
        this.comContent = comContent;
    }

    public Long getComRating() {
        return comRating;
    }

    public void setComRating(Long comRating) {
        this.comRating = comRating;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
