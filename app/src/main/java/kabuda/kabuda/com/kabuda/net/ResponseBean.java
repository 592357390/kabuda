package kabuda.kabuda.com.kabuda.net;

/**
 * Created by YuanGang on 2018/3/19.
 */

/**
 * 包装Json响应的Bean
 * Created by guofe on 2015/9/29.
 */
public class ResponseBean<T> {

    /**
     * 返回代码，0 正确，负数 程序异常，正数 校验错误
     */
    private int state;

    /**
     * 额外信息
     */
    private String msg;


    /**
     * 返回结果
     */
    private T data;

    public ResponseBean() {
    }

    public ResponseBean(int state, String message, T data) {
        this.state = state;
        this.msg = message;
        this.data = data;
    }


    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMessage() {
        return msg;
    }

    public void setMessage(String message) {
        this.msg = message;
    }


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        if (state == 0) {
            if (msg == null) {
                if (data != null) {
                    return data.toString();
                }
            } else {
                if (data != null) {
                    return msg + ": " + data.toString();
                } else {
                    return msg;
                }
            }
        } else {
            return state + " on " + msg;
        }
        return null;
    }
}
