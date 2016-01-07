package heshida.haihong.com.heshida.Utils.Http;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

import heshida.haihong.com.heshida.R;

/**
 * Created by lichanghong on 1/6/16.
 */
public class UniversalImageUtil {
    Context mContext;
    Activity mActivity;

    public UniversalImageUtil(Context mContext, Activity mActivity) {
        this.mContext = mContext;
        this.mActivity = mActivity;
    }

    public void configImageLoader()
    {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(mContext)
        .threadPoolSize(3) // default
            .threadPriority(Thread.NORM_PRIORITY - 1) // default
            .tasksProcessingOrder(QueueProcessingType.FIFO) // default
            .denyCacheImageMultipleSizesInMemory()
            .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
            .memoryCacheSize(2 * 1024 * 1024)
            .memoryCacheSizePercentage(13) // default
            .diskCacheSize(50 * 1024 * 1024)
            .diskCacheFileCount(100)
            .diskCacheFileNameGenerator(new HashCodeFileNameGenerator()) // default
            .imageDownloader(new BaseImageDownloader(mContext)) // default
            .defaultDisplayImageOptions(DisplayImageOptions.createSimple()) // default
            .writeDebugLogs()
            .build();
        ImageLoader.getInstance().init(config);
    }

    public void displayImageWithImageView(ImageView imageView,String imageurl)
    {
        //显示图片的配置
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.ic_launcher)
                .showImageOnFail(R.mipmap.ic_launcher)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();

        ImageLoader.getInstance().displayImage(imageurl, imageView, options);
    }

}
