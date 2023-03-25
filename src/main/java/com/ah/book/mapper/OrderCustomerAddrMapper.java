package com.ah.book.mapper;

import java.util.List;
import com.ah.book.domain.OrderCustomerAddr;

/**
 * 用户地址Mapper接口
 * 
 * @author 陈祥
 * @date 2022-12-11
 */
public interface OrderCustomerAddrMapper 
{
    /**
     * 查询用户地址
     * 
     * @param customerAddrId 用户地址主键
     * @return 用户地址
     */
    public OrderCustomerAddr selectOrderCustomerAddrByCustomerAddrId(Integer customerAddrId);

    /**
     * 查询用户地址列表
     * 
     * @param orderCustomerAddr 用户地址
     * @return 用户地址集合
     */
    public List<OrderCustomerAddr> selectOrderCustomerAddrList(OrderCustomerAddr orderCustomerAddr);

    /**
     * 新增用户地址
     * 
     * @param orderCustomerAddr 用户地址
     * @return 结果
     */
    public int insertOrderCustomerAddr(OrderCustomerAddr orderCustomerAddr);

    /**
     * 修改用户地址
     * 
     * @param orderCustomerAddr 用户地址
     * @return 结果
     */
    public int updateOrderCustomerAddr(OrderCustomerAddr orderCustomerAddr);

    /**
     * 删除用户地址
     * 
     * @param customerAddrId 用户地址主键
     * @return 结果
     */
    public int deleteOrderCustomerAddrByCustomerAddrId(Integer customerAddrId);

    /**
     * 批量删除用户地址
     * 
     * @param customerAddrIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOrderCustomerAddrByCustomerAddrIds(Integer[] customerAddrIds);
}
