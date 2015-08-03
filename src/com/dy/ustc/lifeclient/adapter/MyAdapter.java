package com.dy.ustc.lifeclient.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dy.ustc.lifeclient.R;
import com.dy.ustc.lifeclient.domain.MovieItem;

public class MyAdapter extends ArrayAdapter<MovieItem> {

	private int resourceId;

	public MyAdapter(Context context, int textViewResourceId,
			List<MovieItem> objects) {
		super(context, textViewResourceId, objects);
		resourceId = textViewResourceId;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		MovieItem movieItem = getItem(position);
		ViewHolder holder = null;
		View view;
		if (convertView == null) {
			holder = new ViewHolder();
			view = LayoutInflater.from(getContext()).inflate(resourceId, null);
			holder.actors = (TextView) view.findViewById(R.id.actor);
			holder.avatar = (ImageView) view.findViewById(R.id.avatar);
			holder.desc = (TextView) view.findViewById(R.id.desc);
			holder.score = (TextView) view.findViewById(R.id.score);
			view.setTag(holder);
		} else {
			view = convertView;
			holder = (ViewHolder) view.getTag();
		}
		holder.actors.setText(movieItem.getActors());
		System.out.println(movieItem.getActors());

		holder.desc.setText(movieItem.getDesc());
		
		holder.score.setText(movieItem.getScore() + "·Ö");
		Glide.with(getContext()).load(movieItem.getImageId())
				.into(holder.avatar);
		return view;
	}

	static class ViewHolder {
		TextView actors;
		TextView desc;
		TextView score;
		ImageView avatar;
	}

}
