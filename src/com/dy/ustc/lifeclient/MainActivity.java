package com.dy.ustc.lifeclient;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.dy.ustc.lifeclient.adapter.MyAdapter;
import com.dy.ustc.lifeclient.domain.MovieItem;
import com.dy.ustc.lifeclient.utils.HttpCallBackListener;
import com.dy.ustc.lifeclient.utils.NetworkUtil;

public class MainActivity extends Activity {

	public static final String TAG = "MainActivity";

	private List<MovieItem> movieItems = new ArrayList<MovieItem>();

	private ListView list;

	private MyAdapter myAdapter;

	String address = "http://api.map.baidu.com/telematics/v3/movie?qt=hot_movie&location=%E5%90%88%E8%82%A5&output=json&ak=ts49h7nDhPEAL0qgsYX5N1qt";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		list = (ListView) findViewById(R.id.list);

		initData();

		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				MovieItem movieItem = movieItems.get(position);

				Intent intent = new Intent(MainActivity.this,
						MovieDetailsActivity.class);

				intent.putExtra("movie_detail", movieItem);

				startActivity(intent);
			}
		});

	}

	private void initData() {
		NetworkUtil.sendHttpRequest(address, new HttpCallBackListener() {

			@Override
			public void onFinish(String response) {
				// System.out.println(response);

				// Log.i(TAG, response);

				try {
					JSONObject jsonObject = new JSONObject(response);

					JSONArray jsonArray = jsonObject.getJSONObject("result")
							.getJSONArray("movie");

					for (int i = 0; i < jsonArray.length(); i++) {
						// System.out.println(jsonArray.get(i).toString());

						JSONObject object = (JSONObject) jsonArray.get(i);

						String movie_starring = object
								.getString("movie_starring");

						String movie_message = object
								.getString("movie_message");

						String movie_picture = object
								.getString("movie_picture");

						String movie_score = object.getString("movie_score");

						String movie_big_picture = object
								.getString("movie_big_picture");

						String movieName = object.getString("movie_name");
						String movie_director = object
								.getString("movie_director");
						String movie_release_date = object
								.getString("movie_release_date");
						String movieTime = object.getString("movie_length");

						MovieItem movieItem = new MovieItem(movie_starring,
								movie_message, movie_picture, movie_score,
								movie_big_picture, movieName, movie_director,
								movie_release_date, movieTime);
						
						movieItem.setActors(movie_starring);
						movieItem.setBigImageId(movie_big_picture);
						movieItem.setDesc(movie_message);
						movieItem.setImageId(movie_picture);
						movieItem.setMovieDirector(movie_director);
						movieItem.setMovieName(movieName);
						movieItem.setMovieTime(movieTime);
						movieItem.setReleaseTime(movie_release_date);
						movieItem.setScore(movie_score);
						

						movieItems.add(movieItem);

					}

				} catch (Exception e) {
					e.printStackTrace();
				}

				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						myAdapter = new MyAdapter(MainActivity.this,
								R.layout.item, movieItems);

						list.setAdapter(myAdapter);

					}
				});

			}

			@Override
			public void onError(Exception e) {
				Log.i(TAG, e.getMessage());
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.item, menu);
		return true;
	}

}
