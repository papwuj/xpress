package com.ruoyaya.xpress.modules.home.web;

import com.ruoyaya.xpress.commons.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController extends BaseController {

    @RequestMapping("/")
    public String index() throws Exception {
        return "index";
    }

}
