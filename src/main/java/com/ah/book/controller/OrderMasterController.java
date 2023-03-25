package com.ah.book.controller;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;

import com.ah.book.common.core.controller.BaseController;
import com.ah.book.common.core.domain.AjaxResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ah.book.common.core.page.TableDataInfo;
import com.ah.book.domain.OrderMaster;
import com.ah.book.service.IOrderMasterService;


/**
 * 订单主Controller
 *
 * @author 陈祥
 * @date 2022-12-11
 */
@RestController
@RequestMapping("/system/master")
public class OrderMasterController extends BaseController
{
    @Resource
    private IOrderMasterService orderMasterService;

    /**
     * 查询订单主列表
     */
    @GetMapping("/list")
    public TableDataInfo list(OrderMaster orderMaster)
    {
        startPage();
        List<OrderMaster> list = orderMasterService.selectOrderMasterList(orderMaster);
        return getDataTable(list);
    }

    /**
     * 获取订单主详细信息
     */
    @GetMapping(value = "/{orderId}")
    public AjaxResult getInfo(@PathVariable("orderId") Integer orderId)
    {
        return AjaxResult.success(orderMasterService.selectOrderMasterByOrderId(orderId));
    }

    /**
     * 新增订单主
     */
    @PostMapping
    public AjaxResult add(@RequestBody OrderMaster orderMaster)
    {
        return toAjax(orderMasterService.insertOrderMaster(orderMaster));
    }

    /**
     * 修改订单主
     */
    @PutMapping
    public AjaxResult edit(@RequestBody OrderMaster orderMaster)
    {
        return toAjax(orderMasterService.updateOrderMaster(orderMaster));
    }

    /**
     * 删除订单主
     */
	@DeleteMapping("/{orderIds}")
    public AjaxResult remove(@PathVariable Integer orderIds)
    {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId(orderIds);
        orderMaster.setOrderStatus(1);
        orderMaster.setShippingTime(new Date());
        return toAjax(orderMasterService.updateOrderMaster(orderMaster));
    }
}
