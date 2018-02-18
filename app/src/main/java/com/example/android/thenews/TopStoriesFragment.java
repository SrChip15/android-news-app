package com.example.android.thenews;


import android.content.Context;
import android.graphics.PorterDuff;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TopStoriesFragment
		extends Fragment
		implements LoaderCallbacks<List<News>> {

	/** Adapter that holds the list of top stories */
	private NewsRecyclerAdapter adapter;

	/** Loader ID */
	private static final int LOADER_ID = 1;

	/** News category (fragment initiating parameter) */
	private String newsCategory;

	/** Indeterminate progress bar */
	private ProgressBar progressSpinner;

	/** String key for news category */
	private static final String NEWS_CATEGORY = "CATEGORY";

	public TopStoriesFragment() {
		// Required empty public constructor
	}

	/** Factory method to pass arguments when recreating fragments */
	static TopStoriesFragment newInstance(String newsCategory) {
		// Base fragment to reuse
		TopStoriesFragment fragment = new TopStoriesFragment();

		// Initialize bundle to store arguments
		Bundle bundle = new Bundle(1);

		// String url parameter to pass arguments when recreating {@link TopStoriesFragment}
		bundle.putString(NEWS_CATEGORY, newsCategory);

		// Save arguments to the fragment instance to be called upon later
		fragment.setArguments(bundle);

		// Create and return {@link TopStoriesFragment} with the passed-in string parameter
		return fragment;
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the passed-in argument from the fragment
		if (getArguments() != null) {
			newsCategory = getArguments().getString(NEWS_CATEGORY);
		}
	}

	/** Create the fragment UI view */
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate UI view for the fragment
		final View rootView = inflater.inflate(R.layout.recycler, container, false); // think about layout

		// Create adapter
		adapter = new NewsRecyclerAdapter(getContext(), new ArrayList<News>());

		// Get a reference to the recycler view
		RecyclerView recyclerView = rootView.findViewById(R.id.recycler_view);

		// Set layout for recycler view
		recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
		recyclerView.setHasFixedSize(true);

		// Set adapter for recycler view
		recyclerView.setAdapter(adapter);

		// Get reference to the Progress bar
		progressSpinner = rootView.findViewById(R.id.progress_spinner);
		// Indeterminate progress bar type
		progressSpinner.setIndeterminate(true);
		// Set progress bar color
		progressSpinner.getIndeterminateDrawable()
				.setColorFilter(
						ContextCompat.getColor(getContext(), R.color.colorPrimary),
						PorterDuff.Mode.SRC_IN
				);

		// Set empty view when there is no data on the recycler view
		TextView mEmptyStateView = rootView.findViewById(R.id.empty_text_view);

		// Check internet connectivity
		if (getActivity() != null) {
			ConnectivityManager connMgr = (ConnectivityManager) getActivity()
					.getSystemService(Context.CONNECTIVITY_SERVICE);

			// Get details on the currently active default data network
			if (connMgr != null) {
				NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

				// If there is a network connection, fetch data
				if (networkInfo != null && networkInfo.isConnected()) {
					// Get a reference to the loader manager in order to interact with the loaders
					LoaderManager mLoaderManager = getLoaderManager();

					// Initialize the loader manager. Pass in the constant declared above as the ID of the
					// loader manager and pass in null for the bundle parameter. Finally, also pass in the
					// context of the application since this application implements the LoaderCallbacks interface
					mLoaderManager.initLoader(LOADER_ID, null, this);
				} else {
					// Otherwise, display error
					// First, hide loading indicator so error message will be visible
					progressSpinner.setVisibility(View.GONE);

					// Update empty state with no connection error message
					mEmptyStateView.setText(R.string.no_internet_connection);
				}
			}
		}

		// Return the populated UI view
		return rootView;
	}

	/** Loader methods */
	@Override
	public Loader<List<News>> onCreateLoader(int id, Bundle args) {
		// Create loader object to connect to the internet and fetch news stories from the API
		return new NewsLoader(getActivity(), newsCategory);
	}

	@Override
	public void onLoadFinished(Loader<List<News>> loader, List<News> news) {
		// Hide progress bar
		progressSpinner.setVisibility(View.GONE);

		// Clear the adapter of previous data
		adapter.clear();

		// Check for valid list of news
		if (news != null && !news.isEmpty()) {
			// Add all the items fetched via loader to the adapter's data set
			adapter.addAll(news);
		}
	}

	@Override
	public void onLoaderReset(Loader<List<News>> loader) {
		// Clear out items from the adapter's data set
		adapter.clear();
	}
}
