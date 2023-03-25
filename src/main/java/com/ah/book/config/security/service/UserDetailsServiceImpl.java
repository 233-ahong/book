package com.ah.book.config.security.service;

import com.ah.book.common.core.domain.model.LoginUser;
import com.ah.book.common.exception.ServiceException;
import com.ah.book.common.utils.StringUtils;
import com.ah.book.domain.UserInf;
import com.ah.book.service.IUserInfService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 用户验证处理
 *
 * @author ruoyi
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService
{
    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private IUserInfService userService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        UserInf user = userService.selectUserInfByUserLoginName(username);
        if (StringUtils.isNull(user))
        {
            log.info("登录用户：{} 不存在.", username);
            throw new ServiceException("登录用户：" + username + " 不存在");
        }
        else if ("0".equals(user.getUserStats()))
        {
            log.info("登录用户：{} 已被停用.", username);
            throw new ServiceException("对不起，您的账号：" + username + " 已停用");
        }

        return createLoginUser(user);
    }

    public UserDetails createLoginUser(UserInf user)
    {
        return new LoginUser(user.getUserInfId(), user);
    }
}
