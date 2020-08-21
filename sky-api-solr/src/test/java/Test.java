import com.swing.sky.solr.SolrApplication;
import com.swing.sky.solr.module.dao.QuestionDAO;
import com.swing.sky.solr.module.service.SolrQuestionService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest(classes = SolrApplication.class)
@RunWith(SpringRunner.class)
public class Test {

    @Resource
    private QuestionDAO questionDAO;

    @Autowired
    private SolrQuestionService solrQuestionService;

    @org.junit.Test
    public void name() {
        questionDAO.listAllQuestions().forEach(System.out::println);
    }

    @org.junit.Test
    public void name3() {
        solrQuestionService.updateQuestionSolrData();
    }

    @org.junit.Test
    public void name4() {
//        solrQuestionService.searchQuestion("电流", null, null, "ti_keywords", 0, 10).forEach(System.out::println);
    }
}
