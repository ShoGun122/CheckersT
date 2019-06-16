public enum PieceCol
{
    BLACK(1), WHITE(-1);

    final int moveDirection;

    PieceCol(int mDirection)
    {
        this.moveDirection = mDirection;
    }
}
