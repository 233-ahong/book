package com.ah.book.controller;

import com.ah.book.common.core.domain.AjaxResult;
import com.ah.book.domain.SysMenu;
import com.ah.book.common.utils.SecurityUtils;
import com.ah.book.domain.LoginBody;
import com.ah.book.domain.UserInf;
import com.ah.book.service.ICodeService;
import com.ah.book.service.ILogService;
import com.ah.book.service.ISysMenuService;
import com.ah.book.service.IUserInfService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class LogController {

    @Resource
    private ISysMenuService menuService;

    @Resource
    ILogService logService;

    @Resource
    IUserInfService userInfService;

    @Resource
    ICodeService codeService;

    @PostMapping("/loginByPassword")
    public AjaxResult loginByPassword(@RequestBody LoginBody loginBody) {
        final String login = logService.loginByPassword(loginBody.getUsername(), loginBody.getPassword());
        return AjaxResult.success(login);
    }
    @PostMapping("/loginByPhone")
    public AjaxResult loginByPhone(@RequestBody LoginBody loginBody) {
        final String login = logService.loginByPhone(loginBody);
        return AjaxResult.success(login);
    }

    @GetMapping("/code/{phone}")
    public AjaxResult getCode(@PathVariable("phone") String phone){
        try {
            UserInf userInf = userInfService.selectUserInfByPhone(phone);
            if (userInf==null) {
                return AjaxResult.error("当前号码未注册!");
            } else {
                String uuid = codeService.getCode(phone);
                 return codeService.code(phone);
                // return AjaxResult.success(uuid);
            }
        } catch (Exception e) {
            return AjaxResult.error("当前号码未注册");
        }
    }

    @GetMapping("getRouters")
    public AjaxResult getRouters()
    {
        String  userId = SecurityUtils.getLoginUser().getUser().getUserRole();
        if (!userId.equals("admin")){
            List<SysMenu> menus = menuService.selectMenuTreeByUserId("admin");
            return AjaxResult.success(menuService.buildMenus(menus));
        } else {
            List<SysMenu> menus = menuService.selectMenuTreeByAdmin();
            return AjaxResult.success(menuService.buildMenus(menus));
        }
    }

    @GetMapping("/getInfo")
    public AjaxResult getInfo() {
        final UserInf user = SecurityUtils.getLoginUser().getUser();
        Set<String> roles = new HashSet<String>();
        roles.add("admin");
        Set<String> permissions =new HashSet<String >();
        permissions.add("*.*.*");
        AjaxResult ajaxResult =new AjaxResult();
        ajaxResult.put("user",user);
        ajaxResult.put("roles",roles);
        ajaxResult.put("permissions",permissions);
        return ajaxResult;
    }

    @PostMapping("/register")
    public AjaxResult register(@RequestBody UserInf userInf) {
        final UserInf userInf1 = userInfService.selectUserInfByUserLoginName(userInf.getUserName());
        if (userInf1!=null) {
            return AjaxResult.error("当前账号已被占用");
        }
        final UserInf userInf2 = userInfService.selectUserInfByPhone(userInf.getMobilePhone());
        if (userInf2!=null) {
            return AjaxResult.error("当前手机号已被使用");
        }
        UserInf u = new UserInf();
        u.setNickName(userInf.getNickName());
        final List<UserInf> userInfs = userInfService.selectUserInfList(u);
        if (userInfs.size()!=0) {
            return AjaxResult.error("当前昵称已被占用");
        }
        userInf.setPassword(SecurityUtils.encryptPassword(userInf.getPassword()));
        return AjaxResult.success(userInfService.insertUserInf(userInf));
    }

}
