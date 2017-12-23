# Room-AndroidPersistance
This is the simplest way that a Room ,android persistance library can be used

#### A new collection of libraries that help you design robust, testable, and maintainable apps. – developer.android.com

It helps us developers address two pain points:

1. Manage our UI components lifecycle
2. Persist data over configuration changes

In fact, these are the two biggest problems we Android Developers face. Period.

Maintaining data over orientation changes and handling our objects with lifecycle is hard. That’s why, to avoid the hassle, you lock your apps in Portrait mode, don’t you? Don’t lie. Even I’ve done it.

But don’t worry, Android Architecture Components will help alleviate both our fears.

There are 3 main architecture components:

1. Room
2. LiveData
3. ViewModel


## Room
Remember the amount of boilerplate code you had to write to create and manipulate even a very small database? You had to define the database structure, create an SQLiteHelper class etc.

#### Room is a library that saves you all such trouble. Now you can query your data without having to deal with cursors or loaders. You can define your database by adding annotations in your Model class. Yes, it’s that simple.

##### If you’ve used third-party ORMs like Sugar, you’ll feel right at home here. In fact, from now on, I wouldn’t even want to use one. Room is that brilliant! Why would you want to use a third-party library, when the official Android libraries give you an equal, or if not, better solution.

### Architecture
In this app, we will follow an architecture called **MVVM – Model View ViewModel**.

In MVVM, the **ViewModel** exposes the required data and interested parties can listen to it.

So in our case, the **Activity** will listen on the data and make changes in the UI.
