# Movie DB Showcase

An example application using the latest practices, principles and Android features. It uses the themoviedb.org's API to retrieve the top 20 popular movies, tv shows and people and display them in their own lists. Each of these lists are searchable. When a movie or tv show is selected it opens the details view.

[View Demo on YouTube]

## Architecture

The idea behind the architecture for this app is based heavily on Fernando Cejas' [Android-CleanArchitecture] project which, in turn, uses Uncle Bob's clean coding principles.

There are 3 modules:

- `app`: This is where the application specific code happens. It is involved with display, user input and is really the glue for the app.
- `domain`: This is a java module. It contains domain specific objects and classes; objects and classes that you would use on other platforms too. This module does not know about the app or the `data` module.
- `data`: An android module used to store, retrieve and update data. The idea is to abstract away the inner working so any dependents don't know, or need to know, where the data came from. 
 
No entity objects get passed across boundaries; `Entity` objects get mapped to `Domain` objects when passed from the `data` module to the `domain` module and `Domain` objects get mapped to `Model` objects when passing from `domain` to `app`.

## Testing

Throughout the process of developing the application, TDD was the driving force. Wherever possible I prioritised writing java unit tests by using `Mockito` to mock dependencies and `Dagger` made this easier. Something to do in the future is implement Robolectric  as this mocks all Android dependencies.


  [Android-CleanArchitecture]: <https://github.com/android10/Android-CleanArchitecture>
  [View Demo on YouTube]: <https://youtu.be/eeC4fTVXwD8>
