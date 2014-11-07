package video;

import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_features2d;
import org.bytedeco.javacpp.opencv_features2d.KeyPoint;
import org.bytedeco.javacpp.opencv_nonfree.SURF;


public class KeyPointImagesAlgorithm {

	public Mat drawAllKeypoints(Mat source){
		KeyPoint keyPoint = new KeyPoint();
		SURF surf = new SURF(2500d, 4, 2, true, true);
		Mat result = new Mat();
		surf.detect(source, keyPoint);
		//opencv_features2d.drawKeypoints(source, keyPoint, result);
		opencv_features2d.drawKeypoints(source, keyPoint, result);
	    return result;	
	}
	
	public void drawAllKeypoints1(Mat source){
		KeyPoint keyPoint = new KeyPoint();
		SURF surf = new SURF(2500d, 4, 2, true, true);
		surf.detect(source, keyPoint);
		opencv_features2d.drawKeypoints(source, keyPoint, source);	
	}
}
