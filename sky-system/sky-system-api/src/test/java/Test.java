import com.swing.sky.center.module.service.CenterCourseService;
import com.swing.sky.center.module.service.CenterDeptCourseLinkService;
import com.swing.sky.system.Sky;
import com.swing.sky.system.module.service.SysNoticeService;
import com.swing.sky.tiku.module.domain.TiQuestionDO;
import com.swing.sky.tiku.module.domain.TiQuestionSubmitDO;
import com.swing.sky.tiku.module.service.TiQuestionService;
import com.swing.sky.tiku.module.service.TiQuestionSubmitService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Sky.class)
public class Test {
    @Autowired
    private CenterCourseService courseService;

    @Autowired
    private SysNoticeService noticeService;

    @Autowired
    private CenterDeptCourseLinkService deptCourseLinkService;

    @Autowired
    private TiQuestionService questionService;

    @Autowired
    private TiQuestionSubmitService submitService;

    @org.junit.Test
    public void test1() {
        deptCourseLinkService.listTwoByOneId(103L).forEach(System.out::println);
        System.out.println(questionService.getById(105L).toString());
    }

    @org.junit.Test
    public void init() throws SQLException {
//        Connection connection = SqliteUtils.getConnection("D:\\code\\python\\百度题库\\tiku.db");
//        ResultSet resultSet = SqliteUtils.executeQuery(connection, "select * from question");
//        while (resultSet.next()) {
//            String content = resultSet.getString(2);
//            TiQuestionDO questionDO = new TiQuestionDO();
//            questionDO.setContent(content);
//            questionDO.setFullContent(content);
//            questionDO.setUse(true);
//            questionDO.setCreateBy("swing");
//            questionDO.setUpdateBy("swing");
//            questionDO.setCreateTime(new Date());
//            questionDO.setUpdateTime(new Date());
//            questionDO.setCreatorId(0L);
//            questionDO.setQuestionType("B");
//            questionDO.setCourseId(106L);
//            questionDO.setReviewPoint("测试用题");
//            questionDO.setRemark("测试用题");
//            questionDO.setAuditStatus("B");
//            questionDO.setOrderNum(1);
//            questionService.insert(questionDO);
//        }
    }

    @org.junit.Test
    public void test3() {
        TiQuestionSubmitDO submit = new TiQuestionSubmitDO();
        submit.setSubmitStatus("A");
        submitService.listByCondition(submit, null, null).forEach(System.out::println);
    }
}
