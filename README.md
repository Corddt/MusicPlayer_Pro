English | [简体中文](README_zh.md)
# MusicPlayer_Pro
MusicPlayer_Pro is an Android music player app that provides music playing, lyrics display, and video playing features. The app allows users to enjoy the experience of playing music and videos through a user-friendly interface.

## Features
1. **Music playing**: Users can play and pause music, and control the music playing progress through a progress bar.
2. **Lyrics display**: The app provides a separate interface for displaying lyrics, and the lyrics of each song are defined in a separate resource file.
3. **Video playing**: In addition to music playing, the app also supports playing video files.
4. **Custom circle image view**: The app contains a custom `CircleImageView` class for displaying images in a circular way, such as album covers.

## How to use
- **Music playing**: In `MusicActivity`, users can control the playing and pausing of music through the play button. The progress bar shows the current playing progress, and users can drag the progress bar to change the playing position.
- **Viewing lyrics**: Click the specific button in `MusicActivity`, the app will jump to `LyricsActivity`, where users can see the lyrics of the currently playing song.
- **Video playing**: In `VideoActivity`, users can play video files. The playing, pausing, and progress control of the video are similar to the music playing features.

## Development and environment configuration
- Development environment: Android Studio
- Target SDK: API34

## Build and run
1. Clone the repository to local or download the source code.
2. Open the project with Android Studio.
3. Connect an Android device or use an emulator.
4. Build and run the app.

## Contribution guidelines
Contributions to the project are welcome, whether it's new features, bug fixes, or documentation improvements. Please follow these steps:
1. Fork the project repository.
2. Create a new branch.
3. Commit your changes.
4. Open a pull request.

## License
This project uses the MIT license. For more information, please see [LICENSE](LICENSE).

## Future plans
- [ ] Optimize the interface layout
- [ ] Package and release the complete app