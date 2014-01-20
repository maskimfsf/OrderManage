package com.example.testaml;

import android.graphics.drawable.Drawable;

public class ImageBean {
	private Integer id;
	private Drawable imageData;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Drawable getImageData() {
		return imageData;
	}
	public void setImageData(Drawable imageData) {
		this.imageData = imageData;
	}
	
	public int getWidth()
	{
		return imageData.getIntrinsicWidth();
	}
	
	public int getHeight()
	{
		return imageData.getIntrinsicHeight();
	}
}
