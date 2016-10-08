package com.lyx.demo.utils;

import android.graphics.Bitmap;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

public class ImgUtils {

	/**
	 * 使用DisplayImageOptions.Builder()创建DisplayImageOptions，即显示图片的设置
	 */
	public static DisplayImageOptions mDisplayImageOptions() {
		DisplayImageOptions options = new DisplayImageOptions.Builder()
				.showImageOnLoading(null) // 设置图片下载期间显示的图片
				.showImageForEmptyUri(null) // 设置图片Uri为空或是错误的时候显示的图片
				.showImageOnFail(null) // 设置图片加载或解码过程中发生错误显示的图片
				.cacheInMemory(true) // 设置下载的图片是否缓存在内存中
				.cacheOnDisk(true) // 设置下载的图片是否缓存在SD卡中
				.considerExifParams(true) // 是否考虑JPEG图像EXIF参数（旋转，翻转）
				.imageScaleType(ImageScaleType.EXACTLY_STRETCHED)// 设置图片以如何的编码方式显示（图片会缩放到目标大小完全）
				.bitmapConfig(Bitmap.Config.RGB_565) // 设置图片的解码类型
				.displayer(new RoundedBitmapDisplayer(30)) // 设置成圆角图片
				.build(); // 创建配置过得DisplayImageOption对象
		return options;
	}

	public static DisplayImageOptions mImageOptions() {
		DisplayImageOptions options = new DisplayImageOptions.Builder()
				.showImageOnLoading(null) // 设置图片下载期间显示的图片
				.showImageForEmptyUri(null) // 设置图片Uri为空或是错误的时候显示的图片
				.showImageOnFail(null) // 设置图片加载或解码过程中发生错误显示的图片
				.cacheInMemory(true) // 设置下载的图片是否缓存在内存中
				.cacheOnDisk(true) // 设置下载的图片是否缓存在SD卡中
				.considerExifParams(true) // 是否考虑JPEG图像EXIF参数（旋转，翻转）
				.imageScaleType(ImageScaleType.EXACTLY_STRETCHED)// 设置图片以如何的编码方式显示（图片会缩放到目标大小完全）
				.bitmapConfig(Bitmap.Config.RGB_565) // 设置图片的解码类型
				.displayer(new FadeInBitmapDisplayer(100)) // 是否图片加载好后渐入的动画时间
				.build(); // 创建配置过得DisplayImageOption对象
		return options;
	}
}
