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

```
MIT License

Copyright (c) 2018 Srinath Chintapalli

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

Project License

In addition to the below copyright license, the author expressly prohibits the 
use of this project for submission as part of course completion reqiurements to
an educational institution or online courses, like Coursera, Udacity, edX and 
the like. Failure to comply is by definition plagiarism and the author is 
completely absolved from all the consequences arising out of the aforementioned
act of plagiarism. 
```
