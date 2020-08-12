import com.swing.sky.tiku.TiKuApplication;
import com.swing.sky.tiku.framework.solr.dao.QuestionDAO;
import com.swing.sky.tiku.framework.solr.service.SolrService;
import com.swing.sky.tiku.module.service.TiAnswerService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest(classes = TiKuApplication.class)
@RunWith(SpringRunner.class)
public class Test {
    @Autowired
    private TiAnswerService answerService;

    @Resource
    private QuestionDAO questionDAO;

    @Autowired
    private SolrService solrService;

    @org.junit.Test
    public void name() {
        questionDAO.listAllQuestions().forEach(System.out::println);
    }

    @org.junit.Test
    public void name2() {
        answerService.listByCondition(null, null, null).forEach(System.out::println);
    }

    @org.junit.Test
    public void name3() {
        solrService.updateQuestionSolrData();
    }

    @org.junit.Test
    public void name4() {
        solrService.searchQuestion("飞行器水平", "ti_keywords", 0, 10).forEach(System.out::println);
    }
}
