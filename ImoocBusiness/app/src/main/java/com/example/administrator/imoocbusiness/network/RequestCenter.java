package com.example.administrator.imoocbusiness.network;


import com.example.administrator.imoocbusiness.module.recommand.BaseRecommandModel;

import learning.ly.com.imoocsdk.okhttp.listener.DisposeDataHandle;
import learning.ly.com.imoocsdk.okhttp.listener.DisposeDataListener;
import learning.ly.com.imoocsdk.okhttp.request.RequestParams;

import static learning.ly.com.imoocsdk.okhttp.CommonOkHttpClient.get;
import static learning.ly.com.imoocsdk.okhttp.request.CommonRequest.createGetRequest;

/**
 * Created by ly on 2018/12/25.
 * @function 存放应用中所有的请求
 */
public class RequestCenter {
    private static void postRequest(String url, RequestParams params,
                                    DisposeDataListener listener, Class<?> clazz){
        get(createGetRequest(url,params),
                new DisposeDataHandle(listener,clazz));
    }

    /**
     * 首页请求
     * @param listener
     */
    public static void requestRecommondData(DisposeDataListener listener){
        RequestCenter.postRequest(HttpConstants.HOME_RECOMMAND,null,
                listener,BaseRecommandModel.class);
    }
}
