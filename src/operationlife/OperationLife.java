package operationlife;

import java.util.ArrayList;
import java.util.Random;
/*
 * File: OperationLife.java
 * Author1: Isaac Cornelius (isaac.h.cornelius@gmail.com)
 * Author2: Gershon Fosu (wrote the copyState function)
 * Date: August 2019
 *
 * Summary of file:
 *
 *  This file contains code for Conway's Game of Life.
 *  Functions define the rules for a system of cells that
 *  are contained in a grid, where cells can live and die.
 *  This allows for wonderful patterns to be formed.
 *
 *  As explained by Robert Heaton on 
 *  https://robertheaton.com/2018/07/20/project-2-game-of-life/,
 *  "It is played on a 2-D grid. Each square in the grid contains a cell, 
 *  and each cell starts 
 *  the game as either "alive" or "dead". Play proceeds in rounds. During each 
 *  round, each cell 
 *  looks at its 8 immediate neighbors and counts up the number of them that 
 *  are currently alive.
 *  The cell then updates its own liveness according to 4 rules:
 *
 *  1. Any live cell with 0 or 1 live neighbors becomes dead, because of 
 *  underpopulation
 *  2. Any live cell with 2 or 3 live neighbors stays alive, because its 
 *  neighborhood is just right
 *  3. Any live cell with more than 3 live neighbors becomes dead, because of 
 *  overpopulation
 *  4. Any dead cell with exactly 3 live neighbors becomes alive, by 
 *  reproduction"
 *
 *  This code is designed to run in the OS terminal.
 * 
 */
public class OperationLife {
    
    /**
    *
    * static ArrayList<ArrayList<Integer>> random_state(int width, int height)
    * 
    * Summary of random_state function:
    *   
    *   The random_state function checks every cell within the board state of 
    *   the given width and height, completely comprised if dead cells 
    *   (represented by a 0). 
    *   Each cell is possibly replaced by a living cell (represented by a 1) 
    *   based on the randomisation of a generated number being greater than a 
    *   particular number.
    *   
    * Parameters:
    *   
    * Description:
    * 
    *   This function returns a board state where every cell has 
    *   been randomly initialised to either alive (represented by a 1) or dead
    *   (represented by a 0). These random patterns are called "soups".
    *
    */
    static ArrayList<ArrayList<Integer>> random_state(int width, int height){
        ArrayList<ArrayList<Integer>> state = new ArrayList<>();
        state = dead_state(width, height);
        
        
        for(int i=0; i<height;i++){
            for(int j=0; j<width; j++){
                double random_number = Math.random();
                if(random_number >= 0.6){
                    // cell_state = 0;
                    state.get(i).set(j,0);
                }else{
                     //cell_state = 1;
                     state.get(i).set(j,1);
                }
            }
        }
        System.out.println();
        return state;
    } 
    
    /**
    *
    * static ArrayList<ArrayList<Integer>> dead_state(int width, int height)
    * 
    * Summary of dead_state function:
    *   
    *   The dead_state function creates a 2D ArrayList completely full of zeros.
    *   
    * Parameters: width - equivalent to the number of columns
    *             height - equivalent to the number of rows
    * 
    * Description:
    * 
    *   This function returns a board state where every cell has 
    *   been initialised to dead (represented by a 0). This function is required
    *   in the random_state function in order to create randomised board states.
    *
    */
    static ArrayList<ArrayList<Integer>> dead_state(int width, int height){
        ArrayList<ArrayList<Integer>> table = new ArrayList<>();
       
        for(int i=0;i<height;i++){
            table.add(new ArrayList<>());
        }
        
        
        
        for(int i=0;i<height;i++){
            for(int j=0;j<width;j++){
                table.get(i).add(0);
            }
        }
       
        return table;
        
    }
    
    
    /**
    *
    * static String render(int width, int height, ArrayList<ArrayList<Integer>> 
    * board)
    * 
    * Summary of render function:
    *   
    *   The render function takes a given ArrayList of a given height and width
    *   and creates a board state as a String of cells which can be represented
    *   by character other than 0 and 1.
    *   
    * Parameters: width - equivalent to the number of columns
    *             height - equivalent to the number of rows
    *   
    * Description:
    * 
    *   This function returns a formatted board state so it looks more like a 
    *   2D grid after output. Dead and alive cells can be represented by 
    *   different characters in this function.
    *
    */
    static String render(int width, int height, ArrayList<ArrayList<Integer>> 
    board){
        
        String finalBoardState = "";
        String dead_cell=" ";
        String live_cell="#";
        
        
        for(int i = 0; i < height; i++){ 
            finalBoardState += "|"; //creates the border of the 2d grid
            for(int j=0;j<width; j++){ 
                if(board.get(i).get(j)==0){
                    finalBoardState += dead_cell;
                }else{
                    finalBoardState += live_cell;
                }
                
            }
          finalBoardState += "|\n";
        }finalBoardState += "__"; //creates the border of the 2d grid
        for(int i=0;i<width;i++){
            finalBoardState += "_"; //creates the border of the 2d grid
        }
        
        return finalBoardState;
    }
    
    /**
    *
    * static ArrayList<ArrayList<Integer>> next_board_state(int width, 
    * int height, ArrayList<ArrayList<Integer>> loadedBoard)
    * 
    * Summary of next_board_state function:
    *   
    *   The next_board_state function takes a given initial board state or a 
    *   board state of given width and height. Using for loops, each cell in the 
    *   current board state is checked against the rules of the game of life and
    *   is converted into a dead cell or alive cell. After all conversion is 
    *   done, the process is repeated for the following board state for the next
    *   round using a while loop.
    * 
    * Parameters: width - equivalent to the number of columns
    *             height - equivalent to the number of rows
    *             loadedBoard - an initial board state loaded (not randomised)
    *   
    *   NOTE 1: It is important to use "null" as the argument for the 
    *   loadedBoard parameter when creating a randomised board.
    *   
    *   NOTE 2: It is important to use the newWidth() function and the 
    *   newHeight() function to calculate the width and height of loaded 
    *   boards to pass as arguments.
    *   
    * Description:
    * 
    *   This function applies the rules of Conway's Game of Life to a randomised
    *   board state of either a given width and height, or a given initial board
    *   state from a loaded file.
    *
    */
    static ArrayList<ArrayList<Integer>> next_board_state(int width, int height,
    ArrayList<ArrayList<Integer>> loadedBoard){
        
        ArrayList<ArrayList<Integer>> next_state;
        
        ArrayList<ArrayList<Integer>> original_state;
        
        if(loadedBoard == null){
            //this board state is what will be changed according to the rules of
            //Conway's Game of Life and output in the render() function
            next_state = copyState(random_state(width,height));
            //this board state is the previous board state that is used to check
            //against the rules of Conway's Game of Life so that the new changes
            //will be applied to the new_state. Therefore, it's important that 
            //original_state remains a constant until the next round where there
            //is a new board
            original_state = random_state(width,height);
        }else{
            //this board state is what will be changed according to the rules of
            //Conway's Game of Life and output in the render() function
            next_state = copyState(loadedBoard); 
            //this board state is the previous board state that is used to check
            //against the rules of Conway's Game of Life so that the new changes
            //will be applied to the new_state. Therefore, it's important that 
            //original_state remains a constant until the next round where there
            //is a new board
            original_state = loadedBoard;
        }
        
        int infinity =8; // initialised number used for infinite while loop
        
        //controls the animation speed in the terminal by printing only the 
        //first board state a repeat number of times
        int speed1=0;
        do{
            System.out.println(render(width, height, next_state));
            speed1+=1;
        }while(speed1<500);
        
        do{
            
            //System.out.println(width);
            int num_live_cells=0;// total number of live cells

            //i and j are the cells that can be changed
            for(int i=1;i<(height-1);i++){
                for(int j=1;j<(width-1);j++){
                    //i1 and j1 are the neighbours
                    for(int i1=(i-1);i1<(i+2);i1++){
                        for(int j1=(j-1);j1<(j+2);j1++){
                            //checks dead cell to change
                            if(original_state.get(i).get(j)==0){  
                                if(original_state.get(i1).get(j1).equals(1)){ 
                                    //if neighbour is a live cell then increment
                                    //total number of live cells  
                                    num_live_cells = num_live_cells +1;   
                                }
                            }
                            //checks live cell to change
                            if(original_state.get(i).get(j)==1){ 
                                if((i==i1) && (j==j1)){
                                    num_live_cells = num_live_cells + 0;
                                }else if(
                                 (original_state.get(i1).get(j1).equals(1))){
                                    //if neighbour is a live cell then increment
                                    //total number of live cells
                                    num_live_cells = num_live_cells +1;     
                                }
                            }

                        }
                    }
                    if(num_live_cells == 3 && original_state.get(i).get(j)==0){
                        //dead cell becomes live cell because of 
                        //reproduction - RULE #4
                        
                        next_state.get(i).set(j,1); 

                    }else if(num_live_cells>3 && 
                     original_state.get(i).get(j)==1){
                        
                        next_state.get(i).set(j,0); //overpopulation - RULE #3
                    }else if((num_live_cells==3 && 
                     original_state.get(i).get(j)==1)||(num_live_cells==2 && 
                     original_state.get(i).get(j)==1)){
                        
                        // remains as a live cell - RULE #2
                        next_state.get(i).set(j,1); 
                    }else if(num_live_cells <= 1 && 
                     original_state.get(i).get(j)==1){
                        
                        next_state.get(i).set(j,0); //unerpopulation - RULE #1
                    }

                    num_live_cells=0;
                    
                }
                //num_live_cells=0;
            }
            int speed2 =0;
            //controls the animation speed in the terminal by printing each 
            //state after the first state a repeat number of times
            do{
                
                System.out.println(render(width,height,next_state));
                speed2+=1;
            }while(speed2<500);
            
            //initialises the new originial state
            original_state = copyState(next_state);

        }while(infinity==8);
        
        //next_state never gets returned on infinite while loop
        //(and that's ok!)
        return next_state;
    }
    
    /**
    *
    * static ArrayList<ArrayList<Integer>> loadState(String board)
    * 
    * Summary of load_state function:
    *   
    *   The load_state function takes a String of the board state which 
    *   originally came from a loaded file and rearranges lines of 0s and 1s
    *   into a 2D ArrayList. The function checks the number of newlines in the 
    *   board state and utilises that as the number of rows the 2D ArrayList 
    *   will have.
    *   
    * Parameters: board - an initial board state loaded (not randomised) 
    *   
    * Description:
    * 
    *   This function takes a given initial board state loaded from a text file 
    *   as a String and converts it into an ArrayList so it can be used in the
    *   next_board_state function as a compatible type.
    *
    */
    static ArrayList<ArrayList<Integer>> loadState(String board){
        
        //loaded state will be transferred to this ArrayList
        ArrayList<ArrayList<Integer>> newBoard = new ArrayList<>();
        
        int lineCount=1;
        //count the number of newlines
        //number of rows in a board is the number of newlines plus 1
        for(int i=0;i<board.length();i++){
            int j=i+1;
            if(board.substring(i,j).equals("\n")){
                lineCount = lineCount + 1;
            }
        }
        
        //create arraylist object for every row in the board
        for(int i=0;i<lineCount;i++){
            newBoard.add(new ArrayList<>());
        }
        
        try{
            int height = lineCount; // number of lines in the state
            
            int width = (board.length() - lineCount +1)/lineCount;
            
            for(int i=0;i<height;i++){
                // i is also equivalent to the no. of newlines
                for(int j=(width*i)+i;j<(width*(i+1))+i;j++){
                    int k = j+1;
                    //must convert String into Integer
                    int cell = Integer.parseInt(board.substring(j,k));	
                    newBoard.get(i).add(cell);
                }
            }
        }catch(Exception e){
            System.out.println("There was an issue with transferring the String"
                    + " type loaded board to the ArrayList. It may also be an "
                    + "issue with initiallising the height or calculating the "
                    + "width.");
        }
        return newBoard;
    }
    
    /**
    *
    * static int getWidth(String board)
    * 
    * Summary of getWidth function:
    *   
    *   The getWidth function takes a String of the board state which 
    *   originally came from a loaded file and calculates the width of the board
    *   state by counting the number of newlines.
    * 
    * Parameters: board - an initial board state loaded (not randomised) 
    *   
    * Description:
    * 
    *   This function takes a given initial board state loaded from a text file 
    *   and calculates and returns the width of the board. 
    *
    */
    static int getWidth(String board){
        int lineCount=1;
        //count the number of newlines
        //number of rows in a board is the number of newlines plus 1
        for(int i=0;i<board.length();i++){
            int j=i+1;
            if(board.substring(i,j).equals("\n")){
                lineCount = lineCount + 1;
            }
        }
        
        int width = (board.length() - lineCount +1)/lineCount;
        
        return width;
    }
    
    /**
    *
    * static int getHeight(String board)
    * 
    * Summary of getHeight function:
    *   
    *   The getHeight function takes a String of the board state which 
    *   originally came from a loaded file and calculates the width of the board
    *   state by counting the number of newlines.
    * 
    * Parameters: board - an initial board state loaded (not randomised) 
    *   
    * Description:
    * 
    *   This function takes a given initial board state loaded from a text file 
    *   and calculates and returns the height of the board. 
    *
    */
    static int getHeight(String board){
        int lineCount=1;
        //count the number of newlines
        //number of rows in a board is the number of newlines plus 1
        for(int i=0;i<board.length();i++){
            int j=i+1;
            if(board.substring(i,j).equals("\n")){
                lineCount = lineCount + 1;
            }
        }
        
        int height = lineCount;
        
        return height;
    }
    
    public static void main(String[] args) {
        
        try{
            
            readfile r = new readfile();
            r.openFile("beluchenkosp37");
            
            int newWidth=0;
            int newHeight=0;
            
            String specificSoup = r.readFile();
            
            // width of the loaded state
            newWidth = getWidth(specificSoup);
            
            //height of the loaded state
            newHeight = getHeight(specificSoup);loadState(specificSoup);
            System.out.println(next_board_state(newWidth,newHeight,
            loadState(specificSoup))); //used when loading a pre-existing board
            //System.out.println(next_board_state(90,20,null));
            r.closeFile();
        }catch(Exception e){
            System.out.println("Joseph Joestar voice: OH NO!");
        }
        //boom = r.readFile();
        
        
    }
    
    /**
    *
    * static ArrayList<ArrayList<Integer>> copyState
    * (ArrayList<ArrayList<Integer>> state)
    * 
    * Summary of copyState function:
    *   
    *   The copyState function takes a given board state and copies the contents
    *   of the 2D ArrayList by breaking it down into 1D ArrayLists and copying 
    *   each individual item in the ArrayList for every 1D ArrayList iterated.
    * 
    * Parameters: state - a board state loaded (may or may not be randomised) 
    *   
    * Description:
    * 
    *   This function takes a given board state and performs a deep copy of the 
    *   ArrayList instead of a shadow copy.
    *
    */
    static ArrayList<ArrayList<Integer>> copyState(ArrayList<ArrayList<Integer>>
    state){
        ArrayList<ArrayList<Integer>> copiedState = 
        new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < state.size(); i++){
            ArrayList<Integer> intArray = new ArrayList<Integer>();
            copiedState.add(intArray);
            ArrayList<Integer> existingArray = state.get(i);
            for (int j = 0; j < existingArray.size(); j++){
               intArray.add(existingArray.get(j));
            }
        }
        return copiedState;
    }
    
    
}
