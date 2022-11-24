package controller;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.swing.filechooser.FileNameExtensionFilter;

import controller.commands.BrightnessCmd;
import controller.commands.FilterCmd;
import controller.commands.HorizontalFlipCmd;
import controller.commands.LoadCmd;
import controller.commands.SaveCmd;
import controller.commands.VerticalFlipCmd;
import controller.commands.VisualizeCmd;
import model.Image;
import model.StoredImages;
import model.transformations.Visualize;
import view.ImageProcessorGui;

/**
 * The class that implements the ImageProcessorGuiController.
 * ImageProcessorGuiControllerImpl implements all the features detailed out in the interface.
 */
public class ImageProcessorGuiControllerImpl implements ImageProcessorGuiController {

  private final StoredImages store;
  private ImageProcessorGui view;

  private String newFileName;
  private String name;

  private List<String> questions;
  private String[] answers;

  public ImageProcessorGuiControllerImpl(StoredImages store) {
    if(store == null) {
      throw new IllegalArgumentException("Store cannot be null.");
    }
    this.store = store;
  }

  public void setView(ImageProcessorGui v) {
    this.view = v;
    view.addFeatures(this);
  }

  @Override
  public void loadImage() {
    List<String> questions = new ArrayList<>();
    questions.add("Please select a file format.");
    questions.add("Give this file a new name:");

    String[] answers = this.view.renderInput(questions, null);
    String fileFormat = answers[0];
    String newFileName = answers[1];

    FileNameExtensionFilter filter = new FileNameExtensionFilter(null, fileFormat);
    String path = this.view.loadFile(filter);

    ImageProcessorCmd command = new LoadCmd(this.view, this.store, path, newFileName);
    command.execute();

    Image image = this.store.retrieve(newFileName);
    BufferedImage buffered = (BufferedImage) image;
    int[][] histogram = image.makeHistogram();

    this.view.displayImage(newFileName, buffered, histogram);
  }

  @Override
  public void removeImage() {
    String name = this.view.getCurrentImageName();
    this.view.removeImage(name);
    this.store.remove(name);
  }

  @Override
  public void saveImage() {
    List<String> questions = new ArrayList<>();
    questions.add("Please select a file format.");

    String[] answers = this.view.renderInput(questions, null);
    String fileFormat = answers[0];

    String name = this.view.getCurrentImageName();

    //not sure how to use the filenameextensionfilter just yet,,
    FileNameExtensionFilter filter = new FileNameExtensionFilter(null, fileFormat);
    String path = this.view.loadFile(filter);

    ImageProcessorCmd command = new SaveCmd(this.view, this.store, path, name);
    command.execute();
  }

  private void questions() {
    this.questions = new ArrayList<>();
    questions.add("Give this file a new name:");
  }

  private void answers() {
    String[] answers = this.view.renderInput(questions, null);
    this.newFileName = answers[0];
  }

  private void storedImage() {
    Image image = this.store.retrieve(newFileName);
    BufferedImage buffered = (BufferedImage) image;
    int[][] histogram = image.makeHistogram();
    this.view.displayImage(newFileName, buffered, histogram);
  }

  @Override
  public void transformImage(String command) {

    switch (command) {
      case "Blur":
        this.questions();
        this.answers();
        this.name = this.view.getCurrentImageName();
        ImageProcessorCmd blur = new FilterCmd(this.view, this.store, FilterCmd.FilterType.Blur,
                this.name, newFileName);
        blur.execute();
        this.storedImage();
        break;
      case "Brightness":
        this.questions();
        this.questions.add("How much do you want to brighten by?");
        this.answers();
        this.name = this.view.getCurrentImageName();        int value = Integer.parseInt(this.answers[2]);
        ImageProcessorCmd brighten = new BrightnessCmd(this.view, this.store, value, name,
                newFileName);
        brighten.execute();
        this.storedImage();
        break;
      case "Greyscale":
        this.questions();
        this.answers();
        this.name = this.view.getCurrentImageName();
        ImageProcessorCmd greyscale = new FilterCmd(this.view, this.store,
                FilterCmd.FilterType.Greyscale, name, newFileName);
        greyscale.execute();
        this.storedImage();
        break;
      case "HorizontalFlip":
        this.questions();
        this.answers();
        this.name = this.view.getCurrentImageName();
        ImageProcessorCmd horFlip = new HorizontalFlipCmd(this.view, this.store, name,
                newFileName);
        horFlip.execute();
        this.storedImage();
        break;
      case "VerticalFlip":
        this.questions();
        this.answers();
        this.name = this.view.getCurrentImageName();
        ImageProcessorCmd verFlip = new VerticalFlipCmd(this.view, this.store, name,
                newFileName);
        verFlip.execute();
        this.storedImage();
        break;
      case "Sepia":
        this.questions();
        this.answers();
        this.name = this.view.getCurrentImageName();
        ImageProcessorCmd sepia = new FilterCmd(this.view, this.store, FilterCmd.FilterType.Sepia,
                name, newFileName);
        sepia.execute();
        this.storedImage();
        break;
      case "Sharpen":
        this.questions();
        this.answers();
        this.name = this.view.getCurrentImageName();
        ImageProcessorCmd sharpen = new FilterCmd(this.view, this.store, FilterCmd.FilterType.Sharpen,
                name, newFileName);
        sharpen.execute();
        this.storedImage();
        break;
      case "Visualize-Red":
        this.questions();
        this.answers();
        this.name = this.view.getCurrentImageName();
        ImageProcessorCmd visualizeR = new VisualizeCmd(this.view, this.store,
                Visualize.Channel.Red, name, newFileName);
        visualizeR.execute();
        this.storedImage();
        break;
      case "Visualize-Green":
        this.questions();
        this.answers();
        this.name = this.view.getCurrentImageName();
        ImageProcessorCmd visualizeG = new VisualizeCmd(this.view, this.store,
                Visualize.Channel.Green, name, newFileName);
        visualizeG.execute();
        this.storedImage();
        break;
      case "Visualize-Blue":
        this.questions();
        this.answers();
        this.name = this.view.getCurrentImageName();
        ImageProcessorCmd visualizeB = new VisualizeCmd(this.view, this.store,
                Visualize.Channel.Blue, name, newFileName);
        visualizeB.execute();
        this.storedImage();
        break;
      case "Visualize-Luma":
        this.questions();
        this.answers();
        this.name = this.view.getCurrentImageName();
        ImageProcessorCmd visualizeL = new VisualizeCmd(this.view, this.store,
                Visualize.Channel.Luma, name, newFileName);
        visualizeL.execute();
        this.storedImage();
        break;
      case "Visualize-Value":
        this.questions();
        this.answers();
        this.name = this.view.getCurrentImageName();
        ImageProcessorCmd visualizeV = new VisualizeCmd(this.view, this.store,
                Visualize.Channel.Value, name, newFileName);
        visualizeV.execute();
        this.storedImage();
        break;
      case "Visualize-Intensity":
        this.questions();
        this.answers();
        this.name = this.view.getCurrentImageName();
        ImageProcessorCmd visualizeI = new VisualizeCmd(this.view, this.store,
                Visualize.Channel.Intensity, name, newFileName);
        visualizeI.execute();
        this.storedImage();
        break;
      default:
        throw new IllegalArgumentException("Invalid command");
    }
  }
}

