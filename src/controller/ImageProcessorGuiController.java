package controller;

import view.ImageProcessorGui;

public interface ImageProcessorGuiController extends ImageProcessorController {

  void loadImage(String input);

  void removeImage(String input);
  void saveImage(String input);

  void transformImage(String command);

  void setView(ImageProcessorGui view);
}
