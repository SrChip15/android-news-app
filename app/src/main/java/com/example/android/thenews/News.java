package com.example.android.thenews;

import android.graphics.Bitmap;

class News {

	/** News title */
	private String mTitle;

	/** News section */
	private String mSection;

	/** Date when news was published */
	private String mDate;

	/** Url for the news story */
	private String mStoryUrl;

	/** Url for thumbnail of news story */
	private String mThumbnailUrl;

	/** Thumbnail bitmap of news story */
	private Bitmap mThumbnail;

	/** Create new news object that stores title, section, story url and thumbnail url of the news */
	News(String title, String section, String storyUrl, String thumbnailUrl, String date) {
		this.mTitle = title;
		this.mSection = section;
		this.mStoryUrl = storyUrl;
		this.mThumbnailUrl = thumbnailUrl;
		this.mDate = date;
	}

	/** Return news title information */
	String getTitle() {
		return this.mTitle;
	}

	/** Return news section information */
	String getSection() {
		return this.mSection;
	}

	/** Return date when news story was published */
	String getTestDate() {
		return mDate;
	}

	/** Return news story url information */
	String getStoryUrl() {
		return this.mStoryUrl;
	}

	/** Return news story thumbnail url information */
	String getThumbnailUrl() {
		return this.mThumbnailUrl;
	}

	/** Return news story thumbnail bitmap information */
	Bitmap getThumbnail() {
		return this.mThumbnail;
	}

	/** Set news story thumbnail bitmap */
	void setThumbnail(Bitmap thumbnail) {
		this.mThumbnail = thumbnail;
	}

}
