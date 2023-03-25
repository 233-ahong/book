package com.ah.book.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ah.book.mapper.BorrowMapper;
import com.ah.book.domain.Borrow;
import com.ah.book.service.IBorrowService;

import javax.annotation.Resource;

/**
 * 借阅记录详情Service业务层处理
 *
 * @author ah
 * @date 2022-09-20
 */
@Service
public class BorrowServiceImpl implements IBorrowService
{
    @Resource
    private BorrowMapper borrowMapper;

    /**
     * 查询借阅记录详情
     *
     * @param borrowId 借阅记录详情主键
     * @return 借阅记录详情
     */
    @Override
    public Borrow selectBorrowByBorrowId(Integer borrowId)
    {
        return borrowMapper.selectBorrowByBorrowId(borrowId);
    }

    /**
     * 查询借阅记录详情列表
     *
     * @param borrow 借阅记录详情
     * @return 借阅记录详情
     */
    @Override
    public List<Borrow> selectBorrowList(Borrow borrow)
    {
        return borrowMapper.selectBorrowList(borrow);
    }

    /**
     * 新增借阅记录详情
     *
     * @param borrow 借阅记录详情
     * @return 结果
     */
    @Override
    public int insertBorrow(Borrow borrow)
    {
        return borrowMapper.insertBorrow(borrow);
    }

    /**
     * 修改借阅记录详情
     *
     * @param borrow 借阅记录详情
     * @return 结果
     */
    @Override
    public int updateBorrow(Borrow borrow)
    {
        return borrowMapper.updateBorrow(borrow);
    }

    /**
     * 批量删除借阅记录详情
     *
     * @param borrowIds 需要删除的借阅记录详情主键
     * @return 结果
     */
    @Override
    public int deleteBorrowByBorrowIds(Integer[] borrowIds)
    {
        return borrowMapper.deleteBorrowByBorrowIds(borrowIds);
    }

    /**
     * 删除借阅记录详情信息
     *
     * @param borrowId 借阅记录详情主键
     * @return 结果
     */
    @Override
    public int deleteBorrowByBorrowId(Integer borrowId)
    {
        return borrowMapper.deleteBorrowByBorrowId(borrowId);
    }

    @Override
    public int returnBookByIds(Integer[] borrowIds) {
        return borrowMapper.returnBookByIds(borrowIds);
    }

    @Override
    public Borrow selectBorrowByBookId(int id) {
        return borrowMapper.selectBorrowByBookId(id);
    }

    @Override
    public int returnBookByUser(Integer[] borrowIds) {
        return borrowMapper.returnBookByUser(borrowIds);
    }

}
