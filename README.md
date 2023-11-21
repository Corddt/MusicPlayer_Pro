English | [简体中文](README_zh.md)

# MusicPlayer_Pro Technical Documentation

## 1. Project Overview

MusicPlayer_Pro is an Android music player application that not only provides basic music playback and pause functions but also supports lyrics display and custom circular image view presentation. The app offers users a rich music playback experience through a user-friendly interface.

## 2. System Requirements

- Android Studio 3.0 or higher
- Android SDK API Level 28 or higher
- Gradle 4.1 or higher

## 3. Introduction to Functional Modules

### Main Interface (`MainActivity`)

This code represents the implementation of the `MainActivity` of the `com.corddt.musicplayer_pro` music player application. It is mainly responsible for initializing the interface and providing user interaction functions.

#### Basic Functions
1. **Theme Setting**: Set the application's theme style based on the device's current mode (day or night).
2. **Song Selector Setting**: Initialize and configure a dropdown list (Spinner) that allows users to select songs from a predefined list.
3. **Exit Button Setting**: Provide an exit button to allow users to exit the application.

#### Code Flow
1. **onCreate(Bundle savedInstanceState)**: This method is called when the activity is created.
    - First, set the theme based on the current night mode.
    - Set the content view to the `activity_main` layout.
    - Call the `setupSongSpinner()` and `setupExitButton()` methods to initialize interface components.

2. **setupSongSpinner()**: Set up the song selector.
    - Get the song title array from resources.
    - Create a new array with a prompt option for the Spinner.
    - Create and set an adapter to display the list of songs.
    - Set an item selection listener for the Spinner that calls the `openMusicActivity` method when a song is selected.

3. **setupExitButton()**: Set a listener for the exit button to exit the application when clicked.

4. **openMusicActivity(String songName)**: Open the `MusicActivity` based on the selected song name and pass the song name via an Intent.

#### Key Methods
- `onCreate(Bundle savedInstanceState)`: Initialization method when the activity is created.
- `setupSongSpinner()`: Initialize and configure the song selector.
- `setupExitButton()`: Set the behavior of the exit button.
- `openMusicActivity(String songName)`: Open the music playback interface based on the selected song.

### Playback Interface (`MusicActivity`)

This code represents the implementation of the `MusicActivity` class within the `com.corddt.musicplayer_pro` application. It is primarily responsible for the functionality and user interaction of the music playback interface.

#### Basic Functions
1. **Playback Control**: Control the playback and pause of music.
2. **Animation Effects**: Implement a rotating animation for the album cover.
3. **Progress Bar Control**: Display and control the progress of music playback.
4. **Interface Navigation**: Allow users to navigate to the lyrics interface or return to the previous interface.

#### Code Flow
1. **onCreate(Bundle savedInstanceState)**: This method is called when the activity is created.
    - Initialize interface elements (play button, image view, progress bar).
    - Retrieve the song name from the intent received from the previous activity.
    - Call `setupMediaPlayer`, `setupRotationAnimator`, and `setupSeekBar` methods to configure the media player, animation, and progress bar.
    - Set click event listeners for the image view and play button.

2. **setupMediaPlayer(String songName)**: Configure the `MediaPlayer` based on the provided song name.

3. **setupRotationAnimator()**: Set up the rotating animation for the album cover.

4. **setupSeekBar()**: Set the maximum value and update logic for the progress bar, and define behavior when the user drags the progress bar.

5. **togglePlayPause()**: Control the playback and pause of music, and update button display and rotation animation based on the playback state.

#### Key Methods
- `onCreate(Bundle savedInstanceState)`: Main initialization method when the activity is created.
- `setupMediaPlayer(String songName)`: Configure the media player based on the song name.
- `setupRotationAnimator()`: Set up the album cover rotation animation.
- `setupSeekBar()`: Configure the music playback progress bar.
- `togglePlayPause()`: Logic for controlling music playback and pause.
- `onDestroy()`: Release `MediaPlayer` resources when the activity is destroyed.

### Lyrics Display (`LyricsActivity`)

This code represents the implementation of the `LyricsActivity` class within the `com.corddt.musicplayer_pro` application. It is responsible for displaying song lyrics and allowing users to select the language of the lyrics.

#### Basic Functions
1. **Display Lyrics**: Display lyrics based on the selected song and language.
2. **Language Selection**: Provide a dropdown list (Spinner) for users to select the language of the lyrics (original, Chinese, English).
3. **Back Button**: Provide a back button to allow users to return to the previous interface.

#### Code Flow
1. **onCreate(Bundle savedInstanceState)**: This method is called when the activity is created.
    - Retrieve the song name passed via the intent.
    - Call `setupBackButton` and `setupLanguageSpinner` methods to initialize interface components.

2. **setupBackButton()**: Initialize and set the click event listener for the back button to finish the current activity.

3. **setupLanguageSpinner()**: Set up the language selector.
    - Initialize the language selector and set an adapter.
    - Set an item selection listener for the language selector that calls the `updateLyricsView` method to update the lyrics display when a different language is selected.

4. **updateLyricsView(int languagePosition)**: Update the lyrics view based on the selected language.
    - Determine the identifier for the lyrics resource based on the selected language and song name.
    - Retrieve the corresponding lyrics string from resources and update the lyrics display view.

#### Key Methods
- `onCreate(Bundle savedInstanceState)`: Initialization method when the activity is created.
- `setupBackButton()`: Set the behavior of the back button.
- `setupLanguageSpinner()`: Configure the language selector.
- `updateLyricsView(int languagePosition)`: Update the lyrics view based on the selected language.

### Custom Circular Image Rotation View (`CircleImageView`)

This code represents the implementation of the `CircleImageView` class within the `com.corddt.musicplayer_pro` application, which is a custom `AppCompatImageView` control. Its main function is to crop and display images in a circular shape.

#### Basic Functions
1. **Circular Image Display**: Crop any image assigned to this view into a circular shape.

#### Code Flow
1. **Constructor**: Defines three constructors that accept different parameters (`Context`, `AttributeSet`, `defStyleAttr`) to accommodate different use cases within an Android application.

2. **onDraw(Canvas canvas)**: Overrides the `onDraw` method of `AppCompatImageView`.
    - Check if an image has been set; if not, do not perform drawing.
    - Check the width and height of the view to ensure they are not zero.
    - Convert the retrieved `Drawable` to a `Bitmap`.
    - Call the `getCroppedBitmap` method to crop the `Bitmap` into a circular shape.
    - Draw the cropped circular image on the canvas.

3. **getCroppedBitmap(Bitmap bmp, int radius)**: A static method used to crop the incoming bitmap into a circular shape.
    - Check the dimensions of the bitmap and scale it if necessary to fit the given radius.
    - Create a new bitmap and canvas for drawing the circular image.
    - Use `Paint` and `Xfermode` to crop and draw the circular image.
    - Return the cropped circular bitmap.

#### Key Methods
- `onDraw(Canvas canvas)`: The main method responsible for drawing the image.
- `getCroppedBitmap(Bitmap bmp, int radius)`: A static helper method for cropping a bitmap into a circular shape.

### Splash Screen (`SplashActivity`)

This code defines the `SplashActivity` class within the `com.corddt.musicplayer_pro` application. It serves as a splash screen activity that displays an animated interface when the app is launched.

#### Basic Functions
1. **Display Splash Screen**: Show a splash screen with animations when the app is launched.
2. **Day/Night Mode Adaptation**: Display different splash screen layouts based on the device's current day/night mode.
3. **Animation Effects**: Apply animation effects to the splash screen.
4. **Automatic Navigation**: Automatically navigate to the main interface after a certain duration of displaying the splash screen.

#### Code Flow
1. **onCreate(Bundle savedInstanceState)**: This method is called when the activity is created.
    - First, set the appropriate splash screen layout based on the current day/night mode.
    - Load defined animation resources.
    - Find the `ImageView` in the layout and apply animation effects to it.
    - Set a `Handler` to delay the transition to the `MainActivity` by 3 seconds and finish the current `SplashActivity`.

#### Key Methods
- `onCreate(Bundle savedInstanceState)`: Initialization method when the activity is created.

### Class Diagram Display

![Class Diagram](readme_pics/类图.png)

## 4. Key Implementation Details

### Synchronization of Progress Bar and Cover Rotation

To synchronize the progress bar with the cover rotation animation, real-time updates to the cover's rotation angle need to be made when the user interacts with the progress bar. This is achieved by calling the `updateRotationAnimator()` method.

### Song Resource Management

Song information (title, file, cover, and lyrics) is stored in `res/values/songs.xml` and mapped to specific files using resource IDs.

### Exception Handling

During development, attention should be given to handling exceptions related to the MediaPlayer, such as resource not found or playback errors.

## 5. Layout and Styles

The application's interface layout files are located in the `res/layout/` directory, and themes and styles are defined in `res/values/styles.xml`. The application supports a night mode, and different theme styles can be configured in the `res/values-night/` directory.

## 6. Building and Testing

The application is built using Gradle. Before building a release version, run lint checks and address any potential issues.
1. Clone the repository or download the source code.
2. Open the project in Android Studio.
3. Connect an Android device or start an emulator.
4. Build and run the application.

## 7. Version Control

Git is used for version control. Ensure that each change has appropriate commit messages and utilize branching features.

## 8. Future Plans

- **Audio Recognition and Auto-generated Lyrics**: Planned feature aimed at auto-generating lyrics through audio recognition technology.
- **Language Translation and Localization**: Plan to add language translation features to help users overcome language barriers and enjoy music better.

### 9. Contribution Guidelines

Contributions to the project, including new features, bug fixes, or documentation improvements, are welcome. Follow these steps to contribute:
1. Fork the project repository.
2. Create your branch.
3. Submit your changes.
4. Create a Pull Request.

### 10. License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

### 11. Technical Support

If you encounter any issues during usage or have suggestions, please provide feedback via Issues or Pull Requests. We greatly appreciate any form of contribution to enhance MusicPlayer_Pro.

**Note**: In this document, "we" and "project" refer to the MusicPlayer_Pro application and its development team.
