package view;

import controller.ImageProcessorGuiController;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

/**
 * This interface represents a view of the Image Processor that implements the graphical user
 * interface.
 */
public interface ImageProcessorGui extends ImageProcessorView {

  /**
   * Adds the action listeners from the controller to the view.
   *
   * @param features the controller with action listeners to add
   */
  void addFeatures(ImageProcessorGuiController features);

  /**
   * Displays a {@link BufferedImage} in the view identified by the given file name.
   *
   * <p>
   * If an image's file name has already been added, the previous buffered image will be overridden
   * with the new image, otherwise a new tab will be added (like a map).
   * </p>
   *
   * @param name  the name to identify this image by
   * @param image the image to render in the preview panel
   */
  void displayImage(String name, BufferedImage image);


  /**
   * Removes an image from the preview pane identified by the given file name.
   *
   * @param name the name of the image to remove
   * @return true if the image was removed, false if the file name was not found
   */
  boolean removeImage(String name);

  /**
   * Renders a given dialog message to the user in the form of a popup.
   *
   * @param type    the type of popup to display
   * @param message the content of the message to display
   */
  void renderDialog(DialogType type, String message);

  /**
   * Renders a form with multiple inputs in the form of a popup.
   *
   * <p>An input box will be generated for each question title in the given list of strings, and an
   * equally sized list will be returned with the response to each question. If a user did not
   * answer any of the questions, an empty string will be returned in its' place.</p>
   *
   * @param questions
   * @return
   */
  String[] renderInput(List<String> questions, String error);


  File loadFile(String prompt, String fileTypes);

  enum DialogType {
    Success, Warning, Danger, Note
  }

}
