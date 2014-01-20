package com.example.testaml;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public class ImageCacheManager {
	private static ImageCacheManager instance = null;
	private Map<Integer, ImageBean> data = new HashMap<Integer, ImageBean>();
	private Integer nextInteger = Integer.valueOf(1);
	private Random random = new Random();
	
	public static ImageCacheManager getInstance()
	{
		if (ImageCacheManager.instance == null)
		{
			ImageCacheManager.instance = new ImageCacheManager();
			
		}
		return ImageCacheManager.instance;
	}
	
	private synchronized int getNextInt()
	{
		int currInt = this.nextInteger.intValue();
		++this.nextInteger;
		return currInt;
	}
	
	private ImageCacheManager() {}
	
	public Integer getImageIDRandom()
	{
		int count = data.keySet().size();
		int idx = this.random.nextInt(count);
		
		return Integer.valueOf(data.keySet().toArray()[idx].toString());
	}
	
	public ImageBean getImage(Integer id)
	{
		if (data.containsKey(id))
		{
			return data.get(id);
		}
		else
		{
			return null;
		}
	}
	
	public Integer addBitmapDrawable(BitmapDrawable bitmap)
	{
		Integer idx = Integer.valueOf(this.getNextInt());
		
		ImageBean ib = new ImageBean();
		ib.setId(idx);
		ib.setImageData(bitmap);
		this.data.put(idx, ib);
		
		return idx;
	}
}
