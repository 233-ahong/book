package com.ah.book.service;

import com.ah.book.common.core.domain.AjaxResult;

public interface ICodeService {
     AjaxResult code(String phone);
     String getCode(String phone);
}
