# Random-Dungeon
A top-down dungeon crawler with procedurally generated levels. Uses StdDraw library of functions 

## Getting Started
Download/clone the repo and open the folder in an IDE (that supports Java) of your choice. From there, just run Main.java.

## How to Use
When launching the program, you will see a title screen with three option: New World, Load World, and Quit. Press the N key on your keyboard to create a new world, and you'll be able to start entering the integers of the seed you want to use for the random world generation. When you are done entering integers, press the S key and the world will be generated. To load a world, press the L key instead when you are in the title screen, and the program will load the last-played level (if there is one). The last option on the title screen can be selected with the Q key, which will prompt the program to close.

## In-Game
While you are in-game, you can used the W, A, S, and D keys to go up, left, down, and right, respectively. You can explore the dungeon to your hearts content! Pressing the : key (Shift + ;) and then the Q key will exit the program.

## Autosave
This program has an autosave function where it automatically saves the level layout and your current position. The save file will be saved in the root folder of the program (the Random-Dungeon folder), and it'll be named "savefile.txt". As long as this file exists, choosing "Load World" option on the title screen (see the How to Use section above) will load up your last-played level and remember your character's last position. Note that--if you do have an existing autosave--you save will be overwritten when you create a new world.
