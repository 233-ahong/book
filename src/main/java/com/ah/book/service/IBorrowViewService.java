package com.ah.book.service;

import com.ah.book.domain.BorrowView;

import java.util.List;

public interface IBorrowViewService {

    List<BorrowView> selectBorrowViewList(BorrowView borrowView);
}
