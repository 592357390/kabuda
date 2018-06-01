package kabuda.kabuda.com.kabuda.bean;

/**
 * Created by YuanGang on 2018/6/1.
 */

public class UserBean {

    /**
     * user_password : $2a$10$KbO9hOOSYIEFpKDO8/o2PewPCecJZCksp5JlGen45lJJ4FI93KyE.
     * create_time : 1527824457313
     * user_nickname : 测试账号
     * user_phone :
     * user_account : test
     * id : 1
     * app_id : 3
     */

    private String user_password;
    private long create_time;
    private String user_nickname;
    private String user_phone;
    private String user_account;
    private int id;
    private int app_id;

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(long create_time) {
        this.create_time = create_time;
    }

    public String getUser_nickname() {
        return user_nickname;
    }

    public void setUser_nickname(String user_nickname) {
        this.user_nickname = user_nickname;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_account() {
        return user_account;
    }

    public void setUser_account(String user_account) {
        this.user_account = user_account;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getApp_id() {
        return app_id;
    }

    public void setApp_id(int app_id) {
        this.app_id = app_id;
    }
}
