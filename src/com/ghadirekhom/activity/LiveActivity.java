package com.ghadirekhom.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.ghadirekhom.extra.ConnectionDetector;
import com.google.analytics.tracking.android.EasyTracker;

public class LiveActivity extends Activity {

	@Override
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.activity_live);

		ImageView img1 = (ImageView) findViewById(R.id.live1);
		img1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// Check connection
				ConnectionDetector cd = new ConnectionDetector(
						getApplicationContext());
				Boolean isInternetPresent = cd.isConnectingToInternet();
				if (isInternetPresent) {
					Intent intent = new Intent();
					intent.setAction(Intent.ACTION_VIEW);
					intent.addCategory(Intent.CATEGORY_BROWSABLE);
					intent.setData(Uri
							.parse("rtsp://mob.ghadir.tv:1935/live/ghadir64"));
					startActivity(intent);
				} else {
					showAlertDialog(LiveActivity.this, getResources()
							.getString(R.string.d_internet_access));
				}
			}
		});

		ImageView img2 = (ImageView) findViewById(R.id.live2);
		img2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// Check connection
				ConnectionDetector cd = new ConnectionDetector(
						getApplicationContext());
				Boolean isInternetPresent = cd.isConnectingToInternet();
				if (isInternetPresent) {
					Intent intent = new Intent();
					intent.setAction(Intent.ACTION_VIEW);
					intent.addCategory(Intent.CATEGORY_BROWSABLE);
					intent.setData(Uri
							.parse("rtsp://mob.ghadir.tv:1935/live/ghadir128"));
					startActivity(intent);
				} else {
					showAlertDialog(LiveActivity.this, getResources()
							.getString(R.string.d_internet_access));
				}
			}
		});

		ImageView img3 = (ImageView) findViewById(R.id.live3);
		img3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// Check connection
				ConnectionDetector cd = new ConnectionDetector(
						getApplicationContext());
				Boolean isInternetPresent = cd.isConnectingToInternet();
				if (isInternetPresent) {
					Intent intent = new Intent();
					intent.setAction(Intent.ACTION_VIEW);
					intent.addCategory(Intent.CATEGORY_BROWSABLE);
					intent.setData(Uri
							.parse("rtsp://mob.ghadir.tv:1935/live/ghadir256"));
					startActivity(intent);
				} else {
					showAlertDialog(LiveActivity.this, getResources()
							.getString(R.string.d_internet_access));
				}
			}
		});
	}
	
	@SuppressWarnings("deprecation")
	public void showAlertDialog(Context context, String Message) {
		AlertDialog alertDialog = new AlertDialog.Builder(context).create();

		// Setting Dialog Title
		alertDialog.setTitle(getResources().getString(R.string.d_internet));

		// Setting Dialog Message
		alertDialog.setMessage(Message);

		// Setting alert dialog icon
		alertDialog.setIcon(R.drawable.cancel);

		// Setting OK Button
		alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Intent intent = new Intent();
				intent.setClass(LiveActivity.this, MainActivity.class);
				LiveActivity.this.startActivity(intent);
				LiveActivity.this.finish();
			}
		});

		// Showing Alert Message
		alertDialog.show();
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
