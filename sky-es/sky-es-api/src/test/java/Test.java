import com.swing.sky.center.module.service.CenterCourseService;
import com.swing.sky.center.module.service.CenterDeptCourseLinkService;
import com.swing.sky.es.api.EsApplication;
import com.swing.sky.es.framework.datasource.elasticsearch.EsClient;
import com.swing.sky.es.framework.datasource.elasticsearch.service.DocService;
import com.swing.sky.es.module.service.TiQuestionSearchService;
import com.swing.sky.tiku.module.service.TiQuestionService;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.sql.SQLException;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = EsApplication.class)
public class Test {
    @Autowired
    private CenterCourseService courseService;

    @Autowired
    private CenterDeptCourseLinkService deptCourseLinkService;

    @Autowired
    private TiQuestionService questionService;

    @Autowired
    private TiQuestionSearchService searchService;

    @Resource
    private EsClient esClient;

    @Resource
    private DocService tikuQuestionDocServiceImpl;

    @org.junit.Test
    public void test1() {
        RestHighLevelClient restClient = esClient.getRestClient();
        searchService.listAll().forEach(System.out::println);
    }

    @org.junit.Test
    public void test2() throws IOException, SQLException {
        tikuQuestionDocServiceImpl.updateDoc();
    }
}
