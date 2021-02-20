package com.cn.exam.service.exam;

import com.cn.exam.dto.exam.ExamProdPaperDTO;
import com.cn.exam.entity.exam.ExamTestPaper;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 *@Author fengzhilong
 *@Date 2021/1/27 15:14
 *@Desc 试卷模板
 **/
@Validated
public interface ExamTempService {

    /*生成试卷模板*/
    void prodThePaperTemp(ExamProdPaperDTO examProdPaperDTO);

    /*计划下试卷列表*/
    List<ExamTestPaper> testPaperList(String planId);

    /*设置试卷是否生效*/
    void updateIsUsed(String paperId, Integer isUsed);
}
