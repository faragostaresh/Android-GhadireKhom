package com.ghadirekhom.activity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

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
import android.widget.ListView;

import com.ghadirekhom.database.DatabaseHandler;
import com.ghadirekhom.database.Story;
import com.ghadirekhom.extra.ConnectionDetector;
import com.ghadirekhom.extra.MainListAdapter;
import com.google.analytics.tracking.android.EasyTracker;

public class ArticleActivity extends Activity {

	public int GetId;
	public int TopicId = 58;
	public int LastId;
	
	private ListView lv;
	public MainListAdapter adapter;
	
	ProgressDialog progressDialog;

	public static final String KEY_TITLE = "title";
	public static final String KEY_THUMBNAIL = "thumbnail";

	@Override
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.activity_article);

		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			GetId = extras.getInt("listid");
		} else {
			GetId = 1;
		}

		// Set list array
		ArrayList<HashMap<String, String>> listview_main = new ArrayList<HashMap<String, String>>();

		switch (GetId) {
		case 1:

			HashMap<String, String> map1 = new HashMap<String, String>();
			map1.put(KEY_TITLE, "غدیر خم");
			map1.put(KEY_THUMBNAIL, "topic");
			listview_main.add(map1);

			HashMap<String, String> map2 = new HashMap<String, String>();
			map2.put(KEY_TITLE, "امیر المومنین علی علیه السلام");
			map2.put(KEY_THUMBNAIL, "topic");
			listview_main.add(map2);

			HashMap<String, String> map3 = new HashMap<String, String>();
			map3.put(KEY_TITLE, "اهل بيت علیهم السلام");
			map3.put(KEY_THUMBNAIL, "topic");
			listview_main.add(map3);

			HashMap<String, String> map4 = new HashMap<String, String>();
			map4.put(KEY_TITLE, "اولیاء الله");
			map4.put(KEY_THUMBNAIL, "topic");
			listview_main.add(map4);

			HashMap<String, String> map5 = new HashMap<String, String>();
			map5.put(KEY_TITLE, "مناسبت ها");
			map5.put(KEY_THUMBNAIL, "topic");
			listview_main.add(map5);

			HashMap<String, String> map6 = new HashMap<String, String>();
			map6.put(KEY_TITLE, "پاسخ به شبهات");
			map6.put(KEY_THUMBNAIL, "topic");
			listview_main.add(map6);

			HashMap<String, String> map7 = new HashMap<String, String>();
			map7.put(KEY_TITLE, "مقاله");
			map7.put(KEY_THUMBNAIL, "topic");
			listview_main.add(map1);

			HashMap<String, String> map8 = new HashMap<String, String>();
			map8.put(KEY_TITLE, "محرم");
			map8.put(KEY_THUMBNAIL, "topic");
			listview_main.add(map8);

			HashMap<String, String> map9 = new HashMap<String, String>();
			map9.put(KEY_TITLE, "شب قدر");
			map9.put(KEY_THUMBNAIL, "topic");
			listview_main.add(map9);

			HashMap<String, String> map10 = new HashMap<String, String>();
			map10.put(KEY_TITLE, "فاطميه");
			map10.put(KEY_THUMBNAIL, "topic");
			listview_main.add(map10);

			HashMap<String, String> map11 = new HashMap<String, String>();
			map11.put(KEY_TITLE, "تحقيقات");
			map11.put(KEY_THUMBNAIL, "topic");
			listview_main.add(map11);

			HashMap<String, String> map12 = new HashMap<String, String>();
			map12.put(KEY_TITLE, "خطبه ها");
			map12.put(KEY_THUMBNAIL, "topic");
			listview_main.add(map12);

			// Getting adapter by ArrayList
			adapter = new MainListAdapter(this, listview_main);

			// Set custom list view
			lv = (ListView) findViewById(R.id.listView1);
			lv.setAdapter(adapter);
			lv.setDivider(null);
			lv.setTextFilterEnabled(true);
			lv.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					switch (arg2) {
					case 0:
						Intent list2 = new Intent(getApplicationContext(),
								ArticleActivity.class);
						list2.putExtra("listid", 2);
						startActivity(list2);
						break;

					case 1:
						Intent cid1 = new Intent(getApplicationContext(),
								TopicActivity.class);
						cid1.putExtra("cid", 1);
						startActivity(cid1);
						break;

					case 2:
						Intent list3 = new Intent(getApplicationContext(),
								ArticleActivity.class);
						list3.putExtra("listid", 3);
						startActivity(list3);
						break;

					case 3:
						Intent list4 = new Intent(getApplicationContext(),
								ArticleActivity.class);
						list4.putExtra("listid", 4);
						startActivity(list4);
						break;

					case 4:
						Intent cid12 = new Intent(getApplicationContext(),
								TopicActivity.class);
						cid12.putExtra("cid", 12);
						startActivity(cid12);
						break;

					case 5:
						Intent cid19 = new Intent(getApplicationContext(),
								TopicActivity.class);
						cid19.putExtra("cid", 19);
						startActivity(cid19);
						break;

					case 6:
						Intent cid16 = new Intent(getApplicationContext(),
								TopicActivity.class);
						cid16.putExtra("cid", 16);
						startActivity(cid16);
						break;

					case 7:
						Intent cid11 = new Intent(getApplicationContext(),
								TopicActivity.class);
						cid11.putExtra("cid", 11);
						startActivity(cid11);
						break;

					case 8:
						Intent cid10 = new Intent(getApplicationContext(),
								TopicActivity.class);
						cid10.putExtra("cid", 10);
						startActivity(cid10);
						break;

					case 9:
						Intent cid7 = new Intent(getApplicationContext(),
								TopicActivity.class);
						cid7.putExtra("cid", 7);
						startActivity(cid7);
						break;

					case 10:
						Intent cid4 = new Intent(getApplicationContext(),
								TopicActivity.class);
						cid4.putExtra("cid", 4);
						startActivity(cid4);
						break;

					case 11:
						Intent cid3 = new Intent(getApplicationContext(),
								TopicActivity.class);
						cid3.putExtra("cid", 3);
						startActivity(cid3);
						break;
					}
				}
			});
			break;

		case 2:

			HashMap<String, String> map1b = new HashMap<String, String>();
			map1b.put(KEY_TITLE, "۲۳ روز با غدير");
			map1b.put(KEY_THUMBNAIL, "topic");
			listview_main.add(map1b);

			HashMap<String, String> map2b = new HashMap<String, String>();
			map2b.put(KEY_TITLE, "آداب غدیر");
			map2b.put(KEY_THUMBNAIL, "topic");
			listview_main.add(map2b);

			HashMap<String, String> map3b = new HashMap<String, String>();
			map3b.put(KEY_TITLE, "سخن رانی پیاده شده");
			map3b.put(KEY_THUMBNAIL, "topic");
			listview_main.add(map3b);

			HashMap<String, String> map4b = new HashMap<String, String>();
			map4b.put(KEY_TITLE, "سناریو");
			map4b.put(KEY_THUMBNAIL, "topic");
			listview_main.add(map4b);

			HashMap<String, String> map5b = new HashMap<String, String>();
			map5b.put(KEY_TITLE, "مراسم");
			map5b.put(KEY_THUMBNAIL, "topic");
			listview_main.add(map5b);

			HashMap<String, String> map6b = new HashMap<String, String>();
			map6b.put(KEY_TITLE, "سوال ها و مسابقات");
			map6b.put(KEY_THUMBNAIL, "topic");
			listview_main.add(map6b);

			HashMap<String, String> map7b = new HashMap<String, String>();
			map7b.put(KEY_TITLE, "فیش سخن رانی");
			map7b.put(KEY_THUMBNAIL, "topic");
			listview_main.add(map7b);

			HashMap<String, String> map8b = new HashMap<String, String>();
			map8b.put(KEY_TITLE, "داستان");
			map8b.put(KEY_THUMBNAIL, "topic");
			listview_main.add(map8b);

			HashMap<String, String> map9b = new HashMap<String, String>();
			map9b.put(KEY_TITLE, "شعر");
			map9b.put(KEY_THUMBNAIL, "topic");
			listview_main.add(map9b);

			HashMap<String, String> map10b = new HashMap<String, String>();
			map10b.put(KEY_TITLE, "متن ادبی");
			map10b.put(KEY_THUMBNAIL, "topic");
			listview_main.add(map10b);

			// Getting adapter by ArrayList
			adapter = new MainListAdapter(this, listview_main);

			// Set custom list view
			lv = (ListView) findViewById(R.id.listView1);
			lv.setAdapter(adapter);
			lv.setDivider(null);
			lv.setTextFilterEnabled(true);
			lv.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					switch (arg2) {
					case 0:
						Intent cid5 = new Intent(getApplicationContext(),
								TopicActivity.class);
						cid5.putExtra("cid", 5);
						startActivity(cid5);
						break;

					case 1:
						Intent cid42 = new Intent(getApplicationContext(),
								TopicActivity.class);
						cid42.putExtra("cid", 42);
						startActivity(cid42);
						break;

					case 2:
						Intent cid43 = new Intent(getApplicationContext(),
								TopicActivity.class);
						cid43.putExtra("cid", 43);
						startActivity(cid43);
						break;

					case 3:
						Intent cid41 = new Intent(getApplicationContext(),
								TopicActivity.class);
						cid41.putExtra("cid", 41);
						startActivity(cid41);
						break;

					case 4:
						Intent cid40 = new Intent(getApplicationContext(),
								TopicActivity.class);
						cid40.putExtra("cid", 40);
						startActivity(cid40);
						break;

					case 5:
						Intent cid39 = new Intent(getApplicationContext(),
								TopicActivity.class);
						cid39.putExtra("cid", 39);
						startActivity(cid39);
						break;

					case 6:
						Intent cid38 = new Intent(getApplicationContext(),
								TopicActivity.class);
						cid38.putExtra("cid", 38);
						startActivity(cid38);
						break;

					case 7:
						Intent cid17 = new Intent(getApplicationContext(),
								TopicActivity.class);
						cid17.putExtra("cid", 17);
						startActivity(cid17);
						break;

					case 8:
						Intent cid18 = new Intent(getApplicationContext(),
								TopicActivity.class);
						cid18.putExtra("cid", 18);
						startActivity(cid18);
						break;

					case 9:
						Intent cid36 = new Intent(getApplicationContext(),
								TopicActivity.class);
						cid36.putExtra("cid", 36);
						startActivity(cid36);
						break;
					}
				}
			});
			break;

		case 3:

			HashMap<String, String> map1c = new HashMap<String, String>();
			map1c.put(KEY_TITLE, "پیامبر اکرم صلی الله علیه و آله");
			map1c.put(KEY_THUMBNAIL, "topic");
			listview_main.add(map1c);

			HashMap<String, String> map2c = new HashMap<String, String>();
			map2c.put(KEY_TITLE, "حضرت فاطمه زهرا سلام الله علیها");
			map2c.put(KEY_THUMBNAIL, "topic");
			listview_main.add(map2c);

			HashMap<String, String> map3c = new HashMap<String, String>();
			map3c.put(KEY_TITLE, "امام حسن علیه السلام");
			map3c.put(KEY_THUMBNAIL, "topic");
			listview_main.add(map3c);

			HashMap<String, String> map4c = new HashMap<String, String>();
			map4c.put(KEY_TITLE, "امام حسین علیه السلام");
			map4c.put(KEY_THUMBNAIL, "topic");
			listview_main.add(map4c);

			HashMap<String, String> map5c = new HashMap<String, String>();
			map5c.put(KEY_TITLE, "امام زین العابدین علیه السلام");
			map5c.put(KEY_THUMBNAIL, "topic");
			listview_main.add(map5c);

			HashMap<String, String> map6c = new HashMap<String, String>();
			map6c.put(KEY_TITLE, "امام محمد باقر علیه السلام");
			map6c.put(KEY_THUMBNAIL, "topic");
			listview_main.add(map6c);

			HashMap<String, String> map7c = new HashMap<String, String>();
			map7c.put(KEY_TITLE, "امام جعفر صادق علیه السلام");
			map7c.put(KEY_THUMBNAIL, "topic");
			listview_main.add(map7c);

			HashMap<String, String> map8c = new HashMap<String, String>();
			map8c.put(KEY_TITLE, "امام موسی کاظم علیه السلام");
			map8c.put(KEY_THUMBNAIL, "topic");
			listview_main.add(map8c);

			HashMap<String, String> map9c = new HashMap<String, String>();
			map9c.put(KEY_TITLE, "امام رضا علیه السلام");
			map9c.put(KEY_THUMBNAIL, "topic");
			listview_main.add(map9c);

			HashMap<String, String> map10c = new HashMap<String, String>();
			map10c.put(KEY_TITLE, "امام محمد تقی علیه السلام");
			map10c.put(KEY_THUMBNAIL, "topic");
			listview_main.add(map10c);

			HashMap<String, String> map11c = new HashMap<String, String>();
			map11c.put(KEY_TITLE, "امام علی نقی علیه السلام");
			map11c.put(KEY_THUMBNAIL, "topic");
			listview_main.add(map11c);

			HashMap<String, String> map12c = new HashMap<String, String>();
			map12c.put(KEY_TITLE, "امام حسن عسکری علیه السلام");
			map12c.put(KEY_THUMBNAIL, "topic");
			listview_main.add(map12c);

			HashMap<String, String> map13c = new HashMap<String, String>();
			map13c.put(KEY_TITLE, "حضرت مهدی عجل الله فرجه");
			map13c.put(KEY_THUMBNAIL, "topic");
			listview_main.add(map13c);

			HashMap<String, String> map14c = new HashMap<String, String>();
			map14c.put(KEY_TITLE, "متن ادبی");
			map14c.put(KEY_THUMBNAIL, "topic");
			listview_main.add(map14c);

			// Getting adapter by ArrayList
			adapter = new MainListAdapter(this, listview_main);

			// Set custom list view
			lv = (ListView) findViewById(R.id.listView1);
			lv.setAdapter(adapter);
			lv.setDivider(null);
			lv.setTextFilterEnabled(true);
			lv.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					switch (arg2) {
					case 0:
						Intent cid46 = new Intent(getApplicationContext(),
								TopicActivity.class);
						cid46.putExtra("cid", 46);
						startActivity(cid46);
						break;

					case 1:
						Intent cid37 = new Intent(getApplicationContext(),
								TopicActivity.class);
						cid37.putExtra("cid", 37);
						startActivity(cid37);
						break;

					case 2:
						Intent cid20 = new Intent(getApplicationContext(),
								TopicActivity.class);
						cid20.putExtra("cid", 20);
						startActivity(cid20);
						break;

					case 3:
						Intent cid21 = new Intent(getApplicationContext(),
								TopicActivity.class);
						cid21.putExtra("cid", 21);
						startActivity(cid21);
						break;

					case 4:
						Intent cid22 = new Intent(getApplicationContext(),
								TopicActivity.class);
						cid22.putExtra("cid", 22);
						startActivity(cid22);
						break;

					case 5:
						Intent cid23 = new Intent(getApplicationContext(),
								TopicActivity.class);
						cid23.putExtra("cid", 23);
						startActivity(cid23);
						break;

					case 6:
						Intent cid24 = new Intent(getApplicationContext(),
								TopicActivity.class);
						cid24.putExtra("cid", 24);
						startActivity(cid24);
						break;

					case 7:
						Intent cid25 = new Intent(getApplicationContext(),
								TopicActivity.class);
						cid25.putExtra("cid", 25);
						startActivity(cid25);
						break;

					case 8:
						Intent cid26 = new Intent(getApplicationContext(),
								TopicActivity.class);
						cid26.putExtra("cid", 26);
						startActivity(cid26);
						break;

					case 9:
						Intent cid27 = new Intent(getApplicationContext(),
								TopicActivity.class);
						cid27.putExtra("cid", 27);
						startActivity(cid27);
						break;

					case 10:
						Intent cid28 = new Intent(getApplicationContext(),
								TopicActivity.class);
						cid28.putExtra("cid", 28);
						startActivity(cid28);
						break;

					case 11:
						Intent cid29 = new Intent(getApplicationContext(),
								TopicActivity.class);
						cid29.putExtra("cid", 29);
						startActivity(cid29);
						break;

					case 12:
						Intent cid30 = new Intent(getApplicationContext(),
								TopicActivity.class);
						cid30.putExtra("cid", 30);
						startActivity(cid30);
						break;
					}
				}
			});
			break;

		case 4:

			HashMap<String, String> map1d = new HashMap<String, String>();
			map1d.put(KEY_TITLE, "حضرت رقیه سلام الله علیها");
			map1d.put(KEY_THUMBNAIL, "topic");
			listview_main.add(map1d);

			HashMap<String, String> map2d = new HashMap<String, String>();
			map2d.put(KEY_TITLE, "حضرت زینب سلام الله علیها");
			map2d.put(KEY_THUMBNAIL, "topic");
			listview_main.add(map2d);

			HashMap<String, String> map3d = new HashMap<String, String>();
			map3d.put(KEY_TITLE, "حضرت معصومه سلام الله علیها");
			map3d.put(KEY_THUMBNAIL, "topic");
			listview_main.add(map3d);

			HashMap<String, String> map4d = new HashMap<String, String>();
			map4d.put(KEY_TITLE, "حضرت عباس علیه السلام");
			map4d.put(KEY_THUMBNAIL, "topic");
			listview_main.add(map4d);

			// Getting adapter by ArrayList
			adapter = new MainListAdapter(this, listview_main);

			// Set custom list view
			lv = (ListView) findViewById(R.id.listView1);
			lv.setAdapter(adapter);
			lv.setDivider(null);
			lv.setTextFilterEnabled(true);
			lv.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					switch (arg2) {
					case 0:
						Intent cid35 = new Intent(getApplicationContext(),
								TopicActivity.class);
						cid35.putExtra("cid", 35);
						startActivity(cid35);
						break;

					case 1:
						Intent cid34 = new Intent(getApplicationContext(),
								TopicActivity.class);
						cid34.putExtra("cid", 34);
						startActivity(cid34);
						break;

					case 2:
						Intent cid33 = new Intent(getApplicationContext(),
								TopicActivity.class);
						cid33.putExtra("cid", 33);
						startActivity(cid33);
						break;

					case 3:
						Intent cid32 = new Intent(getApplicationContext(),
								TopicActivity.class);
						cid32.putExtra("cid", 32);
						startActivity(cid32);
						break;
					}
				}
			});
			break;

		case 5:

			HashMap<String, String> map1e = new HashMap<String, String>();
			map1e.put(KEY_TITLE, "ماه رجب");
			map1e.put(KEY_THUMBNAIL, "topic");
			listview_main.add(map1e);

			HashMap<String, String> map2e = new HashMap<String, String>();
			map2e.put(KEY_TITLE, "ماه شعبان");
			map2e.put(KEY_THUMBNAIL, "topic");
			listview_main.add(map2e);

			HashMap<String, String> map3e = new HashMap<String, String>();
			map3e.put(KEY_TITLE, "ماه رمضان");
			map3e.put(KEY_THUMBNAIL, "topic");
			listview_main.add(map3e);

			HashMap<String, String> map4e = new HashMap<String, String>();
			map4e.put(KEY_TITLE, "ماه شوال");
			map4e.put(KEY_THUMBNAIL, "topic");
			listview_main.add(map4e);

			HashMap<String, String> map5e = new HashMap<String, String>();
			map5e.put(KEY_TITLE, "ماه ذی القعده");
			map5e.put(KEY_THUMBNAIL, "topic");
			listview_main.add(map5e);

			HashMap<String, String> map6e = new HashMap<String, String>();
			map6e.put(KEY_TITLE, "ماه ذی الحجه");
			map6e.put(KEY_THUMBNAIL, "topic");
			listview_main.add(map6e);

			HashMap<String, String> map7e = new HashMap<String, String>();
			map7e.put(KEY_TITLE, "ماه محرم");
			map7e.put(KEY_THUMBNAIL, "topic");
			listview_main.add(map7e);

			HashMap<String, String> map8e = new HashMap<String, String>();
			map8e.put(KEY_TITLE, "ماه صفر");
			map8e.put(KEY_THUMBNAIL, "topic");
			listview_main.add(map8e);

			HashMap<String, String> map9e = new HashMap<String, String>();
			map9e.put(KEY_TITLE, "ماه ربیع الاول");
			map9e.put(KEY_THUMBNAIL, "topic");
			listview_main.add(map9e);

			HashMap<String, String> map10e = new HashMap<String, String>();
			map10e.put(KEY_TITLE, "ماه ربیع الثانی");
			map10e.put(KEY_THUMBNAIL, "topic");
			listview_main.add(map10e);

			HashMap<String, String> map11e = new HashMap<String, String>();
			map11e.put(KEY_TITLE, "ماه جمادی الاول");
			map11e.put(KEY_THUMBNAIL, "topic");
			listview_main.add(map11e);

			HashMap<String, String> map12e = new HashMap<String, String>();
			map12e.put(KEY_TITLE, "ماه جمادی الثانی");
			map12e.put(KEY_THUMBNAIL, "topic");
			listview_main.add(map12e);

			// Getting adapter by ArrayList
			adapter = new MainListAdapter(this, listview_main);

			// Set custom list view
			lv = (ListView) findViewById(R.id.listView1);
			lv.setAdapter(adapter);
			lv.setDivider(null);
			lv.setTextFilterEnabled(true);
			lv.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					switch (arg2) {
					case 0:
						Intent cid45 = new Intent(getApplicationContext(),
								TopicActivity.class);
						cid45.putExtra("cid", 45);
						startActivity(cid45);
						break;

					case 1:
						Intent cid47 = new Intent(getApplicationContext(),
								TopicActivity.class);
						cid47.putExtra("cid", 47);
						startActivity(cid47);
						break;

					case 2:
						Intent cid48 = new Intent(getApplicationContext(),
								TopicActivity.class);
						cid48.putExtra("cid", 48);
						startActivity(cid48);
						break;

					case 3:
						Intent cid49 = new Intent(getApplicationContext(),
								TopicActivity.class);
						cid49.putExtra("cid", 49);
						startActivity(cid49);
						break;

					case 4:
						Intent cid50 = new Intent(getApplicationContext(),
								TopicActivity.class);
						cid50.putExtra("cid", 50);
						startActivity(cid50);
						break;

					case 5:
						Intent cid51 = new Intent(getApplicationContext(),
								TopicActivity.class);
						cid51.putExtra("cid", 51);
						startActivity(cid51);
						break;

					case 6:
						Intent cid52 = new Intent(getApplicationContext(),
								TopicActivity.class);
						cid52.putExtra("cid", 52);
						startActivity(cid52);
						break;

					case 7:
						Intent cid53 = new Intent(getApplicationContext(),
								TopicActivity.class);
						cid53.putExtra("cid", 53);
						startActivity(cid53);
						break;

					case 8:
						Intent cid54 = new Intent(getApplicationContext(),
								TopicActivity.class);
						cid54.putExtra("cid", 54);
						startActivity(cid54);
						break;

					case 9:
						Intent cid55 = new Intent(getApplicationContext(),
								TopicActivity.class);
						cid55.putExtra("cid", 55);
						startActivity(cid55);
						break;

					case 10:
						Intent cid56 = new Intent(getApplicationContext(),
								TopicActivity.class);
						cid56.putExtra("cid", 56);
						startActivity(cid56);
						break;

					case 11:
						Intent cid57 = new Intent(getApplicationContext(),
								TopicActivity.class);
						cid57.putExtra("cid", 57);
						startActivity(cid57);
						break;
					}
				}
			});
			break;

		case 6:

			// Check Database and connection
			DatabaseHandler db = new DatabaseHandler(this);
			int count = db.getStoryCount(58);
			if (count == 0) {
				ConnectionDetector cd = new ConnectionDetector(getApplicationContext());
				Boolean isInternetPresent = cd.isConnectingToInternet();
				if (isInternetPresent) {
					new GetListStory().execute();
				} else {
					showAlertDialog(ArticleActivity.this,
							getResources().getString(R.string.d_internet_access));
				}
			}
			

			HashMap<String, String> map1f = new HashMap<String, String>();
			map1f.put(KEY_TITLE, "فراز یک - حمد و ثنای الهی");
			map1f.put(KEY_THUMBNAIL, "ghadir");
			listview_main.add(map1f);

			HashMap<String, String> map2f = new HashMap<String, String>();
			map2f.put(KEY_TITLE, "فراز دو - فرمان الهی");
			map2f.put(KEY_THUMBNAIL, "ghadir");
			listview_main.add(map2f);

			HashMap<String, String> map3f = new HashMap<String, String>();
			map3f.put(KEY_TITLE, "فراز سه - اعلام رسمی ولایت و امامت دوازده امام");
			map3f.put(KEY_THUMBNAIL, "ghadir");
			listview_main.add(map3f);

			HashMap<String, String> map4f = new HashMap<String, String>();
			map4f.put(KEY_TITLE, "فراز چهار - معرفی علی بن ابیطالب(ع)");
			map4f.put(KEY_THUMBNAIL, "ghadir");
			listview_main.add(map4f);

			HashMap<String, String> map5f = new HashMap<String, String>();
			map5f.put(KEY_TITLE, "فراز پنج - اهمیّت مسأله امامت");
			map5f.put(KEY_THUMBNAIL, "ghadir");
			listview_main.add(map5f);

			HashMap<String, String> map6f = new HashMap<String, String>();
			map6f.put(KEY_TITLE, "فراز شش - خطر انحراف و کار شکنی");
			map6f.put(KEY_THUMBNAIL, "ghadir");
			listview_main.add(map6f);

			HashMap<String, String> map7f = new HashMap<String, String>();
			map7f.put(KEY_TITLE, "فراز هفت - معرفی دوستان و دشمنان");
			map7f.put(KEY_THUMBNAIL, "ghadir");
			listview_main.add(map7f);

			HashMap<String, String> map8f = new HashMap<String, String>();
			map8f.put(KEY_TITLE, "فراز هشت - معرفی حضرت مهدی (عج)");
			map8f.put(KEY_THUMBNAIL, "ghadir");
			listview_main.add(map8f);

			HashMap<String, String> map9f = new HashMap<String, String>();
			map9f.put(KEY_TITLE, "فراز نه - طرح مسئله بیعت");
			map9f.put(KEY_THUMBNAIL, "ghadir");
			listview_main.add(map9f);

			HashMap<String, String> map10f = new HashMap<String, String>();
			map10f.put(KEY_TITLE, "فراز ده - حج");
			map10f.put(KEY_THUMBNAIL, "ghadir");
			listview_main.add(map10f);

			HashMap<String, String> map11f = new HashMap<String, String>();
			map11f.put(KEY_TITLE, "فراز یازده - احکام الهی");
			map11f.put(KEY_THUMBNAIL, "ghadir");
			listview_main.add(map11f);

			HashMap<String, String> map12f = new HashMap<String, String>();
			map12f.put(KEY_TITLE, "فراز دوازده - تنها راه هدایت");
			map12f.put(KEY_THUMBNAIL, "ghadir");
			listview_main.add(map12f);

			HashMap<String, String> map13f = new HashMap<String, String>();
			map13f.put(KEY_TITLE, "فراز سیزده - بیعت گرفتن");
			map13f.put(KEY_THUMBNAIL, "ghadir");
			listview_main.add(map13f);

			// Getting adapter by ArrayList
			adapter = new MainListAdapter(this, listview_main);

			// Set custom list view
			lv = (ListView) findViewById(R.id.listView1);
			lv.setAdapter(adapter);
			lv.setDivider(null);
			lv.setTextFilterEnabled(true);
			lv.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					switch (arg2) {
					case 0:
						Intent ghadir1 = new Intent(getApplicationContext(),
								StoryActivity.class);
						ghadir1.putExtra("id", 849);
						startActivity(ghadir1);
						break;

					case 1:
						Intent ghadir2 = new Intent(getApplicationContext(),
								StoryActivity.class);
						ghadir2.putExtra("id", 854);
						startActivity(ghadir2);
						break;

					case 2:
						Intent ghadir3 = new Intent(getApplicationContext(),
								StoryActivity.class);
						ghadir3.putExtra("id", 855);
						startActivity(ghadir3);
						break;

					case 3:
						Intent ghadir4 = new Intent(getApplicationContext(),
								StoryActivity.class);
						ghadir4.putExtra("id", 856);
						startActivity(ghadir4);
						break;

					case 4:
						Intent ghadir5 = new Intent(getApplicationContext(),
								StoryActivity.class);
						ghadir5.putExtra("id", 857);
						startActivity(ghadir5);
						break;

					case 5:
						Intent ghadir6 = new Intent(getApplicationContext(),
								StoryActivity.class);
						ghadir6.putExtra("id", 858);
						startActivity(ghadir6);
						break;

					case 6:
						Intent ghadir7 = new Intent(getApplicationContext(),
								StoryActivity.class);
						ghadir7.putExtra("id", 859);
						startActivity(ghadir7);
						break;

					case 7:
						Intent ghadir8 = new Intent(getApplicationContext(),
								StoryActivity.class);
						ghadir8.putExtra("id", 860);
						startActivity(ghadir8);
						break;

					case 8:
						Intent ghadir9 = new Intent(getApplicationContext(),
								StoryActivity.class);
						ghadir9.putExtra("id", 861);
						startActivity(ghadir9);
						break;

					case 9:
						Intent ghadir10 = new Intent(getApplicationContext(),
								StoryActivity.class);
						ghadir10.putExtra("id", 862);
						startActivity(ghadir10);
						break;

					case 10:
						Intent ghadir11 = new Intent(getApplicationContext(),
								StoryActivity.class);
						ghadir11.putExtra("id", 863);
						startActivity(ghadir11);
						break;

					case 11:
						Intent ghadir12 = new Intent(getApplicationContext(),
								StoryActivity.class);
						ghadir12.putExtra("id", 864);
						startActivity(ghadir12);
						break;

					case 12:
						Intent ghadir13 = new Intent(getApplicationContext(),
								StoryActivity.class);
						ghadir13.putExtra("id", 865);
						startActivity(ghadir13);
						break;
					}
				}
			});
			break;
		}
	}
	
	private class GetListStory extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			progressDialog = new ProgressDialog(ArticleActivity.this);
			progressDialog.setTitle(getResources().getString(
					R.string.d_processing));
			progressDialog
					.setMessage(getResources().getString(R.string.d_wait));
			progressDialog.setCancelable(true);
			progressDialog.show();
		}

		@Override
		protected String doInBackground(String... ars) {

			DatabaseHandler db = new DatabaseHandler(ArticleActivity.this);
			
			
			String url = "http://www.ghadirekhom.com/modules/news/ajax.php?op=liststory&storyid=0&storytopic=58&limit=15";
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
				intent.setClass(ArticleActivity.this, MainActivity.class);
				ArticleActivity.this.startActivity(intent);
				ArticleActivity.this.finish();
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
