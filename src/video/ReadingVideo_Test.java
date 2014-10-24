package video;

import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.opencv_core.CvMat;
import org.bytedeco.javacpp.opencv_core.IplImage;
import org.bytedeco.javacpp.opencv_features2d.FeatureDetector;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber.Exception;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

public class ReadingVideo_Test {

public static void main(String[] args) throws Exception, org.bytedeco.javacv.FrameRecorder.Exception, ClassNotFoundException {
		
	    // Load the native library.
		System.loadLibrary("opencv_java249");
	    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	    Class.forName("org.bytedeco.javacpp.avutil");
	    Class.forName("org.bytedeco.javacpp.swresample");
	    
	    
	    Mat m  = Mat.eye(3, 3, CvType.CV_8UC1);
        System.out.println("m = " + m.dump());

        
	    String filename = "..\\steganography\\input\\test.avi";
	    String video_output = "video_test.avi";
	    KeyPointImagesAlgorithm kpa = new KeyPointImagesAlgorithm();
	    
	    FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(filename);
	    Frame grabbedImage = null;

		grabber.start();
        System.out.println(String.format("OpenCV %s", Core.VERSION));
        
        FFmpegFrameRecorder recorder = new FFmpegFrameRecorder(video_output, grabber.getImageWidth(),
        		grabber.getImageHeight(), grabber.getAudioChannels());
        
        
        recorder.setVideoCodec(13);
        recorder.setFormat(grabber.getFormat());
        System.out.println("Pixel Format:" + grabber.getPixelFormat());
        //recorder.setPixelFormat(grabber.getPixelFormat());

        recorder.setFrameRate(grabber.getFrameRate());
        
        recorder.start();
        while ((grabbedImage = grabber.grabFrame()) != null) {
            recorder.record(grabbedImage);
            IplImage image = grabbedImage.image;
            CvMat asCvMat = image.asCvMat();
            Mat s = new Mat(asCvMat.address());
            s = kpa.drawAllKeypoints(s, 80);
            
        	System.out.println(grabber.getFrameNumber()); 
        }
        recorder.stop();
        grabber.stop();
		System.out.println("Done!");
	}

}
