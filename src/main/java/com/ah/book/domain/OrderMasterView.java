package com.ah.book.domain;

import java.math.BigDecimal;
import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ah.book.common.core.domain.BaseEntity;

/**
 * 订单主对象 order_master
 *
 * @author 陈祥
 * @date 2022-12-28
 */
public class OrderMasterView extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 订单ID */
    private Integer orderId;

    /** 订单编号 yyyymmddnnnnnnnn */
    private Long orderSn;

    /** 下单人ID */
    private Long customerId;

    /** 收货人姓名 */
    private String shippingUser;

    /** 省 */
    private String  province;

    /** 市 */
    private String city;

    /** 区 */
    private String  district;

    /** 地址 */
    private String address;

    /** 订单金额 */
    private BigDecimal orderMoney;

    /** 优惠金额 */
    private BigDecimal districtMoney;

    /** 运费金额 */
    private BigDecimal shippingMoney;

    /** 支付金额 */
    private BigDecimal paymentMoney;

    /** 快递公司名称 */
    private String shippingCompName;

    /** 快递单号 */
    private String shippingSn;

    /** 发货时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date shippingTime;

    /** 支付时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date payTime;

    /** 收货时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date receiveTime;

    /** 订单状态 */
    private Integer orderStatus;

    /** 发票抬头 */
    private String invoiceTime;

    /** 最后修改时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date modifiedTime;

    private String coverAddress;

    /** 订单详情信息 */
    private OrderDetail orderDetailList;

    public void setOrderId(Integer orderId)
    {
        this.orderId = orderId;
    }

    public Integer getOrderId()
    {
        return orderId;
    }
    public void setOrderSn(Long orderSn)
    {
        this.orderSn = orderSn;
    }

    public Long getOrderSn()
    {
        return orderSn;
    }
    public void setCustomerId(Long customerId)
    {
        this.customerId = customerId;
    }

    public Long getCustomerId()
    {
        return customerId;
    }
    public void setShippingUser(String shippingUser)
    {
        this.shippingUser = shippingUser;
    }

    public String getShippingUser()
    {
        return shippingUser;
    }
    public void setProvince(String province)
    {
        this.province = province;
    }

    public String getProvince()
    {
        return province;
    }
    public void setCity(String city)
    {
        this.city = city;
    }

    public String getCity()
    {
        return city;
    }
    public void setDistrict(String district)
    {
        this.district = district;
    }

    public String getDistrict()
    {
        return district;
    }
    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getAddress()
    {
        return address;
    }
    public void setOrderMoney(BigDecimal orderMoney)
    {
        this.orderMoney = orderMoney;
    }

    public BigDecimal getOrderMoney()
    {
        return orderMoney;
    }
    public void setDistrictMoney(BigDecimal districtMoney)
    {
        this.districtMoney = districtMoney;
    }

    public BigDecimal getDistrictMoney()
    {
        return districtMoney;
    }
    public void setShippingMoney(BigDecimal shippingMoney)
    {
        this.shippingMoney = shippingMoney;
    }

    public BigDecimal getShippingMoney()
    {
        return shippingMoney;
    }
    public void setPaymentMoney(BigDecimal paymentMoney)
    {
        this.paymentMoney = paymentMoney;
    }

    public BigDecimal getPaymentMoney()
    {
        return paymentMoney;
    }
    public void setShippingCompName(String shippingCompName)
    {
        this.shippingCompName = shippingCompName;
    }

    public String getShippingCompName()
    {
        return shippingCompName;
    }
    public void setShippingSn(String shippingSn)
    {
        this.shippingSn = shippingSn;
    }

    public String getShippingSn()
    {
        return shippingSn;
    }
    public void setShippingTime(Date shippingTime)
    {
        this.shippingTime = shippingTime;
    }

    public Date getShippingTime()
    {
        return shippingTime;
    }
    public void setPayTime(Date payTime)
    {
        this.payTime = payTime;
    }

    public Date getPayTime()
    {
        return payTime;
    }
    public void setReceiveTime(Date receiveTime)
    {
        this.receiveTime = receiveTime;
    }

    public Date getReceiveTime()
    {
        return receiveTime;
    }
    public void setOrderStatus(Integer orderStatus)
    {
        this.orderStatus = orderStatus;
    }

    public Integer getOrderStatus()
    {
        return orderStatus;
    }
    public void setInvoiceTime(String invoiceTime)
    {
        this.invoiceTime = invoiceTime;
    }

    public String getInvoiceTime()
    {
        return invoiceTime;
    }
    public void setModifiedTime(Date modifiedTime)
    {
        this.modifiedTime = modifiedTime;
    }

    public Date getModifiedTime()
    {
        return modifiedTime;
    }

    public OrderDetail getOrderDetailList()
    {
        return orderDetailList;
    }

    public void setOrderDetailList(OrderDetail orderDetailList)
    {
        this.orderDetailList = orderDetailList;
    }

    public String getCoverAddress() {
        return coverAddress;
    }

    public void setCoverAddress(String coverAddress) {
        this.coverAddress = coverAddress;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("orderId", getOrderId())
                .append("orderSn", getOrderSn())
                .append("customerId", getCustomerId())
                .append("shippingUser", getShippingUser())
                .append("province", getProvince())
                .append("city", getCity())
                .append("district", getDistrict())
                .append("address", getAddress())
                .append("orderMoney", getOrderMoney())
                .append("districtMoney", getDistrictMoney())
                .append("shippingMoney", getShippingMoney())
                .append("paymentMoney", getPaymentMoney())
                .append("shippingCompName", getShippingCompName())
                .append("shippingSn", getShippingSn())
                .append("createTime", getCreateTime())
                .append("shippingTime", getShippingTime())
                .append("payTime", getPayTime())
                .append("receiveTime", getReceiveTime())
                .append("orderStatus", getOrderStatus())
                .append("invoiceTime", getInvoiceTime())
                .append("modifiedTime", getModifiedTime())
                .append("orderDetailList", getOrderDetailList())
                .append("coverAddress",getCoverAddress())
                .toString();
    }
}
