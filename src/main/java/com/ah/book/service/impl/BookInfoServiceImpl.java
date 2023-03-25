package com.ah.book.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.ah.book.mapper.BookInfoMapper;
import com.ah.book.domain.BookInfo;
import com.ah.book.service.IBookInfoService;

import javax.annotation.Resource;

/**
 * 图书信息Service业务层处理
 *
 * @author ruoyi
 * @date 2022-09-10
 */
@Service
public class BookInfoServiceImpl implements IBookInfoService
{
    @Resource
    private BookInfoMapper bookInfoMapper;

    /**
     * 查询图书信息
     *
     * @param bookId 图书信息主键
     * @return 图书信息
     */
    @Override
    public BookInfo selectBookInfoByBookId(Integer bookId)
    {
        return bookInfoMapper.selectBookInfoByBookId(bookId);
    }

    /**
     * 查询图书信息列表
     *
     * @param bookInfo 图书信息
     * @return 图书信息
     */
    @Override
    public List<BookInfo> selectBookInfoList(BookInfo bookInfo)
    {
        return bookInfoMapper.selectBookInfoList(bookInfo);
    }

    /**
     * 新增图书信息
     *
     * @param bookInfo 图书信息
     * @return 结果
     */
    @Override
    public int insertBookInfo(BookInfo bookInfo)
    {
        return bookInfoMapper.insertBookInfo(bookInfo);
    }

    /**
     * 修改图书信息
     *
     * @param bookInfo 图书信息
     * @return 结果
     */
    @Override
    public int updateBookInfo(BookInfo bookInfo)
    {
        return bookInfoMapper.updateBookInfo(bookInfo);
    }

    /**
     * 批量删除图书信息
     *
     * @param bookIds 需要删除的图书信息主键
     * @return 结果
     */
    @Override
    public int deleteBookInfoByBookIds(Integer[] bookIds)
    {
        return bookInfoMapper.deleteBookInfoByBookIds(bookIds);
    }

    /**
     * 删除图书信息信息
     *
     * @param bookId 图书信息主键
     * @return 结果
     */
    @Override
    public int deleteBookInfoByBookId(Integer bookId)
    {
        return bookInfoMapper.deleteBookInfoByBookId(bookId);
    }

    @Override
    public int modifyBookInventory(int id) {
        return bookInfoMapper.modifyBookInventory(id);
    }

    @Override
    public int returnBookByIds(Integer[] bookIds) {
        return bookInfoMapper.returnBookByIds(bookIds);
    }
}
