package com.cn.exam.mapper.exam;

import com.cn.exam.entity.exam.ExamTopic;
import com.cn.exam.vo.exam.ExamTopicVO;
import org.mapstruct.Mapper;
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

    /*@Mappings({
            @Mapping(source = "createTime", target = "createTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    })*/
    ExamTopic toExamTopic(ExamTopicVO examTopicVO);
}
