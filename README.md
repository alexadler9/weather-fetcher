# Weather application for Android OS
REST application based on Clean Architecture to receive and display data about:
* current weather in the specified region;
* weather forecast for the specified region for the next 3 days.

<p float="middle">
  <kbd> <img src="https://github.com/alexadler9/weather-fetcher/assets/56451293/b457443b-6652-4e63-b885-9f6ffc6a4053" height="320" /> </kbd>
  <kbd> <img src="https://github.com/alexadler9/weather-fetcher/assets/56451293/681e31e2-dd8b-4681-85eb-33e5696a74f7" height="320" /> </kbd>
</p>

## Tech stack and open-source libraries
* **_Kotlin_** based, **_Coroutines_** for asynchronous.
* Architecture:
  - **_MVI_** architecture (Model-View-Intent);
  - **_repository_** pattern.
* **_Koin_** - for dependency injection.
* **_Jetpack_** libraries:
  - _LiveData_ - notify domain layer data to views;
  - _ViewModel_ - UI related data holder, lifecycle aware;
  - _View binding_ - simplified interaction with views;
  - _RecyclerView_ - view for providing a limited window into a data set.
* **_SharedPreferences_** - to store user preferences.
* **_ProductFlavor_** feature - to support multiple versions of the application:
  - _prod_ (main version) - makes real requests to the OpenWeather API;
  - _mock_ (testing version) - mocks Interceptor for OkHttpClient to spoof server responses.
* **_Retrofit2_** & **_OkHttp3_** - construct the REST APIs and paging network data.
* **_Gson_** - JSON representation.
* **_Glide_** - loading images.
* Testing:
  - **_JUnit5_** & **_Mockito_** for unit tests;
  - **_Espresso_** for UI tests.
