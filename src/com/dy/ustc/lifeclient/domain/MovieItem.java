package com.dy.ustc.lifeclient.domain;

import android.os.Parcel;
import android.os.Parcelable;

public class MovieItem implements Parcelable{
	
	private String actors;
	
	private String desc;
	
	private String imageId;
	
	private String score;
	
	private String bigImageId;
	
	private String movieName;
	
	private String movieDirector;
	
	private String releaseTime;
	
	private String movieTime;
	
	
	
	public MovieItem() {
	}

	public MovieItem(String actors, String desc, String imageId, String score) {
		this.actors = actors;
		this.desc = desc;
		this.imageId = imageId;
		this.score = score;
	}

	
	
	public MovieItem(String actors, String desc, String imageId, String score,
			String bigImageId) {
		this.actors = actors;
		this.desc = desc;
		this.imageId = imageId;
		this.score = score;
		this.bigImageId = bigImageId;
	}
	
	

	public MovieItem(String actors, String desc, String imageId, String score,
			String bigImageId, String movieName, String movieDirector,
			String releaseTime, String movieTime) {
		this.actors = actors;
		this.desc = desc;
		this.imageId = imageId;
		this.score = score;
		this.bigImageId = bigImageId;
		this.movieName = movieName;
		this.movieDirector = movieDirector;
		this.releaseTime = releaseTime;
		this.movieTime = movieTime;
	}

	public String getActors() {
		return actors;
	}

	public void setActors(String actors) {
		this.actors = actors;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}
	
	

	public String getBigImageId() {
		return bigImageId;
	}

	public void setBigImageId(String bigImageId) {
		this.bigImageId = bigImageId;
	}

	

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getMovieDirector() {
		return movieDirector;
	}

	public void setMovieDirector(String movieDirector) {
		this.movieDirector = movieDirector;
	}

	public String getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(String releaseTime) {
		this.releaseTime = releaseTime;
	}

	public String getMovieTime() {
		return movieTime;
	}

	public void setMovieTime(String movieTime) {
		this.movieTime = movieTime;
	}

	

	@Override
	public String toString() {
		return "MovieItem [actors=" + actors + ", desc=" + desc + ", imageId="
				+ imageId + ", score=" + score + ", bigImageId=" + bigImageId
				+ ", movieName=" + movieName + ", movieDirector="
				+ movieDirector + ", releaseTime=" + releaseTime
				+ ", movieTime=" + movieTime + "]";
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {

		dest.writeString(actors);
		dest.writeString(desc);
		dest.writeString(imageId);
		dest.writeString(bigImageId);
		dest.writeString(score);
		
		dest.writeString(movieName);
		dest.writeString(movieDirector);
		dest.writeString(releaseTime);
		dest.writeString(movieTime);
		
	}
	
	public static final Parcelable.Creator<MovieItem> CREATOR = new Creator<MovieItem>() {
		
		@Override
		public MovieItem[] newArray(int size) {
			return new MovieItem[size];
		}
		
		@Override
		public MovieItem createFromParcel(Parcel source) {
			
			MovieItem movieItem = new MovieItem();
			movieItem.actors = source.readString();
			movieItem.desc = source.readString();
			movieItem.imageId = source.readString();
			movieItem.bigImageId = source.readString();
			movieItem.score = source.readString();
			movieItem.movieName = source.readString();
			movieItem.movieDirector = source.readString();
			movieItem.releaseTime = source.readString();
			movieItem.movieTime = source.readString();
			
			return movieItem;
		}
	};

	
	
	

}
