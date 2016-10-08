package com.lyx.demo.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lyx.demo.utils.ImgUtils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.List;



public class BannerAdapter extends PagerAdapter {
	private List<String> adList;
	private Context context;
	private DisplayImageOptions options;
	private ImageLoader imageLoader;
	public BannerAdapter(Context mContext, List<String> adList) {
		this.adList = adList;
		context = mContext; 
		options = ImgUtils.mImageOptions();
		imageLoader = ImageLoader.getInstance();
		imageLoader.init(ImageLoaderConfiguration.createDefault(mContext));
	}

	@Override
	public int getCount() {
		return Integer.MAX_VALUE;
	}

	@Override
	public Object instantiateItem(ViewGroup container, int arg2) {
		final int position = arg2 % adList.size();
		ImageView imageView = new ImageView(context);
		ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT);
		imageView.setLayoutParams(params);
		imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//		imageView.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				if (adList != null) {
//					Intent mIntent = new Intent();
//					if (adList.get(position).getTYPE().equals("1")) {
//						Intent intent= new Intent();
//					    intent.setAction("android.intent.action.VIEW");
//					    Uri content_url = Uri.parse(adList.get(position).getBOOKID().trim());
//					    intent.setData(content_url);
//					    mContext.startActivity(intent);
//					} else if (adList.get(position).getTYPE().equals("2")){
//						Bundle bundle = new Bundle();
//						bundle.putString("bookId", adList.get(position).getBOOKID().trim());
//						mActivity.index(Constants.Code.DETAIL_CONTENT, bundle);
//					}else if (adList.get(position).getTYPE().equals("3")){
//
//					}
//					try {
//						mContext.startActivity(mIntent);
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//				}
//			}
//		});
		imageLoader.displayImage(adList.get(position), imageView, options);
		container.addView(imageView, ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT);
		return imageView;
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == object;
	}
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View) object);
	}


}
