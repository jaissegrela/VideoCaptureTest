package video;

import java.util.concurrent.ExecutionException;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FrameGrabber.Exception;
import org.opencv.core.Core;

public class VideoInfo {

public static void main(String[] args) throws Exception, org.bytedeco.javacv.FrameRecorder.Exception, ClassNotFoundException, InterruptedException, ExecutionException {
		
	    // Load the native library.
		System.loadLibrary("opencv_java249");
	    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	    Class.forName("org.bytedeco.javacpp.avutil");
	    Class.forName("org.bytedeco.javacpp.swresample");


        System.out.println(String.format("OpenCV %s", Core.VERSION));
        
	    String[] videos = {"output\\cartoon.avi", "output\\real_scene.avi", "output\\video_test1.avi"};
	    for (String filename : videos) {
	    	System.out.println(filename);
	    	FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(filename);
			grabber.start();
	        System.out.println(String.format("AudioChannels: %s", grabber.getAudioChannels()));
	        System.out.println(String.format("BitsPerPixel: %s", grabber.getBitsPerPixel()));
	        System.out.println(String.format("DelayedImage: %s", grabber.getDelayedImage()));
	        System.out.println(String.format("Format: %s", grabber.getFormat()));
	        System.out.println(String.format("DelayedTime: %s", grabber.getDelayedTime()));
	        System.out.println(String.format("FrameNumber: %s", grabber.getFrameNumber()));
	        System.out.println(String.format("FrameRate: %s", grabber.getFrameRate()));
	        System.out.println(String.format("Gamma: %s", grabber.getGamma()));
	        System.out.println(String.format("ImageHeight: %s", grabber.getImageHeight()));
	        System.out.println(String.format("LengthInFrames: %s", grabber.getLengthInFrames()));
	        System.out.println(String.format("LengthInTime: %s", grabber.getLengthInTime()));
	        System.out.println(String.format("NumBuffers: %s", grabber.getNumBuffers()));
	        System.out.println(String.format("PixelFormat: %s", grabber.getPixelFormat()));
	        System.out.println(String.format("SampleFormat: %s", grabber.getSampleFormat()));
	        System.out.println(String.format("SampleRate: %s", grabber.getSampleRate()));
	        System.out.println(String.format("SensorPattern: %s", grabber.getSensorPattern()));
	        System.out.println(String.format("Timeout: %s", grabber.getTimeout()));
	        System.out.println(String.format("Timestamp: %s\n", grabber.getTimestamp()));
	        grabber.stop();	
		}
	    
		System.out.println("Done!");
	}

}
