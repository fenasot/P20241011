package com.systex.lottery.exception;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError() {
        // 在这里可以记录错误信息
        return "redirect:/"; // 重定向到首页
    }

    public String getErrorPath() {
        return "/error"; // 指定错误路径
    }
}