package com.youjian.uitl;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.youjian.exception.ParamException;
import org.apache.commons.collections.MapUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.*;

/**
 * 参数校验 工具类
 *
 * @author shen youjian
 * @date 2018/12/3 23:35
 */
public class BeanValidator {
    private static ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

    /**
     *  根据入参实体类字段上的表示注解, 验证入参是否错误
     *  @return 返回 null 集合则表示没有错误
     */
    public static <T> Map<String, String> validate(T t, Class... groups) {
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<T>> validateResult = validator.validate(t, groups);
        if (validateResult.isEmpty()) {
            return Collections.emptyMap();
        } else {
            Iterator<ConstraintViolation<T>> iterator = validateResult.iterator();
            LinkedHashMap<String, String> map = Maps.newLinkedHashMap();
            while (iterator.hasNext()) {
                ConstraintViolation<T> violation = iterator.next();
                map.put(violation.getPropertyPath().toString(), violation.getMessage());
            }
            return map;
        }
    }

    @SuppressWarnings("unchecked")
    public static Map<String, String> validateList(Collection<?> collection) {
        Preconditions.checkNotNull(collection);
        Iterator<?> it = collection.iterator();
        Map<String, String > errors = null;
        do{
            if (!it.hasNext()) {
                return Collections.EMPTY_MAP;
            }
            errors = validate(it.next());

        }while (errors.isEmpty());

        return errors;
    }

    public static<T> void check(T param) throws ParamException {
        Map<String, String> result = validate(param);
        if (MapUtils.isNotEmpty(result)) {
            throw new ParamException(result.toString());
        }
    }
}
