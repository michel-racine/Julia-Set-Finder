import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import java.lang.Math.*;
import java.io.File;
import javax.imageio.ImageIO;

public class Mandelbrot extends Frame {
	Image img;

	Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
	int w = 400; // (int)size.getWidth();
	int h = 400; // (int)size.getHeight();

	public Mandelbrot() {
		int pixels[] =  new int[w * h];
		int i = 0;
		double xn1 = 0.0;
		double yn1 = 0.0;
		double xn = 0.0;
		double yn = 0.0;
		double xc = 0.0;
		double yc = 0.0;
		double radius = 0.0;
		int iti = 0;
		int max_iter = 255;

		for(double y = 0; y < 400; y++) {
			for(double x = 0; x < 400; x++) {
				xc = (x / 100) - 2.0 - 0.01;
				yc = (y / 100) - 2.0 - 0.01;
				xn = xc; xn1 = xc;
				yn = yc; yn1 = yc;
				
//				// Julia set coordinates
//				xc = -.785; yc = .148;
				
				iti = 0;
				radius = Math.sqrt(Math.pow(xn1, 2) + Math.pow(yn1, 2));

				while((radius < 2.0) & iti < max_iter){
					xn1 = Math.pow(xn, 2) - Math.pow(yn, 2) + xc;
					yn1 = 2 * xn * yn + yc;
					radius = Math.sqrt(Math.pow(xn1, 2) + Math.pow(yn1, 2));
					xn = xn1;
					yn = yn1;
					iti += 1;
				}
				pixels[i++] = (255 << 24) | (iti << 16) | (iti << 8) | iti;
			}
		}
		img = createImage(new MemoryImageSource(w, h, pixels, 0, w));

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});
	}

	public void paint(Graphics g) {
		g.drawImage(img, getInsets().left, getInsets().top, null);
	}
}

// File outputfile = new File("mandelbrot.png");
// ImageIO.write(img, "png", outputfile);
