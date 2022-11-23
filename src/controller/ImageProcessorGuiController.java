package controller;

import view.ImageProcessorGui;

public interface ImageProcessorGuiController {

  void loadImage();

  void removeImage(String input);
  void saveImage();

  void transformImage(String command);
  void setView(ImageProcessorGui view);
}
