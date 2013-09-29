package com.ghadirekhom.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import com.ghadirekhom.extra.ImageLoader;
import com.google.analytics.tracking.android.EasyTracker;

public class ImageActivity extends Activity {
	@Override
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.activity_image);
		
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			String GetImg = extras.getString("image");
			
			ImageView image1 = (ImageView) findViewById(R.id.showimage);
			// Image url
			String image_url1 = "http://www.ghadirekhom.com/uploads/news/image/medium/" + GetImg;
			// ImageLoader class instance
			ImageLoader imgLoader = new ImageLoader(getApplicationContext());
			// image - ImageView
			imgLoader.DisplayImage(image_url1, image1);
		}
		
	}
	
	@Override
	  public void onStart() {
	    super.onStart();
	    EasyTracker.getInstance().setContext(this);
	    EasyTracker.getInstance().activityStart(this);
	  }

	  @Override
	  public void onStop() {
	    super.onStop();
	    EasyTracker.getInstance().setContext(this);
	    EasyTracker.getInstance().activityStop(this);
	  }
}
