package learning.ly.com.imoocsdk.okhttp.listener;

/**
 * Created by ly on 2018/12/21.
 */
public interface DisposeDataListener {

    /**
     * 请求成功回调事件处理
     * @param responseObj
     */
    void onSuccess(Object responseObj);

    /**
     *  请求失败回调事件处理
     * @param reasonObj
     */
    void onFailure(Object reasonObj);
}
