package com.example.android.thenews;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

class NewsRecyclerAdapter extends RecyclerView.Adapter<NewsRecyclerAdapter.CardViewHolder> {

	/** Adapter's data set */
	private List<News> mNewsList;

	/** Activity's context */
	private Context mContext;

	/**
	 * Create new {@link NewsRecyclerAdapter} for the {@link RecyclerView} displaying
	 * list of news stories
	 */
	NewsRecyclerAdapter(Context context, List<News> news) {
		this.mNewsList = news;
		this.mContext = context;
	}

	/** Create new {@link ViewGroup} for the {@link NewsRecyclerAdapter} to populate with data
	 * This method gets called when there are no {@link ViewGroup}s to recycle
	 */
	@Override
	public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		// Use layout inflater and inflate the necessary view
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		View view = inflater.inflate(R.layout.story_card, parent, false);

		// Return inflated view
		return new CardViewHolder(view);
	}

	@Override
	public void onBindViewHolder(CardViewHolder holder, int position) {
		// Get the current news item at the position
		final News currentItem = mNewsList.get(position);

		// Get the news title information from the current news item and
		// set text on the news title {@link TextView}
		holder.newsTitleTextView.setText(currentItem.getTitle());

		// Get the news section information from the current news item
		// and set text on the section {@link TextView}
		holder.sectionNameTextView.setText(currentItem.getSection());

		// Get the published date of the current news item information from the current news item
		// and set the same as text on the date published {@link TextView}
		holder.datePublishedTextView.setText(currentItem.getTestDate());

		// Register and setup listener to open up news story in web browser
		holder.storyCard.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// Parse string as url object
				Uri webpage = Uri.parse(currentItem.getStoryUrl());

				// Create web browser intent
				Intent storyOnWebIntent = new Intent(Intent.ACTION_VIEW, webpage);

				// Check web activity can be handled by the device and start activity
				if (storyOnWebIntent.resolveActivity(mContext.getPackageManager()) != null) {
					mContext.startActivity(storyOnWebIntent);
				}

			}
		});

		// Check whether or not the current news item has a thumbnail or not
		if (currentItem.getThumbnail() == null) {
			// The current news item does not have thumbnail information
			// Set scale type for the default image
			holder.newsThumbnail.setScaleType(ImageView.ScaleType.CENTER);

			// Set the default image on the {@link ImageView} for the thumbnail
			holder.newsThumbnail.setImageResource(R.drawable.no_thumbnail);
		} else {
			// The current news item has thumbnail information
			holder.newsThumbnail.setScaleType(ImageView.ScaleType.CENTER_CROP);

			// Get the bitmap thumbnail from the current news item and
			// Set it as the image on the {@link ImageView} thumbnail
			holder.newsThumbnail.setImageBitmap(currentItem.getThumbnail());
		}
	}

	/** Informs the {@link RecyclerView} of the total number of items in the data set */
	@Override
	public int getItemCount() {
		// Return the number of news story in the list
		return mNewsList.size();
	}

	/**
	 * Clear the adapter's data set
	 */
	void clear() {
		// Initialize new empty list, clearing out old data
		mNewsList = new ArrayList<>();
	}

	/** Add all the data items from another list to the adapter's data set */
	void addAll(List<News> data) {
		// Traverse the data list to add news item to the adapter's data set
		for (int i = 0; i < data.size(); i++) {
			// Get the book at current index
			News newsStory = data.get(i);
			// Add the book to the data set
			mNewsList.add(newsStory);

			// Notify the adapter of the change in the data set
			// so that it repopulates the view with the updated data set
			notifyDataSetChanged();
		}
	}

	/**
	 * Inner class to cache the the expensive findViewById() results to be used by the adapter's
	 * bindView callback
	 */
	static class CardViewHolder extends RecyclerView.ViewHolder {

		/** {@link ImageView} for the news story's thumbnail */
		ImageView newsThumbnail;

		/** {@link TextView} for the title of the news story */
		TextView newsTitleTextView;

		/** {@link TextView} for the section of the news story */
		TextView sectionNameTextView;

		/** {@link TextView} for the published date of the news story */
		TextView datePublishedTextView;

		/** {@link CardView} for every news story */
		CardView storyCard;

		/** ItemView to cache reference hooks to the view elements of the recycler view */
		CardViewHolder(View itemView) {
			super(itemView);

			// Find the {@ink ImageView} for the thumbnail
			newsThumbnail = itemView.findViewById(R.id.story_image);

			// Find the {@link TextView} for the news title
			newsTitleTextView = itemView.findViewById(R.id.news_title_text);

			// Find the {@link TextView} for the section of the news story
			sectionNameTextView = itemView.findViewById(R.id.section_name_text);

			// Find the {@link TextView} for the published date
			datePublishedTextView = itemView
					.findViewById(R.id.date_published_text);

			// Find the {@link CardView} for each news story
			storyCard = itemView.findViewById(R.id.story_card);
		}
	}
}
