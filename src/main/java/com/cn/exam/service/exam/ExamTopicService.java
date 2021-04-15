package com.cn.exam.service.exam;

import com.cn.common.exception.FzlException;
import com.cn.common.jpa.vo.JsonPage;
import com.cn.exam.dto.exam.ExamTopicDTO;
import com.cn.exam.dto.exam.SelfTrainingDTO;
import com.cn.exam.entity.exam.ExamTopic;
import com.cn.exam.vo.exam.ExamTopicVO;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 *@Author fengzhilong
 *@Date 2021/1/8 13:41
 *@Desc
 **/
@Validated
public interface ExamTopicService {

    /*题库列表*/
    Page<ExamTopic> listExamTopicPage(ExamTopicDTO examTopicDTO, JsonPage pageable);

    /*获取详情*/
    ExamTopic topicEditInfo(Integer id);

    /*保存和修改题目*/
    void saveExamTopic(ExamTopicVO examTopicVO);

    /*禁用或启用题目*/
    void removeOrRebootTopic(String topicId, Integer isDel);

    /*获取自主练习题目内容*/
    List<ExamTopic> getSelfExamData(SelfTrainingDTO selfTrainingDTO);
}
