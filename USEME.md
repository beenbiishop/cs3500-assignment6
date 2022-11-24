# How to Use the Image Processor

_For a more detailed overview of the program, see the [README.md](README.md) file._

## Overview

The image processor can currently be used in two ways: via the command line interface, or via an
imported script file. The command line interface allows users to enter commands with real-time
feedback displayed int the terminal window. The script file interface makes it easy to run a series
of commands in a batch process.

## Supported Commands

The following commands are supported by both the command line interface and the script file
interface:

* "quit" - _quits the program_
* "menu" - _displays the menu of commands_
* "load" <path> <filename> - _loads an image (identified by given name) into the processor  (
  supports ppm, png, jpg, jpeg)_
* "save" <path> <filename> - _saves an image to an output file (supports ppm, png, jpg, jpeg)_
* "visualize-`<component>`" `<filename>` `<new filename>` - _transforms an image to a new greyscale
  image using a chosen component_
    * `<component>` can be "red", "green", "blue", "value", "intensity", or "luma"
* "brighten" `<amount>` `<filename>` `<new filename>` - _transforms an image to a new image
  brightened by an amount_
* "darken" `<amount>` `<filename>` `<new filename>` - _transforms an image to a new image darkened
  by an amount_
* "horizontal-flip" `<filename>` `<new filename>` - _horizontally flips an image to a new image_
* "vertical-flip" `<filename>` `<new filename>` - _vertically flips an image to a new image_
* "blur" `<filename>` `<new filename>` - _transforms an image to a new blurred image_
* "sharpen" `<filename>` `<new filename>` - _transforms an image to a new sharpened image_
* "sepia" `<filename>` `<new filename>` - _transforms an image to a new sepia filtered image_
* "greyscale" `<filename>` `<new filename>` - _transforms an image to a new greyscale filtered
  image_

## Command Line Interface

1. Copy the `res/ImageProcessor.jar` file to the directory where you want to run the program.
2. Open a terminal window and navigate to the directory where you copied the file.
3. Enter the command `java -jar ImageProcessor.jar` to run the program.

Upon launch, the user will be shown a welcome message, with the option to "quit" the program or view
a "menu" of available commands. After entering a command and hitting the return key, the program
will either display a detailed error message or a success message. The user can enter the
command `quit` at any time to exit the program.

## Script File Interface

A script file is a text file containing a series of commands to be executed by the program. The
script file should only contain supported commands, with one command per line. Extra line breaks can
be added for readability, but will not affect the program's execution.

1. Create a text file with the extension `.txt` in the directory where you want to run the program.
2. Enter the commands you want to execute in the file, one per line.
3. Save the file.
4. Copy the `res/ImageProcessor.jar` file to the same directory.
5. Open a terminal window and navigate to the directory where both files are located.
6. Enter the command `java -jar ImageProcessor.jar -script <script file name>` to run the program.
    * _An example script file is provided in the `res` directory (`example-script.txt`)._
7. The program will execute the commands in the script file, and display the success/failure of each
   command in the terminal window as if you were entering the commands manually.

## Graphical User Interface

1. When the program loads, navigate to the File menu item on the left side of the program. Here
   select `Load` and select an image file when the FileChooser pops up.
2. Now navigate to the right side of the program and select from the list of transitions which
   transformation you would like to apply onto your current image.
3. You may apply any number of transformations or choose to not apply any at all before
   saving/loading.
4. To save the image, navigate once more to the File menu item at the top left of the program. Here
   select `Save`.
5. To remove the current image on the screen, repeat the previous step but click `Remove` instead of
   load.
6. To quit the program all together, repeat the previous step but click `quit`.
