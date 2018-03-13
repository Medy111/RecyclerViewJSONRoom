In this Example :
Storing Data from Json into Room with LiveData , ViewModel classes. ( The default way , going by the Documentation).
Also we leaning on Official Sample from Codelabs Room with a view , so we know we dont create some nonesense / bad practice .
With this Basic sample , you wil be able to tend to all your data storage / network request needs .
Though if you only want so save like single values , like passwords , username ... you should use SharedPreferences , because we dont need database for that.

    1. Recyclerview / ViewHolder
    2. JSON parsing
    3. Data Storage ( Room Library ) - with LiveData ViewModel


Steps :

    1 . We Parse Json data( with Volley Library or Retrofit) , which we display in in Recyclerview ( CardViews )
    2 . Clicks on a RecyclerviewItem will store item data in Database (Room Library)  Title , Image url
    3 . Then we  will have 1 activity/fragment that querys our DB for those items we saved , to display them inside a new Recyclerview or ListLiew ( we probably have to worry about recycled views if the list is small) , though we could use recyclerview anyways jsut to be save...

Result :

    We click a button on a RecyclerView item , that will save item data into our db , in another activity or fragment that data will be displayed again.
    Basically creating custom lists from lists.
    Or do whatever we want with the data we have in our db.

What is the Challenge here ? :

    Creating the ViewModels / Repositories / Adapters
    feeding / recieving data on a background thread to room database.



Note :
    Room Database does not allow operations on the main thread ( so the UI does not get blocked on long network querys) ,
    which is why we have to structure our code to save / load data via background thread.

    Additional thoughts by Creator of this Sample( Beginner @ Android):

    Room is probably the easiest way to Store Data. ( The official Doc says you should use it instead of the core SqliteOpenhelper)
    Though by itself you basically cant use it.
    You need Classes like LiveData to do background work.
    Room can only recieve Data on background thread ( you should not use it any other way. There is a method to allowonMainthread though. Use it for testing / learning only).


Step by Step Overview :

0. Setup Dependencies
        - INTERNET PERMISSION
        <uses-permission android:name="android.permission.INTERNET" />

        - DEPENDENCY for the stuff we use : ( make sure you use google for the latest once to support the latest android version).

            // Room (use 1.1.0-alpha3 for latest alpha)
            implementation "android.arch.persistence.room:runtime:1.0.0"
            annotationProcessor "android.arch.persistence.room:compiler:1.0.0"

            // Classes for BACKGROUND THREAD to make Room work
            // ViewModel and LiveData
            implementation "android.arch.lifecycle:extensions:1.1.0"
            // alternatively, just ViewModel
            implementation "android.arch.lifecycle:viewmodel:1.1.0"
            // alternatively, just LiveData
            implementation "android.arch.lifecycle:livedata:1.1.0"
            annotationProcessor "android.arch.lifecycle:compiler:1.1.0"

            // JSON PARSING
            compile 'com.android.volley:volley:1.1.0'  // Later we use retrofit
            compile "com.android.support:recyclerview-v7:27.1.0"
            compile "com.android.support:cardview-v7:27.1.0"
            compile 'com.squareup.picasso:picasso:2.5.2'



1. We Setup the a List item XML ( search _ item . xml , giving IDS to each view )
        - Imageview
        - Textview

2. Recyclerview SETUP

        - We Create Model class --SearchItem.java with variables for Image , Text
        - We create the RecyclerviewAdapter ( wee look up the boilerplate & implement the neccecary methods).
        https://developer.android.com/guide/topics/ui/layout/recyclerview.html



3. We try to get the JSON Data & check if its correct
        create a method to parse our JSON via Volley / create the neccecary adapter / item model
        - Creating Object Model

4. implement Singleton patterrn class ( its easy)
https://code.tutsplus.com/tutorials/android-design-patterns-the-singleton-pattern--cms-29153



2 . Recyclerview / RecyclerviewAdapter+ViewHolder  -- ( Viewholders solve issue of too many findviewbyid calls , which is smth that could slow down the UI)
        - Create Single item Layout
        - Add onclick interface
        - Insert single item data Onclick into database ( make sure Primary key has unique id , autogenerate or you own defined key)








!!!
3. ROOM database steps -- ( Setup the other steps properly first) , this is where it gets a little advanced , because we need to adjust our code to run in background threads so the db can accept them ( otherwise app crash if you use main thread).







4. GOOD JOB ! ENJOY & Keep Practice
5- Look into online data storage next ( then you basically know it all :D ). Then create full apps that use online features.



