News App Project
=================

The application connects to the internet and fetches news from the New York Times API using HttpURLConnection, which is then 
parsed using a custom built JSON parser. The parsed information is then presented as a news feed in namely four categories - 
Tech, Science, Travel, and Books. The splash screen presents these categories with a help of a tabbed layout and viewpager, 
thereby doing away with additional screens and making it easier for the user to access any news category with just a finger 
swipe.

Objective
-----

The goal is to create a News feed app that provides regularly updated news from the internet.

Tools
-----

* Gradle v4.1
* Android Plugin v3.0.1
* Android API v27
* Android Build Tools v27
* [NYTimes API](https://developer.nytimes.com/top_stories_v2.json)

API Key
------
You must provide your own NYTimes API key in order to connect with the API and fetch news data from it. Just put your API key 
into `~/.gradle/gradle.properties` file (create the file if it does not exist already):

```
API_KEY="MySecretKey"
```

Screenshots
------------

<img src="https://github.com/SrChip15/android-news-app/blob/master/splash_sceen.png" width="300"/>


License
------
```
Copyright 2018 Srinath Chintapalli

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

```
