package irmaANALYSIS;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import math.geom2d.Point2D;
import irmaANALYSIS.GUI;

public class triangleWidget {
	Sample s;
	Canvas background, layer1;
	static GraphicsContext gB, gL;
	Image triangleImage;
	Pane canvasContainer; 
	
	triangleWidget(Sample _s){
		s = _s;
		canvasContainer = new Pane();
		background = new Canvas(300, 300);
		layer1 = new Canvas(300, 300);
        gB = background.getGraphicsContext2D();
        gL = layer1.getGraphicsContext2D();
        canvasContainer.getChildren().addAll(background, layer1);
        GUI.rootLayout.setRight(canvasContainer);
        
        triangleImage = new Image("file:data/colorTriangle.png");
        
        gB.clearRect(0, 0, background.getWidth(), background.getHeight());
        gB.drawImage(triangleImage, 0, 0, 200, 174);
        gB.setStroke(Color.BLACK);
        gB.setLineWidth(1);
	}
	
	public void clearCanvas() {
		gL.clearRect(0, 0, layer1.getWidth(), layer1.getHeight());
	}
	
	public static void drawSample(int _t) {
		gL.setFill(Color.BLACK);
		for (Subject sub: Sample.SubjectsList) {
			Point2D p = sub.getPointByIndex(_t);
			double px = (p.x()*100)+100;
			double py = (p.y()*100)+87;
			gL.fillOval(px-2, py-2, 4, 4);
		}
		
	}
	
	public static void drawAFA(int _t) {
		gL.setFill(Color.WHITE);
		Point2D pointAFA = Sample.getAFA(_t);
		double px = (pointAFA.x()*100)+100;
		double py = (pointAFA.y()*100)+87;
		gL.fillOval(px-5, py-5, 10, 10);
	}
	
	public static void drawConnections(int _t) {
		Point2D pointAFA = Sample.getAFA(_t);
		double afaX = (pointAFA.x()*100)+100;;
		double afaY = (pointAFA.y()*100)+87;
		for (Subject sub: Sample.SubjectsList) {
			Point2D p = sub.getPointByIndex(_t);
			double px = (p.x()*100)+100;
			double py = (p.y()*100)+87;
			gL.strokeLine(afaX, afaY, px, py);
		}
	}
	
	
}
