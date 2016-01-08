package heshida.haihong.com.heshida.Hot;

/**
 * Created by lichanghong on 12/26/15.
 */
public class HotModel {
    private String lostid;
    private String title;
    private String foundtime;
    private String location;
    private String line;
    private String blocked;
    private String losted;

    public HotModel() {
    }

    @Override
    public String toString() {
        return "HotModel{" +
                "lostid='" + lostid + '\'' +
                ", title='" + title + '\'' +
                ", foundtime='" + foundtime + '\'' +
                ", location='" + location + '\'' +
                ", line='" + line + '\'' +
                ", blocked='" + blocked + '\'' +
                ", losted='" + losted + '\'' +
                '}';
    }

    public String getLostid() {
        return lostid;
    }

    public void setLostid(String lostid) {
        this.lostid = lostid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFoundtime() {
        return foundtime;
    }

    public void setFoundtime(String foundtime) {
        this.foundtime = foundtime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getBlocked() {
        return blocked;
    }

    public void setBlocked(String blocked) {
        this.blocked = blocked;
    }

    public String getLosted() {
        return losted;
    }

    public void setLosted(String losted) {
        this.losted = losted;
    }
}
