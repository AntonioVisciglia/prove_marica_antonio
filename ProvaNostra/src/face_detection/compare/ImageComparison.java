package face_detection.compare;

import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import com.sun.mail.handlers.image_gif;

import java.io.File;
import java.io.IOException;

/**
 * Inferiore al 10% va bene... fin'ora. Stesso posto. stessa luce, stessa telecamera. Stesso tutto
 * 
 * 
 * 
 * @author anton
 *
 */

class ImageComparision {

	public void compare(Image imgA, Image imgB) {
//		BufferedImage imgA = null;
//		BufferedImage imgB = null;


	//		imgA = ImageIO.read(fileA);
	//		imgB = ImageIO.read(fileB);


			/**
			 * TODO SCALE of image
			 */
			int width1 = imgA.getWidth(null);
			int width2 = imgB.getWidth(null);
			int height1 = imgA.getHeight(null);
			int height2 = imgB.getHeight(null);

			if ((width1 != width2) || (height1 != height2))
				System.out.println("Error: Images dimensions" + " mismatch");
			else {
				long difference = 0;
				for (int y = 0; y < height1; y++) {
					for (int x = 0; x < width1; x++) {
						int rgbA = ((BufferedImage) imgA).getRGB(x, y);
						int rgbB = ((BufferedImage) imgB).getRGB(x, y);
						int redA = (rgbA >> 16) & 0xff;
						int greenA = (rgbA >> 8) & 0xff;
						int blueA = (rgbA) & 0xff;
						int redB = (rgbB >> 16) & 0xff;
						int greenB = (rgbB >> 8) & 0xff;
						int blueB = (rgbB) & 0xff;
						difference += Math.abs(redA - redB);
						difference += Math.abs(greenA - greenB);
						difference += Math.abs(blueA - blueB);
					}
				}

				// Total number of red pixels = width * height
				// Total number of blue pixels = width * height
				// Total number of green pixels = width * height
				// So total number of pixels = width * height * 3
				double total_pixels = width1 * height1 * 3;

				// Normalizing the value of different pixels
				// for accuracy(average pixels per color
				// component)
				double avg_different_pixels = difference / total_pixels;

				// There are 255 values of pixels in total
				double percentage = (avg_different_pixels / 255) * 100;

				if( percentage < 6.1 )
					System.out.println("I due volti SONO gli stessi.");
				else 
					System.err.println("I due volti NON SONO gli stessi.");
				System.out.println("Difference Percentage-->" + percentage);
			}
	}
	
	public static void main(String[] args) {
		try {
			
			ImageComparision imageComparision = new ImageComparision();
			
			String base = "C:\\Users\\anton\\Desktop\\";
			File fileA = new File("C:\\Users\\anton\\Desktop\\crop10.jpg");
			File fileB = new File("C:\\Users\\anton\\Desktop\\crop7.jpg");
			
			Image im = ImageIO.read(fileA);
			Image im1 = ImageIO.read(fileB);
			
			imageComparision.compare(im, im1);

		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}

}
