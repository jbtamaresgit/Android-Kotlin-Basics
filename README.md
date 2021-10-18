## Android-Kotlin-Basics
Following https://developer.android.com/courses/android-basics-kotlin/course 

## Activity Lifecycle
The activity lifecycle is a set of states through which an activity migrates. 
The activity lifecycle begins when the activity is first created and ends when the activity is destroyed.

###### The core set of lifecycle methods are:
``` onCreate() onStart() onPause() onRestart() onResume() onStop() onDestroy() ```

###### Preserving activity state
- When your app goes into the background, just after ``` onStop()```  is called, app data can be **saved** to a bundle. 
- The bundle is an instance of ```Bundle```, which is a collection of **keys and values**. The keys are always **strings**.
- Use the ```onSaveInstanceState()``` callback to save other data to the bundle that you want to retain, even if the app was automatically shut down. 
To put data into the bundle, use the bundle methods that start with put, such as ```putInt()```
- You can get data back out of the bundle in the ```onRestoreInstanceState()``` method, or more commonly in ```onCreate()```. 
The ```onCreate()``` method has a **savedInstanceState** parameter that holds the bundle.
- If the savedInstanceState variable is ```null```, the activity was started without a state bundle and there is no state data to retrieve.
- To retrieve data from the bundle with a key, use the **Bundle** methods that start with get, such as getInt().

###### Configuration changes
- Configuration changes happens when there is a change of state on the device. To resolve the change, the easiest way is to destroy and rebuild the activity.
- The most common example of a configuration change is when the user rotates the device from portrait to landscape mode, or from landscape to portrait mode.
- When a configuration change occurs, Android invokes all the activity lifecycle's shutdown callbacks. 
Then Android restarts the activity from scratch, running all the lifecycle startup callbacks.
- When Android shuts down an app because of a configuration change, it restarts the activity with the state bundle that is available to ```onCreate()```.
- As with process shutdown, save your app's state to the bundle in ```onSaveInstanceState()```

## Fragment Lifecycle
Fragment lifecycle has **5 states**, represented by ```Lifecycle.State``` enum
Similar to activities ```Fragment``` class provides methods that can be overrided to respond to lifecycle events.
- ```INITIALIZED```: New instance of a fragment.
- ```CREATED```: First fragment lifecycle methods are created, along with the view associated with the fragment.
  - ```onCreate()```: Fragment has been instantiated. You cannot **inflate** the layout here.
  - ```onCreateView()```: Method where you **inflate** the layout.
  - ```onViewCreated()```: Called after the view is created. This is where you typically **bind** specific views to properties by calling ```findViewById()```
  - ```onStop()```: Re-entered the ```CREATED``` state. Object is instantiated but is no longer presented on screen. Usually happens after ```onPause()```
  - ```onDestroyView()```: Called before the fragment enters the ```DESTROYED``` state. View has already been removed to the memory, but the fragment object still exists.
- ```STARTED```: Fragment is visible on screen however, it cannot respond to user input yet.
  - ```onStart()```: Entered the ```STARTED``` state.
  - ```onPause()```: Re-entered the ```STARTED``` state. UI is visible to the user. Usually happens after ```onResume()```
- ```RESUMED```: Fragment is visible and able to respond to the user input.
  - ```onResume()```: Fragment has entered ```RESUMED``` state and able to respond to the user input.
- ```DESTROYED```: Fragment object has been de-instantiated.
  - ```onDestroy()```: Enters the ```DESTROYED``` state.

## Navigation Components
- ```NavHostFragment```: The container where the fragments are replaced while navigating.
- ```NavController```: Conducts the navigation 
- ```NavigationView```: It is a separate entity outside of the ```NavHostFragment```, a menu for ```DrawerLayout```
- ```NavigationUI```: Updates the contents outside of the ```NavHostFragment```, E.g., ```NavigationView```, ```BottomNavBar```

## Architecture Components
``` Android Jetpack ``` libraries are a collection of libraries to create a better Android apps. The libraries allows you to follow best practices, free from boilerplate codes, and simplify complex task.
``` Android Architecture Components ``` are part of ```Android Jetpack``` libraries, allows you to design apps with good architecture.
## App Architecture
- Set of design rules that provides the structure of your app.
###### Separation of Concerns
App should be divided into classes, each with separate responsibilities
###### Drive UI from a model
- A principle that drives your UI from a model, preferably a persistent model. **Models** are components that are responsible for handling the data for an app. They are independent from ```Views``` and app components in the app, so they are unaffected by the app's lifecycle and the associated concerns.
- The main classes or components in an android architecture are ```UI Controller``` (```Activity/Fragment```), ```ViewModel```, ```LiveData```, and ```Room```. These components takes care some of the complexity of the lifecycle and help you avoid lifecycle related issues. 
- ```UI Controller``` 
  - Displays data and capture user events, in other words, anything else related to the UI that the user interacts with. Data in the app or any decision-making logic about data, it should not be in the UI Controller classes since there are events that are not under your control, such as system conditions like low memory.
- ```ViewModel``` 
  - Holds all the data needed for the UI and prepares it for display. It should never access view hierarchy (like view binding object) or hold a reference to the activity or the fragment
  - Stores the app related data that is not destroyed when an activity or fragment is destroyed and recreated by the Android framework. ```ViewModel``` objects are automatically retained (they are not destroyed like the activity or a fragment instance) during configuration changes so that the data they hold is immediately available to the next activity or fragment instance.

###### Kotlin Property Delegate
- Provides getter-setter responsibility to a different class
- A delegate property is defined using the ```by``` clause and a ```delegate-class``` instance:
```var property-name : property-type by delegate-class()```
- In the app, if the ```ViewModel``` is initialised using a default constructor e.g. ```var viewModel = yourViewModel()``` then the app will lose its configuration during configuration change. For example, rotation of a device will cause the activity to be destroyed and re-created, and a new view model will have a new instance again.

###### Backing Property
- Allows you to return something from a getter. e.g.  
```  
private var _count = 0  
val count: Int  
get() = _count  
```  
Property ```_count``` is private and mutable. Hence, it is only accessible inside the ```ViewModel```. To access the value of the ```_count``` creation of a ```property``` is needed such aso overriding ```get()``` method, however, using the ```get()``` will only allow the property to be **immutable** and **read-only**.  
```  
Never expose mutable data fields from the **ViewModel**, make sure that the mutable variables cannot be modified from other classes.  
```  
