package com.cn.exam.service.exam;

import com.cn.common.exception.FzlException;
import com.cn.common.vo.ResResult;
import com.cn.exam.dto.exam.ExamProdPaperDTO;
import com.cn.exam.entity.exam.ExamTestPaper;
import com.cn.exam.entity.exam.ExamTestPerson;
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

    /*试卷预览*/
    ResResult previewPaperData(String planId, String paperId);

    /*分配试卷*/
    void allotPaper(String planId) throws FzlException;

    /*参考人员列表*/
    List<ExamTestPerson> getTestPerson(String planId) throws FzlException;
}
