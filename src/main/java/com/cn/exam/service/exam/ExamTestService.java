package com.cn.exam.service.exam;

import com.cn.common.exception.FzlException;
import com.cn.exam.dto.exam.PersonTestingDTO;
import com.cn.exam.dto.exam.TestInfoDTO;
import com.cn.exam.dto.exam.TestPersonDTO;
import com.cn.exam.vo.exam.EditTestPersonVO;
import com.cn.exam.vo.exam.TestPersonVO;
import org.springframework.validation.annotation.Validated;

/**
 *@Author fengzhilong
 *@Date 2021/3/12 10:35
 *@Desc
 **/
@Validated
public interface ExamTestService {

    /*获取参考人信息*/
    TestPersonDTO getTestPersonInfo(TestPersonVO testPersonVO) throws FzlException;

    /*编辑参考人*/
    void editTestPaperToPlan(EditTestPersonVO editTestPersonVO) throws FzlException;

    /*获取考试信息*/
    TestInfoDTO getTestInfo(String planId, String ghid) throws FzlException;

    /*提交试卷*/
    void submitPage(PersonTestingDTO personTestingDTO) throws FzlException;
}
