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


