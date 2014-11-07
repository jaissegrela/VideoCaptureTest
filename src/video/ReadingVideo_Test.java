package video;

import org.bytedeco.javacpp.avcodec;
import org.bytedeco.javacpp.opencv_core.IplImage;
import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber.Exception;
import org.opencv.core.Core;

public class ReadingVideo_Test {

public static void main(String[] args) throws Exception, org.bytedeco.javacv.FrameRecorder.Exception, ClassNotFoundException {
		
	    // Load the native library.
		System.loadLibrary("opencv_java249");
	    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	    Class.forName("org.bytedeco.javacpp.avutil");
	    Class.forName("org.bytedeco.javacpp.swresample");

        
	    String filename = "output\\cartoon.mp4";
	    String video_output = "output\\cartoon_kp.mp4";
	    KeyPointImagesAlgorithm kpa = new KeyPointImagesAlgorithm();
	    
	    FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(filename);
	    Frame frame = null;

		grabber.start();
        System.out.println(String.format("OpenCV %s", Core.VERSION));
        
        FFmpegFrameRecorder recorder = new FFmpegFrameRecorder(video_output, grabber.getImageWidth(),
        		grabber.getImageHeight(), grabber.getAudioChannels());
        
        
        recorder.setFormat(grabber.getFormat());
        recorder.setSampleRate(grabber.getSampleRate());
        recorder.setFrameRate(grabber.getFrameRate());
        //recorder.setVideoBitrate(grabber.getV);
        recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264);
        recorder.setAudioCodec(avcodec.AV_CODEC_ID_AAC);
        System.out.println("Pixel Format:" + grabber.getPixelFormat());
        //recorder.setPixelFormat(grabber.getPixelFormat());
        
        recorder.setFrameRate(grabber.getFrameRate());
        recorder.start();
        while ((frame = grabber.grabFrame()) != null) {
        	
            IplImage image = frame.image;
			if (image != null) {
				Mat mat = new Mat(image);
				kpa.drawAllKeypoints1(mat);          	
            }
			recorder.record(frame);
			System.out.println("Frame: " + grabber.getFrameNumber());
        }
        recorder.stop();
        grabber.stop();
		System.out.println("Done!");
	}

}
