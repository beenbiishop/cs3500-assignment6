package controller;

import view.ImageProcessorGui;

/**
 * Interface with all the features that the user can request.
 */
public interface ImageProcessorGuiController {

  /**
   * Controls the feature that loads an image onto the ImageProcessorGui (view).
   */
  void loadImage();

  /**
   * Controls the feature that removes an image that was previously loaded from the
   * ImageProcessorGui (view).
   */
  void removeImage(String input);

  /**
   * Controls the feature that saves the current image on the screen into the
   * computer's file directory.
   */
  void saveImage();

  /**
   * Transforms the image according to what the user selected to apply from the list of Commands.
   * @param command a string that represents a selected command.
   */
  void transformImage(String command);

  /**
   * Sets the view as the given ImageProcessorGui view and connects the controller and view.
   * Passes an actionListener to the view, as that it could add these actionListeners to each
   * button in the JFrame.
   * @param view
   */
  void setView(ImageProcessorGui view);
}
