package com.ah.book.domain;

import java.util.Date;

import com.ah.book.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 借阅记录详情对象 borrow
 *
 * @author ruoyi
 * @date 2022-09-22
 */
public class Borrow extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 借阅信息表ID */
    private Integer borrowId;

    /** 用户ID */
    private Long userId;

    /** 借阅人 */
    private String userName;

    /** 图书ID */
    private Integer bookId;

    /** 图书名称 */
    private String bookName;

    /** 借阅时长 */
    private Long borrowingTime;

    /** 还书日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date remandDate;

    /** 借阅时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date modifiedTime;

    /** 归还状态 */
    private Long borrowState;

    public void setBorrowId(Integer borrowId)
    {
        this.borrowId = borrowId;
    }

    public Integer getBorrowId()
    {
        return borrowId;
    }
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }
    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getUserName()
    {
        return userName;
    }
    public void setBookId(Integer bookId)
    {
        this.bookId = bookId;
    }

    public Integer getBookId()
    {
        return bookId;
    }
    public void setBookName(String bookName)
    {
        this.bookName = bookName;
    }

    public String getBookName()
    {
        return bookName;
    }
    public void setBorrowingTime(Long borrowingTime)
    {
        this.borrowingTime = borrowingTime;
    }

    public Long getBorrowingTime()
    {
        return borrowingTime;
    }
    public void setRemandDate(Date remandDate)
    {
        this.remandDate = remandDate;
    }

    public Date getRemandDate()
    {
        return remandDate;
    }
    public void setModifiedTime(Date modifiedTime)
    {
        this.modifiedTime = modifiedTime;
    }

    public Date getModifiedTime()
    {
        return modifiedTime;
    }
    public void setBorrowState(Long borrowState)
    {
        this.borrowState = borrowState;
    }

    public Long getBorrowState()
    {
        return borrowState;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("borrowId", getBorrowId())
                .append("userId", getUserId())
                .append("userName", getUserName())
                .append("bookId", getBookId())
                .append("bookName", getBookName())
                .append("borrowingTime", getBorrowingTime())
                .append("remandDate", getRemandDate())
                .append("modifiedTime", getModifiedTime())
                .append("borrowState", getBorrowState())
                .toString();
    }
}
