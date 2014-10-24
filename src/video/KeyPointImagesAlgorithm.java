package video;

import java.util.List;

import org.opencv.core.Mat;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.core.Scalar;
import org.opencv.features2d.FeatureDetector;
import org.opencv.features2d.Features2d;
import org.opencv.features2d.KeyPoint;

public class KeyPointImagesAlgorithm {

	public Mat drawAllKeypoints(Mat source, int quantity){
		MatOfKeyPoint matOfKeyPoints = new MatOfKeyPoint();
	    FeatureDetector blobDetector = FeatureDetector.create(FeatureDetector.SURF);
	    blobDetector.detect(source, matOfKeyPoints);
	    List<KeyPoint> list = matOfKeyPoints.toList();
		Scalar[] colors = new Scalar[]{
			new Scalar(256, 0, 0), new Scalar(128, 0, 0), new Scalar(0, 256, 0), new Scalar(0, 128, 0),
			new Scalar(0, 0, 256), new Scalar(0, 0, 128), new Scalar(256, 256, 0), new Scalar(0, 256, 256),
			new Scalar(256, 0, 256), new Scalar(256, 256, 256)
		};
		int loops = colors.length;
		Mat result = new Mat();
		source.copyTo(result);
		System.out.println(list.size());
		for (int i = 0; i < loops && i  * quantity <= list.size() ; i++) {
			List<KeyPoint> subList = list.subList(i * quantity, (i + 1) * quantity);
			MatOfKeyPoint mat = new MatOfKeyPoint();
			mat.fromList(subList);
			Features2d.drawKeypoints(result, mat, result, colors[i % colors.length], Features2d.DRAW_RICH_KEYPOINTS);
		}
	    return result;	
	}
}
