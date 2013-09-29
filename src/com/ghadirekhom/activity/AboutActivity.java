package com.ghadirekhom.activity;

import com.google.analytics.tracking.android.EasyTracker;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class AboutActivity extends Activity {
	@Override
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.activity_about);

		ImageView img1 = (ImageView) findViewById(R.id.faragostaresh);
		img1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setAction(Intent.ACTION_VIEW);
				intent.addCategory(Intent.CATEGORY_BROWSABLE);
				intent.setData(Uri.parse("http://www.faragostaresh.com/"));
				startActivity(intent);
			}
		});
		
		ImageView img2 = (ImageView) findViewById(R.id.slogo);
		img2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setAction(Intent.ACTION_VIEW);
				intent.addCategory(Intent.CATEGORY_BROWSABLE);
				intent.setData(Uri.parse("http://www.ghadirekhom.com/"));
				startActivity(intent);
			}
		});
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
