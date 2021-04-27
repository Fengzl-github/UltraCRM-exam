package com.cn.exam.dao.exam;

import com.cn.exam.dto.exam.TestInfoDTO;
import com.cn.exam.entity.exam.ExamTestPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

    /*根据计划id查询和工号查询考生信息*/
    @Query("select ep from ExamTestPerson ep where ep.planId = :planId and ep.ghid = :ghid")
    ExamTestPerson findByPlanIdAndGhid(@Param("planId") String planId, @Param("ghid") String ghid);

    /*参考人列表*/
    @Query("select ep from ExamTestPerson ep where ep.planId = :planId order by ep.ghid asc ")
    List<ExamTestPerson> findbyPlanId(@Param("planId") String planId);

    /*阅卷默认取五个人的试题*/
    @Query(value = "select ghid from t_exam_test_person ep where ep.plan_id = :planId order by ep.ghid asc limit 5", nativeQuery = true)
    List<String> findbyPlanIdLimit5(@Param("planId") String planId);

    /*删除参考人*/
    @Transactional
    @Modifying
    @Query("delete from ExamTestPerson ep where ep.planId = :planId and ep.ghid = :ghid")
    void removePerson(@Param("planId") String planId, @Param("ghid") String ghid);


    /**
     * @Author fengzhilong
     * @Desc  获取考试信息
     * @Date 2021/3/30 10:52
     * @param planId
	 * @param ghid
     * @return com.cn.exam.dto.exam.TestInfoDTO
     **/
    @Query("select new com.cn.exam.dto.exam.TestInfoDTO(ps,ep,tp) from ExamTestPerson ps " +
            "left join ExamPlan ep on ep.planId = ps.planId and ep.isDel = 0 " +
            "left join ExamTestPaper tp on tp.paperId = ps.paperId and tp.isDel = 0 " +
            "where 1=1 " +
            "and ps.planId = :planId and ps.ghid = :ghid ")
    TestInfoDTO getTestInfo(@Param("planId") String planId, @Param("ghid") String ghid);

    /**
     * @Author fengzhilong
     * @Desc  改变是否阅卷中状态
     * @Date 2021/4/14 10:18
     * @param id 主键
	 * @param isScoring 0-待阅 1-阅卷中
     * @return void
     **/
    @Transactional
    @Modifying
    @Query("update ExamTestPerson ep set ep.isScoring = :isScoring where ep.id = :id")
    void updateIsScoring(@Param("id") Integer id, @Param("isScoring") Integer isScoring);
}
