import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageManager {
    private static BufferedImage scaleImageToFit(BufferedImage originalImage, int targetWidth, int targetHeight) {
        // Calculate scaling factors to fit the image within the target dimensions
        double scaleX = (double) targetWidth / originalImage.getWidth();
        double scaleY = (double) targetHeight / originalImage.getHeight();
        double scale = Math.max(scaleX, scaleY); // Use the smaller scale to fit within both dimensions

        // Calculate the new dimensions based on the scaling factor
        int scaledWidth = (int) (originalImage.getWidth() * scale);
        int scaledHeight = (int) (originalImage.getHeight() * scale);

        // Create a scaled version of the image
        BufferedImage scaledImage = new BufferedImage(scaledWidth, scaledHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = scaledImage.createGraphics();
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null);
        graphics2D.dispose();

        return scaledImage;
    }

    public static void getImageAndCrop(int windowSize, int panelSize) {
        // Create a file chooser
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose an image file");

        // Show the file chooser dialog
        int userSelection = fileChooser.showOpenDialog(null);

        // Check if the user selected a file
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            // Get the selected file
            File selectedFile = fileChooser.getSelectedFile();

            try {
                // Load the image
                BufferedImage originalImage = ImageIO.read(selectedFile);

                originalImage = scaleImageToFit(originalImage, windowSize, windowSize);

                // Calculate the size of each piece
                int pieceSize = windowSize / panelSize;

                int heightOffset = (originalImage.getHeight()-windowSize)/2;
                int widthOffset = (originalImage.getWidth()-windowSize)/2;

                // Create an array to hold the 9 pieces
                BufferedImage[] pieces = new BufferedImage[panelSize*panelSize];

                // Divide the image into 9 pieces
                for (int row = 0; row < panelSize; row++) {
                    for (int col = 0; col < panelSize; col++) {
                        // Create a new BufferedImage for each piece
                        pieces[row * panelSize + col] = originalImage.getSubimage(widthOffset + col * pieceSize,  heightOffset + row * pieceSize, pieceSize, pieceSize);
                    }
                }

                // Save or display the pieces as needed
                for (int i = 0; i < panelSize*panelSize; i++) {
                    ImageIO.write(pieces[i], "jpg", new File("images/img" + i + ".jpg"));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

