package com.dy.ustc.lifeclient;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dy.ustc.lifeclient.domain.MovieItem;
import com.github.clans.fab.FloatingActionButton;

public class MovieDetailsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_moviedetail);

		FloatingActionButton btnLocation = (FloatingActionButton) findViewById(R.id.fab);

		btnLocation.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MovieDetailsActivity.this,
						NearbyCinemaActivity.class);
				startActivity(intent);

			}
		});

		ActionBar actionBar = getActionBar();

		actionBar.setDisplayHomeAsUpEnabled(true);

		TextView movie_name = (TextView) findViewById(R.id.movie_name);
		ImageView movie_big_avatar = (ImageView) findViewById(R.id.movie_big_avatar);
		TextView movie_director = (TextView) findViewById(R.id.movie_director);
		TextView movie_actor = (TextView) findViewById(R.id.movie_actor);
		TextView movie_brief_desc = (TextView) findViewById(R.id.movie_brief_desc);
		TextView movie_release_time = (TextView) findViewById(R.id.movie_release_time);
		TextView movie_time = (TextView) findViewById(R.id.movie_time);

		MovieItem movieItem = getIntent().getParcelableExtra("movie_detail");

		String movieName = movieItem.getMovieName();
		String bigImageId = movieItem.getBigImageId();
		String movieDirector = movieItem.getMovieDirector();

		String actors = movieItem.getActors();

		String desc = movieItem.getDesc();

		String releaseTime = movieItem.getReleaseTime();

		String movieTime = movieItem.getMovieTime();

		movie_name.setText(movieName);

		Glide.with(this).load(bigImageId).into(movie_big_avatar);

		movie_director.setText("导演:" + movieDirector);
		movie_actor.setText("主演:" + actors);
		movie_brief_desc.setText("简介:" + desc);
		movie_release_time.setText("发行时间:" + releaseTime);

		movie_time.setText("时长:" + movieTime);

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			return true;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

}
