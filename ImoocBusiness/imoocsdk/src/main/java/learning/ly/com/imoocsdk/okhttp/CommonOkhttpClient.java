package learning.ly.com.imoocsdk.okhttp;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.OkHttpClient;

/**
 * Created by ly on 2018/12/20.
 * @function 请求的发送，请求参数的配置，HTTPS支持。
 */
public class CommonOkhttpClient {

    private static final int TIME_OUT=30;//超时参数
    private static OkHttpClient client;
    /*
    一般情况下，如果有些代码必须在项目启动的时候执行，就需要使用静态代码块，这种代码是主动执行的；
    需要在项目启动的时候初始化，又不在创建对象的情况下，其他程序来调用的时候，需要使用静态方法，这种代码是被动执行的。
    静态方法在类加载的时候就已经加载可以用类名直接调用。

    一个类可以使用不包含在任何方法体中的静态代码块，当类被载入时，静态代码块被执行，且只被执行一次，静态代码块通常永磊执行性类属性的初始化，
    例如这里client。
     */
    //为client配置参数
    static {
        OkHttpClient.Builder okHttpBuilder=new OkHttpClient.Builder();
        okHttpBuilder.connectTimeout(TIME_OUT,TimeUnit.SECONDS);
        okHttpBuilder.readTimeout(TIME_OUT,TimeUnit.SECONDS);
        okHttpBuilder.writeTimeout(TIME_OUT,TimeUnit.SECONDS);
        okHttpBuilder.followRedirects(true);//允许重定向
        //添加HTTPS支持
        okHttpBuilder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String s, SSLSession sslSession) {
                return true;
            }
        });

        //okHttpBuilder.sslSocketFactory(HttpsUtils.getSslSocketFactory());
        //生成client对象
        client=okHttpBuilder.build();
    }
}
