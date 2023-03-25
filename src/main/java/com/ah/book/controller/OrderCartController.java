package com.ah.book.controller;

import java.util.List;
import javax.annotation.Resource;
import com.ah.book.common.core.controller.BaseController;
import com.ah.book.common.core.domain.AjaxResult;
import com.ah.book.domain.OrderCart;
import com.ah.book.service.IOrderCartService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ah.book.common.core.page.TableDataInfo;

/**
 * 购物车Controller
 *
 * @author 陈祥
 * @date 2022-12-25
 */
@RestController
@RequestMapping("/system/cart")
public class OrderCartController extends BaseController
{
    @Resource
    private IOrderCartService orderCartService;

    /**
     * 查询购物车列表
     */
    @GetMapping("/list")
    public TableDataInfo list(OrderCart orderCart)
    {
        startPage();
        List<OrderCart> list = orderCartService.selectOrderCartList(orderCart);
        return getDataTable(list);
    }

    /**
     * 获取购物车详细信息
     */
    @GetMapping(value = "/{cartId}")
    public AjaxResult getInfo(@PathVariable("cartId") Integer cartId)
    {
        return AjaxResult.success(orderCartService.selectOrderCartByCartId(cartId));
    }

    /**
     * 新增购物车
     */
    @PostMapping
    public AjaxResult add(@RequestBody OrderCart orderCart)
    {
        return toAjax(orderCartService.insertOrderCart(orderCart));
    }

    /**
     * 修改购物车
     */
    @PutMapping
    public AjaxResult edit(@RequestBody OrderCart orderCart)
    {

        return toAjax(orderCartService.updateOrderCart(orderCart));
    }

    /**
     * 删除购物车
     */
    @DeleteMapping("/{cartIds}")
    public AjaxResult remove(@PathVariable Integer[] cartIds)
    {
        return toAjax(orderCartService.deleteOrderCartByCartIds(cartIds));
    }
}

