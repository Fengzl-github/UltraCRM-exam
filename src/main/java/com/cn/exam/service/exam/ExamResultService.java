package com.cn.exam.service.exam;

import com.cn.common.jpa.vo.JsonPage;
import com.cn.exam.dto.exam.SubmitScoringDTO;
import com.cn.exam.dto.exam.TestResultDTO;
import com.cn.exam.vo.exam.TestResultVO;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 *@Author fengzhilong
 *@Date 2021/4/13 16:55
 *@Desc
 **/
@Validated
public interface ExamResultService {

    /*考试结果  阅卷取试题，查看成绩共用*/
    List<TestResultDTO> testResultPage(TestResultVO testResultVO, JsonPage pageable);

    /*提交阅卷结果*/
    void submitScoring(SubmitScoringDTO submitScoringDTO);
}
