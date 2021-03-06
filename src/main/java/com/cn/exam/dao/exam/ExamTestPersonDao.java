package com.cn.exam.dao.exam;

import com.cn.exam.entity.exam.ExamTestPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *@Author fengzhilong
 *@Date 2021/1/27 16:19
 *@Desc
 **/
@Repository
public interface ExamTestPersonDao extends JpaRepository<ExamTestPerson, Integer>, JpaSpecificationExecutor<ExamTestPerson> {

    /*根据计划id查询所有需要分配试卷的参考人员*/
    @Query("select ep from ExamTestPerson ep where ep.planId = :planId and (ep.paperId IS NULL or ep.paperId = '') order by ep.ghid asc ")
    List<ExamTestPerson> findByPlanIdAndPaperId(@Param("planId") String planId);
}
