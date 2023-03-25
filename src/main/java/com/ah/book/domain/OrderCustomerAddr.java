package com.ah.book.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ah.book.common.core.domain.BaseEntity;

/**
 * 用户地址对象 order_customer_addr
 *
 * @author 陈祥
 * @date 2022-12-26
 */
public class OrderCustomerAddr extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 自增主键ID */
    private Integer customerAddrId;

    /** 用户ID */
    private Long customerId;

    /** 邮编 */
    private Integer zip;

    /** 省份 */
    private String province;

    /** 城市 */
    private String city;

    /** 区 */
    private String district;

    /** 具体地址 */
    private String address;

    /** 是否默认 */
    private Integer isDefault;

    /** 最后修改时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date modifiedTime;

    public void setCustomerAddrId(Integer customerAddrId)
    {
        this.customerAddrId = customerAddrId;
    }

    public Integer getCustomerAddrId()
    {
        return customerAddrId;
    }
    public void setCustomerId(Long customerId)
    {
        this.customerId = customerId;
    }

    public Long getCustomerId()
    {
        return customerId;
    }
    public void setZip(Integer zip)
    {
        this.zip = zip;
    }

    public Integer getZip()
    {
        return zip;
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
    public void setIsDefault(Integer isDefault)
    {
        this.isDefault = isDefault;
    }

    public Integer getIsDefault()
    {
        return isDefault;
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
                .append("customerAddrId", getCustomerAddrId())
                .append("customerId", getCustomerId())
                .append("zip", getZip())
                .append("province", getProvince())
                .append("city", getCity())
                .append("district", getDistrict())
                .append("address", getAddress())
                .append("isDefault", getIsDefault())
                .append("modifiedTime", getModifiedTime())
                .toString();
    }
}
