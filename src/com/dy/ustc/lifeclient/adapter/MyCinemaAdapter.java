package com.dy.ustc.lifeclient.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dy.ustc.lifeclient.R;
import com.dy.ustc.lifeclient.domain.CinemaItem;

public class MyCinemaAdapter extends ArrayAdapter<CinemaItem> {

	private int resourceId;

	public MyCinemaAdapter(Context context, int textViewResourceId,
			List<CinemaItem> objects) {
		super(context, textViewResourceId, objects);
		resourceId = textViewResourceId;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		CinemaItem cinemaItem  = getItem(position);
		ViewHolder holder = null;
		View view;
		if (convertView == null) {
			holder = new ViewHolder();
			view = LayoutInflater.from(getContext()).inflate(resourceId, null);
			holder.name = (TextView) view.findViewById(R.id.name);
			holder.address = (TextView) view.findViewById(R.id.address);
			holder.rating = (TextView) view.findViewById(R.id.rating);
			view.setTag(holder);
		} else {
			view = convertView;
			holder = (ViewHolder) view.getTag();
		}
		holder.rating.setText(cinemaItem.getRating());
		System.out.println(cinemaItem.getRating());

		holder.address.setText(cinemaItem.getAddress());
		
		System.out.println(cinemaItem.getAddress());
		
		holder.name.setText(cinemaItem.getName());
		
		System.out.println(cinemaItem.getName());
		
		return view;
	}

	static class ViewHolder {
		TextView name;
		TextView address;
		TextView rating;
	}

}
