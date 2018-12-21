package learning.ly.com.imoocsdk.okhttp.exception;

/**
 * Created by ly on 2018/12/21.
 */
public class OkHttpException extends Exception {
    private static final long serialVersionID=1L;

    private int ecode;//the server return code;

    private Object emsg;//the server return error message;

    public OkHttpException(int ecode,Object emsg){
        this.ecode=ecode;
        this.emsg=emsg;
    }

    public int getEcode() {
        return ecode;
    }

    public Object getEmsg() {
        return emsg;
    }
}
