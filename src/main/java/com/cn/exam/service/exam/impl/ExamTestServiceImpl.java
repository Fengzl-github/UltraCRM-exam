package com.cn.exam.service.exam.impl;

import com.cn.common.exception.FzlException;
import com.cn.common.jpa.util.JpaUtil;
import com.cn.common.utils.DateTime;
import com.cn.common.utils.MyString;
import com.cn.exam.dao.exam.ExamPlanDao;
import com.cn.exam.dao.exam.ExamTestPersonDao;
import com.cn.exam.dao.user.UserDao;
import com.cn.exam.dto.exam.TestPersonDTO;
import com.cn.exam.entity.exam.ExamPlan;
import com.cn.exam.entity.exam.ExamTestPerson;
import com.cn.exam.entity.user.User;
import com.cn.exam.service.exam.ExamTestService;
import com.cn.exam.vo.exam.EditTestPersonVO;
import com.cn.exam.vo.exam.TestPersonVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *@Author fengzhilong
 *@Date 2021/3/12 10:35
 *@Desc
 **/
@Service
public class ExamTestServiceImpl implements ExamTestService {

    @Resource
    private JpaUtil jpaUtil;
    @Resource
    private ExamPlanDao examPlanDao;
    @Resource
    private ExamTestPersonDao examTestPersonDao;
    @Resource
    private UserDao userDao;


    @Override
    public TestPersonDTO getTestPersonInfo(TestPersonVO testPersonVO) throws FzlException {

        //1.判断是否在考试时间段内
        ExamPlan examPlan = examPlanDao.findByPlanId(testPersonVO.getPlanId());
        Date start = DateTime.parse(examPlan.getTestStartTime(), "");
        Date end = DateTime.parse(examPlan.getTestEndTime(), "");
        Date date = new Date();
        if (date.before(start)) {
            throw new FzlException("考试尚未开始");
        }
        if (date.after(end)) {
            throw new FzlException("考试已结束");
        }
        // 2.判断该工号是否存在考试资格，是否已分配试卷
        ExamTestPerson examTestPerson = examTestPersonDao.findByPlanIdAndGhid(testPersonVO.getPlanId(), testPersonVO.getGhid());
        if (examTestPerson == null) {
            throw new FzlException("没有考试资格");
        }
        if (MyString.isEmpty(examTestPerson.getPaperId())) {
            throw new FzlException("没有试卷信息");
        }

        // 3.返回信息
        String hql = "";
        Map<String, Object> params = new HashMap<>();
        hql = "select new com.cn.exam.dto.exam.TestPersonDTO(etp.ep) from ExamTestPerson etp " +
                "left join ExamPlan ep on ep.planId = etp.planId " +
                "where 1=1 ";

        if (MyString.isNotEmpty(testPersonVO.getPlanId())) {
            hql += "and etp.planId = :planId ";
            params.put("planId", testPersonVO.getPlanId());
        }
        if (MyString.isNotEmpty(testPersonVO.getGhid())) {
            hql += "and etp.ghid = :ghid ";
            params.put("ghid", testPersonVO.getGhid());
        }

        List<TestPersonDTO> list = jpaUtil.list(hql, params, TestPersonDTO.class);
        TestPersonDTO testPersonDTO = list.get(0);

        return testPersonDTO;

    }

    /**
     * @Author fengzhilong
     * @Desc 编辑参考人员，移入/移出
     * @Date 2021/3/17 17:52
     * @param editTestPersonVO
     * @return void
     **/
    @Override
    public void editTestPaperToPlan(EditTestPersonVO editTestPersonVO) throws FzlException {

        //1. 获取要操作的ghid
        String selMuls = editTestPersonVO.getSelectMul();
        //2. 获取操作类型 1-添加 2-删除
        Integer flag = editTestPersonVO.getFlag();
        //3. 执行编辑
        String[] ghids = selMuls.split(",");
        for (String ghid : ghids) {
            if (flag == 1) {
                // 执行添加操作
                ExamPlan examPlan = examPlanDao.findByPlanId(editTestPersonVO.getPlanId());
                User user = userDao.findByGhid(ghid);

                ExamTestPerson examTestPerson = new ExamTestPerson();
                examTestPerson.setPlanId(examPlan.getPlanId());
                examTestPerson.setPlanName(examPlan.getPlanName());
                examTestPerson.setGhid(user.getGhid());
                examTestPerson.setName(user.getName());

                examTestPersonDao.saveAndFlush(examTestPerson);
            } else if (flag == 2) {
                // 执行删除操作
                examTestPersonDao.removePerson(editTestPersonVO.getPlanId(), ghid);
            }
        }


    }
}
