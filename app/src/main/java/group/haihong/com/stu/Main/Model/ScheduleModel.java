package group.haihong.com.stu.Main.Model;

/**
 * Created by lichanghong on 12/20/15.
 */
public class ScheduleModel {
    private int week;
    private String  amupDetail;
    private String  amdownDetail;
    private String  pmupDetail;
    private String  pmdownDetail;

    public ScheduleModel(int week, String amupDetail, String amdownDetail, String pmupDetail, String pmdownDetail)
    {
        this.week = week;
        this.amupDetail = amupDetail;
        this.amdownDetail = amdownDetail;
        this.pmupDetail = pmupDetail;
        this.pmdownDetail = pmdownDetail;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }


    public String getAmupDetail() {
        return amupDetail;
    }

    public void setAmupDetail(String amupDetail) {
        this.amupDetail = amupDetail;
    }

    public String getAmdownDetail() {
        return amdownDetail;
    }

    public void setAmdownDetail(String amdownDetail) {
        this.amdownDetail = amdownDetail;
    }

    public String getPmupDetail() {
        return pmupDetail;
    }

    public void setPmupDetail(String pmupDetail) {
        this.pmupDetail = pmupDetail;
    }

    public String getPmdownDetail() {
        return pmdownDetail;
    }

    public void setPmdownDetail(String pmdownDetail) {
        this.pmdownDetail = pmdownDetail;
    }

    @Override
    public String toString() {
        return "ScheduleModel{" +
                "week=" + week +
                ", amupDetail='" + amupDetail + '\'' +
                ", amdownDetail='" + amdownDetail + '\'' +
                ", pmupDetail='" + pmupDetail + '\'' +
                ", pmdownDetail='" + pmdownDetail + '\'' +
                '}';
    }
}
