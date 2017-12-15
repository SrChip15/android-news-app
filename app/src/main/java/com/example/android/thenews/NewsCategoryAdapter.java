package com.example.android.thenews;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

class NewsCategoryAdapter extends FragmentPagerAdapter {

	/** News section names for tab titles */
	private String tabTitles[] = new String[]{ "Tech", "Science", "Travel", "Books" };
	private static final String API_KEY = BuildConfig.API_KEY;

	NewsCategoryAdapter(FragmentManager fm) {
		super(fm);
	}

	/** Instantiate fragment based on user horizontal scroll position */
	@Override
	public Fragment getItem(int position) {
		// Url along with API key to query the API
		String category;

		// Fragment for different news section
		Fragment fragment;
		if (position == 0) {
			// Url for technology news section
			category = "https://api.nytimes.com/svc/topstories/v2/technology.json?api-key=" + API_KEY;

			// Initialize fragment with news section as argument
			fragment = TopStoriesFragment.newInstance(category);

			// Return the fragment
			return fragment;

		} else if (position == 1) {
			// Url for science news section
			category = "https://api.nytimes.com/svc/topstories/v2/science.json?api-key=" + API_KEY;

			// Initialize fragment with news section as argument
			fragment = TopStoriesFragment.newInstance(category);

			// Return the fragment
			return fragment;

		} else if (position == 2) {
			// Url for travel news section
			category = "https://api.nytimes.com/svc/topstories/v2/travel.json?api-key=" + API_KEY;

			// Initialize fragment with news section as argument
			fragment = TopStoriesFragment.newInstance(category);

			// Return the fragment
			return fragment;

		} else {
			// Url for books news section
			category = "https://api.nytimes.com/svc/topstories/v2/books.json?api-key=" + API_KEY;

			// Initialize fragment with news section as argument
			fragment = TopStoriesFragment.newInstance(category);

			// Return the fragment
			return fragment;
		}
	}

	/** Informs the adapter of the total number of available fragments views */
	@Override
	public int getCount() {
		return 4;
	}

	/** Set tab title */
	@Override
	public CharSequence getPageTitle(int position) {
		return tabTitles[position];
	}
}
