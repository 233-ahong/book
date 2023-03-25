package com.ah.book.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ah.book.common.core.domain.BaseEntity;

/**
 * 订单详情对象 order_detail
 *
 * @author 陈祥
 * @date 2022-12-11
 */
public class OrderDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 订单详情表ID */
    private Integer orderDetailId;

    /** 订单表ID */
    private Integer orderId;

    /** 书籍ID */
    private Integer productId;

    /** 书名 */
    private String productName;

    /** 购买数量 */
    private Integer productCnt;

    /** 购买单价 */
    private BigDecimal productPrice;

    /** 商品重量 */
    private Long weight;

    /** 优惠分摊金额 */
    private BigDecimal feeMoney;

    /** 最后修改时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date modifiedTime;

    public void setOrderDetailId(Integer orderDetailId)
    {
        this.orderDetailId = orderDetailId;
    }

    public Integer getOrderDetailId()
    {
        return orderDetailId;
    }
    public void setOrderId(Integer orderId)
    {
        this.orderId = orderId;
    }

    public Integer getOrderId()
    {
        return orderId;
    }
    public void setProductId(Integer productId)
    {
        this.productId = productId;
    }

    public Integer getProductId()
    {
        return productId;
    }
    public void setProductName(String productName)
    {
        this.productName = productName;
    }

    public String getProductName()
    {
        return productName;
    }
    public void setProductCnt(Integer productCnt)
    {
        this.productCnt = productCnt;
    }

    public Integer getProductCnt()
    {
        return productCnt;
    }
    public void setProductPrice(BigDecimal productPrice)
    {
        this.productPrice = productPrice;
    }

    public BigDecimal getProductPrice()
    {
        return productPrice;
    }
    public void setWeight(Long weight)
    {
        this.weight = weight;
    }

    public Long getWeight()
    {
        return weight;
    }
    public void setFeeMoney(BigDecimal feeMoney)
    {
        this.feeMoney = feeMoney;
    }

    public BigDecimal getFeeMoney()
    {
        return feeMoney;
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
            .append("orderDetailId", getOrderDetailId())
            .append("orderId", getOrderId())
            .append("productId", getProductId())
            .append("productName", getProductName())
            .append("productCnt", getProductCnt())
            .append("productPrice", getProductPrice())
            .append("weight", getWeight())
            .append("feeMoney", getFeeMoney())
            .append("modifiedTime", getModifiedTime())
            .toString();
    }
}
