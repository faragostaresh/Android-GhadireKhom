package com.ghadirekhom.activity;

import java.io.IOException;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ghadirekhom.database.DatabaseHandler;
import com.ghadirekhom.database.Story;
import com.ghadirekhom.extra.ConnectionDetector;
import com.ghadirekhom.extra.ImageLoader;
import com.google.analytics.tracking.android.EasyTracker;

public class StoryActivity extends Activity {
	public int GetId;
	ProgressDialog progressDialog;

	@Override
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.activity_story);

		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			GetId = extras.getInt("id");
		}

		// Check connection
		ConnectionDetector cd = new ConnectionDetector(getApplicationContext());
		Boolean isInternetPresent = cd.isConnectingToInternet();

		// Read page from DB
		DatabaseHandler db = new DatabaseHandler(this);
		final Story item = db.getItem(GetId);
		if (item.getBody().matches("")) {
			if (isInternetPresent) {
				new GetStory().execute();
			} else {
				showAlertDialog(StoryActivity.this,
						getResources().getString(R.string.d_internet_access));
			}
		} else {
			// Set title for layout
			TextView showTitle = (TextView) findViewById(R.id.showtitle);
			showTitle.setText(item.getTitle());

			// Set body for layout
			TextView showBody = (TextView) findViewById(R.id.showbody);
			showBody.setText(item.getBody());

			// Imageview to show
			ImageView image = (ImageView) findViewById(R.id.showimage);
			// Image url
			String image_url = "http://www.ghadirekhom.com/uploads/news/image/medium/"
					+ item.getImage();
			// ImageLoader class instance
			ImageLoader imgLoader = new ImageLoader(getApplicationContext());
			// image - ImageView
			imgLoader.DisplayImage(image_url, image);
			// Click on image
			image.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent img = new Intent(getApplicationContext(),
							ImageActivity.class);
					img.putExtra("image", item.getImage());
					startActivity(img);
				}
			});
		}
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
				intent.setClass(StoryActivity.this, MainActivity.class);
				StoryActivity.this.startActivity(intent);
				StoryActivity.this.finish();
			}
		});

		// Showing Alert Message
		alertDialog.show();
	}

	private class GetStory extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			progressDialog = new ProgressDialog(StoryActivity.this);
			progressDialog.setTitle(getResources().getString(
					R.string.d_processing));
			progressDialog
					.setMessage(getResources().getString(R.string.d_wait));
			progressDialog.setCancelable(true);
			progressDialog.show();
		}

		@Override
		protected String doInBackground(String... ars) {

			Bundle extras = getIntent().getExtras();
			if (extras != null) {
				GetId = extras.getInt("id");
			}
			DatabaseHandler db = new DatabaseHandler(StoryActivity.this);

			String url = "http://www.ghadirekhom.com/modules/news/ajax.php?op=singlestory&storyid="
					+ GetId;
			Log.d("Get URL", url);
			// TODO 4 new activity with custom adapter to show schedules
			try {
				HttpClient client = new DefaultHttpClient();
				HttpGet get = new HttpGet(url);
				ResponseHandler<String> responseHandler = new BasicResponseHandler();
				JSONArray ja = new JSONArray(client.execute(get,
						responseHandler));
				for (int i = 0; i < ja.length(); i++) {
					JSONObject jo = (JSONObject) ja.get(i);
					db.updateItem(new Story(jo.getInt("story_id"), jo
							.getInt("story_topic"),
							jo.getString("story_title"), jo
									.getString("story_body"), jo
									.getString("story_img"), jo
									.getString("story_publish")));
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

			return null;
		}

		@SuppressLint("CutPasteId")
		@Override
		protected void onPostExecute(String result) {
			progressDialog.dismiss();

			Bundle extras = getIntent().getExtras();
			if (extras != null) {
				GetId = extras.getInt("id");
			}

			// Read page from DB
			DatabaseHandler db = new DatabaseHandler(StoryActivity.this);
			final Story item = db.getItem(GetId);

			// Set title for layout
			TextView showTitle = (TextView) findViewById(R.id.showtitle);
			showTitle.setText(item.getTitle());

			// Set body for layout
			TextView showBody = (TextView) findViewById(R.id.showbody);
			showBody.setText(item.getBody());

			// Imageview to show
			ImageView image = (ImageView) findViewById(R.id.showimage);
			// Image url
			String image_url = "http://www.ghadirekhom.com/uploads/news/image/medium/"
					+ item.getImage();
			// ImageLoader class instance
			ImageLoader imgLoader = new ImageLoader(getApplicationContext());
			// image - ImageView
			imgLoader.DisplayImage(image_url, image);
			// Click on image
			image.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent img = new Intent(getApplicationContext(),
							ImageActivity.class);
					img.putExtra("image", item.getImage());
					startActivity(img);
				}
			});
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
