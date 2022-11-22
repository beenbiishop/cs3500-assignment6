package controller;

import model.Image;
import model.ImageTransformation;
import view.ImageProcessorGui;
import view.ImageProcessorView;

public class ImageProcessorGuiControllerImpl implements ImageProcessorGuiController {

  private Image model;
  private ImageProcessorGui view;


  @Override
  public void run() {

  }

  public void loadImage() {

  }

  public void removeImage() {
  }

  public void saveImage() {
  }

  public void transformImage() {
//    String text = model.getString();
//    view.transformImage(text);
  }

  public void setView(ImageProcessorGui v) {
    this.view = v;
    view.addFeatures(this);

  }



}
