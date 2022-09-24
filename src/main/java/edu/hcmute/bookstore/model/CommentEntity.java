package edu.hcmute.bookstore.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "comments")
public class CommentEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comContent;
    private String comStatus = "Active";
    private Long comRating;
    private Long userId;

    // Relationship with table ProductEntity
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "productId", nullable = false, referencedColumnName = "id")
    private ProductEntity productEntity;

    // Constructor


    public CommentEntity(Long id, String comContent, String comStatus, Long comRating, Long userId) {
        this.id = id;
        this.comContent = comContent;
        this.comStatus = comStatus;
        this.comRating = comRating;
        this.userId = userId;
    }

    public CommentEntity() {
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

    public String getComStatus() {
        return comStatus;
    }

    public void setComStatus(String comStatus) {
        this.comStatus = comStatus;
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
}
