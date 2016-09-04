package com.xuexiang.util.net;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader.ImageCache;

@SuppressLint("NewApi")
public class BitmapCache implements ImageCache{

	private LruCache<String,Bitmap> mMemoryCache;
	
	private static BitmapCache mBitmapCache;
	
	//�������� �ߴ�ֵ	
	public BitmapCache() {
		//���췽�� ʵ�� LruCache ���� ͼƬ	
		int maxMemory = (int) Runtime.getRuntime().maxMemory();
		int cacheSize = maxMemory / 8;
		mMemoryCache = new LruCache<String,Bitmap>(cacheSize){
			@Override
			protected int sizeOf(String key, Bitmap value) {
				return value.getRowBytes() * value.getHeight();
			}
		};
		
	}
	
	public static BitmapCache instance(){
		if(mBitmapCache == null){
			mBitmapCache = new BitmapCache();
		}
		return mBitmapCache;
	}
	
	@Override
	public Bitmap getBitmap(String url) {
		// �õ�
		return mMemoryCache.get(url);
	}

	@Override
	public void putBitmap(String url, Bitmap bitmap) {
		// ����
		if(getBitmap(url) == null) {
		   mMemoryCache.put(url, bitmap);
		}
	}

}
