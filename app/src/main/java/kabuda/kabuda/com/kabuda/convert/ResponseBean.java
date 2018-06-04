package kabuda.kabuda.com.kabuda.convert;

/**
 * Created by YuanGang on 2018/5/4.
 */

public class ResponseBean {

    /**
     * data : eyJpZCI6IjEwOSIsIjAiOiIxMDkiLCJ1cmwiOiJ3d3cuYmFpZHUuY29tIiwiMSI6Ind3dy5iYWlkdS5jb20iLCJ0eXBlIjoiYW5kcm9pZCIsIjIiOiJhbmRyb2lkIiwic2hvd191cmwiOiIxIiwiMyI6IjEiLCJhcHBpZCI6ImNvbS5iaWxpYmlsaSIsIjQiOiJjb20uYmlsaWJpbGkiLCJyZXF1ZXN0X251bSI6IjgiLCI1IjoiOCIsIkltZ0xpc3QiOiIiLCI2IjoiIiwibWFycXVlZUNvbnRlbnQiOiIiLCI3IjoiIiwiY29tbWVudCI6Ij8/Pz8/PyIsIjgiOiI/Pz8/Pz8iLCJxcU51bWJlciI6IiIsIjkiOiIiLCJyZXNlcnZlMSI6IiIsIjEwIjoiIiwicmVzZXJ2ZTIiOiIiLCIxMSI6IiIsInJlc2VydmUzIjoiIiwiMTIiOiIiLCJjcmVhdGVBdCI6IjIwMTgtMDUtMDQgMDU6NDE6MjQiLCIxMyI6IjIwMTgtMDUtMDQgMDU6NDE6MjQiLCJ1cGRhdGVBdCI6IjIwMTgtMDUtMDQgMTg6NDM6NDgiLCIxNCI6IjIwMTgtMDUtMDQgMTg6NDM6NDgiLCJjcmVhdGVfdXNlcl9pZCI6IjM3IiwiMTUiOiIzNyJ9
     * rt_code : 200
     */

    private String data;
    private String rt_code;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getRt_code() {
        return rt_code;
    }

    public void setRt_code(String rt_code) {
        this.rt_code = rt_code;
    }
}
