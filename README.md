# NYTimes-Browser
Browser of NYTimes articles

<h2>Configuration</h2>
Application requires NYTimes API key, which you can get for free from <a href="http://developer.nytimes.com/" target="_blank">here</a>.
Once you get the API key, open the project in Android Studio and follow below steps:

1. Choose <i>Android</i> type for Project left sidebar.
2. Open <i>gradle.properties</i> global file (make sure you are opening correct file, as there is another file with the same name, which is project specific).

![image](https://user-images.githubusercontent.com/23655108/44476450-09f17a80-a638-11e8-90df-d26f296cbc98.png)


3. Put a new property with name <i>NYTimesBrowser_API_Key</i> as property name and your API key as property value.

![image](https://user-images.githubusercontent.com/23655108/44519286-df9dcc80-a6cc-11e8-9e9d-c9369072b5ba.png)

Alternatively, you can put API key directly in code. It should be OK for quick local tests, although not recommented for use with remote repositories, as your API key may become known by others.
Open <a href="https://github.com/mirokolodii/NYTimes-Browser/blob/master/app/src/main/java/com/unagit/nytimesbrowser/helpers/Constants.java">Constants.java</a> file. Locate a sub-class called <i>Retrofit</i> and replace a value of NYTApiKey property with your own API key.
