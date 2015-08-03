package com.dy.ustc.lifeclient;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.ActionBar;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationConfiguration.LocationMode;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.dy.ustc.lifeclient.adapter.MyCinemaAdapter;
import com.dy.ustc.lifeclient.domain.CinemaItem;
import com.dy.ustc.lifeclient.utils.HttpCallBackListener;
import com.dy.ustc.lifeclient.utils.NetworkUtil;
import com.github.clans.fab.FloatingActionButton;

public class NearbyCinemaActivity extends Activity {

	// ��λ���
	LocationClient mLocClient;
	public MyLocationListenner myListener = new MyLocationListenner();

	// private LocationMode tempMode = LocationMode.Hight_Accuracy;

	private LocationMode mCurrentMode;

	boolean isFirstLoc = true;// �Ƿ��״ζ�λ

	BitmapDescriptor mCurrentMarker;
	MapView mMapView;
	BaiduMap mBaiduMap;

	private FloatingActionButton fab;

	private String lat;
	private String lon;

	private SharedPreferences sp;

	
	private ListView list;
	
	private List<CinemaItem> cinemaItems = new ArrayList<CinemaItem>();
	
	private MyCinemaAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nearby_cinema);

		fab = (FloatingActionButton) findViewById(R.id.fab);
		
		list = (ListView) findViewById(R.id.list);

		sp = getSharedPreferences("config", MODE_PRIVATE);

		mCurrentMode = LocationMode.NORMAL;

		OnClickListener btnClickListener = new OnClickListener() {
			public void onClick(View v) {
				switch (mCurrentMode) {
				case NORMAL:
					fab.setImageResource(R.drawable.bg_talking_location);
					mCurrentMode = LocationMode.FOLLOWING;
					mBaiduMap
							.setMyLocationConfigeration(new MyLocationConfiguration(
									mCurrentMode, true, mCurrentMarker));
					break;
				case COMPASS:
					fab.setImageResource(R.drawable.bg_group_location);
					mCurrentMode = LocationMode.NORMAL;
					mBaiduMap
							.setMyLocationConfigeration(new MyLocationConfiguration(
									mCurrentMode, true, mCurrentMarker));
					break;
				case FOLLOWING:
					fab.setImageResource(R.drawable.bg_talking_location);
					mCurrentMode = LocationMode.COMPASS;
					mBaiduMap
							.setMyLocationConfigeration(new MyLocationConfiguration(
									mCurrentMode, true, mCurrentMarker));
					break;
				}
			}
		};
		fab.setOnClickListener(btnClickListener);

		/*
		 * mBaiduMap.setMyLocationConfigeration(new MyLocationConfiguration(
		 * mCurrentMode, true, null));
		 */

		// ��ͼ��ʼ��
		mMapView = (MapView) findViewById(R.id.bmapView);
		mBaiduMap = mMapView.getMap();
		// ������λͼ��
		mBaiduMap.setMyLocationEnabled(true);

		ActionBar actionBar = getActionBar();

		actionBar.setDisplayHomeAsUpEnabled(true);

		// ��λ��ʼ��
		mLocClient = new LocationClient(this);
		mLocClient.registerLocationListener(myListener);

		initLocation();

	
		
		initData();
		
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
			}
		});
	

	}

	private void initLocation() {
		LocationClientOption option = new LocationClientOption();
		option.setCoorType("bd09ll");// ���صĶ�λ����ǰٶȾ�γ�ȣ�Ĭ��ֵgcj02
		int span = 5 * 1000;
		option.setScanSpan(span);// ���÷���λ����ļ��ʱ��Ϊ5000ms
		option.setOpenGps(true);// ��gps
		mLocClient.setLocOption(option);
		mLocClient.start();

	}

	/**
	 * ��λSDK��������
	 */
	public class MyLocationListenner implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation location) {
			// map view ���ٺ��ڴ����½��յ�λ��
			if (location == null || mMapView == null)
				return;
			MyLocationData locData = new MyLocationData.Builder()
					.accuracy(location.getRadius())
					// �˴����ÿ����߻�ȡ���ķ�����Ϣ��˳ʱ��0-360
					.direction(100).latitude(location.getLatitude())
					.longitude(location.getLongitude()).build();
			mBaiduMap.setMyLocationData(locData);
			if (isFirstLoc) {
				isFirstLoc = false;
				LatLng ll = new LatLng(location.getLatitude(),
						location.getLongitude());
				MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);
				mBaiduMap.animateMapStatus(u);
			}

			lat = String.valueOf(location.getLatitude());

			lon = String.valueOf(location.getLongitude());

			Editor edit = sp.edit();
			edit.putString("lat", lat);

			edit.putString("lon", lon);
			edit.commit();

		}

		public void onReceivePoi(BDLocation poiLocation) {
		}
	}

	private void initData() {
		
		lat = sp.getString("lat", null);
		lon = sp.getString("lon", null);
		
		 String urlRequest = "http://api.map.baidu.com/telematics/v3/movie?qt=nearby_cinema&location="
				+ lon
				+ ","
				+ lat
				+ "&output=json&ak=ts49h7nDhPEAL0qgsYX5N1qt";

		System.out.println(urlRequest);
		
		String url = "http://api.map.baidu.com/telematics/v3/movie?qt=nearby_cinema&location=117.275366,31.843017&output=json&ak=ts49h7nDhPEAL0qgsYX5N1qt";

		NetworkUtil.sendHttpRequest(url, new HttpCallBackListener() {

			@Override
			public void onFinish(String response) {

				System.out.println(response);
				
				
				try {
					JSONObject jsonObject = new JSONObject(response);
					JSONArray jsonArray = jsonObject.getJSONArray("result");
					for (int i = 0; i < jsonArray.length(); i++) {
						JSONObject object = jsonArray.getJSONObject(i);
						
						String address = object.getString("address");
						
						
						
						String name = object.getString("name");
						
						String rating = object.getString("rating");
						
						String telephone = object.getString("telephone");
						
						
						System.out.println(address + rating + name + telephone + "dassssssssssssssssssssssssssssss");
						
						
						CinemaItem cinemaItem = new CinemaItem(name, address, rating);
						cinemaItem.setAddress(address);
						cinemaItem.setName(name);
						cinemaItem.setRating(rating);
						
						cinemaItems.add(cinemaItem);
						
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						adapter = new MyCinemaAdapter(NearbyCinemaActivity.this, R.layout.cinemaitem, cinemaItems);
						

						list.setAdapter(adapter);
						
					}
				});


			}

			@Override
			public void onError(Exception e) {

			}
		});
		
		
	}

	

	@Override
	protected void onPause() {
		mMapView.onPause();
		super.onPause();
	}

	@Override
	protected void onResume() {
		mMapView.onResume();
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		// �˳�ʱ���ٶ�λ
		mLocClient.stop();
		// �رն�λͼ��
		mBaiduMap.setMyLocationEnabled(false);
		mMapView.onDestroy();
		mMapView = null;
		super.onDestroy();
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
