package model;

/**
 * Created by TangJiong on 2015/12/30.
 * model class for question
 * 和其他model类不同，有一个比较复杂的json str字段
 */
public class Question {

    private int id = -1;
    private String content;
    private int courseId = -1;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    // 这里让输出的string是json格式的string
    @Override
    public String toString() {
        return "{"
                + "\"id\":" + id + ","
                + "\"courseId\":" + courseId + ","
                + "\"content\":" + content
                + "}";
    }
}
