package com.ah.book.service;

import java.util.List;
import com.ah.book.domain.BookInfo;

/**
 * 图书信息Service接口
 *
 * @author ruoyi
 * @date 2022-09-10
 */
public interface IBookInfoService
{
    /**
     * 查询图书信息
     *
     * @param bookId 图书信息主键
     * @return 图书信息
     */
    public BookInfo selectBookInfoByBookId(Integer bookId);

    /**
     * 查询图书信息列表
     *
     * @param bookInfo 图书信息
     * @return 图书信息集合
     */
    public List<BookInfo> selectBookInfoList(BookInfo bookInfo);

    /**
     * 新增图书信息
     *
     * @param bookInfo 图书信息
     * @return 结果
     */
    public int insertBookInfo(BookInfo bookInfo);

    /**
     * 修改图书信息
     *
     * @param bookInfo 图书信息
     * @return 结果
     */
    public int updateBookInfo(BookInfo bookInfo);

    /**
     * 批量删除图书信息
     *
     * @param bookIds 需要删除的图书信息主键集合
     * @return 结果
     */
    public int deleteBookInfoByBookIds(Integer[] bookIds);

    /**
     * 删除图书信息信息
     *
     * @param bookId 图书信息主键
     * @return 结果
     */
    public int deleteBookInfoByBookId(Integer bookId);

    int modifyBookInventory(int id);

    int returnBookByIds(Integer[] bookIds);
}
