package com.ah.book.mapper;

import com.ah.book.domain.Cart;

import java.util.List;

/**
 * @author 26471
 */
public interface CartMapper {
    List<Cart> selectCartListByUserId(Long userId);
}
