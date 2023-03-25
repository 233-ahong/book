package com.ah.book.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ah.book.mapper.OrderDetailMapper;
import com.ah.book.domain.OrderDetail;
import com.ah.book.service.IOrderDetailService;

/**
 * 订单详情Service业务层处理
 * 
 * @author 陈祥
 * @date 2022-12-11
 */
@Service
public class OrderDetailServiceImpl implements IOrderDetailService 
{
    @Autowired
    private OrderDetailMapper orderDetailMapper;

    /**
     * 查询订单详情
     * 
     * @param orderDetailId 订单详情主键
     * @return 订单详情
     */
    @Override
    public OrderDetail selectOrderDetailByOrderDetailId(Integer orderDetailId)
    {
        return orderDetailMapper.selectOrderDetailByOrderDetailId(orderDetailId);
    }

    /**
     * 查询订单详情列表
     * 
     * @param orderDetail 订单详情
     * @return 订单详情
     */
    @Override
    public List<OrderDetail> selectOrderDetailList(OrderDetail orderDetail)
    {
        return orderDetailMapper.selectOrderDetailList(orderDetail);
    }

    /**
     * 新增订单详情
     * 
     * @param orderDetail 订单详情
     * @return 结果
     */
    @Override
    public int insertOrderDetail(OrderDetail orderDetail)
    {
        return orderDetailMapper.insertOrderDetail(orderDetail);
    }

    /**
     * 修改订单详情
     * 
     * @param orderDetail 订单详情
     * @return 结果
     */
    @Override
    public int updateOrderDetail(OrderDetail orderDetail)
    {
        return orderDetailMapper.updateOrderDetail(orderDetail);
    }

    /**
     * 批量删除订单详情
     * 
     * @param orderDetailIds 需要删除的订单详情主键
     * @return 结果
     */
    @Override
    public int deleteOrderDetailByOrderDetailIds(Integer[] orderDetailIds)
    {
        return orderDetailMapper.deleteOrderDetailByOrderDetailIds(orderDetailIds);
    }

    /**
     * 删除订单详情信息
     * 
     * @param orderDetailId 订单详情主键
     * @return 结果
     */
    @Override
    public int deleteOrderDetailByOrderDetailId(Integer orderDetailId)
    {
        return orderDetailMapper.deleteOrderDetailByOrderDetailId(orderDetailId);
    }
}
