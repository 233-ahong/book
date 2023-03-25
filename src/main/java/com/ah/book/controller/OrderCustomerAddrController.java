package com.ah.book.controller;

import java.util.List;
import javax.annotation.Resource;
import com.ah.book.common.core.controller.BaseController;
import com.ah.book.common.core.domain.AjaxResult;
import com.ah.book.common.utils.SecurityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ah.book.common.core.page.TableDataInfo;
import com.ah.book.domain.OrderCustomerAddr;
import com.ah.book.service.IOrderCustomerAddrService;

/**
 * 用户地址Controller
 *
 * @author 陈祥
 * @date 2022-12-11
 */
@RestController
@RequestMapping("/system/addr")
public class OrderCustomerAddrController extends BaseController
{
    @Resource
    private IOrderCustomerAddrService orderCustomerAddrService;

    /**
     * 查询用户地址列表
     */
    @GetMapping("/list")
    public TableDataInfo list(OrderCustomerAddr orderCustomerAddr)
    {
        startPage();
        orderCustomerAddr.setCustomerId(SecurityUtils.getUserId());
        List<OrderCustomerAddr> list = orderCustomerAddrService.selectOrderCustomerAddrList(orderCustomerAddr);
        return getDataTable(list);
    }

    /**
     * 获取用户地址详细信息
     */
    @GetMapping(value = "/{customerAddrId}")
    public AjaxResult getInfo(@PathVariable("customerAddrId") Integer customerAddrId)
    {
        return AjaxResult.success(orderCustomerAddrService.selectOrderCustomerAddrByCustomerAddrId(customerAddrId));
    }

    /**
     * 新增用户地址
     */
    @PostMapping
    public AjaxResult add(@RequestBody OrderCustomerAddr orderCustomerAddr)
    {
        orderCustomerAddr.setCustomerId(SecurityUtils.getUserId());
        return toAjax(orderCustomerAddrService.insertOrderCustomerAddr(orderCustomerAddr));
    }

    /**
     * 修改用户地址
     */
    @PutMapping
    public AjaxResult edit(@RequestBody OrderCustomerAddr orderCustomerAddr)
    {
        return toAjax(orderCustomerAddrService.updateOrderCustomerAddr(orderCustomerAddr));
    }

    /**
     * 删除用户地址
     */
	@DeleteMapping("/{customerAddrIds}")
    public AjaxResult remove(@PathVariable Integer[] customerAddrIds)
    {
        return toAjax(orderCustomerAddrService.deleteOrderCustomerAddrByCustomerAddrIds(customerAddrIds));
    }
}
