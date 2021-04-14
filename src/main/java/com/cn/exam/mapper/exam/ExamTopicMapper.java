package com.cn.exam.mapper.exam;

import com.cn.exam.entity.exam.ExamTopic;
import com.cn.exam.vo.exam.ExamTopicVO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

/**
 *@Author fengzhilong
 *@Date 2021/3/4 10:20
 *@Desc
 **/
@Component
@Mapper
public interface ExamTopicMapper {

    ExamTopicMapper INSTANCE = Mappers.getMapper(ExamTopicMapper.class);

    ExamTopic toExamTopic(ExamTopicVO examTopicVO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateExamTopic(ExamTopicVO examTopicVO, @MappingTarget ExamTopic examTopic);
}
