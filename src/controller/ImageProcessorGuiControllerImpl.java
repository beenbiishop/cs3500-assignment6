package controller;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

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

  private String fileName;

  private String newFileName;

  private List<String> questions;
  private String[] answers;

  public ImageProcessorGuiControllerImpl(StoredImages store) {
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
  public void removeImage(String input) {
    this.view.removeImage(input);
    this.store.remove(input);
  }

  @Override
  public void saveImage() {
    this.questions = new ArrayList<>();
    questions.add("Please select which file to save.");

    this.answers = this.view.renderInput(questions, null);
    String file = answers[0];

    //not sure how to use the filenameextensionfilter just yet,,
    FileNameExtensionFilter filter = new FileNameExtensionFilter(null, file);
    String path = this.view.loadFile(filter);

    ImageProcessorCmd command = new SaveCmd(this.view, this.store, path, file);
    command.execute();
  }

  private void questions() {
    this.questions = new ArrayList<>();
    questions.add("Please select a file.");
    questions.add("Give this file a new name:");
  }

  private void answers() {
    String[] answers = this.view.renderInput(questions, null);
    this.fileName = answers[0];
    this.newFileName = answers[1];
  }

  private void storedImage() {
    Image image = this.store.retrieve(newFileName);
    BufferedImage buffered = (BufferedImage) image;
    int[][] histogram = image.makeHistogram();
    this.view.displayImage(fileName, buffered, histogram);
  }

  @Override
  public void transformImage(String command) {

    switch (command) {
      case "Blur":
        this.questions();
        this.answers();
        ImageProcessorCmd blur = new FilterCmd(this.view, this.store, FilterCmd.FilterType.Blur,
                fileName, newFileName);
        blur.execute();
        this.storedImage();
        break;
      case "Brightness":
        this.questions();
        this.questions.add("How much do you want to brighten by?");
        this.answers();
        int value = Integer.parseInt(this.answers[2]);
        ImageProcessorCmd brighten = new BrightnessCmd(this.view, this.store, value, fileName,
                newFileName);
        brighten.execute();
        this.storedImage();
        break;
      case "Greyscale":
        this.questions();
        this.answers();
        ImageProcessorCmd greyscale = new FilterCmd(this.view, this.store,
                FilterCmd.FilterType.Greyscale, fileName, newFileName);
        greyscale.execute();
        this.storedImage();
        break;
      case "HorizontalFlip":
        this.questions();
        this.answers();
        ImageProcessorCmd horFlip = new HorizontalFlipCmd(this.view, this.store, fileName,
                newFileName);
        horFlip.execute();
        this.storedImage();
        break;
      case "VerticalFlip":
        this.questions();
        this.answers();
        ImageProcessorCmd verFlip = new VerticalFlipCmd(this.view, this.store, fileName,
                newFileName);
        verFlip.execute();
        this.storedImage();
        break;
      case "Sepia":
        this.questions();
        this.answers();
        ImageProcessorCmd sepia = new FilterCmd(this.view, this.store, FilterCmd.FilterType.Sepia,
                fileName, newFileName);
        sepia.execute();
        this.storedImage();
        break;
      case "Sharpen":
        this.questions();
        this.answers();
        ImageProcessorCmd sharpen = new FilterCmd(this.view, this.store, FilterCmd.FilterType.Sharpen,
                fileName, newFileName);
        sharpen.execute();
        this.storedImage();
        break;
      case "Visualize-Red":
        this.questions();
        this.answers();
        ImageProcessorCmd visualizeR = new VisualizeCmd(this.view, this.store,
                Visualize.Channel.Red, fileName, newFileName);
        visualizeR.execute();
        this.storedImage();
        break;
      case "Visualize-Green":
        this.questions();
        this.answers();
        ImageProcessorCmd visualizeG = new VisualizeCmd(this.view, this.store,
                Visualize.Channel.Green, fileName, newFileName);
        visualizeG.execute();
        this.storedImage();
        break;
      case "Visualize-Blue":
        this.questions();
        this.answers();
        ImageProcessorCmd visualizeB = new VisualizeCmd(this.view, this.store,
                Visualize.Channel.Blue, fileName, newFileName);
        visualizeB.execute();
        this.storedImage();
        break;
      case "Visualize-Luma":
        this.questions();
        this.answers();
        ImageProcessorCmd visualizeL = new VisualizeCmd(this.view, this.store,
                Visualize.Channel.Luma, fileName, newFileName);
        visualizeL.execute();
        this.storedImage();
        break;
      case "Visualize-Value":
        this.questions();
        this.answers();
        ImageProcessorCmd visualizeV = new VisualizeCmd(this.view, this.store,
                Visualize.Channel.Value, fileName, newFileName);
        visualizeV.execute();
        this.storedImage();
        break;
      case "Visualize-Intensity":
        this.questions();
        this.answers();
        ImageProcessorCmd visualizeI = new VisualizeCmd(this.view, this.store,
                Visualize.Channel.Intensity, fileName, newFileName);
        visualizeI.execute();
        this.storedImage();
        break;
      default:
        throw new IllegalArgumentException("Invalid command");
    }
  }
}

