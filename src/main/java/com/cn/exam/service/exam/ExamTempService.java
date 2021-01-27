package com.cn.exam.service.exam;

import com.cn.exam.dto.exam.ExamProdPaperDTO;
import org.springframework.validation.annotation.Validated;

/**
 *@Author fengzhilong
 *@Date 2021/1/27 15:14
 *@Desc 试卷模板
 **/
@Validated
public interface ExamTempService {

    /*生成试卷模板*/
    void prodThePaperTemp(ExamProdPaperDTO examProdPaperDTO);
}
