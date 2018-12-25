package learning.ly.com.imoocsdk.okhttp;


import learning.ly.com.imoocsdk.module.AdInstance;
import learning.ly.com.imoocsdk.okhttp.listener.DisposeDataHandle;
import learning.ly.com.imoocsdk.okhttp.listener.DisposeDataListener;
import learning.ly.com.imoocsdk.okhttp.request.CommonRequest;

/**
 * Created by renzhiqiang on 16/10/27.
 *
 * @function sdk请求发送中心
 */
public class RequestCenter {

    /**
     * 发送广告请求
     */
    public static void sendImageAdRequest(String url, DisposeDataListener listener) {

        CommonOkHttpClient.post(CommonRequest.createPostRequest(url, null),
                new DisposeDataHandle(listener, AdInstance.class));
    }
}
