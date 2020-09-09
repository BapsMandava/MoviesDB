# Android Assignment CS

This application shows the list of Movies that are classified into two categories "Now Playing" and "Popular"

"Now Playing" movie list section is just a carousel which uses recycler view with view holder pattern
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



## Instructions

You can find all the instrucrtions for the assingment on [Assingment Instructions](https://docs.google.com/document/d/1zCIIkybu5OkMOcsbuC106B92uqOb3L2PPo9DNFBjuWg/edit?usp=sharing).

## Delivering the code
* Fork this repo and select the access level as private **[Check how to do it here](https://confluence.atlassian.com/bitbucket/forking-a-repository-221449527.html)**
* Go to settings and add the user **m-cs-recruitment@backbase.com** with the read access **[Check how to do it here](https://confluence.atlassian.com/bitbucket/grant-repository-access-to-users-and-groups-221449716.html)**
* Send an e-mail to **m-cs-recruitment@backbase.com** with your info and repo once the development is done

Please remember to work with small commits, it help us to see how you improve your code :)
