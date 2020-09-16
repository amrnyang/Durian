import com.swing.sky.center.module.dao.CenterDeptCourseLinkDAO;
import com.swing.sky.center.module.domain.CenterUserDO;
import com.swing.sky.center.module.service.CenterCourseService;
import com.swing.sky.center.module.service.CenterDeptService;
import com.swing.sky.center.module.service.CenterUserService;
import com.swing.sky.tiku.TiKuApplication;
import com.swing.sky.tiku.module.service.TiQuestionService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TiKuApplication.class)
public class Test {
    @Autowired
    private TiQuestionService questionService;

    @org.junit.Test
    public void test1() {
        System.out.println(questionService.getById(127L).toString());
    }
}
