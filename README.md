# Internshala Assessment
This is an assignment for the post of SDE-intern in internshala

## Problem Statement
Design and develop a simple Notes Android app, which has the following features:
1. Use only 1 activity for the entire app. Use fragments for different screens.
2. When a user starts the app, a login screen appears (if the user is not logged in). Login can only happen through Google Sign-In. Use shared preferences/ SQLite to track logged-in users.
3. Once the user logs in, display all notes of the logged-in user (complete notes, not preview). Use Recyclerview to display notes.
4. Add a button to add a new note.
5. Allow users to update a note. 
6. Allow the user to delete a note.

## Final prototype
<p align="center">
  <img width="auto" height="auto" alt="parallel processing" src="gifs/feature-gif.gif">
</p>

## Features

1. MVVM architecture.
2. Repository Pattern.
3. Modular design (User management module)
4. Clean and minimal UI design.
5. Provision to include Network requests when required.
6. One time sign-in using Google Sign In API.
8. Ability to search across title and contents
9. Mark note as important.

## APK

Please find the signed apk file of this app [here](https://github.com/JerrySJoseph/InternShala-Notes-App/tree/master/app/release)


License
-------

    Copyright 2014 - 2021 Jerin Sebastian

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
