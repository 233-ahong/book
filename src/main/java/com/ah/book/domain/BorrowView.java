package com.ah.book.domain;

import com.ah.book.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author 26471
 */
public class BorrowView extends BaseEntity {
    /** 借阅信息表ID */
    private Integer borrowId;

    private Integer bookId;

    /** 用户ID */
    private Long userId;

    /** 图书名称 */
    private String bookName;

    /** 借阅时长 */
    private Long borrowingTime;

    /** 借阅时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date modifiedTime;

    /** 归还状态 */
    private Long borrowState;

    /** 封面地址 */
    private String coverAddress;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(Integer borrowId) {
        this.borrowId = borrowId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Long getBorrowingTime() {
        return borrowingTime;
    }

    public void setBorrowingTime(Long borrowingTime) {
        this.borrowingTime = borrowingTime;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public Long getBorrowState() {
        return borrowState;
    }

    public void setBorrowState(Long borrowState) {
        this.borrowState = borrowState;
    }

    public String getCoverAddress() {
        return coverAddress;
    }

    public void setCoverAddress(String coverAddress) {
        this.coverAddress = coverAddress;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    @Override
    public String toString() {
        return "BorrowView{" +
                "borrowId=" + borrowId +
                ", bookId=" + bookId +
                ", userId=" + userId +
                ", bookName='" + bookName + '\'' +
                ", borrowingTime=" + borrowingTime +
                ", modifiedTime=" + modifiedTime +
                ", borrowState=" + borrowState +
                ", coverAddress='" + coverAddress + '\'' +
                '}';
    }
}
