package com.ah.book.domain;

import com.ah.book.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 购物车回显
 * @author 26471
 */
public class Cart extends BaseEntity {

    /** 购物车Id */
    private Integer cartId;

    private Integer bookId;
    /** 书籍价格 */
    private BigDecimal price;

    /** 加入购物车数量 */
    private Long productAmount;

    /** 图书名称 */
    private String bookName;

    /** 图书出版社 */
    private String bookPress;

    /** 封面地址 */
    private String coverAddress;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(Long productAmount) {
        this.productAmount = productAmount;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookPress() {
        return bookPress;
    }

    public void setBookPress(String bookPress) {
        this.bookPress = bookPress;
    }

    public String getCoverAddress() {
        return coverAddress;
    }

    public void setCoverAddress(String coverAddress) {
        this.coverAddress = coverAddress;
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartId=" + cartId +
                ", bookId=" + bookId +
                ", price=" + price +
                ", productAmount=" + productAmount +
                ", bookName='" + bookName + '\'' +
                ", bookPress='" + bookPress + '\'' +
                ", coverAddress='" + coverAddress + '\'' +
                '}';
    }
}
