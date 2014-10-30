package video;

import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacpp.opencv_core.IplImage;
import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_highgui;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber.Exception;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.highgui.Highgui;

public class ReadingVideo_Test {

public static void main(String[] args) throws Exception, org.bytedeco.javacv.FrameRecorder.Exception, ClassNotFoundException {
		
	    // Load the native library.
		System.loadLibrary("opencv_java249");
	    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	    Class.forName("org.bytedeco.javacpp.avutil");
	    Class.forName("org.bytedeco.javacpp.swresample");

        
	    String filename = "..\\steganography\\input\\test.avi";
	    String video_output = "output\\video_test1.avi";
	    KeyPointImagesAlgorithm kpa = new KeyPointImagesAlgorithm();
	    
	    FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(filename);
	    Frame frame = null;

		grabber.start();
        System.out.println(String.format("OpenCV %s", Core.VERSION));
        
        FFmpegFrameRecorder recorder = new FFmpegFrameRecorder(video_output, grabber.getImageWidth(),
        		grabber.getImageHeight(), grabber.getAudioChannels());
        
        
        recorder.setVideoCodec(13);
        recorder.setFormat(grabber.getFormat());
        System.out.println("Pixel Format:" + grabber.getPixelFormat());
        //recorder.setPixelFormat(grabber.getPixelFormat());
        
        recorder.setFrameRate(grabber.getFrameRate());
        int i = 0;
        recorder.start();
        while ((frame = grabber.grabFrame()) != null) {
        	
            IplImage image = frame.image;
			if (image != null) {
				//Mat mat = new Mat(image);
				//kpa.drawAllKeypoints(mat);
              i++;
//              if(i == 2){
//            	  Mat mat = new Mat(image);
//            	  opencv_highgui.cvSaveImage("output\\test.jpg", mat.asCvMat());
//            	  mat = kpa.drawAllKeypoints(mat);
//            	  opencv_highgui.cvSaveImage("output\\test1.jpg", mat.asCvMat());
//              }
//              opencv_core.cvSave(String.format("output\\test%s.jpg", i++), frame.image);
            	
            }else{
            	System.out.println("        " + i);
            }
//        	System.out.println(grabber.getFrameNumber());
			recorder.record(frame);

        }
        recorder.stop();
        grabber.stop();
		System.out.println("Done!");
	}

}
