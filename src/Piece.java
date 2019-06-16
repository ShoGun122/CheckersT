import javafx.scene.chart.PieChart;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import java.awt.*;


public class Piece extends StackPane
{
    private PieceCol pieceColor;
    private  double mouseX, mouseY;
    private double oldX, oldY;

    public PieceCol getPieceColor()
    {
        return pieceColor;
    }

    public double getOldX()
    {
        return oldX;
    }

    public double getOldY()
    {
        return oldY;
    }

    public Piece (PieceCol pieceC, int x, int y)
    {
        move(x , y);

        this.pieceColor = pieceC;
        Circle circle = new Circle(GameView.FIELD_SIZE * 0.3215);
        circle.setFill((pieceColor == PieceCol.BLACK ? Color.valueOf("#000") : Color.valueOf("#fff")));
        circle.setStroke(Color.BLACK);
        circle.setStrokeWidth(GameView.FIELD_SIZE*0.03);
        circle.setTranslateX((GameView.FIELD_SIZE - GameView.FIELD_SIZE * 0.3215 *2)/2);
        circle.setTranslateY((GameView.FIELD_SIZE - GameView.FIELD_SIZE * 0.3215 *2)/2);
        getChildren().add(circle);

        setOnMousePressed(e -> {
            mouseX = e.getSceneX();
            mouseY = e.getSceneY();
        });

        setOnMouseDragged(e -> {
            relocate(e.getSceneX() - mouseX + oldX, e.getSceneY() - mouseY + oldY);
        });

    }

    public void move( int x, int y)
    {
        oldX =  x * GameView.FIELD_SIZE;
        oldY = y * GameView.FIELD_SIZE;
        relocate(oldX,oldY);
    }

    public void abortMove ()
    {
        relocate(oldX,oldY);
    }
}
