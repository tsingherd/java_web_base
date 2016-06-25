package test;

import dao.KnowledgeDao;
import dao.QuestionDao;
import model.Knowledge;
import model.Question;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by TangJiong on 2015/12/16.
 * 测试类，使用了junit单元测试
 */
public class TestClass {


    private ApplicationContext context;

    public TestClass(){
        // 初始化Spring Context
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    @Test
    public void testAddQuestion(){
        String qJsonStr = "{\n" +
                "    \"title\": \"scanf函数被称为______输入函数。\",\n" +
                "    \"option\": [\n" +
                "      {\n" +
                "        \"id\": 1,\n" +
                "        \"content\": \"字符\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": 2,\n" +
                "        \"content\": \"整数\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": 3,\n" +
                "        \"content\": \"格式\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": 4,\n" +
                "        \"content\": \"浮点\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"type\": 1,\n" +
                "    \"answer\": 3,\n" +
                "    \"difficulty\": 1,\n" +
                "    \"checkpoint\": [\"基础\",\"概念\"],\n" +
                "    \"applicability\": [\"练习\",\"测验\"],\n" +
                "    \"knowledge\": [\n" +
                "      {\n" +
                "        \"id\": 47,\n" +
                "        \"name\": \"常用的数据输入/输出函数\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"from\": \"system administrator\",\n" +
                "    \"time\": \"2015-12-30\"\n" +
                "  }";

        QuestionDao questionDao = (QuestionDao) context.getBean("questionDao");
        Question question = new Question();
        question.setCourseId(1);
        question.setContent(qJsonStr);
        int id = questionDao.add(question);

        System.out.println("add new question " + id + " successfully!");
    }

    @Test
    public void testFindQuestionByKnow(){
        QuestionDao questionDao = (QuestionDao) context.getBean("questionDao");
        System.out.println(questionDao.findByKnowledge(1,47));
    }

    @Test
    public void testAddKnowledge(){

        KnowledgeDao knowledgeDao = (KnowledgeDao) context.getBean("knowledgeDao");

        Knowledge knowledge = new Knowledge();

        knowledge.setName("字符数据输入/输出");
        knowledge.setEnName("char I/O");
        knowledge.setLevel(2);
        knowledge.setCourseId(1);
        knowledge.setParentId(47);

        int id = knowledgeDao.add(knowledge);

        System.out.println("add new knowledge " + id + " successfully!");

    }

    @Test
    public void testUpdateKnowledge(){

        KnowledgeDao knowledgeDao = (KnowledgeDao) context.getBean("knowledgeDao");

        Knowledge knowledge = new Knowledge();

        knowledge.setId(5);
        knowledge.setEnName("constant");

        knowledgeDao.update(knowledge);

    }

    @Test
    public void testFindKnowledgeByCourse(){

        KnowledgeDao knowledgeDao = (KnowledgeDao) context.getBean("knowledgeDao");

        System.out.println(knowledgeDao.findByCourseId(1));

    }

    @Test
    public void testDeleteKnowledge(){

        KnowledgeDao knowledgeDao = (KnowledgeDao) context.getBean("knowledgeDao");

        Knowledge knowledge = new Knowledge();

        knowledge.setId(45);

        knowledgeDao.delete(knowledge);

    }

}
