package com.ah.book.domain.vo;

import java.math.BigDecimal;

public class OrderVo {
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(Integer productAmount) {
        this.productAmount = productAmount;
    }

    public String getShippingUser() {
        return shippingUser;
    }

    public void setShippingUser(String shippingUser) {
        this.shippingUser = shippingUser;
    }

    public Integer getCustomerAddrId() {
        return customerAddrId;
    }

    public void setCustomerAddrId(Integer customerAddrId) {
        this.customerAddrId = customerAddrId;
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    /** 书籍价格 */
    private BigDecimal price;
    /** 加入购物车数量 */
    private Integer productAmount;
    /** 收货人*/
    private String shippingUser;
    /** 地址ID */
    private Integer customerAddrId;
    /** 购物车Id */
    private Integer cartId;

    /** 书籍ID */
    private Integer productId;

    @Override
    public String toString() {
        return "OrderVo{" +
                "price=" + price +
                ", productAmount=" + productAmount +
                ", shippingUser='" + shippingUser + '\'' +
                ", customerAddrId=" + customerAddrId +
                ", cartId=" + cartId +
                ", productId=" + productId +
                '}';
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }
}
