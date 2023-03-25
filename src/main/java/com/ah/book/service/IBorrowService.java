package com.ah.book.service;

import java.util.List;
import com.ah.book.domain.Borrow;

/**
 * 借阅记录详情Service接口
 *
 * @author ah
 * @date 2022-09-20
 */
public interface IBorrowService
{
    /**
     * 查询借阅记录详情
     *
     * @param borrowId 借阅记录详情主键
     * @return 借阅记录详情
     */
    public Borrow selectBorrowByBorrowId(Integer borrowId);

    /**
     * 查询借阅记录详情列表
     *
     * @param borrow 借阅记录详情
     * @return 借阅记录详情集合
     */
    public List<Borrow> selectBorrowList(Borrow borrow);

    /**
     * 新增借阅记录详情
     *
     * @param borrow 借阅记录详情
     * @return 结果
     */
    public int insertBorrow(Borrow borrow);

    /**
     * 修改借阅记录详情
     *
     * @param borrow 借阅记录详情
     * @return 结果
     */
    public int updateBorrow(Borrow borrow);

    /**
     * 批量删除借阅记录详情
     *
     * @param borrowIds 需要删除的借阅记录详情主键集合
     * @return 结果
     */
    public int deleteBorrowByBorrowIds(Integer[] borrowIds);

    /**
     * 删除借阅记录详情信息
     *
     * @param borrowId 借阅记录详情主键
     * @return 结果
     */
    public int deleteBorrowByBorrowId(Integer borrowId);

    int returnBookByIds(Integer[] borrowIds);

    Borrow selectBorrowByBookId(int id);

    int returnBookByUser(Integer[] borrowIds);
}
