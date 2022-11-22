package model;

import java.awt.Color;

/**
 * Represents utilities for images.
 */
public final class ImageUtils {

  /**
   * Returns a 2D array of integers that represent the given image's frequencies of red, green,
   * blue, and intensity values in the range [0, 255].
   *
   * <p>
   * The 2D array is in the format [channel][value], where channel is the index of the channel (0 =
   * red, 1 = green, 2 = blue, 3 = intensity) and value is the index of the value (0 = 0, 1 = 1,
   * ..., 255 = 255).
   * </p>
   *
   * @param image the image to get the frequencies of
   * @return a 2D array of integers in the format [channel][value]
   */
  public static int[][] getChannelFrequencies(Image image) {
    int[][] frequencies = new int[4][256];
    Color[][] pixels = image.getPixels();
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        Color pixel = pixels[i][j];
        int red = pixel.getRed();
        int green = pixel.getGreen();
        int blue = pixel.getBlue();
        int intensity = Math.toIntExact(Math.round((red + green + blue) / 3.0));
        frequencies[0][red]++;
        frequencies[1][green]++;
        frequencies[2][blue]++;
        frequencies[3][intensity]++;
      }
    }
    return frequencies;
  }

}
