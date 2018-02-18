package com.example.android.thenews;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

class NewsCategoryAdapter extends FragmentPagerAdapter {

    private Resources resources;

    NewsCategoryAdapter(Context context, FragmentManager fm) {
        super(fm);
        resources = context.getResources();
    }

    /** Instantiate fragment based on user horizontal scroll position */
    @Override
    public Fragment getItem(int position) {
        // Url along with API key to query the API
        String category;

        // Fragment for different news section
        Fragment fragment;

        switch (position) {
            case 0:
                // Url for technology news section, re-use the tab title text here
                category = "technology";

                // Initialize fragment with news section as argument
                fragment = TopStoriesFragment.newInstance(category);

                // Return the fragment
                return fragment;
            case 1:
                // Url for science news section
                category = resources.getString(R.string.science_news_title);

                // Initialize fragment with news section as argument
                fragment = TopStoriesFragment.newInstance(category);

                // Return the fragment
                return fragment;
            case 2:
                // Url for travel news section
                category = resources.getString(R.string.travel_news_title);

                // Initialize fragment with news section as argument
                fragment = TopStoriesFragment.newInstance(category);

                // Return the fragment
                return fragment;
            case 3:
                // Url for books news section
                category = resources.getString(R.string.books_news_title);

                // Initialize fragment with news section as argument
                fragment = TopStoriesFragment.newInstance(category);

                // Return the fragment
                return fragment;
            default:
                return null;
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
        switch (position) {
            case 0:
                return resources.getString(R.string.tech_news_title);
            case 1:
                return resources.getString(R.string.science_news_title);
            case 2:
                return resources.getString(R.string.travel_news_title);
            case 3:
                return resources.getString(R.string.books_news_title);
            default:
                return null;

        }
    }
}
