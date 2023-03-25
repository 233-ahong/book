package com.ah.book.domain;

import com.ah.book.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 书籍分类对象 book_sort
 *
 * @author ruoyi
 * @date 2022-12-05
 */
public class BookSort extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 分类ID */
    private Long sortId;

    /** 分类标签 */
    private String sortLabel;

    /** 分类键值 */
    private String sortValue;

    /** 样式属性（其他样式扩展） */
    private String cssClass;

    /** 表格回显样式 */
    private String listClass;

    /** 是否默认（Y是 N否） */
    private String isDefault;

    /** 状态 */
    private String status;

    public void setSortId(Long sortId)
    {
        this.sortId = sortId;
    }

    public Long getSortId()
    {
        return sortId;
    }
    public void setSortLabel(String sortLabel)
    {
        this.sortLabel = sortLabel;
    }

    public String getSortLabel()
    {
        return sortLabel;
    }
    public void setSortValue(String sortValue)
    {
        this.sortValue = sortValue;
    }

    public String getSortValue()
    {
        return sortValue;
    }
    public void setCssClass(String cssClass)
    {
        this.cssClass = cssClass;
    }

    public String getCssClass()
    {
        return cssClass;
    }
    public void setListClass(String listClass)
    {
        this.listClass = listClass;
    }

    public String getListClass()
    {
        return listClass;
    }
    public void setIsDefault(String isDefault)
    {
        this.isDefault = isDefault;
    }

    public String getIsDefault()
    {
        return isDefault;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("sortId", getSortId())
                .append("sortLabel", getSortLabel())
                .append("sortValue", getSortValue())
                .append("cssClass", getCssClass())
                .append("listClass", getListClass())
                .append("isDefault", getIsDefault())
                .append("status", getStatus())
                .append("remark", getRemark())
                .toString();
    }
}

