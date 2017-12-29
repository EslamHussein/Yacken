# Yacken

App that connects to nytimes & openweathermap APIs to display a list of news & weather items.
-------------------------------------------------
Architecture (MVP - Model View Presenter)
Model: This layer is responsible for data retreival from any data provider (Cloud, Database... etc). This layer contains implementation for the Repository pattern which is defined in the presentation layer, this pattern represents the communication point between the Model and the Presenter
Presenter: This layer is responsible for communication with the Model to retreive the model, format its values and manipluate the View to show the data accordingly
View: This layer contains Android Framwork classes that are used to present the UI to the user (Activity, Fragment, Adapters and Android Views). Implemented through an interface which provides abstraction between the View layer and the Presenter layer

--------------------------
Build Requirements
------------------
