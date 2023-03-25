package com.ah.book.service.impl;

import com.ah.book.domain.Cart;
import com.ah.book.mapper.CartMapper;
import com.ah.book.service.ICartService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 26471
 */
@Service
public class CartServiceImpl implements ICartService {

    @Resource
    CartMapper cartMapper;

    @Override
    public List<Cart> selectCartListByUserId(Long userId) {
        return cartMapper.selectCartListByUserId(userId);
    }
}
