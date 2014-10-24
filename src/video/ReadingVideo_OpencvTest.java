package video;

import java.io.File;

import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_highgui.VideoCapture;
import org.bytedeco.javacpp.opencv_highgui.VideoWriter;
import org.opencv.core.Core;

public class ReadingVideo_OpencvTest {

public static void main(String[] args) {
		
	    // Load the native library.
		System.loadLibrary("opencv_java249");
	    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	    
	    
//	    MatExpr m  = Mat.eye(3, 3, CvType.CV_8UC1);
//        System.out.println("m = " + m.asBuffer());

        
	    String filename = "..\\steganography\\input\\test1.avi";
	    String output = "test.jpg";
	    
		VideoCapture videoCapture = new VideoCapture();
		videoCapture.open(filename);
		
		
        System.out.println(String.format("OpenCV %s", Core.VERSION));
        //System.out.println(String.format("framesize %s", frameSize));
		
        if(!videoCapture.isOpened())
		{
			System.out.println(String.format("Cannot load the video: %s - %s", filename, new File(filename).exists()));
			return;
		}
	    
	    Mat image = new Mat();
		videoCapture.read(image);
		try{
			org.bytedeco.javacpp.opencv_highgui.imwrite(output, image);
		}catch(Exception e){
			System.out.println("Could not print the image");	
		}
		System.out.println("Done!");
	}

}
