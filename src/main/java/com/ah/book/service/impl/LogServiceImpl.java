package com.ah.book.service.impl;

import com.ah.book.common.constant.Constants;
import com.ah.book.common.core.domain.model.LoginUser;
import com.ah.book.common.core.redis.RedisCache;
import com.ah.book.common.exception.ServiceException;
import com.ah.book.config.security.service.TokenService;
import com.ah.book.domain.LoginBody;
import com.ah.book.domain.UserInf;
import com.ah.book.service.ILogService;
import com.ah.book.service.IUserInfService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LogServiceImpl implements ILogService {

    @Resource
    private TokenService tokenService;

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    RedisCache redisCache;

    @Resource
    IUserInfService userInfService;

    @Override
    public String loginByPassword(String name,String password) {
        Authentication authentication = null;
        try
        {
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(name, password));
        }catch (Exception e){
            throw new ServiceException(e.getMessage());
        }
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        return tokenService.createToken(loginUser);
    }

    @Override
    public String loginByPhone(LoginBody loginBody) {
        final String cacheObject =
                redisCache.getCacheObject(Constants.CAPTCHA_CODE_KEY + loginBody.getUuid() + loginBody.getUsername());
        if (cacheObject.equals(loginBody.getCode())) {
            UserInf userInf = userInfService.selectUserInfByPhone(loginBody.getUsername());
            LoginUser user = new LoginUser(userInf.getUserInfId(),userInf);
            return tokenService.createToken(user);
        }  else {
            throw new ServiceException("验证码错误");
        }
    }
}
