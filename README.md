
![Logo](https://github.com/DeckyProgrammer/Decky/blob/master/Inclusi'game.png)


# Decky - Inclusigame

The Decky application lets users view and interact with the cards they draw from the Decky Box via Bluetooth with an Android device. It has been coded in Kotlin, using Android Studio as IDE.
The application lets you choose a pack of cards, standard or Uno, then draw and play cards. The cards are physically drawn by the Decky Box, then scanned and sent to the application's game screen. Users can select the card they want to send, then press the play button to send it out of the Decky Box.

The folder contains several files :

- MainActivity :
Where the player can start a new game and verify its device is Bluetooth compatible

- noms2 : 
Where the player can chose the type of game he wants to play

- Gameplay :
Where the player can draw cards, organize them and play them

- Interpreter : 
This file is used to create an interpreter class to use the AI model created on TensorFlow





## Authors

- [@DeckyProgrammer](https://www.github.com/DeckyProgrammer)
- [@DeckyCreator](https://www.github.com/DeckyCreator)
- [@hdufaure](https://www.github.com/hdufaure)
- [@tribulle](https://www.github.com/tribulle)
## Issues

- The Uno game is not usable as it is, some changes must be made to the Gameplay file to account for the game chosen by the player

- The AI for Uno isn't implemented in our application at the moment
## Installation

The application can be installed by downloading the apk file located here :
https://github.com/DeckyProgrammer/Decky/tree/master/app/release
    