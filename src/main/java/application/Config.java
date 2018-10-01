package application;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Config {
	public boolean stabilizedMode = false;
	public boolean isOn = true;

	public DoubleProperty minRatioTextSpan = new SimpleDoubleProperty(0.35);
	public DoubleProperty maxRatioTextSpan = new SimpleDoubleProperty(2);
	public DoubleProperty minRatioSpanHeightWidth = new SimpleDoubleProperty(1);

	public IntegerProperty mserMinArea = new SimpleIntegerProperty(12);
	public IntegerProperty mserMaxArea = new SimpleIntegerProperty(250);
	public IntegerProperty minSpanWidth = new SimpleIntegerProperty(10);
	public IntegerProperty minSpanHeight = new SimpleIntegerProperty(10);

	public DoubleProperty spanMinConsistency = new SimpleDoubleProperty(0.14);
	public DoubleProperty dxEnlargement = new SimpleDoubleProperty(4);
	public DoubleProperty dyEnlargement = new SimpleDoubleProperty(4);
	public IntegerProperty minOcrConfidence = new SimpleIntegerProperty(5);

	public IntegerProperty hMaxDist = new SimpleIntegerProperty(300);
	public DoubleProperty flowTh = new SimpleDoubleProperty(2.5);
	public DoubleProperty flowTv = new SimpleDoubleProperty(0.5);
	public DoubleProperty flowTs = new SimpleDoubleProperty(0.85);
	public DoubleProperty distanceSizeBalance = new SimpleDoubleProperty(0.5);
	public DoubleProperty letterGain = new SimpleDoubleProperty(3);
	public DoubleProperty entryCost = new SimpleDoubleProperty(5);
	public DoubleProperty xyDistanceCoeff = new SimpleDoubleProperty(9);

}
