import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class GameState {
    public int[][] board = new int[5][4];
    public GameState parent = null;
    public int cost = 0;
    public int steps = 0;

    public GameState(int [][] inputBoard, int steps) {
        for(int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                this.board[i][j] = inputBoard[i][j];
            }
        }
        this.steps = steps;
    }

    public GameState(int [][] inputBoard) {
        for(int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                this.board[i][j] = inputBoard[i][j];
            }
        }
    }

    public boolean canMoveUp(Block block, int[][] board){
        int x = block.x;
        int y = block.y;
        String type = block.type;

        if(block.x == 0){
            return false;
        }
        else if(type.equals("2x1")){
            if(board[x-1][y] == 0){
                return true;
            }
            else{
                return false;
            }
        }
        else if(type.equals("1x2") || type.equals("2x2")){
            if(board[x-1][y] == 0 && board[x-1][y+1] == 0){
                return true;
            }
            else{
                return false;
            }
        }
        // shouldn't be hit
        return false;
    }

    public boolean canMoveDown(Block block, int[][] board){
        int x = block.x;
        int y = block.y;
        String type = block.type;

        if(block.x == 4){
            return false;
        }
        else if(type.equals("2x1")){
            if(block.x == 3){
                return false;
            }
            else if(board[x+2][y] == 0){
                return true;
            }
            else{
                return false;
            }
        }
        else if(type.equals("1x2")){
            if(board[x+1][y] == 0 && board[x+1][y+1] ==0){
                return true;
            }
            else{
                return false;
            }
        }
        else if(type.equals("2x2")){
            if(block.x == 3){
                return false;
            }
            else if(board[x+2][y] == 0 && board[x+2][y+1] ==0){
                return true;
            }
            else{
                return false;
            }
        }
        // shouldn't be hit
        return false;
    }

    public boolean canMoveRight(Block block, int[][] board){
        int x = block.x;
        int y = block.y;
        String type = block.type;

        if(block.y == 3){
            return false;
        }
        else if(type.equals("2x1")){
            if(board[x][y+1] == 0 && board[x+1][y+1] == 0){
                return true;
            }
            else{
                return false;
            }
        }
        else if(type.equals("1x2")){
            if(block.y == 2){
                return false;
            }
            else if(board[x][y+2] == 0){
                return true;
            }
            else{
                return false;
            }
        }
        else if(type.equals("2x2")){
            if(block.y == 2){
                return false;
            }
            else if(board[x][y+2] == 0 && board[x+1][y+2] == 0){
                return true;
            }
            else{
                return false;
            }
        }
        // shouldn't be hit
        return false;
    }

    public boolean canMoveLeft(Block block, int[][] board){
        int x = block.x;
        int y = block.y;
        String type = block.type;

        if(block.y == 0){
            return false;
        }
        else if(type.equals("2x1") || type.equals("2x2")){
            if(board[x][y-1] == 0 && board[x+1][y-1] == 0){
                return true;
            }
            else{
                return false;
            }
        }
        else if(type.equals("1x2")){
            if(board[x][y-1] == 0){
                return true;
            }
            else{
                return false;
            }
        }
        // shouldn't be hit
        return false;
    }


    // get all successors and return them in sorted order
    public List<GameState> getNextStates() {
        List<GameState> successors = new ArrayList<>();

        List<Block> blocks = new ArrayList<>();

        int[][] board = this.board;


        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 4; j++) {
                if (i == 0 && j == 0) {
                    if (board[i][j] == 1) {
                        // make 2x2 block
                        Block block = new Block(i, j, "2x2");
                        blocks.add(block);
                    } else if (board[i][j] == 2) {
                        // make 2x1 block
                        Block block = new Block(i, j, "2x1");
                        blocks.add(block);
                    } else if (board[i][j] == 3) {
                        // make 1x2 block
                        Block block = new Block(i, j, "1x2");
                        blocks.add(block);
                    } else if (board[i][j] == 4) {
                        // make 1x1 block
                        Block block = new Block(i, j, "1x1");
                        blocks.add(block);
                    }
                } else if (i == 0) {
                    if (board[i][j] == 1 && board[i][j - 1] != 1) {
                        // make 2x2 block
                        Block block = new Block(i, j, "2x2");
                        blocks.add(block);
                    } else if (board[i][j] == 2) {
                        // make 2x1 block
                        Block block = new Block(i, j, "2x1");
                        blocks.add(block);
                    } else if (board[i][j] == 3 && board[i][j - 1] != 3) {
                        // make 1x2 block
                        Block block = new Block(i, j, "1x2");
                        blocks.add(block);
                    } else if (board[i][j] == 4) {
                        // make 1x1 block
                        Block block = new Block(i, j, "1x1");
                        blocks.add(block);
                    }

                } else if (j == 0) {
                    if (board[i][j] == 1 && board[i - 1][j] != 1) {
                        // make 2x2 block
                        Block block = new Block(i, j, "2x2");
                        blocks.add(block);
                    } else if (board[i][j] == 2 && i != 4) {
                        // make 2x1 block
                        if(board[i+1][j] == 2) {
                            if (i < 2 && board[i - 1][j] != 2) {
                                Block block = new Block(i, j, "2x1");
                                blocks.add(block);
                            } else if (i == 2 && (board[i - 1][j] != 2 || (board[i - 1][j] == 2 && board[i - 2][j] == 2))) {
                                Block block = new Block(i, j, "2x1");
                                blocks.add(block);
                            } else if (i >= 3 && (board[i - 1][j] != 2 || (board[i - 1][j] == 2 && board[i - 2][j] == 2 && board[i - 3][j] != 0))) {
                                Block block = new Block(i, j, "2x1");
                                blocks.add(block);
                            }
                        }
                    } else if (board[i][j] == 3) {
                        // make 1x2 block
                        Block block = new Block(i, j, "1x2");
                        blocks.add(block);
                    } else if (board[i][j] == 4) {
                        // make 1x1 block
                        Block block = new Block(i, j, "1x1");
                        blocks.add(block);
                    }

                } else {
                    if (board[i][j] == 1 && board[i - 1][j] != 1 && board[i][j - 1] != 1) {
                        // make 2x2 block
                        Block block = new Block(i, j, "2x2");
                        blocks.add(block);
                    } else if (board[i][j] == 2 && i != 4) {
                        // make 2x1 block
                        if(board[i+1][j] == 2) {
                            if (i < 2 && board[i - 1][j] != 2) {
                                Block block = new Block(i, j, "2x1");
                                blocks.add(block);
                            } else if (i == 2 && (board[i - 1][j] != 2 || (board[i - 1][j] == 2 && board[i - 2][j] == 2))) {
                                Block block = new Block(i, j, "2x1");
                                blocks.add(block);
                            } else if (i >= 3 && (board[i - 1][j] != 2 || (board[i - 1][j] == 2 && board[i - 2][j] == 2 && board[i - 3][j] != 0))) {
                                Block block = new Block(i, j, "2x1");
                                blocks.add(block);
                            }
                        }
                    }else if (board[i][j] == 3 && board[i][j - 1] != 3) {
                        // make 1x2 block
                        Block block = new Block(i, j, "1x2");
                        blocks.add(block);
                    } else if (board[i][j] == 4) {
                        // make 1x1 block
                        Block block = new Block(i, j, "1x1");
                        blocks.add(block);
                    }
                }
            }
        }

        for(int k = 0; k <blocks.size(); k++){
            Block block = blocks.get(k);
            // 1x1 blocks can go anywhere there's a space
            if(block.type.equals("1x1")){
                for(int i = 0; i < 5; i++){
                    for(int j = 0; j < 4; j++){
                        if(board[i][j] == 0){
                            int[][] newBoard = new int[5][4];
                            for(int a = 0; a < 5; a++){
                                for(int b = 0; b < 4; b++){
                                    newBoard[a][b] = board[a][b];
                                }
                            }
                            newBoard[block.x][block.y] = 0;
                            newBoard[i][j] = 4;
                            GameState gs = new GameState(newBoard);
                            successors.add(gs);
                        }
                    }
                }

            }
            else{
                // everything else is bound by stricter rules
                if(canMoveDown(block,board)){
                    if(block.type.equals("2x1")){
                        int[][] newBoard = new int[5][4];
                        for(int a = 0; a < 5; a++){
                            for(int b = 0; b < 4; b++){
                                newBoard[a][b] = board[a][b];
                            }
                        }
                        newBoard[block.x][block.y] = 0;
                        newBoard[block.x+2][block.y] = 2;
                        successors.add(new GameState(newBoard));
                    }
                    else if(block.type.equals("1x2")){
                        int[][] newBoard = new int[5][4];
                        for(int a = 0; a < 5; a++){
                            for(int b = 0; b < 4; b++){
                                newBoard[a][b] = board[a][b];
                            }
                        }
                        newBoard[block.x][block.y] = 0;
                        newBoard[block.x][block.y+1] = 0;
                        newBoard[block.x+1][block.y] = 3;
                        newBoard[block.x+1][block.y+1] = 3;
                        successors.add(new GameState(newBoard));
                    }
                    else if(block.type.equals("2x2")){
                        int[][] newBoard = new int[5][4];
                        for(int a = 0; a < 5; a++){
                            for(int b = 0; b < 4; b++){
                                newBoard[a][b] = board[a][b];
                            }
                        }
                        newBoard[block.x][block.y] = 0;
                        newBoard[block.x][block.y+1] = 0;
                        newBoard[block.x+2][block.y] = 1;
                        newBoard[block.x+2][block.y+1] = 1;
                        successors.add(new GameState(newBoard));
                    }
                }
                if(canMoveUp(block,board)){
                    if(block.type.equals("2x1")){
                        int[][] newBoard = new int[5][4];
                        for(int a = 0; a < 5; a++){
                            for(int b = 0; b < 4; b++){
                                newBoard[a][b] = board[a][b];
                            }
                        }
                        newBoard[block.x-1][block.y] = 2;
                        newBoard[block.x+1][block.y] = 0;
                        successors.add(new GameState(newBoard));
                    }
                    else if(block.type.equals("1x2")){
                        int[][] newBoard = new int[5][4];
                        for(int a = 0; a < 5; a++){
                            for(int b = 0; b < 4; b++){
                                newBoard[a][b] = board[a][b];
                            }
                        }
                        newBoard[block.x][block.y] = 0;
                        newBoard[block.x][block.y+1] = 0;
                        newBoard[block.x-1][block.y] = 3;
                        newBoard[block.x-1][block.y+1] = 3;
                        successors.add(new GameState(newBoard));
                    }
                    else if(block.type.equals("2x2")){
                        int[][] newBoard = new int[5][4];
                        for(int a = 0; a < 5; a++){
                            for(int b = 0; b < 4; b++){
                                newBoard[a][b] = board[a][b];
                            }
                        }
                        newBoard[block.x+1][block.y] = 0;
                        newBoard[block.x+1][block.y+1] = 0;
                        newBoard[block.x-1][block.y] = 1;
                        newBoard[block.x-1][block.y+1] = 1;
                        successors.add(new GameState(newBoard));
                    }

                }
                if(canMoveLeft(block,board)){
                    if(block.type.equals("2x1")){
                        int[][] newBoard = new int[5][4];
                        for(int a = 0; a < 5; a++){
                            for(int b = 0; b < 4; b++){
                                newBoard[a][b] = board[a][b];
                            }
                        }
                        newBoard[block.x][block.y] = 0;
                        newBoard[block.x+1][block.y] = 0;
                        newBoard[block.x][block.y-1] = 2;
                        newBoard[block.x+1][block.y-1] = 2;
                        successors.add(new GameState(newBoard));
                    }
                    else if(block.type.equals("1x2")){
                        int[][] newBoard = new int[5][4];
                        for(int a = 0; a < 5; a++){
                            for(int b = 0; b < 4; b++){
                                newBoard[a][b] = board[a][b];
                            }
                        }
                        newBoard[block.x][block.y+1] = 0;
                        newBoard[block.x][block.y-1] = 3;
                        successors.add(new GameState(newBoard));
                    }
                    else if(block.type.equals("2x2")){
                        int[][] newBoard = new int[5][4];
                        for(int a = 0; a < 5; a++){
                            for(int b = 0; b < 4; b++){
                                newBoard[a][b] = board[a][b];
                            }
                        }
                        newBoard[block.x][block.y+1] = 0;
                        newBoard[block.x+1][block.y+1] = 0;
                        newBoard[block.x][block.y-1] = 1;
                        newBoard[block.x+1][block.y-1] = 1;
                        successors.add(new GameState(newBoard));
                    }

                }
                if(canMoveRight(block,board)){
                    if(block.type.equals("2x1")){
                        int[][] newBoard = new int[5][4];
                        for(int a = 0; a < 5; a++){
                            for(int b = 0; b < 4; b++){
                                newBoard[a][b] = board[a][b];
                            }
                        }
                        newBoard[block.x][block.y] = 0;
                        newBoard[block.x+1][block.y] = 0;
                        newBoard[block.x][block.y+1] = 2;
                        newBoard[block.x+1][block.y+1] = 2;
                        successors.add(new GameState(newBoard));
                    }
                    else if(block.type.equals("1x2")){
                        int[][] newBoard = new int[5][4];
                        for(int a = 0; a < 5; a++){
                            for(int b = 0; b < 4; b++){
                                newBoard[a][b] = board[a][b];
                            }
                        }
                        newBoard[block.x][block.y] = 0;
                        newBoard[block.x][block.y+2] = 3;
                        successors.add(new GameState(newBoard));
                    }
                    else if(block.type.equals("2x2")){
                        int[][] newBoard = new int[5][4];
                        for(int a = 0; a < 5; a++){
                            for(int b = 0; b < 4; b++){
                                newBoard[a][b] = board[a][b];
                            }
                        }
                        newBoard[block.x][block.y] = 0;
                        newBoard[block.x+1][block.y] = 0;
                        newBoard[block.x][block.y+2] = 1;
                        newBoard[block.x+1][block.y+2] = 1;
                        successors.add(new GameState(newBoard));
                    }

                }
            }
        }

        Collections.sort(successors, (s1, s2) -> {
            String sum1 = "";
            String sum2 = "";
            for(int i = 0; i < 5; i++){
                for(int j = 0; j < 4; j++){
                    sum1 += s1.board[i][j];
                    sum2 += s2.board[i][j];
                }
            }
            if (sum1.compareTo(sum2) < 0) {
                return -1;
            } else if (sum1.compareTo(sum2) > 0) {
                return 1;
            } else return 0;
        });

        return successors;
    }

    // return the 20-digit number as ID
    public String getStateID() {
        String s = "";
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++)
                s += this.board[i][j];
        }
        return s;
    }

    public void printBoard() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++)
                System.out.print(this.board[i][j]);
            System.out.println();
        }
    }

    // check whether the current state is the goal
    public boolean goalCheck() {
        if(this.board[3][1] == 1 && this.board[4][1] == 1 && this.board[3][2] == 1){
            return true;
        }
        else{
            return false;
        }
    }

    // add new methods for the GameState if necessary        

}