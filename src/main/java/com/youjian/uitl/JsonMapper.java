package com.youjian.uitl;

import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.ser.impl.SimpleFilterProvider;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;

/**
 *  Json 转换工具类
 * @author shen youjian
 * @date 2018/12/4 21:55
 */
@Slf4j
public class JsonMapper {
    private static ObjectMapper objectMapper = new ObjectMapper();
    static {
        // 设置关闭未知字段
        objectMapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.setFilters(new SimpleFilterProvider().setFailOnUnknownId(false));
        // 设置忽略空字段转换
        objectMapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_EMPTY);

    }

    /**
     *  对象转换为 JSON 字符串
     */
    public static <T> String obj2String (T src) {
        if (src == null) {
            return  null;
        }

        try {
            return src instanceof String ? (String) src : objectMapper.writeValueAsString(src);
        } catch (IOException e) {
            log.warn("object to json exception, error:{}", e.getMessage(), e);
        }
        return null;
    }

    /**
     *  JSON 字符串转换对象
     */
    @SuppressWarnings("unchecked")
    public static <T> T json2Obj(String src, TypeReference<T> type) {
        if (src == null || type == null) {
            return null;
        }
        try {

        return (T) (type.getType().equals(String.class) ? src : objectMapper.readValue(src, type));
        } catch (Exception e) {
            log.warn("json to object exception, String:{}, Type:{}, error:{}", src, type.getType(),e.getMessage(), e);
            return null;
        }
    }
}
