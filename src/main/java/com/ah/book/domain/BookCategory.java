package com.ah.book.domain;

import java.util.Date;

import com.ah.book.common.core.domain.TreeEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 书籍分类对象 book_category
 *
 * @author 陈祥
 * @date 2022-12-11
 */
public class BookCategory extends TreeEntity
{
    private static final long serialVersionUID = 1L;

    /** 分类ID */
    private Integer categoryId;

    /** 分类名称 */
    private String categoryName;

    /** 分类编码 */
    private String categoryCode;

    /** 分类状态 */
    private Integer categoryStatus;

    /** 最后修改时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date modifiedTime;

    public void setCategoryId(Integer categoryId)
    {
        this.categoryId = categoryId;
    }

    public Integer getCategoryId()
    {
        return categoryId;
    }
    public void setCategoryName(String categoryName)
    {
        this.categoryName = categoryName;
    }

    public String getCategoryName()
    {
        return categoryName;
    }
    public void setCategoryCode(String categoryCode)
    {
        this.categoryCode = categoryCode;
    }

    public String getCategoryCode()
    {
        return categoryCode;
    }
    public void setCategoryStatus(Integer categoryStatus)
    {
        this.categoryStatus = categoryStatus;
    }

    public Integer getCategoryStatus()
    {
        return categoryStatus;
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
                .append("categoryId", getCategoryId())
                .append("categoryName", getCategoryName())
                .append("categoryCode", getCategoryCode())
                .append("parentId", getParentId())
                .append("categoryStatus", getCategoryStatus())
                .append("modifiedTime", getModifiedTime())
                .toString();
    }
}
