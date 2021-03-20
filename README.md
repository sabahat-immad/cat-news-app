# cat-news-app
Android kotlin app that show stories of cute cats nearby. 😻

## Design Review
Replicated the wireframes provided, the only change being all stories in the list are now aligned vertically with same sizes rather than first one being slightly bigger. The reason was
to make the screen look consistent and clean.

## Build Prototype
The app follows MVVM architecture to achieve clean code and architecture. The following main tasks are done to create the prototype:

### Mock API:
Used Postman to mock both API. The base url for the mock server is "https://332ea193-ba5c-4be9-8981-5eca5d3c99f9.mock.pstmn.io" and endpoints are
"GET /news-list" and "GET /story/{id}" returning sample JSON provided. 

### App Structure:
The App has a single activity and two fragments showing list and details likewise. The navigation is done by Jetpack Navigation Component. All classes and grouped in pakages
for each feature (eg. Story list package and detail package). The same grouping is done in Android Test and Test modules.

### App Design:
The screen design is quite sleek and follows material design and based on wireframes provided.

### Dependency Injection:
Dependency Injection in done using HILT library.

### Consuming data from the API:
Used Retrofit with Okhttp to get the data from the API and Kotlin Flow and Coroutines to emit the data to the view.

### UI Tests:
UI tests are inside the Android Test module and using Essresso.

### Unit Tests:
Unit tests are inside the Test module and using Mockito to mock objects.

### Weblinks:
The weblinks will be loaded in the phone browser.

### Images:
Images are displayed using Glide to load image links and currently showning placehoalder images. 




