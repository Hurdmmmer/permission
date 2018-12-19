package com.youjian.response;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.executor.ReuseExecutor;
import org.mybatis.spring.mapper.MapperScannerConfigurer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shen youjian
 * @date 2018/12/3 21:31
 */
@Getter
@Setter
public class BaseResponse<T> {
    private boolean success;
    private String msg;
    private T data;

    public BaseResponse(T data){
        this.data = data;
        this.success = true;
    }
    public BaseResponse(String msg, boolean success) {
        this.msg = msg;
        this.success = success;
    }

    public BaseResponse(String msg, T data) {
        this.msg = msg;
        this.data = data;
        this.success = true;
    }

    public BaseResponse(boolean success) {
        this.success = success;
    }

    public static<T> BaseResponse success(T data) {
        return new BaseResponse<T>(data);
    }

    public static BaseResponse success(String msg) {
        return new BaseResponse(msg, true);
    }

    public static<T> BaseResponse error(String msg) {
        return new BaseResponse(msg, false);
    }

    public static <T> BaseResponse success(T data, String msg) {
        return new BaseResponse<T>( msg, data);
    }

    public static <T> BaseResponse success() {
        return new BaseResponse(true);
    }

    /**
     * 将对象转换为 map 对象
     * @return
     */
    public Map<String, Object> toMap() {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", success);
        result.put("data", data);
        result.put("msg", msg);
        return result;
    }
}
