package controller;

/**
 * Represents a controller for the Image Processor program. It handles the interactions between the
 * user and the program for the graphical user interface.
 */
public interface ImageProcessorGuiController extends ImageProcessorController {

  /**
   * Action listener to handle the loading of an image into the program.
   */
  void loadImage();

  /**
   * Action listener to handler the removal of an image from the program.
   */
  void removeImage();

  /**
   * Action listener to handle the saving of an image from the program.
   */
  void saveImage();

  /**
   * Action listener to handle the transformation of an image in the program.
   */
  void transformImage(String transformation);
}
