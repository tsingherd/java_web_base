package model;

/**
 * Created by TangJiong
 * 2015/12/20.
 */
public class Knowledge {

    private int id;
    private String name;
    private String enName;
    // 这里设置默认值-1，方便判断创建对象时是否set过这些字段
    private int courseId = -1;
    private int level = -1;
    private int parentId = -1;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "Knowledge{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", enName='" + enName + '\'' +
                ", courseId=" + courseId +
                ", level=" + level +
                ", parentId=" + parentId +
                '}';
    }
}
