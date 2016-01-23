package group.haihong.com.stu.Utils.net;

/**
 * Created by lichanghong on 12/23/15.
 */
public class Response {
    private String errno;
    private String errmsg;
    private String time;
    private Object data;

    public Response(String errno, String errmsg, Object data) {
        this.errno = errno;
        this.errmsg = errmsg;
        this.time = String.valueOf(System.currentTimeMillis()/1000L);
        this.data = data;
    }

    public String getErrno() {
        return errno;
    }

    public void setErrno(String errno) {
        this.errno = errno;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Response{" +
                "errno='" + errno + '\'' +
                ", errmsg='" + errmsg + '\'' +
                ", time='" + time + '\'' +
                ", data=" + data +
                '}';
    }
}
