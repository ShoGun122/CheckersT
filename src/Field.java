import java.awt.*;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
public class Field extends Rectangle
{

    private Piece piece;

    public boolean hasPiece ()
    {
        return piece !=null;
    }

    public Piece getPiece()
    {
        return this.piece;
    }

    public void setPiece (Piece piece)
    {
        this.piece = piece;
    }

    public Field (boolean light, int x, int y )
    {
        setWidth(GameView.FIELD_SIZE);
        setHeight(GameView.FIELD_SIZE);

        relocate(x * GameView.FIELD_SIZE, y * GameView.FIELD_SIZE);

        setFill(light ? Color.valueOf("#E0E0E0") : Color.valueOf("#6D4C41"));
    }
}
