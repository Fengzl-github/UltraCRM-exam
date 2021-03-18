package com.cn.exam.vo.exam;

import com.cn.exam.dto.login.UserDTO;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 *@Author fengzhilong
 *@Date 2021/3/17 17:44
 *@Desc
 **/
@Data
public class EditTestPersonVO {

    /*选择的ghid*/
    private List<UserDTO> selectMul;
    @NotBlank(message = "缺少计划id")
    private String planId;
    /*操作标记 1-添加 2-删除*/
    @NotNull(message = "缺少参数")
    @Min(value = 1,message = "参数不对")
    @Max(value = 2,message = "参数不对")
    private Integer flag;
}
