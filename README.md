# News App Project
The application connects to the internet and fetches news from the New York Times API using HttpURLConnection, which is then 
parsed using a custom built JSON parser. The parsed information is then presented as a news feed in namely four categories - 
Tech, Science, Travel, and Books. The splash screen presents these categories with a help of a tabbed layout and viewpager, 
thereby doing away with additional screens and making it easier for the user to access any news category with just a finger 
swipe. 

This app has been built to the specifications of the News app project rubric, which is part of the 
Networking module of [Udacity's Android Basics Nanodegree program](https://www.udacity.com/course/android-basics-nanodegree-by-google--nd803).

### Objective
The goal is to create a News feed app which gives an user regularly-updated news from the internet.

### Tools
* Gradle v4.1
* Android Plugin v3.0.1
* Android API v27
* Android Build Tools v27
* [NYTimes API](https://developer.nytimes.com/top_stories_v2.json)

### API Key
You must provide your own NYTimes API key in order to connect with the API and fetch news data from it. Just put your API key 
into `~/.gradle/gradle.properties` file (create the file if it does not exist already):

```
API_KEY="MySecretKey"
```

# Screenshots
<img src="https://github.com/SrChip15/android-news-app/blob/master/splash_sceen.png" width="300"/>


# License

```
Apache License 2.0
A permissive license whose main conditions require preservation of copyright and license notices. 
Contributors provide an express grant of patent rights. Licensed works, modifications, 
and larger works may be distributed under different terms and without source code.

```
