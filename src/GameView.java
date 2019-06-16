import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GameView extends Application
{
    public static final int FIELD_SIZE = 100;
    public  static  final  int WIDHT = 10;
    public  static  final  int HEIGHT = 10;

    private Field [][] board = new Field[WIDHT][HEIGHT];

    private Group fieldGroup = new Group();
    private Group pieceGroup = new Group();

    private Pane createContent ()
    {
        Pane root = new Pane();
        root.setPrefSize(WIDHT *FIELD_SIZE,HEIGHT * FIELD_SIZE);
        root.getChildren().addAll(fieldGroup,pieceGroup);
        for(int row = 0; row < HEIGHT;row ++)
        {
            for(int col = 0; col < WIDHT;col ++)
            {
                Field field = new Field ((row+col)%2==0,col,row);
                board[col][row]= field;

                fieldGroup.getChildren().add(field);
                Piece piece = null;
                if(row < 4 && (row+col)%2!=0)
                {
                    piece = makePiece(PieceCol.BLACK,col,row);
                }
                if(row > 5 && (row+col)%2!=0)
                {
                    piece = makePiece(PieceCol.WHITE,col,row);
                }
                if(piece != null)
                {
                    field.setPiece(piece);
                    pieceGroup.getChildren().add(piece);
                }
            }
        }

        return root;
    }

    private MoveResult tryMove(Piece piece, int newX, int newY) {
        if (board[newX][newY].hasPiece() || (newX + newY) % 2 == 0) {
            return new MoveResult(MoveType.STAY);
        }

        int x0 = toBoard(piece.getOldX());
        int y0 = toBoard(piece.getOldY());

        if (Math.abs(newX - x0) == 1 && newY - y0 == piece.getPieceColor().moveDirection) {
            return new MoveResult(MoveType.MOVE);
        } else if (Math.abs(newX - x0) == 2 && newY - y0 == piece.getPieceColor().moveDirection * 2) {

            int x1 = x0 + (newX - x0) / 2;
            int y1 = y0 + (newY - y0) / 2;

            if (board[x1][y1].hasPiece() && board[x1][y1].getPiece().getPieceColor() != piece.getPieceColor()) {
                return new MoveResult(MoveType.BEAT, board[x1][y1].getPiece());
            }
        }

        return new MoveResult(MoveType.STAY);
    }

    private  int toBoard(double pixels)
    {
        return (int)(pixels + GameView.FIELD_SIZE /2 ) / GameView.FIELD_SIZE;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(createContent());
        scene.getStylesheets().add("CheckersStyle.css");
        primaryStage.setTitle("Checkers");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Piece makePiece ( PieceCol color, int x, int y )
    {
        Piece piece = new Piece(color, x, y);

        piece.setOnMouseReleased(e -> {
            int newX = toBoard(piece.getLayoutX());
            int newY = toBoard(piece.getLayoutY());

            MoveResult result = tryMove(piece, newX,newY);

            int x0 = toBoard(piece.getOldX());
            int y0 = toBoard(piece.getOldY());
            switch (result.getType())
            {
                case MOVE:
                    piece.abortMove();
                    break;
                case STAY:
                    piece.move(newX,newY);
                    board[x0][y0].setPiece(null);
                    board[newX][newY].setPiece(piece);
                    break;
                case BEAT:
                    piece.move(newX,newY);
                    board[x0][y0].setPiece(null);
                    board[newX][newY].setPiece(piece);

                    Piece otherPiece = result.getPiece();
                    board[toBoard(otherPiece.getOldX())][toBoard(otherPiece.getOldY())].setPiece(null);
                    pieceGroup.getChildren().remove(otherPiece);
                    break;
            }
        });
        return piece;
    }

    public static void main (String [] args)
    {
        launch(args);
    }
}
