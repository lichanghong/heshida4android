package group.haihong.com.stu.Groups;

/**
 * Created by lichanghong on 12/27/15.
 */
public class GroupModel {
    private String sid;
    private String groupname;
    private String description;

    public GroupModel(String sid, String groupname, String description) {
        this.sid = sid;
        this.groupname = groupname;
        this.description = description;
    }

    public GroupModel() {
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "GroupModel{" +
                "sid='" + sid + '\'' +
                ", groupname='" + groupname + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
