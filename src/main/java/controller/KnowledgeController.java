package controller;

import model.Knowledge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.KnowledgeService;

import java.util.List;

/**
 * Created by TangJiong on 2015/12/28.
 * controller类
 */
@Controller
@RequestMapping("/knowledge")
public class KnowledgeController {

    @Autowired
    KnowledgeService knowledgeService;

    // 请求一门课程下的知识点
    @RequestMapping(path = "/course/{courseId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<Knowledge> getKnowledgeByCourse(@PathVariable int courseId){
        return knowledgeService.getKnowledgeByCourse(courseId);
    }

}
