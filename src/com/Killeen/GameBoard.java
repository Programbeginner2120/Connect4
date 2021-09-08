package com.Killeen;

public class GameBoard {

    private int rows;
    private int columns;
    private char [][] gameBoard;


    /*
    *
     */
    public GameBoard(int rows, int columns){
        this.rows = rows;
        this.columns = columns;
        gameBoard = new char[this.rows][this.columns];
        this.populateBoard();
    }

    public int getRows(){
        return this.rows;
    }

    public int getColumns(){
        return this.columns;
    }


    /*

    */
    private void populateBoard(){
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++)
                gameBoard[i][j] = ' ';
        }
    }


    /*

    */
    protected void placeSymbol(int col, char userSymbol) throws ImproperPlacementException{

        if (col < 1 || col > this.columns)
            throw new ImproperPlacementException("Invalid symbol placement");

        int formattedColumn = col - 1;
        boolean symbolPlaced = false;
        int i = 0;
        while (i < rows && !symbolPlaced){
            if (gameBoard[i][formattedColumn] == ' '){
                if ((i < rows - 1 && gameBoard[i+1][formattedColumn] != ' ') || i == rows - 1){
                    gameBoard[i][formattedColumn] = userSymbol;
                    symbolPlaced = true;
                }
            }
            i++;
        }

        if (!symbolPlaced)
            throw new ImproperPlacementException("Invalid symbol placement");
    }


    /*

    */
    protected boolean checkVerticalWin(char userSymbol) {
        for (int i = rows - 1; i >= 3; i--) {
            for (int j = 0; j < columns; j++) {
                if (gameBoard[i][j] == userSymbol) {
                    if (gameBoard[i - 1][j] == userSymbol && gameBoard[i - 2][j] == userSymbol &&
                            gameBoard[i - 3][j] == userSymbol)
                        return true;
                }
            }
        }
        return false;
    }


    /*

    */
    protected boolean checkHorizontalWin(char userSymbol) { //TODO: FIX RUNTIME BUG FOR HORIZONTAL WIN
        for (int i = rows - 1; i >= 3; i--) {
            for (int j = columns - 1; j >= 3; j--) {
                if (gameBoard[i][j] == userSymbol) {
                    if (gameBoard[i][j - 1] == userSymbol && gameBoard[i][j - 2] == userSymbol &&
                            gameBoard[i][j - 3] == userSymbol)
                        return true;
                }
            }
            for (int j = 0; j < columns - 4; j++) {
                if (gameBoard[i][j] == userSymbol) {
                    if (gameBoard[i][j + 1] == userSymbol && gameBoard[i][j + 2] == userSymbol &&
                            gameBoard[i][j + 3] == userSymbol)
                        return true;
                }
            }
        }
        return false;
    }


    /*

    */
    protected boolean checkDiagonalWin(char userSymbol){
        boolean rightDiagonal;
        boolean leftDiagonal;
        for (int i = rows - 1; i >= 3; i--) {
            for (int j = 0; j < columns; j++) {
                if (gameBoard[i][j] == userSymbol) {
                    if (j <= columns - 4){
                        rightDiagonal = gameBoard[i - 1][j + 1] == userSymbol && gameBoard[i - 2][j + 2] == userSymbol
                                && gameBoard[i - 3][j + 3] == userSymbol;
                        if (rightDiagonal)
                            return true;
                    }

                    if (j >= 3){
                        leftDiagonal = gameBoard[i - 1][j - 1] == userSymbol && gameBoard[i - 2][j - 2] == userSymbol &&
                                gameBoard[i - 3][j - 3] == userSymbol;
                        if (leftDiagonal)
                            return true;
                    }
                }
            }
        }
        return false;
    }


    /*

    */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rows; i++){
            sb.append(gameBoard[i][0] + "|");
            for (int j = 1; j < columns - 1; j++){
                sb.append(gameBoard[i][j] + "|");
            }
            sb.append(gameBoard[i][columns - 1] + "\n");
            if (i >= 1 || i <= rows - 1){
                for (int k = 0; k < (columns * 2) - 1; k++)
                    sb.append("_");
                sb.append("\n");
            }
        }
        return sb.toString();
    }

}

// TODO: WORK ON THIS APPLICATION FURTHER, START DOCUMENTING