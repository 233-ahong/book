package com.ah.book.service.impl;

import com.ah.book.domain.BorrowView;
import com.ah.book.mapper.BorrowViewMapper;
import com.ah.book.service.IBorrowViewService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 26471
 */
@Service
public class BorrowViewServiceImpl implements IBorrowViewService {

    @Resource
    BorrowViewMapper borrowViewMapper;
    @Override
    public List<BorrowView> selectBorrowViewList(BorrowView borrowView) {
        return borrowViewMapper.selectBorrowViewList(borrowView);
    }
}
