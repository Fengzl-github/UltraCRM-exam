package com.cn.exam.service.excel.down;

import org.springframework.validation.annotation.Validated;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

/**
 *@Author fengzhilong
 *@Date 2021/2/2 14:07
 *@Desc excel 下载
 **/
@Validated
public interface ExcelDownService {

    /*下载试题导入模板*/
    void downTopicTemp(OutputStream out);
}
