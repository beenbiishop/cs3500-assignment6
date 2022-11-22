package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

import controller.commands.LoadCmd;
import model.Image;
import model.StoredImages;
import model.transformations.Visualize;
import view.ImageProcessorGui;
import view.ImageProcessorView;

public class ImageProcessorGuiControllerImpl implements ImageProcessorGuiController {

  private StoredImages store;
  private ImageProcessorGui view;

  private final Map<String, Function<Scanner, ImageProcessorCmd>> commands;


  public ImageProcessorGuiControllerImpl(StoredImages store) {
    this.store = store;
    this.commands = new HashMap<>();
  }

  /**
   * Starts the controller. The controller will continue to run until the user inputs the "quit"
   * command.
   */
  @Override
  public void run() {

  }

  public void setView(ImageProcessorGui v) {
    this.view = v;
    view.addFeatures(this);
  }

  public void loadImage(String input) {
    Image loadedImage = this.store.retrieve(input);
    this.store.add(input, loadedImage, true);
    view.loadImage();
  }

  public void removeImage(String input) {
    view.removeImage();
    this.store.remove(input);

  }

  public void saveImage(String input, String extension) {
    view.saveImage();
    Image retrieved = this.store.retrieve(input);
    ImageFileHandler handler;

    if (extension.toLowerCase() == "ppm") {
      handler = new ImagePPMHandler();
    } else {
      handler = new ImageIOHandler();
    }

    //not yet sure how to
    handler.export(retrieved, input);

  }

  public void BrightenImage(int value) {
  }

  public void BlurImage(int value) {
  }

  public void GreyscaleImage(int value) {
  }

  public void HorizontalFlipImage(int value) {
  }

  public void VerticalFlipImage(int value) {
  }

  public void SepiaImage(int value) {
  }

  public void SharpenImage(int value) {
  }

  public void VisualizeImage(int value) {
  }

}
