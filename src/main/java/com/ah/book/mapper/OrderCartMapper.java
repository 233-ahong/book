package com.ah.book.mapper;

import com.ah.book.domain.OrderCart;

import java.util.List;

/**
 * 购物车Mapper接口
 *
 * @author 陈祥
 * @date 2022-12-25
 */
public interface OrderCartMapper
{
    /**
     * 查询购物车
     *
     * @param cartId 购物车主键
     * @return 购物车
     */
    public OrderCart selectOrderCartByCartId(Integer cartId);

    /**
     * 查询购物车列表
     *
     * @param orderCart 购物车
     * @return 购物车集合
     */
    public List<OrderCart> selectOrderCartList(OrderCart orderCart);

    /**
     * 新增购物车
     *
     * @param orderCart 购物车
     * @return 结果
     */
    public int insertOrderCart(OrderCart orderCart);

    /**
     * 修改购物车
     *
     * @param orderCart 购物车
     * @return 结果
     */
    public int updateOrderCart(OrderCart orderCart);

    /**
     * 删除购物车
     *
     * @param cartId 购物车主键
     * @return 结果
     */
    public int deleteOrderCartByCartId(Integer cartId);

    /**
     * 批量删除购物车
     *
     * @param cartIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOrderCartByCartIds(Integer[] cartIds);
}

