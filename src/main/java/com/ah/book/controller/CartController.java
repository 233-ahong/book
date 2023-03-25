package com.ah.book.controller;

import com.ah.book.common.core.controller.BaseController;
import com.ah.book.common.core.domain.AjaxResult;
import com.ah.book.common.core.page.TableDataInfo;
import com.ah.book.common.utils.SecurityUtils;
import com.ah.book.domain.BookInfo;
import com.ah.book.domain.Cart;
import com.ah.book.domain.OrderCart;
import com.ah.book.service.IBookInfoService;
import com.ah.book.service.ICartService;
import com.ah.book.service.IOrderCartService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController extends BaseController {

    @Resource
    ICartService cartService;

    @Resource
    IOrderCartService orderCartService;

    @Resource
    IBookInfoService bookInfoService;

    @GetMapping("/add/{id}/{num}")
    public AjaxResult addCart(@PathVariable Integer id, @PathVariable Long num) {
        final Long userId = SecurityUtils.getUserId();
        final BookInfo bookInfo = bookInfoService.selectBookInfoByBookId(id);
        OrderCart cart = new OrderCart();
        cart.setBookId(id);
        cart.setUserId(userId);
        cart.setProductAmount(num);
        cart.setPrice(bookInfo.getPrice());
        orderCartService.insertOrderCart(cart);
        return AjaxResult.success();
    }

    @GetMapping("/getCart")
    public TableDataInfo getCart() {
        startPage();
        final Long userId = SecurityUtils.getUserId();
        final List<Cart> list = cartService.selectCartListByUserId(userId);
        return getDataTable(list);
    }

}
