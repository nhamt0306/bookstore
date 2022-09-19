package edu.hcmute.bookstore.model;

import javax.persistence.*;

@Entity
@Table(name = "comments")
public class CommentEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String com_content;
    private String com_status = "Active";
    private Long com_rating;


    public CommentEntity(Long id, String com_content, String com_status, Long com_rating) {
        this.id = id;
        this.com_content = com_content;
        this.com_status = com_status;
        this.com_rating = com_rating;
    }

    public CommentEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCom_content() {
        return com_content;
    }

    public void setCom_content(String com_content) {
        this.com_content = com_content;
    }

    public String getCom_status() {
        return com_status;
    }

    public void setCom_status(String com_status) {
        this.com_status = com_status;
    }

    public Long getCom_rating() {
        return com_rating;
    }

    public void setCom_rating(Long com_rating) {
        this.com_rating = com_rating;
    }
}
