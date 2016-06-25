package service;

import dao.KnowledgeDao;
import model.Knowledge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by TangJiong on 2015/12/28.
 * Knowledge业务逻辑类
 * 同样使用@Component注解
 */
@Component
public class KnowledgeService {

    @Autowired
    // 这里使用@Autowired注解表示该对象实例通过spring注入
    private KnowledgeDao knowledgeDao;

    public List<Knowledge> getKnowledgeByCourse(int courseId){

        return knowledgeDao.findByCourseId(courseId);

    }

}
