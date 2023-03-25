package com.ah.book.mapper;

import com.ah.book.domain.BorrowView;

import java.util.List;

public interface BorrowViewMapper {

    List<BorrowView> selectBorrowViewList(BorrowView borrowView);

}
