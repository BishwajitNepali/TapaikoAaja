package com.thangLaStudio.Adapter;

import java.util.ArrayList;
import java.util.List;

import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;
import com.thangLaStudio.Model.Rashi;
import com.thangLaStudio.today.R;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends ArrayAdapter<Rashi> {
	private List<Rashi> rashi;
	final Context context;
	TextView words;
	ImageView icon;

	public CustomAdapter(Context context, List<Rashi> rashi) {
		// TODO Auto-generated constructor stub

		super(context, R.layout.activity_row, rashi);
		this.context = context;
		this.rashi = rashi;
		Log.e("Inside", "Custom Adapter");
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View v = convertView;

		// First let's verify the convertView is not null

		// This a new view we inflate the new layout
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		v = inflater.inflate(R.layout.activity_row, null);

		try {

			words = (TextView) v.findViewById(R.id.words);
			icon = (ImageView) v.findViewById(R.id.icon);

			words.setText(rashi.get(position).getIrashiwords());
			UrlImageViewHelper.setUrlDrawable(icon, rashi.get(position)
					.getIimageLink());

		} catch (Exception e) {
			// TODO: handle exception
		}

		return v;
	}
}