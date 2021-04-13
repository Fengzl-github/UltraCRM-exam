package com.cn.exam.service.exam;

import com.cn.common.jpa.vo.JsonPage;
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

    List<TestResultDTO> testResultPage(TestResultVO testResultVO, JsonPage pageable);
}
