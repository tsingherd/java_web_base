package dao;

import model.Question;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by TangJiong on 2015/12/30.
 * Dao class for question
 *
 * ！！！！ ==== 注意 ==== ！！！
 * 因为在数据库中对问题表进行了分表处理
 * 即每门课程的问题单独放一张表
 * 表名：q_course_ + courseId
 * 所以这里的dao方法的使用跟接口定义的规范有不同
 * 每个dao方法都必须想办法传入courseId来确定要操作的是哪个表
 *
 */

@Component
public class QuestionDao extends DaoBase implements DaoInterface<Question> {

    public int add(final Question obj) {

        String tableName = "q_course_"+obj.getCourseId();

        final String sql = "INSERT INTO " + tableName + "(course_id,question) VALUES (?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        getJdbcTemplate().update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
                ps.setInt(1,obj.getCourseId());
                ps.setObject(2, obj.getContent());
                return ps;
            }
        }, keyHolder);

        return (Integer)keyHolder.getKey();
    }

    public void delete(final Question obj) {

        String tableName = "q_course_"+obj.getCourseId();

        String sql = "DELETE FROM " + tableName + " WHERE id = ?";

        getJdbcTemplate().update(sql, obj.getId());
    }

    public void update(final Question obj) {

        //TODO

    }

    public Question findById(Object id) {

        Question obj = (Question) id;

        String tableName = "q_course_"+obj.getCourseId();

        String sql = "SELECT * FROM " + tableName + " WHERE id = ?";

        return getJdbcTemplate().queryForObject(sql, new Object[]{obj.getId()}, new QuestionMapper());
    }

    public List<Question> findAll() {
        return null;
    }

    public List<Question> findByCourse(int courseId){

        String tableName = "q_course_"+courseId;

        String sql = "SELECT * FROM " + tableName;

        return getJdbcTemplate().query(sql, new QuestionMapper());
    }

    public List<Question> findByKnowledge(int courseId, int knowledgeId){

        String tableName = "q_course_"+courseId;

        String sql = "SELECT * FROM " + tableName + " WHERE question -> 'knowledge' @> '[{\"id\":" +knowledgeId+ "}]'";

        return getJdbcTemplate().query(sql, new QuestionMapper());
    }

    public List<Question> findByPage(int pageSize, int currentPage) {
        return null;
    }

    /**
     * 结果集到对象的映射类
     */
    private final class QuestionMapper implements RowMapper<Question> {

        @Override
        public Question mapRow(ResultSet resultSet, int i) throws SQLException {

            Question question = new Question();
            question.setId(resultSet.getInt("id"));
            question.setContent(resultSet.getString("question"));
            question.setCourseId(resultSet.getInt("course_id"));

            return question;
        }
    }

}
