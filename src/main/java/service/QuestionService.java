package service;

import dao.QuestionDao;
import model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by 3D-WorkStation on 2015/12/30.
 */
@Component
public class QuestionService {

    @Autowired
    private QuestionDao questionDao;

    public List<Question> getQuestionByKnowledge(int courseId, int kid){
        return questionDao.findByKnowledge(courseId, kid);
    }

    public List<Question> getQuestionByCourse(int courseId){
        return questionDao.findByCourse(courseId);
    }

}
