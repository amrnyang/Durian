import com.swing.sky.tiku.TiKuApplication;
import com.swing.sky.tiku.module.dao.TiAnswerDAO;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest(classes = TiKuApplication.class)
@RunWith(SpringRunner.class)
public class Test {
    @Resource
    private TiAnswerDAO answerDAO;

    @org.junit.Test
    public void name() {
        answerDAO.listByCondition(null, null, null).forEach(System.out::println);
    }
}
