package com.ah.book.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ah.book.common.core.domain.BaseEntity;

/**
 * 购物车对象 order_cart
 *
 * @author 陈祥
 * @date 2022-12-25
 */
public class OrderCart extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 购物车ID */
    private Integer cartId;

    /** 用户ID */
    private Long userId;

    /** 书籍ID */
    private Integer bookId;

    /** 加入购物车数量 */
    private Long productAmount;

    /** 书籍价格 */
    private BigDecimal price;

    /** 加入购物车时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date addTime;

    /** 最后修改时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date modifiedTime;

    public void setCartId(Integer cartId)
    {
        this.cartId = cartId;
    }

    public Integer getCartId()
    {
        return cartId;
    }
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }
    public void setBookId(Integer bookId)
    {
        this.bookId = bookId;
    }

    public Integer getBookId()
    {
        return bookId;
    }
    public void setProductAmount(Long productAmount)
    {
        this.productAmount = productAmount;
    }

    public Long getProductAmount()
    {
        return productAmount;
    }
    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }

    public BigDecimal getPrice()
    {
        return price;
    }
    public void setAddTime(Date addTime)
    {
        this.addTime = addTime;
    }

    public Date getAddTime()
    {
        return addTime;
    }
    public void setModifiedTime(Date modifiedTime)
    {
        this.modifiedTime = modifiedTime;
    }

    public Date getModifiedTime()
    {
        return modifiedTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("cartId", getCartId())
                .append("userId", getUserId())
                .append("bookId", getBookId())
                .append("productAmount", getProductAmount())
                .append("price", getPrice())
                .append("addTime", getAddTime())
                .append("modifiedTime", getModifiedTime())
                .toString();
    }
}

