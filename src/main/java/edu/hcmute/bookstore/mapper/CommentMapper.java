package edu.hcmute.bookstore.mapper;

public class CommentMapper {
    private Long id;
    private String comContent;
    private Long comRating;
    private String userFullName;
    private String userAvatar;
    private Integer totalComment;
    private String userCreater;

    public CommentMapper(Long id, String comContent, Long comRating, String userFullName, String avatar, Integer totalComment, String userCreater) {
        this.id = id;
        this.comContent = comContent;
        this.comRating = comRating;
        this.userFullName = userFullName;
        this.userAvatar =avatar;
        this.totalComment = totalComment;
        this.userCreater = userCreater;
    }

    public String getUserCreater() {
        return userCreater;
    }

    public void setUserCreater(String userCreater) {
        this.userCreater = userCreater;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public Integer getTotalComment() {
        return totalComment;
    }

    public void setTotalComment(Integer totalComment) {
        this.totalComment = totalComment;
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

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }
}
