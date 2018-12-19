package com.youjian.common;

import com.youjian.exception.ParamException;
import com.youjian.exception.PermissionException;
import com.youjian.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *  全局异常的处理类, 需要时间接口 HandlerExceptionResolver
 * @author shen youjian
 * @date 2018/12/3 21:47
 */
@Slf4j
public class SpringExceptionResolver implements HandlerExceptionResolver {
    @SuppressWarnings("unchecked")
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        ModelAndView mv = null;
        StringBuffer url = httpServletRequest.getRequestURL();
        // 我们项目中要请求 json 数据的必须以 .json 结尾
        String defaultMsg = "System error";
        if (url.toString().endsWith(".json")) {
            if (e instanceof PermissionException || e instanceof ParamException) {
                BaseResponse error = BaseResponse.error(e.getMessage());
                // 这里的 jsonView 和 springmvc 中配置 jsonView Bean 对象解析为 JSON 格式数据返回
                mv = new ModelAndView("jsonView", error.toMap());
            } else {
                log.error("Unknown json exception, url: "+url.toString(), e);
                BaseResponse error = BaseResponse.error(defaultMsg);
                // 这里的 jsonView 和 springmvc 中配置 jsonView Bean 对象解析为 JSON 格式数据返回
                mv = new ModelAndView("jsonView", error.toMap());
            }
            // 项目中访问页面的请求, 必须以 .page 结尾
        } else if (url.toString().endsWith(".page")) {
            log.error("Unknown page exception, url: "+url.toString(), e);
            BaseResponse error = BaseResponse.error(e.getMessage());
            mv = new ModelAndView("exception", error.toMap());
            // 这里需要在配置页面 exception.jsp 用于返回页面
        } else {
            log.error("Unknown exception, url: "+url.toString(), e);
            // 默认错误返回 json 格式的错误信息
            BaseResponse error = BaseResponse.error(defaultMsg);
            mv = new ModelAndView("jsonView", error.toMap());

        }


        return mv;
    }
}
