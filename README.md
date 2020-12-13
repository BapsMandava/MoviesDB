# MoviesDB

This application shows the list of Movies that are classified into two categories "Now Playing" and "Popular"

"Now Playing" movie list section is just a carousel which uses recycler view with view holder pattern.

"Popular" section movie list is vertical oriented recycler view which support pagination/Lazy loading 
	- Pagination is done with help of android "Paging" library provided by google.
	- This helps to segregate the logic for pagination in a much simpler way.
	
"Images"
	- For image loading glide library is used which also helps for caching the images for smoother scroll.
	
"Rating View"
	- An xml with the all the required components are placed
	- A progress bar is used to show the rating data on the bar
	- Custom design is created which helps to modify the progress bar into a circular shape.
	- A RatingView class is created to help inflate the view and use if any attributes are required.
	
"Retrofit"
	- A famous networking library is used to perform API calls which also helps to intercept the request and responses.
	
"Rx Java"
	- Rx java is used for asynchronous data flow
	
The whole app is implemented with MVVM architecture, live data is used to communicate data between ViewModel and Activity.
DataBinding is also used to show data in MovieDetails View.

Extensions are used for TextView and ImageView to format the Date Time and Load image respectively.
