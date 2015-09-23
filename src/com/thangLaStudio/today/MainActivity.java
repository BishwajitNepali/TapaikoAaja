package com.thangLaStudio.today;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.admob.android.ads.AdView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.Request.Method;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.thangLaStudio.Adapter.CustomAdapter;
import com.thangLaStudio.Model.Rashi;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
	ListView rashiListView;
	RequestQueue request;
	final String TAG_IMAGE = "circle_image";
	final String TAG_RASHI_WORDS = "panelheading_value";
	final String TAG_RASHI = "panelbody_description";
	ArrayList<Rashi> rashiList = new ArrayList<Rashi>();
	CustomAdapter adapter;
	TextView date_text;

	final String url = "https://api.import.io/store/data/091cca2d-d594-49f4-83df-0de3e40ca719/_query?input/webpage/url=http%3A%2F%2Fnepalipatro.com.np%2Frashifal%2Findex%2Ftype%2Fdaily&_user=1f737b29-ee16-4559-9519-770235ddd211&_apikey=1f737b29ee1645599519770235ddd2118bddb15a432b7b4c0d803011fa957ed8e3116922d0001b2a0eeb64b6c2684e3cd1ab92160010b7ad7612e603c5e9fdbd0f0d81d83671f8dfd9d98ec48f9b3d98";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		

		rashiListView = (ListView) findViewById(R.id.rashiList);
		date_text=(TextView) findViewById(R.id.datetext);
		loadData();
		Log.e("below", "loaddata");
		loadDate();

		adapter = new CustomAdapter(MainActivity.this, rashiList);
		rashiListView.setAdapter(adapter);
		
		rashiListView
		.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View view,
					int position, long arg3) {
				
				Intent ttt=new Intent(MainActivity.this,Rashifal.class);
				ttt.putExtra("apple",rashiList.get(position).getIrashi()+"");
				Log.e("b4", rashiList.get(position).getIrashi()+"");
				startActivity(ttt);
				
			}
		});

	}

	private void loadDate() {
		// TODO Auto-generated method stub
		
		long date = System.currentTimeMillis(); 

		SimpleDateFormat sdf = new SimpleDateFormat("MMM MM dd, yyyy h:mm a");
		String dateString = sdf.format(date);   
		date_text.setText(dateString);
		
	}

	private ArrayList<Rashi> loadData() {
		// TODO Auto-generated method stub

		request = Volley.newRequestQueue(getApplicationContext());
		JsonObjectRequest jsonObjReq = new JsonObjectRequest(Method.GET, url,
				null, new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						/* Log.d("From Main Activity", response.toString()); */
						try {
							JSONArray jsonContentArray = response
									.getJSONArray("results");

							Log.e("JSON", jsonContentArray + "");

							for (int i = 0; i < jsonContentArray.length(); i++) {

								JSONObject data = jsonContentArray
										.getJSONObject(i);

								String icon = data.getString(TAG_IMAGE);
								String words = data.getString(TAG_RASHI_WORDS);
								String rashi_data = data.getString(TAG_RASHI);

								Log.e("icon Link", icon + "");
								Log.e("words", words + "");
								Log.e("data", rashi_data + "");

								Rashi r = new Rashi();
								r.setIimageLink(icon);
								r.setIrashiwords(words);
								r.setIrashi(rashi_data);

								rashiList.add(r);
								adapter.notifyDataSetChanged();

							}

						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						Log.e("From Main Activity",
								"Error: " + error.getMessage());
						// hide the progress dialog
						
						date_text.setText("Internet Not Available");
						date_text.setTextColor(Color.RED);

					}

				});
		request.add(jsonObjReq);
		return rashiList;

	}
}
