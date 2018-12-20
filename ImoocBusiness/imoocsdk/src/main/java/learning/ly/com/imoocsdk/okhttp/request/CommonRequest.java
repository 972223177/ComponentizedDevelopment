package learning.ly.com.imoocsdk.okhttp.request;

import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Request;

/**
 * Created by ly on 2018/12/20.
 *
 * @function 生成request对象
 */
public class CommonRequest {
    /**
     * 返回request对象
     * @param url
     * @param params
     * @return 返回一个post类型的请求
     */
    public static Request createPostRequest(String url, RequestParams params) {
        FormBody.Builder mFormbodyBuild=new FormBody.Builder();
        if (params!=null){
            //将请求参数逐一天添加到params中
            for (Map.Entry<String, String> entry : params.urlParams.entrySet()) {
                mFormbodyBuild.add(entry.getKey(),entry.getValue());
            }
        }
        //通过请求构建类的build方法获取到真正的请求体对象
        FormBody formBody=mFormbodyBuild.build();

        return new Request.Builder().url(url).post(formBody).build();
    }

    /**
     *
     * @param url
     * @param params
     * @return 返回一个get类型的请求
     */
    public static Request createGetRequest(String url,RequestParams params){
        StringBuilder urlBuilder=new StringBuilder(url).append("?");
        if (params!=null){
            for (Map.Entry<String, String> entry : params.urlParams.entrySet()) {
                urlBuilder.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
        }
        return new Request.Builder().url(urlBuilder.substring(0,urlBuilder.length()-1)).get().build();
    }
}
