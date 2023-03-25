package com.ah.book.service;

import java.util.List;
import com.ah.book.domain.OrderDetail;

/**
 * 订单详情Service接口
 * 
 * @author 陈祥
 * @date 2022-12-11
 */
public interface IOrderDetailService 
{
    /**
     * 查询订单详情
     * 
     * @param orderDetailId 订单详情主键
     * @return 订单详情
     */
    public OrderDetail selectOrderDetailByOrderDetailId(Integer orderDetailId);

    /**
     * 查询订单详情列表
     * 
     * @param orderDetail 订单详情
     * @return 订单详情集合
     */
    public List<OrderDetail> selectOrderDetailList(OrderDetail orderDetail);

    /**
     * 新增订单详情
     * 
     * @param orderDetail 订单详情
     * @return 结果
     */
    public int insertOrderDetail(OrderDetail orderDetail);

    /**
     * 修改订单详情
     * 
     * @param orderDetail 订单详情
     * @return 结果
     */
    public int updateOrderDetail(OrderDetail orderDetail);

    /**
     * 批量删除订单详情
     * 
     * @param orderDetailIds 需要删除的订单详情主键集合
     * @return 结果
     */
    public int deleteOrderDetailByOrderDetailIds(Integer[] orderDetailIds);

    /**
     * 删除订单详情信息
     * 
     * @param orderDetailId 订单详情主键
     * @return 结果
     */
    public int deleteOrderDetailByOrderDetailId(Integer orderDetailId);
}
