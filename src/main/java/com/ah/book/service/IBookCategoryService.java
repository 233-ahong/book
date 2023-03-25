package com.ah.book.service;

import java.util.List;
import com.ah.book.domain.BookCategory;

/**
 * 书籍分类Service接口
 *
 * @author 陈祥
 * @date 2022-12-11
 */
public interface IBookCategoryService
{
    /**
     * 查询书籍分类
     *
     * @param categoryId 书籍分类主键
     * @return 书籍分类
     */
    public BookCategory selectBookCategoryByCategoryId(Integer categoryId);

    /**
     * 查询书籍分类列表
     *
     * @param bookCategory 书籍分类
     * @return 书籍分类集合
     */
    public List<BookCategory> selectBookCategoryList(BookCategory bookCategory);

    /**
     * 新增书籍分类
     *
     * @param bookCategory 书籍分类
     * @return 结果
     */
    public int insertBookCategory(BookCategory bookCategory);

    /**
     * 修改书籍分类
     *
     * @param bookCategory 书籍分类
     * @return 结果
     */
    public int updateBookCategory(BookCategory bookCategory);

    /**
     * 批量删除书籍分类
     *
     * @param categoryIds 需要删除的书籍分类主键集合
     * @return 结果
     */
    public int deleteBookCategoryByCategoryIds(Integer[] categoryIds);

    /**
     * 删除书籍分类信息
     *
     * @param categoryId 书籍分类主键
     * @return 结果
     */
    public int deleteBookCategoryByCategoryId(Integer categoryId);
}
