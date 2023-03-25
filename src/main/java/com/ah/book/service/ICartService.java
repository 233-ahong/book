package com.ah.book.service;

import com.ah.book.domain.Cart;

import java.util.List;

public interface ICartService {

    List<Cart> selectCartListByUserId(Long userId);
}
