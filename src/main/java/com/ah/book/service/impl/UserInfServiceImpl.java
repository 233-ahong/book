package com.ah.book.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.ah.book.mapper.UserInfMapper;
import com.ah.book.domain.UserInf;
import com.ah.book.service.IUserInfService;
import javax.annotation.Resource;

/**
 * 用户信息Service业务层处理
 *
 * @author ah
 * @date 2022-09-06
 */
@Service
public class UserInfServiceImpl implements IUserInfService
{
    @Resource
    private UserInfMapper userInfMapper;

    /**
     * 查询用户信息
     *
     * @param userId 用户信息主键
     * @return 用户信息
     */
    @Override
    public UserInf selectUserInfByUserId(Integer userId)
    {
        return userInfMapper.selectUserInfByUserId(userId);
    }

    /**
     * 查询用户信息列表
     *
     * @param userInf 用户信息
     * @return 用户信息
     */
    @Override
    public List<UserInf> selectUserInfList(UserInf userInf)
    {
        return userInfMapper.selectUserInfList(userInf);
    }

    /**
     * 新增用户信息
     *
     * @param userInf 用户信息
     * @return 结果
     */
    @Override
    public int insertUserInf(UserInf userInf)
    {
        return userInfMapper.insertUserInf(userInf);
    }

    /**
     * 修改用户信息
     *
     * @param userInf 用户信息
     * @return 结果
     */
    @Override
    public int updateUserInf(UserInf userInf)
    {
        return userInfMapper.updateUserInf(userInf);
    }

    /**
     * 批量删除用户信息
     *
     * @param userIds 需要删除的用户信息主键
     * @return 结果
     */
    @Override
    public int deleteUserInfByUserIds(Integer[] userIds)
    {
        return userInfMapper.deleteUserInfByUserIds(userIds);
    }

    /**
     * 删除用户信息信息
     *
     * @param userId 用户信息主键
     * @return 结果
     */
    @Override
    public int deleteUserInfByUserId(Integer userId)
    {
        return userInfMapper.deleteUserInfByUserId(userId);
    }

    @Override
    public UserInf selectUserInfByUserLoginName(String name) {
        return userInfMapper.selectUserInfByUserLoginName(name);
    }

    @Override
    public UserInf selectUserInfByPhone(String phone) {
        return userInfMapper.selectUserInfByPhone(phone);
    }
}
