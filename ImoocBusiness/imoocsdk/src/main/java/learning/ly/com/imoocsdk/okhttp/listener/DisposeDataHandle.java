package learning.ly.com.imoocsdk.okhttp.listener;

/**
 * Created by ly on 2018/12/21.
 */
public class DisposeDataHandle {
    public DisposeDataListener mListener=null;
    public Class<?> mClass=null;

    public DisposeDataHandle(DisposeDataListener listener) {
        mListener = listener;
    }

    public DisposeDataHandle(DisposeDataListener listener, Class<?> aClass) {
        mListener = listener;
        mClass = aClass;
    }
}
