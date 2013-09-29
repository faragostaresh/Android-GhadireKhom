package com.ghadirekhom.activity;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ghadirekhom.extra.ConnectionDetector;
import com.google.analytics.tracking.android.EasyTracker;

public class ContactActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.activity_contact);

		// Check connection
		ConnectionDetector cd = new ConnectionDetector(getApplicationContext());
		Boolean isInternetPresent = cd.isConnectingToInternet();

		if (!isInternetPresent) {
			showAlertDialog(ContactActivity.this,
					getResources().getString(R.string.d_internet_contact));
		}

		// Do after press send button
		final Button send = (Button) this.findViewById(R.id.sendemail_intent);
		send.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// Send As Post
				sendAsPost();
				// Back to index
				startActivity(new Intent(getApplicationContext(),
						MainActivity.class));
			}
		});
	}

	public void sendAsPost() {
		// Make and Run Thread for send form as post
		new Thread(new Runnable() {
			@Override
			public void run() {

				// Get , Set , Check Email
				EditText editTextEmail = (EditText) findViewById(R.id.editTextEmail);
				String TextEmail = editTextEmail.getText().toString();
				if (TextEmail.matches("")) {
					// showAlertDialog(ContactActivity.this,
					// getResources().getString(R.string.d_internet_contact));
				}

				// Get , Set , Check Name
				EditText editTextName = (EditText) findViewById(R.id.editTextName);
				String TextName = editTextName.getText().toString();
				if (TextName.matches("")) {
					// showAlertDialog(ContactActivity.this, getResources()
					// .getString(R.string.d_content));
				}

				// Get , Set , Check Subject
				EditText editTextSubject = (EditText) findViewById(R.id.editTextSubject);
				String TextSubject = editTextSubject.getText().toString();
				if (TextSubject.matches("")) {
					// showAlertDialog(ContactActivity.this, getResources()
					// .getString(R.string.d_content));
				}

				// Get , Set , Check Message
				EditText editTextMessage = (EditText) findViewById(R.id.editTextMessage);
				String TextMessage = editTextMessage.getText().toString();
				if (TextMessage.matches("")) {
					// showAlertDialog(ContactActivity.this, getResources()
					// .getString(R.string.d_content));
				}

				// Send
				HttpClient postClient = new DefaultHttpClient();
				String postReq = "http://www.ghadirekhom.com/modules/contact/ajax.php";
				HttpPost request = new HttpPost(postReq);
				List<NameValuePair> postParams = new ArrayList<NameValuePair>();
				postParams
						.add(new BasicNameValuePair("contact_mail", TextEmail));
				postParams
						.add(new BasicNameValuePair("contact_name", TextName));
				postParams.add(new BasicNameValuePair("contact_subject",
						TextSubject));
				postParams.add(new BasicNameValuePair("contact_message",
						TextMessage));
				UrlEncodedFormEntity postEntity = null;
				try {
					postEntity = new UrlEncodedFormEntity(postParams, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				request.setEntity(postEntity);
				@SuppressWarnings("unused")
				HttpResponse response = null;
				try {
					response = postClient.execute(request);
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	@SuppressWarnings("deprecation")
	public void showAlertDialog(Context context, String Message) {
		AlertDialog alertDialog = new AlertDialog.Builder(context).create();
		// Setting Dialog Title
		alertDialog.setTitle(getResources().getString(R.string.d_error));
		// Setting Dialog Message
		alertDialog.setMessage(Message);
		// Setting alert dialog icon
		alertDialog.setIcon(R.drawable.cancel);
		// Setting OK Button
		alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Intent intent = new Intent();
				intent.setClass(ContactActivity.this, MainActivity.class);
				ContactActivity.this.startActivity(intent);
				ContactActivity.this.finish();
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
