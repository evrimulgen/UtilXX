package com.xuexiang.util.net;

import net.tsz.afinal.FinalBitmap;
import android.content.Context;
import android.widget.ImageView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.NetworkImageView;
import com.xuexiang.app.BaseApplication;
import com.xuexiang.util.resource.RUtils;

/**  
 * ����ʱ�䣺2016-5-17 ����9:42:25  
 * ��Ŀ���ƣ�VolleyDemo  
 * @author xuexiang
 * �ļ����ƣ�LoadNetImageUtil.java  
 **/
public class NetImageUtil {
	
	
	/************************************************Volley���*********************************************************************************/
	
    public static void getImage(String url,ImageView imageView){
		
		ImageLoader imageLoader = new ImageLoader(BaseApplication.getVolleyRequestQueue(),BitmapCache.instance());
		// ͼƬ���� ��Ĭ��ͼƬ������ͼƬ�� �� imageView
		ImageListener imageListener = ImageLoader.getImageListener(imageView, RUtils.getDrawable(BaseApplication.getContext(), "noimg"), RUtils.getDrawable(BaseApplication.getContext(), "noimg"));		
		//����ͼƬ
		imageLoader.get(url, imageListener);
		
	}
    
    
    public static void getNetImage(String url, NetworkImageView imageView) {
        ImageLoader imageLoader = new ImageLoader(BaseApplication.getVolleyRequestQueue(),BitmapCache.instance());		
        imageView.setDefaultImageResId(RUtils.getDrawable(BaseApplication.getContext(), "noimg"));
        imageView.setErrorImageResId(RUtils.getDrawable(BaseApplication.getContext(), "noimg"));
        imageView.setImageUrl(url, imageLoader);
	}
    
   /************************************************Final���*********************************************************************************/
	
    public static void getImage(Context context, String url, ImageView imageView){
    	FinalBitmap bitmap = FinalBitmap.create(context);
    	bitmap.configLoadingImage(RUtils.getDrawable(BaseApplication.getContext(), "noimg"));
    	bitmap.configLoadfailImage(RUtils.getDrawable(BaseApplication.getContext(), "noimg"));
        bitmap.display(imageView, url);	
	}
    
    public static void getFinalImage(String url, ImageView imageView){
    	FinalBitmap bitmap = FinalBitmap.create(BaseApplication.getContext());
    	bitmap.configLoadingImage(RUtils.getDrawable(BaseApplication.getContext(), "noimg"));
    	bitmap.configLoadfailImage(RUtils.getDrawable(BaseApplication.getContext(), "noimg"));
        bitmap.display(imageView, url);	
    }
	
	
}
