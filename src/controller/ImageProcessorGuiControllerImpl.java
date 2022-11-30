package controller;

import controller.commands.BrightnessCmd;
import controller.commands.FilterCmd;
import controller.commands.FilterCmd.FilterType;
import controller.commands.HorizontalFlipCmd;
import controller.commands.LoadCmd;
import controller.commands.SaveCmd;
import controller.commands.VerticalFlipCmd;
import controller.commands.VisualizeCmd;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.Image;
import model.ImageUtils;
import model.StoredImages;
import model.transformations.Visualize.Channel;
import view.ImageProcessorGui;
import view.ImageProcessorGui.DialogType;
import view.ImageProcessorView;
import view.ImageProcessorViewImpl;
import view.panels.MenubarPanel;
import view.panels.TransformationPanel;

/**
 * The class that implements the ImageProcessorGuiController. ImageProcessorGuiControllerImpl
 * implements all the features detailed out in the interface.
 */
public class ImageProcessorGuiControllerImpl implements ImageProcessorGuiController {

  private final StoredImages store;
  private final ImageProcessorView fakeView;
  private ImageProcessorGui view;

  /**
   * Constructs a new controller for the GUI.
   *
   * @param store the store to store images in
   */
  public ImageProcessorGuiControllerImpl(StoredImages store) {
    if (store == null) {
      throw new IllegalArgumentException("Store cannot be null.");
    }
    this.store = store;
    this.fakeView = new ImageProcessorViewImpl(new StringBuilder());
  }

  @Override
  public void setView(ImageProcessorGui view) {
    this.view = view;
    MenubarPanel menu = this.view.getMenubarPanel();
    menu.addFeatures(this);
    TransformationPanel transformations = this.view.getTransformationPanel();
    transformations.addFeatures(this);
  }

  @Override
  public void quit() {
    this.view.close();
    System.exit(0);
  }

  @Override
  public void loadImage() {
    String file = this.view.loadFile(
        new FileNameExtensionFilter("Image Files", "jpg", "jpeg", "png", "bmp"));
    if (file == null) {
      this.view.renderMessage("Load cancelled.");
      return;
    }

    List<String> questions = new ArrayList<>();
    questions.add("Choose a name to load this image as: ");

    String[] answers = this.view.renderInput(questions, null);
    if (answers == null) {
      this.view.renderMessage("Load cancelled.");
      return;
    }

    if (answers[0].equals("")) {
      this.view.renderDialog(DialogType.Danger, "Image name cannot be empty.");
      return;
    }
    String newFileName = answers[0];

    try {
      ImageProcessorCmd command = new LoadCmd(this.view, this.store, file, newFileName);
      command.execute();
    } catch (IllegalArgumentException e) {
      this.view.renderDialog(DialogType.Danger, e.getMessage());
    }

    Image image = this.store.retrieve(newFileName);
    BufferedImage buffered = ImageUtils.getBufferedImage(image);
    int[][] histogram = ImageUtils.getChannelFrequencies(image);
    this.view.displayImage(newFileName, buffered, histogram);
  }

  @Override
  public void saveImage() {
    String file = this.view.saveFile(
        new FileNameExtensionFilter("Image Files", "jpg", "jpeg", "png", "bmp", "ppm"));
    if (file == null) {
      this.view.renderMessage("Save cancelled.");
      return;
    }

    String name = this.view.getCurrentImageName();
    if (name == null) {
      this.view.renderDialog(DialogType.Danger, "No image selected.");
      return;
    }

    try {
      ImageProcessorCmd command = new SaveCmd(this.view, this.store, file, name);
      command.execute();
    } catch (IllegalArgumentException e) {
      this.view.renderDialog(DialogType.Danger, e.getMessage());
    }
  }

  @Override
  public void transformImage(String command) {
    String name = this.view.getCurrentImageName();
    if (name == null) {
      this.view.renderDialog(DialogType.Danger, "No image selected.");
      return;
    }
    List<String> questions = new ArrayList<>();
    questions.add("Choose a name to save this image as: ");
    String[] answers = null;
    String newFileName = null;
    System.out.println(command);
    switch (command) {
      case "Blur":
        answers = this.view.renderInput(questions, null);
        if (answers == null) {
          this.view.renderMessage("Blur cancelled.");
          return;
        } else if (answers[0].length() == 0) {
          this.view.renderDialog(DialogType.Danger, "Image name cannot be empty.");
          return;
        } else {
          newFileName = answers[0];
          try {
            ImageProcessorCmd blur = new FilterCmd(this.fakeView, this.store, FilterType.Blur, name,
                newFileName);
            blur.execute();
          } catch (IllegalArgumentException e) {
            this.view.renderDialog(DialogType.Danger, e.getMessage());
          }
        }
        break;
      case "Brighten":
        questions.add("Enter the amount to brighten the image by: ");
        answers = this.view.renderInput(questions, null);
        String brightnessVal = null;
        if (answers == null) {
          this.view.renderMessage("Brighten cancelled.");
          return;
        } else if (answers[0].length() == 0) {
          this.view.renderDialog(DialogType.Danger, "Image name cannot be empty.");
          return;
        } else if (answers[1].length() == 0) {
          this.view.renderDialog(DialogType.Danger, "Must provide brightness value.");
          return;
        } else {
          newFileName = answers[0];
          brightnessVal = answers[1];
          try {
            ImageProcessorCmd brightness = new BrightnessCmd(this.fakeView, this.store,
                Integer.parseInt(brightnessVal), name, newFileName);
            brightness.execute();
          } catch (IllegalArgumentException e) {
            this.view.renderDialog(DialogType.Danger, e.getMessage());
          }
        }
        break;
      case "Darken":
        questions.add("Enter the amount to darken the image by: ");
        answers = this.view.renderInput(questions, null);
        String darknessVal = null;
        if (answers == null) {
          this.view.renderMessage("Darken cancelled.");
          return;
        } else if (answers[0].length() == 0) {
          this.view.renderDialog(DialogType.Danger, "Image name cannot be empty.");
          return;
        } else if (answers[1].length() == 0) {
          this.view.renderDialog(DialogType.Danger, "Must provide darkness value.");
          return;
        } else {
          newFileName = answers[0];
          brightnessVal = answers[1];
          try {
            ImageProcessorCmd brightness = new BrightnessCmd(this.fakeView, this.store,
                Integer.parseInt(brightnessVal) * -1, name, newFileName);
            brightness.execute();
          } catch (IllegalArgumentException e) {
            this.view.renderDialog(DialogType.Danger, e.getMessage());
          }
        }
        break;
      case "Greyscale":
        answers = this.view.renderInput(questions, null);
        if (answers == null) {
          this.view.renderMessage("Greyscale cancelled.");
          return;
        } else if (answers[0].length() == 0) {
          this.view.renderDialog(DialogType.Danger, "Image name cannot be empty.");
          return;
        } else {
          newFileName = answers[0];
          try {
            ImageProcessorCmd greyscale = new FilterCmd(this.fakeView, this.store,
                FilterType.Greyscale, name, newFileName);
            greyscale.execute();
          } catch (IllegalArgumentException e) {
            this.view.renderDialog(DialogType.Danger, e.getMessage());
          }
        }
        break;
      case "Horizontal Flip":
        answers = this.view.renderInput(questions, null);
        if (answers == null) {
          this.view.renderMessage("Horizontal flip cancelled.");
          return;
        } else if (answers[0].length() == 0) {
          this.view.renderDialog(DialogType.Danger, "Image name cannot be empty.");
          return;
        } else {
          newFileName = answers[0];
          try {
            ImageProcessorCmd flip = new HorizontalFlipCmd(this.fakeView, this.store, name,
                newFileName);
            flip.execute();
          } catch (IllegalArgumentException e) {
            this.view.renderDialog(DialogType.Danger, e.getMessage());
          }
        }
        break;
      case "Vertical Flip":
        answers = this.view.renderInput(questions, null);
        if (answers == null) {
          this.view.renderMessage("Vertical flip cancelled.");
          return;
        } else if (answers[0].length() == 0) {
          this.view.renderDialog(DialogType.Danger, "Image name cannot be empty.");
          return;
        } else {
          newFileName = answers[0];
          try {
            ImageProcessorCmd flip = new VerticalFlipCmd(this.fakeView, this.store, name,
                newFileName);
            flip.execute();
          } catch (IllegalArgumentException e) {
            this.view.renderDialog(DialogType.Danger, e.getMessage());
          }
        }
        break;
      case "Sepia":
        answers = this.view.renderInput(questions, null);
        if (answers == null) {
          this.view.renderMessage("Sepia cancelled.");
          return;
        } else if (answers[0].length() == 0) {
          this.view.renderDialog(DialogType.Danger, "Image name cannot be empty.");
          return;
        } else {
          newFileName = answers[0];
          try {
            ImageProcessorCmd sepia = new FilterCmd(this.fakeView, this.store, FilterType.Sepia,
                name, newFileName);
            sepia.execute();
          } catch (IllegalArgumentException e) {
            this.view.renderDialog(DialogType.Danger, e.getMessage());
          }
        }
        break;
      case "Sharpen":
        answers = this.view.renderInput(questions, null);
        if (answers == null) {
          this.view.renderMessage("Sharpen cancelled.");
          return;
        } else if (answers[0].length() == 0) {
          this.view.renderDialog(DialogType.Danger, "Image name cannot be empty.");
          return;
        } else {
          newFileName = answers[0];
          try {
            ImageProcessorCmd sharpen = new FilterCmd(this.fakeView, this.store, FilterType.Sharpen,
                name, newFileName);
            sharpen.execute();
          } catch (IllegalArgumentException e) {
            this.view.renderDialog(DialogType.Danger, e.getMessage());
          }
        }
        break;
      case "Visualize Red":
        answers = this.view.renderInput(questions, null);
        if (answers == null) {
          this.view.renderMessage("Visualize red cancelled.");
          return;
        } else if (answers[0].length() == 0) {
          this.view.renderDialog(DialogType.Danger, "Image name cannot be empty.");
          return;
        } else {
          newFileName = answers[0];
          try {
            ImageProcessorCmd visualize = new VisualizeCmd(this.fakeView, this.store, Channel.Red,
                name, newFileName);
            visualize.execute();
          } catch (IllegalArgumentException e) {
            this.view.renderDialog(DialogType.Danger, e.getMessage());
          }
        }
        break;
      case "Visualize Green":
        answers = this.view.renderInput(questions, null);
        if (answers == null) {
          this.view.renderMessage("Visualize green cancelled.");
          return;
        } else if (answers[0].length() == 0) {
          this.view.renderDialog(DialogType.Danger, "Image name cannot be empty.");
          return;
        } else {
          newFileName = answers[0];
          try {
            ImageProcessorCmd visualize = new VisualizeCmd(this.fakeView, this.store, Channel.Green,
                name, newFileName);
            visualize.execute();
          } catch (IllegalArgumentException e) {
            this.view.renderDialog(DialogType.Danger, e.getMessage());
          }
        }
        break;
      case "Visualize Blue":
        answers = this.view.renderInput(questions, null);
        if (answers == null) {
          this.view.renderMessage("Visualize blue cancelled.");
          return;
        } else if (answers[0].length() == 0) {
          this.view.renderDialog(DialogType.Danger, "Image name cannot be empty.");
          return;
        } else {
          newFileName = answers[0];
          try {
            ImageProcessorCmd visualize = new VisualizeCmd(this.fakeView, this.store, Channel.Blue,
                name, newFileName);
            visualize.execute();
          } catch (IllegalArgumentException e) {
            this.view.renderDialog(DialogType.Danger, e.getMessage());
          }
        }
        break;
      case "Visualize Luma":
        answers = this.view.renderInput(questions, null);
        if (answers == null) {
          this.view.renderMessage("Visualize luma cancelled.");
          return;
        } else if (answers[0].length() == 0) {
          this.view.renderDialog(DialogType.Danger, "Image name cannot be empty.");
          return;
        } else {
          newFileName = answers[0];
          try {
            ImageProcessorCmd visualize = new VisualizeCmd(this.fakeView, this.store, Channel.Luma,
                name, newFileName);
            visualize.execute();
          } catch (IllegalArgumentException e) {
            this.view.renderDialog(DialogType.Danger, e.getMessage());
          }
        }
        break;
      case "Visualize Value":
        answers = this.view.renderInput(questions, null);
        if (answers == null) {
          this.view.renderMessage("Visualize value cancelled.");
          return;
        } else if (answers[0].length() == 0) {
          this.view.renderDialog(DialogType.Danger, "Image name cannot be empty.");
          return;
        } else {
          newFileName = answers[0];
          try {
            ImageProcessorCmd visualize = new VisualizeCmd(this.fakeView, this.store, Channel.Value,
                name, newFileName);
            visualize.execute();
          } catch (IllegalArgumentException e) {
            this.view.renderDialog(DialogType.Danger, e.getMessage());
          }
        }
        break;
      case "Visualize Intensity":
        answers = this.view.renderInput(questions, null);
        if (answers == null) {
          this.view.renderMessage("Visualize intensity cancelled.");
          return;
        } else if (answers[0].length() == 0) {
          this.view.renderDialog(DialogType.Danger, "Image name cannot be empty.");
          return;
        } else {
          newFileName = answers[0];
          try {
            ImageProcessorCmd visualize = new VisualizeCmd(this.fakeView, this.store,
                Channel.Intensity, name, newFileName);
            visualize.execute();
          } catch (IllegalArgumentException e) {
            this.view.renderDialog(DialogType.Danger, e.getMessage());
          }
        }
        break;
      default:
        // should never happen
        throw new IllegalArgumentException("Invalid command");
    }
    this.view.displayImage(newFileName,
        ImageUtils.getBufferedImage(this.store.retrieve(newFileName)),
        ImageUtils.getChannelFrequencies(this.store.retrieve(newFileName)));
  }
}

