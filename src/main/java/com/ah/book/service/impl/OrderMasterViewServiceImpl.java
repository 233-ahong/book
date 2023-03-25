package com.ah.book.service.impl;

import java.util.List;

import com.ah.book.common.utils.DateUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ah.book.domain.OrderDetail;
import com.ah.book.mapper.OrderMasterViewMapper;
import com.ah.book.domain.OrderMasterView;
import com.ah.book.service.IOrderMasterViewService;

import javax.annotation.Resource;

/**
 * 订单主Service业务层处理
 *
 * @author 陈祥
 * @date 2022-12-28
 */
@Service
public class OrderMasterViewServiceImpl implements IOrderMasterViewService
{
    @Resource
    private OrderMasterViewMapper orderMasterViewMapper;

    /**
     * 查询订单主
     *
     * @param orderId 订单主主键
     * @return 订单主
     */
    @Override
    public List <OrderMasterView> selectOrderMasterViewByUserId(Long orderId)
    {
        return orderMasterViewMapper.selectOrderMasterViewByUserId(orderId);
    }

    /**
     * 查询订单主列表
     *
     * @param orderMasterView 订单主
     * @return 订单主
     */
    @Override
    public List<OrderMasterView> selectOrderMasterViewList(OrderMasterView orderMasterView)
    {
        return orderMasterViewMapper.selectOrderMasterViewList(orderMasterView);
    }

    /**
     * 新增订单主
     *
     * @param orderMasterView 订单主
     * @return 结果
     */
    @Transactional
    @Override
    public int insertOrderMasterView(OrderMasterView orderMasterView)
    {
        orderMasterView.setCreateTime(DateUtils.getNowDate());
        int rows = orderMasterViewMapper.insertOrderMasterView(orderMasterView);
        insertOrderDetail(orderMasterView);
        return rows;
    }

    /**
     * 修改订单主
     *
     * @param orderMasterView 订单主
     * @return 结果
     */
    @Transactional
    @Override
    public int updateOrderMasterView(OrderMasterView orderMasterView)
    {
        return orderMasterViewMapper.updateOrderMasterView(orderMasterView);
    }

    /**
     * 批量删除订单主
     *
     * @param orderIds 需要删除的订单主主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteOrderMasterViewByOrderIds(Integer[] orderIds)
    {
        orderMasterViewMapper.deleteOrderDetailByOrderIds(orderIds);
        return orderMasterViewMapper.deleteOrderMasterViewByOrderIds(orderIds);
    }

    /**
     * 删除订单主信息
     *
     * @param orderId 订单主主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteOrderMasterViewByOrderId(Integer orderId)
    {
        orderMasterViewMapper.deleteOrderDetailByOrderId(orderId);
        return orderMasterViewMapper.deleteOrderMasterViewByOrderId(orderId);
    }

    /**
     * 新增订单详情信息
     *
     * @param orderMasterView 订单主对象
     */
    public void insertOrderDetail(OrderMasterView orderMasterView)
    {
        OrderDetail orderDetailList = orderMasterView.getOrderDetailList();
        Integer orderId = orderMasterView.getOrderId();
    }
}
