package dao;

import model.Knowledge;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by TangJiong
 * on 2015/12/20.
 * 知识点的数据库操作类
 * 这里添加 @Component 注解表示将该类放入Spring容器
 * 在使用的地方可以通过添加 @Autowired 让Spring注入
 */
@Component
public class KnowledgeDao extends DaoBase implements DaoInterface<Knowledge> {

    /**
     * 新增知识点
     * @param obj Knowledge
     * @return id
     */
    public int add(final Knowledge obj) {

        final String sql = "INSERT INTO knowledge(name,en_name,course_id,level,parent_id) VALUES (?,?,?,?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        getJdbcTemplate().update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
                ps.setString(1, obj.getName());
                ps.setString(2, obj.getEnName());
                ps.setInt(3, obj.getCourseId());
                ps.setInt(4, obj.getLevel());
                ps.setInt(5, obj.getParentId());
                return ps;
            }
        }, keyHolder);

        return (Integer)keyHolder.getKey();

    }

    /**
     * 删除知识点需要级联删除其子知识点
     * @param obj knowledge，传入对象
     */
    public void delete(final Knowledge obj) {

        int id = obj.getId();

        //删除该知识点
        String sql = "DELETE FROM knowledge WHERE id = ?";
        getJdbcTemplate().update(sql, id);

        // 递归删除子知识点
        List<Knowledge> children = this.findDirectChild(id);
        for(Knowledge knowledge : children){
            this.delete(knowledge);
        }

    }

    /**
     * 更新知识点
     * 这里参数传入一个对象，主要是借鉴ORM框架的方式
     * 更新只需要set需要更新的字段就好
     * @param obj Knowledge
     */
    public void update(final Knowledge obj) {

        String sql = "UPDATE knowledge SET ";
        if(obj.getName() != null){
            sql += "name = '" + obj.getName() + "', ";
        }
        if(obj.getEnName() != null){
            sql += "en_name = '" + obj.getEnName() + "', ";
        }
        if(obj.getCourseId() != -1){
            sql += "course_id = " + obj.getCourseId() + ", ";
        }
        if(obj.getLevel() != -1){
            sql += "level = " + obj.getLevel() + ", ";
        }
        if(obj.getParentId() != -1){
            sql += "parent_id = " + obj.getParentId() + ", ";
        }
        // 去掉set子句最后一个多余的逗号
        sql = sql.substring(0, sql.lastIndexOf(","));
        sql += " WHERE id = " + obj.getId();

        getJdbcTemplate().update(sql);

    }

    /**
     * 通过id查找记录
     * @param id 主键id
     * @return Knowledge对象或null
     */
    public Knowledge findById(Object id) {

        String sql = "SELECT * FROM knowledge WHERE id = ?";

        return getJdbcTemplate().queryForObject(sql, new Object[]{id}, new KnowledgeMapper());

    }

    /**
     * 查询全部知识点
     * @return List<Knowledge>
     */
    public List<Knowledge> findAll() {

        String sql = "SELECT * FROM knowledge";

        return getJdbcTemplate().query(sql, new KnowledgeMapper());
    }

    /**
     * 查询一门课程下的全部知识点
     * @param courseId 课程id
     * @return List<Knowledge>
     */
    public List<Knowledge> findByCourseId(int courseId){

        String sql = "SELECT * FROM knowledge WHERE course_id = ?";

        return getJdbcTemplate().query(sql, new Object[]{courseId}, new KnowledgeMapper());
    }

    /**
     * 分页查询的方法
     * 暂未实现
     * @param pageSize 每页大小
     * @param currentPage 当前页
     * @return List<Knowledge>
     */
    public List<Knowledge> findByPage(int pageSize, int currentPage) {
        return null;
    }

    /**
     * 查找一个知识点下直系子知识点
     * 级联删除会用到
     * @param id id
     * @return List<Knowledge>
     */
    private List<Knowledge> findDirectChild(int id){

        String sql = "SELECT * FROM knowledge WHERE parent_id = ?";

        return getJdbcTemplate().query(sql, new Object[]{id}, new KnowledgeMapper());

    }

    /**
     * 结果集到对象的映射类
     */
    private final class KnowledgeMapper implements RowMapper<Knowledge>{

        public Knowledge mapRow(ResultSet resultSet, int i) throws SQLException {
            Knowledge knowledge = new Knowledge();
            knowledge.setId(resultSet.getInt("id"));
            knowledge.setCourseId(resultSet.getInt("course_id"));
            knowledge.setEnName(resultSet.getString("en_name"));
            knowledge.setName(resultSet.getString("name"));
            knowledge.setLevel(resultSet.getInt("level"));
            knowledge.setParentId(resultSet.getInt("parent_id"));
            return knowledge;
        }
    }

}
