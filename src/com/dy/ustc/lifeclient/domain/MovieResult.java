package com.dy.ustc.lifeclient.domain;

import org.json.JSONObject;


public class MovieResult extends JSONObject{
	
	/*public String date;
	
	public int error;
	
	public String status;
	
	public List<move> result;
	
	
	public static class move{*/
		
		public int is_imax;
		
		public int is_new;
		
		public String movie_big_picture;
		
		public String movie_director;
		
		public long movie_id;
		
		public int movie_length;
		
		public String movie_message;
		
		public String movie_name;
		
		public String movie_nation;
		
		public String movie_picture;
	
		public String movie_release_date;
	
		public int movie_score;
		
		public String movie_starring;
		
		public String movie_tags;
		
		public String movie_type;
		
		public String movies_wd;

		@Override
		public String toString() {
			return "MovieResult [is_imax=" + is_imax + ", is_new=" + is_new
					+ ", movie_big_picture=" + movie_big_picture
					+ ", movie_director=" + movie_director + ", movie_id="
					+ movie_id + ", movie_length=" + movie_length
					+ ", movie_message=" + movie_message + ", movie_name="
					+ movie_name + ", movie_nation=" + movie_nation
					+ ", movie_picture=" + movie_picture
					+ ", movie_release_date=" + movie_release_date
					+ ", movie_score=" + movie_score + ", movie_starring="
					+ movie_starring + ", movie_tags=" + movie_tags
					+ ", movie_type=" + movie_type + ", movies_wd=" + movies_wd
					+ "]";
		}
		
		
		
		
		
		
//	}

}
