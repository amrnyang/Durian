import com.swing.sky.center.CenterApplication;
import com.swing.sky.center.module.dao.CenterDeptCourseLinkDAO;
import com.swing.sky.center.module.domain.CenterCourseDO;
import com.swing.sky.center.module.domain.CenterUserDO;
import com.swing.sky.center.module.service.CenterCourseService;
import com.swing.sky.center.module.service.CenterDeptService;
import com.swing.sky.center.module.service.CenterUserService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CenterApplication.class)
public class Test {
    @Autowired
    private CenterUserService centerUserService;

    @Autowired
    private CenterDeptService deptService;

    @Autowired
    private CenterCourseService courseService;

    @Resource
    private CenterDeptCourseLinkDAO deptCourseLinkDAO;

    @org.junit.Test
    public void test1() {
        CenterUserDO user = new CenterUserDO();
        user.setId(1L);
        user.setRemark("hello");
        System.out.println(centerUserService.update(user));
        System.out.println(centerUserService.getUserByUsername("swing").toString());
    }
}
