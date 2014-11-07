package video;

import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_highgui;

public class ImageOperation_Test {

public static void main(String[] args) throws Exception, org.bytedeco.javacv.FrameRecorder.Exception, ClassNotFoundException {
		
	    // Load the native library.
		System.loadLibrary("opencv_java249");
	    System.loadLibrary(org.opencv.core.Core.NATIVE_LIBRARY_NAME);
        
	    String[] inputfile = {"output\\export_04214_1.tif", "..\\steganography\\input\\export_04214.tif"};
	    String outputfile = "output\\export_04214.tif";
	    Mat image = null;
	    for (int i = 0; i < inputfile.length; i++) {
	    	image = opencv_highgui.imread(inputfile[i], opencv_highgui.IMREAD_UNCHANGED);
	    	System.out.println(String.format("file: %s channels: %s depth: %s size: %s, %s", inputfile[i], image.channels(), image.depth(), image.size().width(), image.size().height()));
		}
        opencv_highgui.cvSaveImage(outputfile, image.asIplImage());
            	

		System.out.println("ImageOperation_Test Done!");
	}

}
