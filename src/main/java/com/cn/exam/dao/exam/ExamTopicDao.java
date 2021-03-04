package com.cn.exam.dao.exam;

import com.cn.exam.entity.exam.ExamTopic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *@Author fengzhilong
 *@Date 2021/1/8 13:40
 *@Desc
 **/
@Repository
public interface ExamTopicDao extends JpaRepository<ExamTopic, Integer>, JpaSpecificationExecutor<ExamTopic> {


    ExamTopic findByTopicId(String topicId);

    @Transactional
    @Modifying
    @Query("update ExamTopic et set et.isDel = :isDel where et.topicId = :topicId")
    void removeOrRebootTopic(@Param("topicId") String topicId, @Param("isDel") Integer isDel);
}
