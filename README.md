# Yacken

App that connects to nytimes & openweathermap APIs to display a list of news & weather items.
------------------------------------------------
Architecture (MVP - Model View Presenter)
------------------------------------------------

 1. **Model**: This layer is responsible for data retrieval from any data provider (Cloud, Database... etc). This layer contains implementation for the Repository pattern which is defined in the presentation layer, this pattern represents the communication point between the Model and the Presenter
 2. **Presenter**: This layer is responsible for communication with the Model to retrieve the model, format its values and manipulate the View to show the data accordingly
 3. **View**: This layer contains Android Framework classes that are used to present the UI to the user (Activity, Fragment, Adapters and Android Views). Implemented through an interface which provides abstraction between the View layer and the Presenter layer

--------------------------
Build Requirements
------------------
- Android studio v3
- Build tools version 26.1.0
- API level 26
- MinSdk 17


--------------------------
Third party libraries
--------------------------


1. **Android support libraries v4 - v7**: used mainly for backward compatability support for Fragments, Styling and Appcompat Theme
2. **Retrofit**: used for making HTTP requests and parsing results in an interface looking way
3. **OkHttp**: used for making the actual HTTP requests by Retrofit
4. **Gson**: used for parsing network calls responses by Retrofit and is used through Retrofit's ConverterFactory
5. **RxJava**: adding a reactive flavor for the glue between our layers, all communication through Repository, Model and Presenter are through RxJava's Observables
6. **Picasso**: used for image loading and caching in memory and on disk
7. **LeakCanary**: used for detecting memory leaks [Only in debug mode]
