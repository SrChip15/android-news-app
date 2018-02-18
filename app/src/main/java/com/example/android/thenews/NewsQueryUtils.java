package com.example.android.thenews;

import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.example.android.thenews.BuildConfig.API_KEY;

final class NewsQueryUtils {

	private static final String LOG_TAG = NewsQueryUtils.class.getSimpleName();

	// Private constructor so that this class objects are not made
	private NewsQueryUtils() {
	}

	// Main method that has sub-routines to fetch top stories
	static List<News> fetchNews(String newsCategory) {
		// Create valid url object from the requestURL
		String requestUrl = "https://api.nytimes.com/svc/topstories/v2/%1$s.json?api-key=%2$s";
		String fullUrl = String.format(requestUrl, newsCategory, API_KEY);
		URL url = createUrl(fullUrl);

		// Initialize empty String object to hold the parsed JSON response
		String jsonResponse = "";

		// Perform HTTP request to the above created valid URL
		try {
			jsonResponse = makeHttpRequest(url);
		} catch (IOException e) {
			Log.e(LOG_TAG, "Problem making the HTTP request for the search criteria");
		}

		// Extract information from the JSON response for each news item
		// Return list of news
		List<News> news =  NewsQueryUtils.extractFeatures(jsonResponse);
		assert news != null;
		for (int i = 0; i < news.size(); i++) {
			News current = news.get(i);
			String imgUrl = current.getThumbnailUrl();
			if (!imgUrl.isEmpty()) {
				try {
					current.setThumbnail(
							BitmapFactory
									.decodeStream(
											(InputStream) new URL(imgUrl).getContent()
									)
					);
				} catch (MalformedURLException e) {
					Log.e(LOG_TAG, "Problem parsing the thumbnail for the news story");
				} catch (IOException e) {
					Log.e(LOG_TAG, "IOException has occurred");
				}
			}
		}

		// Return list of news stories
		return news;
	}

	/** Returns new URL object from the given string URL. */
	private static URL createUrl(String stringUrl) {
		// Initialize an empty {@link URL} object to hold the parsed URL from the stringUrl
		URL url = null;

		// Parse valid URL from param stringUrl
		// Handle Malformed urls
		try {
			url = new URL(stringUrl);
		} catch (MalformedURLException e) {
			Log.e(LOG_TAG, "Problem building the url!");
		}

		// Return valid url
		return url;
	}

	/**
	 * Return a list of {@link String} objects that has been built up from
	 * parsing the given JSON response.
	 */
	private static List<News> extractFeatures(String newsJSON) {
		// Exit early if no data was returned from the HTTP request
		if (TextUtils.isEmpty(newsJSON)) {
			return null;
		}

		// Initialize list of strings to hold the extracted news item
		List<News> newsList = new ArrayList<>();

		// Traverse the raw JSON response parameter and mine for relevant information
		try {
			// Create JSON object from response
			JSONObject rawJSONResponse = new JSONObject(newsJSON);

			// Extract the array that holds the results
			JSONArray topStoriesResults = rawJSONResponse.getJSONArray("results");
			for (int i = 0; i < topStoriesResults.length(); i++) {
				// Get the current item
				JSONObject topStory = topStoriesResults.getJSONObject(i);
				// Get the topStory's title from the volume information
				String topStoryTitle = topStory.getString("title");
				// Get the current topStory's section information
				// The appropriate section that the story belongs to is contained in the subsection
				String topStorySection = topStory.getString("subsection");
				// Often, the subsection is an empty string, in which case we do not want the section
				// information on the screen to be left blank. So, a higher level section information
				// is got from the section string of the JSON array
				if (topStorySection.isEmpty()) {
					topStorySection = topStory.getString("section");
				}
				// Get the date on which the news was published
				// Extract date information from the JSON response
				String topStoryDateTime = topStory.getString("published_date");

				// Format of date published in JSON response
				SimpleDateFormat rawDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.US);

				// Declare variable to store parsed date published information
				Date topStoryDate;

				// Format for human-readable date
				SimpleDateFormat readable = new SimpleDateFormat("EEE, MMM d, ''yy");

				// Initialize string variable to store formatted date
				String formattedDate = "";

				try {
					// Parse date
					topStoryDate = rawDateFormat.parse(topStoryDateTime);

					// Process to human-readable format
					formattedDate = readable.format(topStoryDate);
				} catch (ParseException e) {
					// Catch parse errors
					Log.e(LOG_TAG, "Error parsing date from datetime information contained in the JSON");
				}

				// Get the story url
				String storyUrl = topStory.getString("url");

				// Get the thumbnail for the story
				JSONArray multimedia = topStory.getJSONArray("multimedia");
				String thumbUrl = "";
				if (multimedia.length() > 0 && multimedia.get(0) != null) {
					JSONObject stdThumb = multimedia.getJSONObject(2);
					thumbUrl = stdThumb.getString("url");
				}

				// Add topStory to the list
				newsList.add(new News(topStoryTitle, topStorySection, storyUrl, thumbUrl, formattedDate));
			}

		} catch (JSONException e) {
			// If an error is thrown when executing any of the above statements in the "try" block,
			// catch the exception here, so the app doesn't crash. Print a log message
			// with the message from the exception.
			Log.e(LOG_TAG, "Problem parsing the JSON news results", e);
		}

		// Return successfully parsed information as a {@link List} of {@link News}
		return newsList;
	}

	/** Make an HTTP request to the given URL and return a String as the response. */
	private static String makeHttpRequest(URL url) throws IOException {
		// Initialize variable to hold the parsed json response
		String jsonResponse = "";

		// Return early if url is null
		if (url == null) {
			return jsonResponse;
		}

		// Initialize HTTP connection object
		HttpURLConnection urlConnection = null;

		// Initialize {@link InputStream} to hold response from request
		InputStream inputStream = null;

		try {
			// Establish connection to the url
			urlConnection = (HttpURLConnection) url.openConnection();

			// Set request type
			urlConnection.setRequestMethod("GET");

			// Set read and connection timeout in milliseconds
			// Basically, setting how long to wait on the request
			urlConnection.setReadTimeout(10000);
			urlConnection.setConnectTimeout(15000);

			// Establish connection to the url
			urlConnection.connect();

			// Check for successful connection
			if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				// Connection successfully established
				inputStream = urlConnection.getInputStream();
				jsonResponse = readFromStream(inputStream);
			} else {
				Log.e(LOG_TAG, "Error while connecting. Error Code: " + urlConnection.getResponseCode());
			}
		} catch (IOException e) {
			e.getMessage();
			Log.e(LOG_TAG, "Problem encountered while retrieving news results");
		} finally {
			if (urlConnection != null) {
				// Disconnect the connection after successfully making the HTTP request
				urlConnection.disconnect();
			}
			if (inputStream != null) {
				// Close the stream after successfully parsing the request
				// This may throw an IOException which is why it is explicitly mentioned in the
				// method signature
				inputStream.close();
			}
		}

		// Return JSON as a {@link String}
		return jsonResponse;
	}

	/**
	 * Convert the {@link InputStream} into a String which contains the
	 * whole JSON response from the server.
	 */
	private static String readFromStream(InputStream inputStream) throws IOException {
		StringBuilder output = new StringBuilder();
		if (inputStream != null) {
			// Decode the bits
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));

			// Buffer the decoded characters
			BufferedReader reader = new BufferedReader(inputStreamReader);

			// Store a line of characters from the {@link BufferedReader}
			String line = reader.readLine();

			// If not end of buffered input stream, read next line and add to output
			while (line != null) {
				output.append(line);
				line = reader.readLine();
			}
		}

		// Convert the mutable characters sequence from the builder into a string and return
		return output.toString();
	}
}
