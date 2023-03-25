package com.ah.book.service;


import com.ah.book.domain.LoginBody;

public interface ILogService {

    String loginByPassword(String name,String password);

    String loginByPhone(LoginBody loginBody);
}
