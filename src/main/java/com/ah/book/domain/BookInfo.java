package com.ah.book.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.ah.book.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 图书信息对象 book_info
 *
 * @author 26471
 * @date 2022-09-10
 */
public class BookInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 图书ID */
    private Integer bookId;

    /** 图书编码 */
    private String bookCore;

    /** 图书名称 */
    private String bookName;

    /** 国条码 */
    private String barCode;

    /** 图书出版社 */
    private String bookPress;

    /** 图书作者 */
    private String bookAuthor;

    /** 封面地址 */
    private String coverAddress;

    /** 分类 */
    private Integer bookCategory;

    /** 图书销售价格 */
    private BigDecimal price;

    /** 上下架状态：0下架1上架 */
    private Integer publishStatus;

    /** 审核状态：0未审核，1已审核 */
    private Integer auditStatus;

    /** 图书页数 */
    private String bookPage;

    /** 图书描述 */
    private String descript;

    /** 图书录入时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date indate;

    /** 最后修改时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date modifiedTime;

    public Integer getBookRemaining() {
        return bookRemaining;
    }

    public void setBookRemaining(Integer bookRemaining) {
        this.bookRemaining = bookRemaining;
    }

    private Integer bookRemaining;

    public void setBookId(Integer bookId)
    {
        this.bookId = bookId;
    }

    public Integer getBookId()
    {
        return bookId;
    }
    public void setBookCore(String bookCore)
    {
        this.bookCore = bookCore;
    }

    public String getBookCore()
    {
        return bookCore;
    }
    public void setBookName(String bookName)
    {
        this.bookName = bookName;
    }

    public String getBookName()
    {
        return bookName;
    }
    public void setBarCode(String barCode)
    {
        this.barCode = barCode;
    }

    public String getBarCode()
    {
        return barCode;
    }
    public void setBookPress(String bookPress)
    {
        this.bookPress = bookPress;
    }

    public String getBookPress()
    {
        return bookPress;
    }
    public void setBookAuthor(String bookAuthor)
    {
        this.bookAuthor = bookAuthor;
    }

    public String getBookAuthor()
    {
        return bookAuthor;
    }
    public void setCoverAddress(String coverAddress)
    {
        this.coverAddress = coverAddress;
    }

    public String getCoverAddress()
    {
        return coverAddress;
    }
    public void setBookCategory(Integer bookCategory)
    {
        this.bookCategory = bookCategory;
    }

    public Integer getBookCategory()
    {
        return bookCategory;
    }
    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }

    public BigDecimal getPrice()
    {
        return price;
    }
    public void setPublishStatus(Integer publishStatus)
    {
        this.publishStatus = publishStatus;
    }

    public Integer getPublishStatus()
    {
        return publishStatus;
    }
    public void setAuditStatus(Integer auditStatus)
    {
        this.auditStatus = auditStatus;
    }

    public Integer getAuditStatus()
    {
        return auditStatus;
    }
    public void setBookPage(String bookPage)
    {
        this.bookPage = bookPage;
    }

    public String getBookPage()
    {
        return bookPage;
    }
    public void setDescript(String descript)
    {
        this.descript = descript;
    }

    public String getDescript()
    {
        return descript;
    }
    public void setIndate(Date indate)
    {
        this.indate = indate;
    }

    public Date getIndate()
    {
        return indate;
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
                .append("bookId", getBookId())
                .append("bookCore", getBookCore())
                .append("bookName", getBookName())
                .append("barCode", getBarCode())
                .append("bookPress", getBookPress())
                .append("bookAuthor", getBookAuthor())
                .append("coverAddress", getCoverAddress())
                .append("bookCategory", getBookCategory())
                .append("price", getPrice())
                .append("publishStatus", getPublishStatus())
                .append("auditStatus", getAuditStatus())
                .append("bookPage", getBookPage())
                .append("descript", getDescript())
                .append("indate", getIndate())
                .append("modifiedTime", getModifiedTime())
                .append("bookRemaining", getBookRemaining())
                .toString();
    }
}
