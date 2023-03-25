package com.ah.book.service.impl;


import java.util.List;

import com.ah.book.domain.OrderCart;
import com.ah.book.mapper.OrderCartMapper;
import com.ah.book.service.IOrderCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 购物车Service业务层处理
 *
 * @author 陈祥
 * @date 2022-12-25
 */
@Service
public class OrderCartServiceImpl implements IOrderCartService
{
    @Resource
    private OrderCartMapper orderCartMapper;

    /**
     * 查询购物车
     *
     * @param cartId 购物车主键
     * @return 购物车
     */
    @Override
    public OrderCart selectOrderCartByCartId(Integer cartId)
    {
        return orderCartMapper.selectOrderCartByCartId(cartId);
    }

    /**
     * 查询购物车列表
     *
     * @param orderCart 购物车
     * @return 购物车
     */
    @Override
    public List<OrderCart> selectOrderCartList(OrderCart orderCart)
    {
        return orderCartMapper.selectOrderCartList(orderCart);
    }

    /**
     * 新增购物车
     *
     * @param orderCart 购物车
     * @return 结果
     */
    @Override
    public int insertOrderCart(OrderCart orderCart)
    {
        return orderCartMapper.insertOrderCart(orderCart);
    }

    /**
     * 修改购物车
     *
     * @param orderCart 购物车
     * @return 结果
     */
    @Override
    public int updateOrderCart(OrderCart orderCart)
    {
        return orderCartMapper.updateOrderCart(orderCart);
    }

    /**
     * 批量删除购物车
     *
     * @param cartIds 需要删除的购物车主键
     * @return 结果
     */
    @Override
    public int deleteOrderCartByCartIds(Integer[] cartIds)
    {
        return orderCartMapper.deleteOrderCartByCartIds(cartIds);
    }

    /**
     * 删除购物车信息
     *
     * @param cartId 购物车主键
     * @return 结果
     */
    @Override
    public int deleteOrderCartByCartId(Integer cartId)
    {
        return orderCartMapper.deleteOrderCartByCartId(cartId);
    }
}

