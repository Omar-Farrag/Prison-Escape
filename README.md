# PRISON ESCAPE PROJECT

## Description

An adventurous, open world, command line and text based role-playing game set in prison. The player spawns in his cell and interacts with surroundings to find clues that can help him escape prison. Additionally, the player can use a phone to
punch/dodge during fist fights, play basketball, and lift dumbbells. The game also has a very dynamic nature where the player's actions can influence in-game events. Throughout the game, the player has a good-bad index: good behavior provides greater access to prison rooms while bad behavior frees the player from harrassment by other inmates.

## How to play the game

You need to follow the following instructions to be able to play the game:

### Downloads

1. Download the Eclipse IDE by going to the following link: https://www.eclipse.org/downloads/
2. Clone this repository (either git clone or download zip file and extract)
3. Download the SensorStreamer application from the play store (optional)

### Game Setup

1. Open Eclipse and import the project
2. Add the json-simple-1.1.jar file to the project's paths through the following steps:
     - Right click on project name in the left side-bar
     - Click on properties
     - Click on Java Build Path from the left side-bar of the window that appears
     - Click on the libraries tab in the center of the window
     - Click on Classpath then Add External Jars
     - Browse and select the json-simple-1.1.jar file (it can be found among the project's files)
     - Click on the order and export tab in the center of the window
     - Click on apply and close
3. Use the same instructions in step 4 to add CharacterLines and Tracks folder to project classpath. However, instead of clicking on Add External Jars, click on add class folder and choose the mentioned folders
4. Navigate to a folder named "General" from the left side-bar and double click on main
5. If you want to use sensors while playing, complete the Sensors Setup below
6. Run the file
7. When prompted for host IP address and port number, use your phone's IP address and the port number configured on the SensorStreamer application
8. Enjoy the game 😊

### Sensors Setup (Optional)

1. Download the SensorStreamer application from the Google Play Store
2. Open the application and go to "Manage Packets"
3. Create a new packet
     - Packet type is JSON
     - Choose all available sensors
     - Save packet
4. Go to "Manage Connections"
5. Create a new connection
     - Set the connection type to TCP Server
     - Choose a port number between 1 - 65,536 (4242 for example)
     - Save connection
6. Go to "Stream"
7. Choose connection and packet created then start
8. You're now good to go

If at any point the connection is interrupted, repeat steps 6 - 8.

## Helpful Commands to type while playing the Game

-    **commands?** : Displays a list of all commands that can be executed in the player's current location
-    **items?** : Displays a list of all items present in the player's current location
-    **persons?** : Displays a list of all people present in the player's current location
-    **directions?** : Displays a list of directions the player can walk along
-    **map?** : Displays the prison map
-    **diagnostics?** : Displays some critical information such as current location, time, inventory, etc.
-    **[name of item]?** : Displays the commands that can be executed on a specific item. For example, typing "basketball?" will display
     "shoot/dribble", meaning the player can type "shoot basketball" or "dribble basketball"

## Languages and Frameworks

This project was written exclusively using Java. Audio files are all in .WAV format and text files are in .txt format. For Sensor logs parsing, the json-simple-1.1 package was used, which can be found among project files
