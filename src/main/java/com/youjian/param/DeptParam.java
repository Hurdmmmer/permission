package com.youjian.param;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 部门参数对象
 * @author shen youjian
 * @date 2018/12/4 23:07
 */
@Data
public class DeptParam {

    private Integer id;

    private Integer parentId;
    @NotBlank(message = "部门名称不能为空")
    @Length(max = 15, min = 2, message = "部门名称字数在 2 - 15 之间")
    private String name;
    @NotNull(message = "展示顺序不能为空")
    private Integer seq;
    @Length(max = 150, message = "备注字数在 150 之内")
    private String remark;
}
