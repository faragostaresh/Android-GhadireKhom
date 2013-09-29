package com.ghadirekhom.activity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ghadirekhom.database.DatabaseHandler;
import com.ghadirekhom.database.Story;
import com.ghadirekhom.extra.ConnectionDetector;
import com.google.analytics.tracking.android.EasyTracker;

public class TopicActivity extends Activity {

	public int GetId = 0;
	public int SetId = 0;
	public int LastId;

	ProgressDialog progressDialog;

	private ListView lv;
	private ArrayList<String> listview_title = new ArrayList<String>();
	private ArrayList<Integer> listview_id = new ArrayList<Integer>();

	@Override
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.activity_topic);

		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			GetId = extras.getInt("cid");
		}

		// Check connection
		ConnectionDetector cd = new ConnectionDetector(getApplicationContext());
		Boolean isInternetPresent = cd.isConnectingToInternet();
		if (isInternetPresent) {
			new GetListStory().execute();
		} else {
			// Set Database and get count
			DatabaseHandler db = new DatabaseHandler(this);
			int count = db.getStoryCount(GetId);
			// Check Database and connection
			if (count > 0) {
				// Read page from DB
				List<Story> stores = db.getAllItemCid(GetId);
				// Add to array
				for (Story cn : stores) {
					listview_title.add(cn.getTitle());
					listview_id.add(cn.getId());
				}
				// Make list view
				lv = (ListView) findViewById(R.id.listView1);
				lv.setAdapter(new ArrayAdapter<String>(TopicActivity.this,
						R.layout.activity_topic_list, listview_title));
				lv.setTextFilterEnabled(true);
				lv.setOnItemClickListener(new OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						SetId = listview_id.get(arg2);
						Intent item = new Intent(getApplicationContext(),
								StoryActivity.class);
						item.putExtra("id", SetId);
						startActivity(item);
					}
				});
			} else {
				showAlertDialog(TopicActivity.this,
						getResources().getString(R.string.d_internet_access));
			}
		}
	}

	private class GetListStory extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			progressDialog = new ProgressDialog(TopicActivity.this);
			progressDialog.setTitle(getResources().getString(
					R.string.d_processing));
			progressDialog
					.setMessage(getResources().getString(R.string.d_wait));
			progressDialog.setCancelable(true);
			progressDialog.show();
		}

		@Override
		protected String doInBackground(String... ars) {

			DatabaseHandler db = new DatabaseHandler(TopicActivity.this);
			Bundle extras = getIntent().getExtras();
			if (extras != null) {
				GetId = extras.getInt("cid");
				LastId = db.getStoryLastId(GetId);
			}

			String url = "http://www.ghadirekhom.com/modules/news/ajax.php?op=liststory&storyid="
					+ LastId + "&storytopic=" + GetId + "&limit=100";

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
					db.addItem(new Story(jo.getInt("story_id"), jo
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

		@Override
		protected void onPostExecute(String result) {
			progressDialog.dismiss();

			// Read page from DB
			DatabaseHandler db = new DatabaseHandler(TopicActivity.this);
			List<Story> stores = db.getAllItemCid(GetId);

			// Add to array
			for (Story cn : stores) {
				listview_title.add(cn.getTitle());
				listview_id.add(cn.getId());
			}

			// Make list view
			lv = (ListView) findViewById(R.id.listView1);
			lv.setAdapter(new ArrayAdapter<String>(TopicActivity.this,
					R.layout.activity_topic_list, listview_title));
			lv.setTextFilterEnabled(true);
			lv.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					SetId = listview_id.get(arg2);

					Intent item = new Intent(getApplicationContext(),
							StoryActivity.class);
					item.putExtra("id", SetId);
					startActivity(item);
				}
			});
		}
	}

	/**
	 * Function to display simple Alert Dialog
	 * 
	 * @param context
	 **/
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
				intent.setClass(TopicActivity.this, MainActivity.class);
				TopicActivity.this.startActivity(intent);
				TopicActivity.this.finish();
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
