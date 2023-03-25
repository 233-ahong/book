package com.ah.book.mapper;

import java.util.List;
import com.ah.book.domain.UserInf;

/**
 * 用户信息Mapper接口
 *
 * @author ah
 * @date 2022-09-06
 */
public interface UserInfMapper
{
    /**
     * 查询用户信息
     *
     * @param userInfId 用户信息主键
     * @return 用户信息
     */
    public UserInf selectUserInfByUserId(Integer userInfId);

    /**
     * 查询用户信息列表
     *
     * @param userInf 用户信息
     * @return 用户信息集合
     */
    public List<UserInf> selectUserInfList(UserInf userInf);

    /**
     * 新增用户信息
     *
     * @param userInf 用户信息
     * @return 结果
     */
    public int insertUserInf(UserInf userInf);

    /**
     * 修改用户信息
     *
     * @param userInf 用户信息
     * @return 结果
     */
    public int updateUserInf(UserInf userInf);

    /**
     * 删除用户信息
     *
     * @param userId 用户信息主键
     * @return 结果
     */
    public int deleteUserInfByUserId(Integer userId);

    /**
     * 批量删除用户信息
     *
     * @param userIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUserInfByUserIds(Integer[] userIds);

    UserInf selectUserInfByUserLoginName(String name);

    UserInf selectUserInfByPhone(String phone);
}

