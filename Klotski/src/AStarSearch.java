import java.util.*;

class AStarSearch{
    Queue<GameState> openSet;
    Set<GameState> closedSet;

    //Comparator for the GameState
    public Comparator<GameState> stateComparator = new Comparator<GameState>() {
        @Override
        public int compare(GameState o1, GameState o2) {
            if (o1.cost - o2.cost != 0)
                return o1.cost - o2.cost;
            else
                return o1.getStateID().compareTo(o2.getStateID());
        }
    };

    // print the states of board in open set
    public void printOpenList(int flag, GameState state) {
        System.out.println("OPEN");
        Object[] openArray = openSet.toArray();
        for(int i = 0; i < openArray.length; i++){
            GameState currentState = (GameState)openArray[i];
            System.out.println(currentState.getStateID());
            currentState.printBoard();
            System.out.println(currentState.cost+" "+currentState.steps+" "+(currentState.cost-currentState.steps));
            if(currentState.parent != null) {
                System.out.println(currentState.parent.getStateID());
            }
            else{
                System.out.println("null");
            }
        }

    }

    public void printClosedList(int flag, GameState state) {
        System.out.println("CLOSED");
        Object[] closedArray = closedSet.toArray();
        for(int i = 0; i < closedArray.length; i++){
            GameState currentState = (GameState)closedArray[i];
            System.out.println(currentState.getStateID());
            currentState.printBoard();
            System.out.println(currentState.cost+" "+currentState.steps+" "+(currentState.cost-currentState.steps));
            if(currentState.parent != null) {
                System.out.println(currentState.parent.getStateID());
            }
            else{
                System.out.println("null");
            }
        }
    }

    // implement the A* search
    public GameState aStarSearch(int flag, GameState state) {
        // feel free to using other data structures if necessary
        openSet = new PriorityQueue<>(stateComparator);
        closedSet = new HashSet<>();
        int goalCheck = 0;
        int maxOPEN = -1;
        int maxCLOSED = -1;
        int steps = 0;

        // beginning of algorithm
        int i = 1; // iteration counter
        int h = 0; // heuristic counter

        GameState n;

        if(flag == 400 || flag == 500){
            int[][] board = state.board;
            boolean foundBlock = false;
            for(int j = 0; j < board.length-1; j++){
                for(int k = 0; k < board.length-1; k++){
                    if(board[j][k] == 1 && foundBlock == false){
                        h = 0;
                        if(j < 3){
                            h = 3 - j;
                        }
                        if(k != 1){
                            h += 1;
                        }
                        foundBlock = true;
                    }
                }
            }
        }
        state.cost = h;

        openSet.add(state);
        while(true) {
            if (openSet.isEmpty()) {
                return null;
            }
            n = openSet.poll();
            closedSet.add(n);
            if(flag == 200 || flag == 400) {
                System.out.println("iteration " + i);
                System.out.println(n.getStateID());
                n.printBoard();
                System.out.println(n.cost + " " + n.steps + " " + (n.cost - n.steps));
                if (n.parent != null) {
                    System.out.println(n.parent.getStateID());
                } else {
                    System.out.println("null");
                }
            }

            goalCheck++;
            if(n.goalCheck()){
                break;
            }

            List<GameState> states = n.getNextStates();
            for(int j = 0; j < states.size(); j++){
                GameState successor = states.get(j);
                successor.parent = n;

                if(flag == 400 || flag == 500){
                    int[][] board = successor.board;
                    boolean foundBlock = false;
                    for(int k = 0; k < board.length-1; k++){
                        for(int p = 0; p < board.length-1; p++){
                            if(board[k][p] == 1 && foundBlock == false){
                                h = 0;
                                if(k < 3){
                                    h = 3 - k;
                                }
                                if(p != 1){
                                    h += 1;
                                }
                                foundBlock = true;
                            }
                        }
                    }
                }

                Object[] openArray = openSet.toArray();
                Object[] closedArray = closedSet.toArray();
                boolean closedDuplicate = false;
                boolean openDuplicate = false;

                for(int b = 0; b < closedArray.length; b++){
                    GameState currentState = (GameState)closedArray[b];
                    if(successor.getStateID().equals(currentState.getStateID())){
                        closedDuplicate = true;
                        successor.steps = successor.parent.steps+1;
                        successor.cost = successor.parent.steps+1+h;
                        if(successor.steps < currentState.steps){
                            openSet.remove(successor);
                            openSet.add(successor);
                        }

                    }
                }

                for(int a = 0; a < openArray.length; a++){
                    GameState currentState = (GameState)openArray[a];
                    if(successor.getStateID().equals(currentState.getStateID())){
                        openDuplicate = true;
                        successor.steps = successor.parent.steps+1;
                        successor.cost = successor.parent.steps+1+h;
                        if(successor.steps < currentState.steps && !closedDuplicate) {
                            openSet.remove(successor);
                            openSet.add(successor);
                        }
                    }
                }


                if(!closedDuplicate && !openDuplicate){
                    successor.steps = successor.parent.steps+1;
                    successor.cost = successor.parent.steps+1+h;
                    openSet.add(successor);
                }
            }
            if(flag == 200 || flag == 400) {
                printOpenList(flag, state);
                printClosedList(flag, state);
            }

            if (openSet.size() > maxOPEN) {
                maxOPEN = openSet.size();
            }
            if (closedSet.size() > maxCLOSED) {
                maxCLOSED = closedSet.size();
            }

            i++;
        }
        state = n;
        steps = n.steps;

        if(flag == 300 || flag == 500) {
            Stack states = new Stack();

            while(n != null) {
                states.push(n);
                n = n.parent;
            }

            int size = states.size();

            for(int j = 0; j < size; j++){
                GameState currentState = (GameState)states.pop();
                currentState.printBoard();
                System.out.println(" ");
            }

            System.out.println(" ");
            System.out.println("goalCheckTimes " + goalCheck);
            System.out.println("maxOPENSize " + maxOPEN);
            System.out.println("maxCLOSEDSize " + maxCLOSED);
            System.out.println("steps " + steps);
        }
        return state;
    }

    // add more methods for the A* search if necessary

}