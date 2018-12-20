package com.youjian.controller;

import com.youjian.exception.PermissionException;
import com.youjian.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author shen youjian
 * @date 2018/12/2 21:13
 */
@Controller
@Slf4j
public class BaseController {
    @RequestMapping("hello.json")
    @ResponseBody
    public BaseResponse hello() {
        log.info("hello");
//        double i = 1/0;
//        return BaseResponse.success("permission success");
        throw new PermissionException("test exception");
    }

}
