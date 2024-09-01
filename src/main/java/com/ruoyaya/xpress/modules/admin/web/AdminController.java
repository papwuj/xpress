package com.ruoyaya.xpress.modules.admin.web;

import com.ruoyaya.xpress.commons.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController {

    // @Log(operType = Log.OperatorType.PAGE, module = "首页", content = "首页")
    @RequestMapping("/")
    public String index() throws Exception {
        return "index";
    }

    /**
     * 添加登录页面
     */
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * 添加注册页面
     */
    @RequestMapping("/register")
    public String register() {
        return "register";
    }

    /**
     * 添加忘记密码页面
     */
    @RequestMapping("/forget")
    public String forget() {
        return "forget";
    }

    /**
     * 添加404页面
     */
    @RequestMapping("/404")
    public String error404() {
        return "404";
    }

    /**
     * 添加500页面
     */
    @RequestMapping("/500")
    public String error500() {
        return "500";
    }

    /**
     * 添加403页面
     */
    @RequestMapping("/403")
    public String error403() {
        return "403";
    }

    /**
     * 添加503页面
     */
    @RequestMapping("/503")
    public String error503() {
        return "503";
    }

    /**
     * 添加空白页面
     */
    @RequestMapping("/blank")
    public String blank() {
        return "blank";
    }

}
