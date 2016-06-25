package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.QuestionService;

/**
 * Created by TangJiong on 2015/12/30.
 * Controller class for question api
 */
@Controller
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @RequestMapping(path = "/c/{cid}/k/{kid}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String getQuestionByKnowledge(@PathVariable int cid, @PathVariable int kid){
        return questionService.getQuestionByKnowledge(cid, kid).toString();
    }

    @RequestMapping(path = "/c/{cid}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String getQuestionByCourse(@PathVariable int cid){
        return questionService.getQuestionByCourse(cid).toString();
    }

}
