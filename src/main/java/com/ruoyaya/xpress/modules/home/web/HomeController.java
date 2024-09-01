package com.ruoyaya.xpress.modules.home.web;

import com.ruoyaya.xpress.commons.base.BaseController;
import com.ruoyaya.xpress.modules.home.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController extends BaseController {

    @Autowired
    HomeService homeService;

    @RequestMapping("/")
    public String index(Model model) throws Exception {

        String testMsg = homeService.testDB();
        model.addAttribute("message", testMsg);

        return "index";
    }

}
