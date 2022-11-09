package edu.hcmute.bookstore.mapper;

public class TransactionMapper {
    private Long id;
    private String tranStatus = "Active";
    private Long tranUnitPrice;
    private Long tranQuantity;
    private Long productId;
    private String productImage;
    private String productName;
    private String productAuthor;

    public TransactionMapper(Long id, String tranStatus, Long tranUnitPrice, Long tranQuantity, Long productId, String productImage, String productName, String productAuthor) {
        this.id = id;
        this.tranStatus = tranStatus;
        this.tranUnitPrice = tranUnitPrice;
        this.tranQuantity = tranQuantity;
        this.productId = productId;
        this.productImage = productImage;
        this.productName = productName;
        this.productAuthor = productAuthor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTranStatus() {
        return tranStatus;
    }

    public void setTranStatus(String tranStatus) {
        this.tranStatus = tranStatus;
    }

    public Long getTranUnitPrice() {
        return tranUnitPrice;
    }

    public void setTranUnitPrice(Long tranUnitPrice) {
        this.tranUnitPrice = tranUnitPrice;
    }

    public Long getTranQuantity() {
        return tranQuantity;
    }

    public void setTranQuantity(Long tranQuantity) {
        this.tranQuantity = tranQuantity;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductAuthor() {
        return productAuthor;
    }

    public void setProductAuthor(String productAuthor) {
        this.productAuthor = productAuthor;
    }
}
