package edu.hcmute.bookstore.dto;

public class CategoryDTO {
    private String catName;
    private Long catParent;

    public CategoryDTO(String catName, Long catParent) {
        this.catName = catName;
        this.catParent = catParent;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public Long getCatParent() {
        return catParent;
    }

    public void setCatParent(Long catParent) {
        this.catParent = catParent;
    }
}
