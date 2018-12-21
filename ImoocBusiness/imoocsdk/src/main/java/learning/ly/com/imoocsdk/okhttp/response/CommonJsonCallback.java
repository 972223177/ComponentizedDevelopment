package learning.ly.com.imoocsdk.okhttp.response;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;

import learning.ly.com.imoocsdk.okhttp.exception.OkHttpException;
import learning.ly.com.imoocsdk.okhttp.listener.DisposeDataHandle;
import learning.ly.com.imoocsdk.okhttp.listener.DisposeDataListener;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by ly on 2018/12/21.
 */
public class CommonJsonCallback implements Callback {
    //请求成功结果相关
    protected final String RESULT_CODE = "ecode";//
    protected final int RESULT_CODE_VALUE = 0;
    protected final String ERROR_MSG = "emsg";
    protected final String EMPTY_MSG = "";
    //服务器异常相关
    protected final int NETWORK_ERROE = -1;// the network relative error;
    protected final int JSON_ERROR = -2;//the JSON relative error;
    protected final int OTHER_ERROR = -3;//the unknow error;

    private Handler mDeliveryHandler;
    private DisposeDataListener mListener;
    private Class<?> mClass;

    public CommonJsonCallback(DisposeDataHandle handle) {
        this.mDeliveryHandler = new Handler(Looper.getMainLooper());
        this.mListener = handle.mListener;
        this.mClass = handle.mClass;
    }

    @Override
    public void onFailure(Call call, final IOException e) {
        mDeliveryHandler.post(new Runnable() {
            @Override
            public void run() {
                mListener.onFailure(new OkHttpException(NETWORK_ERROE, e));
            }
        });
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        final String result = response.body().string();
        mDeliveryHandler.post(new Runnable() {
            @Override
            public void run() {
                handleResponse(result);
            }
        });
    }

    /**
     * 处理服务器放回的响应数据
     *
     * @param responseObj
     */
    private void handleResponse(Object responseObj) {
        if (responseObj == null && responseObj.toString().trim().equals("")) {
            mListener.onFailure(new OkHttpException(NETWORK_ERROE, EMPTY_MSG));
            return;
        }
        try {
            JSONObject result=new JSONObject(responseObj.toString());
            if (result.has(RESULT_CODE)){
                //从JSON对象中取出响应吗，若为0，则是正确的响应
                if (result.getInt(RESULT_CODE)==RESULT_CODE_VALUE){
                    if (mClass==null){
                        mListener.onSuccess(responseObj);
                    }else {
                        //否则需要将JSON对象转化为实体对象
                        Gson gson=new Gson();
                        Object obj=gson.fromJson(responseObj.toString(),mClass);
                        if (obj!=null){
                            mListener.onSuccess(obj);
                        }else {
                            //返回的json不合法
                            mListener.onFailure(new OkHttpException(JSON_ERROR,EMPTY_MSG));
                        }
                    }
                }else {
                    //将服务器返回的异常回调到应用层去处理
                    mListener.onFailure(new OkHttpException(OTHER_ERROR,result.get(RESULT_CODE)));
                }
            }
        } catch (Exception e) {
            mListener.onFailure(new OkHttpException(OTHER_ERROR,e.getMessage()));
        }
    }
}
