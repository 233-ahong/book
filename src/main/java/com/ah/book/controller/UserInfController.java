package com.ah.book.controller;

import java.util.List;
import javax.annotation.Resource;

import com.ah.book.common.core.controller.BaseController;
import com.ah.book.common.core.domain.AjaxResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ah.book.common.core.page.TableDataInfo;
import com.ah.book.domain.UserInf;
import com.ah.book.service.IUserInfService;

import javax.annotation.Resource;

/**
 * 用户信息Controller
 *
 * @author 陈祥
 * @date 2022-12-25
 */
@RestController
@RequestMapping("/system/inf")
public class UserInfController extends BaseController
{
    @Resource
    private IUserInfService userInfService;

    /**
     * 查询用户信息列表
     */
    @GetMapping("/list")
    public TableDataInfo list(UserInf userInf)
    {
        startPage();
        List<UserInf> list = userInfService.selectUserInfList(userInf);
        return getDataTable(list);
    }

    /**
     * 获取用户信息详细信息
     */
    @GetMapping(value = "/{userInfId}")
    public AjaxResult getInfo(@PathVariable("userInfId") Integer userInfId)
    {
        return AjaxResult.success(userInfService.selectUserInfByUserId(userInfId));
    }

    /**
     * 新增用户信息
     */
    @PostMapping
    public AjaxResult add(@RequestBody UserInf userInf)
    {
        return toAjax(userInfService.insertUserInf(userInf));
    }

    /**
     * 修改用户信息
     */
    @PutMapping
    public AjaxResult edit(@RequestBody UserInf userInf)
    {
        return toAjax(userInfService.updateUserInf(userInf));
    }

    /**
     * 删除用户信息
     */
    @DeleteMapping("/{userInfIds}")
    public AjaxResult remove(@PathVariable Integer[] userInfIds)
    {
        return toAjax(userInfService.deleteUserInfByUserIds(userInfIds));
    }
}
