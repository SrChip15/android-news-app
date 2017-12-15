package com.example.android.thenews;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Find the view pager that will allow the user to swipe between fragments
		ViewPager viewPager = findViewById(R.id.viewpager);

		// Give the TabLayout the ViewPager
		TabLayout tabLayout = findViewById(R.id.sliding_tabs);
		tabLayout.setupWithViewPager(viewPager);

		// Create an adapter that knows which fragment should be shown on each page
		NewsCategoryAdapter adapter = new NewsCategoryAdapter(getSupportFragmentManager());

		// Set the adapter onto the view pager
		viewPager.setAdapter(adapter);
	}
}