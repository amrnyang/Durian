import com.swing.sky.tiku.TiKuApplication;
import com.swing.sky.tiku.module.service.TiAnswerService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = TiKuApplication.class)
@RunWith(SpringRunner.class)
public class Test {
    @Autowired
    private TiAnswerService answerService;

    @org.junit.Test
    public void name() {
        answerService.listByCondition(null, null, null).forEach(System.out::println);
    }
}
