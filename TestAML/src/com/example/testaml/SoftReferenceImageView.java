package com.example.testaml;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

public class SoftReferenceImageView extends View {

	private Integer imageIndex;
	private Paint mPaint = new Paint();
	
	private int measuredWidth = 0;
	private int measuredHeight = 0;
	
	private Rect srcRect;
	private Rect dstRect;
	
	
	public SoftReferenceImageView(Context context) {
		super(context);
	}
	
	public SoftReferenceImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	@Override  
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int desiredWidth = this.measureWidth(widthMeasureSpec);
		int desiredHeight = this.measureHeight(heightMeasureSpec, desiredWidth);
        setMeasuredDimension(desiredWidth, desiredHeight);
        this.measuredWidth = desiredWidth;
        this.measuredHeight = desiredHeight;
        
        this.calculateSrcDst();
        
    }  
  
    private int measureWidth(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
  
        if (specMode == MeasureSpec.EXACTLY) {
            // We were told how big to be
            result = specSize;
        } else {
            // Measure the picture
        	
            result = this.getImageWidth() + getPaddingLeft() + getPaddingRight();
            if (specMode == MeasureSpec.AT_MOST) {
                // Respect AT_MOST value if that was what is called for by
                // measureSpec
                result = Math.min(result, specSize);
            }
        }
  
        return result;
    }
  
    private void calculateSrcDst()
    {
    	this.srcRect = new Rect(0, 0, this.getImageWidth() - 1, this.getImageHeight() - 1);
        this.dstRect = new Rect(this.getPaddingLeft(), this.getPaddingTop(), 
        		this.measuredWidth - 1 - this.getPaddingRight(), this.measuredHeight - 1 - this.getPaddingBottom());
    }
    
    private int measureHeight(int measureSpec, int desiredWidth) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
   
        if (specMode == MeasureSpec.EXACTLY) {
            // We were told how big to be
            result = specSize;
        } else {
        	// measure the picture
            //result = this.getImageHeight() + getPaddingTop() + getPaddingBottom();
        	
        	float hwRatio = (float)this.getImageHeight() / this.getImageWidth();
        	
        	result = Math.round(desiredWidth * hwRatio);
        	
            if (specMode == MeasureSpec.AT_MOST) {
                // Respect AT_MOST value if that was what is called for by
                // measureSpec
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

	@Override  
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        
        BitmapDrawable d = (BitmapDrawable)this.getImageDrawable();
        canvas.drawBitmap(d.getBitmap(), this.srcRect, this.dstRect, this.mPaint); 
    }  
    
	public Integer getImageIndex() {
		return imageIndex;
	}

	public void setImageIndex(Integer imageIndex) {
		this.imageIndex = imageIndex;
	} 
	
	private int getImageWidth()
	{
		return ImageCacheManager.getInstance().
				getImage(this.getImageIndex()).getWidth();
	}
	
	private int getImageHeight()
	{
		return ImageCacheManager.getInstance().
				getImage(this.getImageIndex()).getHeight();
	}
	
	private Drawable getImageDrawable()
	{
		return ImageCacheManager.getInstance().
				getImage(this.getImageIndex()).getImageData();
	}
}
