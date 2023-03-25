package com.ah.book.service;

import com.ah.book.domain.Dict;
import com.ah.book.domain.BookSort;

import java.util.List;

/**
 * 书籍分类Service接口
 *
 * @author ruoyi
 * @date 2022-12-05
 */
public interface IBookSortService
{
    /**
     * 查询书籍分类
     *
     * @param sortId 书籍分类主键
     * @return 书籍分类
     */
    public BookSort selectBookSortBySortId(Long sortId);

    /**
     * 查询书籍分类列表
     *
     * @param bookSort 书籍分类
     * @return 书籍分类集合
     */
    public List<BookSort> selectBookSortList(BookSort bookSort);

    /**
     * 新增书籍分类
     *
     * @param bookSort 书籍分类
     * @return 结果
     */
    public int insertBookSort(BookSort bookSort);

    /**
     * 修改书籍分类
     *
     * @param bookSort 书籍分类
     * @return 结果
     */
    public int updateBookSort(BookSort bookSort);

    /**
     * 批量删除书籍分类
     *
     * @param sortIds 需要删除的书籍分类主键集合
     * @return 结果
     */
    public int deleteBookSortBySortIds(Long[] sortIds);

    /**
     * 删除书籍分类信息
     *
     * @param sortId 书籍分类主键
     * @return 结果
     */
    public int deleteBookSortBySortId(Long sortId);

    List<Dict> selectBookSortDict();
}

