package com.ah.book.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ah.book.mapper.BookCategoryMapper;
import com.ah.book.domain.BookCategory;
import com.ah.book.service.IBookCategoryService;

/**
 * 书籍分类Service业务层处理
 *
 * @author 陈祥
 * @date 2022-12-11
 */
@Service
public class BookCategoryServiceImpl implements IBookCategoryService
{
    @Autowired
    private BookCategoryMapper bookCategoryMapper;

    /**
     * 查询书籍分类
     *
     * @param categoryId 书籍分类主键
     * @return 书籍分类
     */
    @Override
    public BookCategory selectBookCategoryByCategoryId(Integer categoryId)
    {
        return bookCategoryMapper.selectBookCategoryByCategoryId(categoryId);
    }

    /**
     * 查询书籍分类列表
     *
     * @param bookCategory 书籍分类
     * @return 书籍分类
     */
    @Override
    public List<BookCategory> selectBookCategoryList(BookCategory bookCategory)
    {
        return bookCategoryMapper.selectBookCategoryList(bookCategory);
    }

    /**
     * 新增书籍分类
     *
     * @param bookCategory 书籍分类
     * @return 结果
     */
    @Override
    public int insertBookCategory(BookCategory bookCategory)
    {
        return bookCategoryMapper.insertBookCategory(bookCategory);
    }

    /**
     * 修改书籍分类
     *
     * @param bookCategory 书籍分类
     * @return 结果
     */
    @Override
    public int updateBookCategory(BookCategory bookCategory)
    {
        return bookCategoryMapper.updateBookCategory(bookCategory);
    }

    /**
     * 批量删除书籍分类
     *
     * @param categoryIds 需要删除的书籍分类主键
     * @return 结果
     */
    @Override
    public int deleteBookCategoryByCategoryIds(Integer[] categoryIds)
    {
        return bookCategoryMapper.deleteBookCategoryByCategoryIds(categoryIds);
    }

    /**
     * 删除书籍分类信息
     *
     * @param categoryId 书籍分类主键
     * @return 结果
     */
    @Override
    public int deleteBookCategoryByCategoryId(Integer categoryId)
    {
        return bookCategoryMapper.deleteBookCategoryByCategoryId(categoryId);
    }
}
