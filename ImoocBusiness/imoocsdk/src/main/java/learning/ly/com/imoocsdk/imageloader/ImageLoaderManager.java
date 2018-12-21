package learning.ly.com.imoocsdk.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import learning.ly.com.imoocsdk.R;

/**
 * Created by ly on 2018/12/21.
 *
 * @function 初始化UniverImageLoader, 并且加载网络图片
 */
public class ImageLoaderManager {
    /**
     * 默认的参数值
     */
    private static final int THREAD_COUN = 4;//最多可以有4条线程
    private static final int PRIORITY = 2;//加载优先级
    private static final int DISK_CACHE_SIZE = 50 * 1024;//图标缓存大小50M
    private static final int CONNECTION_TIME_OUT = 5 * 1000;//连接超时时间
    private static final int READ_TIME_OUT = 30 * 1000;//读取超时时间

    private static ImageLoader imageLoader = null;
    private static ImageLoaderManager manager = null;

    private static ImageLoaderManager getInstance(Context context) {
        if (manager == null) {
            synchronized (ImageLoaderManager.class) {//保证不会因为多线程产生多个实例
                if (manager == null) {
                    manager = new ImageLoaderManager(context);
                }
            }
        }
        return manager;
    }

    /**
     * 单例模式 构造方法私有
     *
     * @param context
     */
    private ImageLoaderManager(Context context) {
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration
                .Builder(context)
                .threadPoolSize(THREAD_COUN)//配置图片下载线程的最大数量
                .threadPriority(Thread.NORM_PRIORITY - PRIORITY)//各个系统norm_priority不同
                .denyCacheImageMultipleSizesInMemory()//禁止不同尺寸的图片到缓存中
                .memoryCache(new WeakMemoryCache())//缓存弱引用
                .diskCacheSize(DISK_CACHE_SIZE)//硬盘缓存大小
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())//使用MD5命名文件
                .tasksProcessingOrder(QueueProcessingType.LIFO)//图片下载顺序
                .defaultDisplayImageOptions(getDefaultOptions())
                .imageDownloader(new BaseImageDownloader(context, CONNECTION_TIME_OUT
                        , READ_TIME_OUT))//设置图片下载器
                .writeDebugLogs()//debug输出日志
                .build();
        ImageLoader.getInstance().init(configuration);
        imageLoader = ImageLoader.getInstance();
    }

    /**
     * 实现默认的options
     *
     * @return
     */
    private DisplayImageOptions getDefaultOptions() {
        return new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.drawable.xadsdk_img_error)//url为空的占位图
                .showImageOnFail(R.drawable.xadsdk_img_error)//下载失败的占位图
                .cacheInMemory(true)//允许图片可以缓存在内存
                .cacheOnDisk(true)//允许图片缓存在硬盘
                .bitmapConfig(Bitmap.Config.RGB_565)//图片解码类型，RGB_565降低图片色彩，减少内存使用
                .decodingOptions(new BitmapFactory.Options())//图片解码的配置，使用默认的
                .build();
    }

    /**
     * 图片加载api
     *
     * @param imageView
     * @param url
     * @param options
     * @param listener
     */
    public void displayImage(ImageView imageView, String url, DisplayImageOptions options,
                             ImageLoadingListener listener) {
        if (imageLoader != null) {
            imageLoader.displayImage(url, imageView, options, listener);
        }
    }

    public void displayImage(ImageView imageView, String url, ImageLoadingListener listener) {
        displayImage(imageView, url, null, listener);
    }

    public void displayImage(ImageView imageView, String url) {
        displayImage(imageView, url, null, null);
    }

}
