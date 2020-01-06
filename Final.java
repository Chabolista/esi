package FInal;

import java.util.Scanner;

public class Final {
	
	final public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		int row,col,B1,B2;
	boolean end = false;
	row = askDimension("rows");
	col = askDimension("columns");
	B1 = askBoats("1");
	B2 = askBoats("2");
	System.out.println("Player 1");
	int board1[][]= generateBoard(row,col,B1,B2);
	System.out.println("Player 2");
	int board2[][]= generateBoard(row,col,B1,B2);
	
	int visual1[][]=generateVisual(board1.length, board1[0].length);
	int visual2[][]=generateVisual(board2.length, board2[0].length);
	
	//printMatrix(visual1);
	
	do {
		System.out.println("Turn for player 1, this is what you see");
		printVisual(visual2);
		visual2=attack(board2, visual2);
		printVisual(visual2);
		end =isTheEnd(visual2,1,B1,B2);
		
		if(end==false) {
		System.out.println("Turn for player 2, this is what you see");
		printVisual(visual1);
		visual1=attack(board1,visual1);
		printVisual(visual1);
		end = isTheEnd(visual1,2,B1,B2);
		}else {
			end=true;
		}
	}while(end==false);

	

}

public static int askDimension(String message) {
	int dimension;
	do{
	System.out.println("Introduce the number of "+message+": ");
	dimension = sc.nextInt();
	}while(dimension<3);
	return dimension;
}
public static int askBoats(String number) {
	int boat;
	do{
	System.out.println("Introduce the number of boats of length "+number+": ");
	boat = sc.nextInt();
	}while(boat<0);
	return boat;
}

	
public static int[][]generateBoard(int row,int col,int b1, int b2){
	
	//B1
	int a,b;
	int array1[][]=new int[row][col];
	for(int i=0;i<b1;i++) {
		do {
			System.out.println("Introduce the row you want your B1 boat to be: [0,"+ row+"]");
			a = sc.nextInt();
			System.out.println("Introduce the column you want your B1 boat to be: [0,"+ col+"]");
			b = sc.nextInt();
		}while(a<0 || a>row || b<0 || b>col);
		if((array1[a-1][b-2])==1 || (array1[a-1][b])==1 || (array1[a-2][b-1])==1 || (array1[a][b-1])==1) {
			System.out.println("You can't put a boat here. It is contigous to another one");
			i--;
		}
		else {
		array1[a-1][b-1]=1;
		}
		
		//We print the board
		printBoard(array1);
	}
	
	//B2
	String direction, upOrDown, leftOrRight;
	for(int i=0;i<b2;i++) {
		do {
		System.out.println("Introduce the row you want your B2 boat to be: [0,"+ row+"]");
		a = sc.nextInt();
		System.out.println("Introduce the column you want your B2 boat to be: [0,"+ col+"]");
		b = sc.nextInt();
		}while(a<0 || a>row || b<0 || b>col);
		
		if((array1[a-1][b-2])==0 && (array1[a-1][b])==0 && (array1[a-2][b-1])==0 && (array1[a][b-1])==0) {
			
			array1[a-1][b-1]=2;
			
			//We print the board
			printBoard(array1);
			
			do {
			System.out.println("Do you want to place it vertically (v) or horizontaly (h)? ");
			direction = sc.next();
			}while(!direction.equalsIgnoreCase("v") && !direction.equalsIgnoreCase("h"));
			if(direction.equalsIgnoreCase("v")) {   //Vertical
				if(a==1) {
					array1[a][b-1]=2;
					
					//We print the board
					printBoard(array1);
				}else if(a==row) {
					array1[a-2][b-1]=2;
					
					//We print the board
					printBoard(array1);
				}else {
					do {
						System.out.println("Do you want it upwards (u) or downwards (d)?");
						upOrDown = sc.next();
					}while(!upOrDown.equalsIgnoreCase("u") && !upOrDown.equalsIgnoreCase("d"));
					if(upOrDown.equalsIgnoreCase("u")) {
						if((array1[a-2][b-2])==0 && (array1[a-2][b])==0) {
							
							array1[a-2][b-1]=2;
							
							//We print the board
							printBoard(array1);
						}
						else {
							System.out.println("You can't put a boat here. It is contigous to another one");
							i--;
							array1[a-1][b-1]=0;
						}
					}else{
						if((array1[a][b-2])==0 && (array1[a][b])==0 && (array1[a+1][b-1])==0 && (array1[a-2][b-1])==0) {
							
							array1[a][b-1]=2;
							
							//We print the board
							printBoard(array1);
						}
						else {
							System.out.println("You can't put a boat here. It is contigous to another one");
							i--;
							array1[a-1][b-1]=0;
						}

					}
				}
			}else { 			//Horizontal
				if(b==1) {
					array1[a-1][b]=2;
					
					//We print the board
					printBoard(array1);
				}else if(b==col) {
					array1[a-1][b-2]=2;
					
					//We print the board
					printBoard(array1);
				}else {
				do {
					System.out.println("Do you want it leftwards (l) or rightwards (r)?");
					leftOrRight = sc.next();
					}while(!leftOrRight.equalsIgnoreCase("r") && !leftOrRight.equalsIgnoreCase("l"));
					if(leftOrRight.equalsIgnoreCase("r")) {
						if((array1[a-2][b])==0 && (array1[a][b])==0 && (array1[a][b+1])==0) {
							
							array1[a-1][b]=2;
							
							//We print the board
							printBoard(array1);
						}
						else {
							System.out.println("You can't put a boat here. It is contigous to another one");
							i--;
							array1[a-1][b-1]=0;
						}

					}else{
						if((array1[a-2][b-2])==0 && (array1[a][b-2])==0 && (array1[a][b-3])==0) {
							
							array1[a-1][b-2]=2;
							
							//We print the board
							printBoard(array1);
						}
						else {
						System.out.println("You can't put a boat here. It is contigous to another one");
						i--;
						array1[a-1][b-1]=0;
						}

					}
				}
			}
		}
		else {
			System.out.println("You can't put a boat here. It is contigous to another one");
			i--;
	}
	}
	
	return array1;
}



private static void printBoard(int[][] array1) {
	for(int i=0;i<array1.length;i++) {
		for(int j=0;j<array1[i].length;j++) {
			if(array1[i][j]==1) {
			System.out.print("1");
			}else if (array1[i][j]==2)
				System.out.print("2");
			else {
				System.out.print("0");
			}
		}
		System.out.println();
	}

}




public static int[][]generateVisual(int a, int b) {
	int visual[][]=new int[a][b];
	for(int i=0; i<visual.length; i++) {
		for (int j=0; j<visual[i].length; j++) {
			visual[i][j]=5;
		}
		System.out.println();
	}
	
	return visual;
}
	

public static void printVisual(int[][]visual) {
	for(int i=0; i<visual.length; i++) {
		for (int j=0; j<visual[i].length; j++) {
			if(visual[i][j]==5) {
				System.out.print("~");		
			}else if(visual[i][j]==6) {
				System.out.print("#");
			}else if (visual[i][j]==7) {
				System.out.print("X");
			}else if (visual[i][j]==8) {
				System.out.print("O");
			}
		}
		System.out.println();
	}
}


public static int[][]attack(int[][]board,int[][]visual){
	//int [][]array = new int[a][b];//las coordenadas del attack son a-1 y b-1 
	boolean son2=false;
	System.out.println("Select the coordenates for your attack");
	int coorX;
	int coorY;
	coorX = askCoor(board,"X")-1;
	coorY = askCoor(board,"Y")-1;
	if(coorX>board.length-2 || coorY>board[0].length-2 || coorX<0 || coorY<0) {
		System.out.println("Those coordenates are out of the map");
	}
	checkPhrase(board,visual, coorX,coorY);	
		
	

	if(board[coorX][coorY]==1) {
		visual[coorX][coorY]=7;
	}else if(board[coorX][coorY]==2) {
			visual[coorX][coorY]=6;
					visual=killTwo(visual,coorX,coorY);
			}else if (board[coorX][coorY]==0) {
				visual[coorX][coorY]=8;
			}

	
	return visual;
}

public static void checkPhrase(int[][]board,int[][]visual, int a, int b) {

	if(board[a][b]==1) {
		System.out.println("Drown");
	}
	if (board[a][b]==2) {
		System.out.println("Hit");		
	}
	if (board[a][b]==0){
		System.out.println("You missed");
			
	}
	
}

public static void printMatrix(int[][]matriz) {
	for(int i=0; i<matriz.length;i++) {
		for(int j=0; j<matriz[0].length; j++) {
			System.out.print(matriz[i][j]);
		}
		System.out.println();
	}
}

public static int askCoor(int[][]board,String message) {
	int coor;
	do{
		System.out.println("Introduce the "+message+" coordenate: ");
		coor = sc.nextInt();
	}while(coor>board.length || coor<0);
	return coor;
}

public static int[][]killTwo(int[][]visual, int a, int b){
	if(a!=0) {
		visual=matarDosArriba(visual, a, b);
	}
	
	if(a!=visual.length-1) {
		visual=matarDosAbajo(visual, a, b);
	}
	if(b!=0) {
		visual=matarDosIzquierda(visual, a, b);
	}
	if(b!=visual[a].length-1) {
		visual=matarDosDerecha(visual, a, b);
	}
	return visual;
}

public static int[][]matarDosArriba(int[][]visual, int a, int b){
	if(visual[a-1][b]==6) {
		visual[a-1][b]=7;
		visual[a][b]=7;
		System.out.println("and sunk");
	}
	return visual;
}

public static int[][]matarDosAbajo(int[][]visual, int a, int b){
	if(visual[a+1][b]==6) {
		visual[a+1][b]=7;
		visual[a][b]=7;
		System.out.println("and sunk");
	}
	return visual;
}

public static int[][]matarDosIzquierda(int[][]visual, int a, int b){
	if(visual[a][b-1]==6) {
		visual [a][b-1]=7;
		visual[a][b]=7;
		System.out.println("and sunk");
	}
	return visual;
}

public static int[][]matarDosDerecha(int[][]visual, int a, int b){
	if(visual[a][b+1]==6) {
		visual [a][b+1]=7;
		visual[a][b]=7;
		System.out.println("and sunk");
	}
	return visual;
}

public static int[][]onlyLeft(int[][]board, int a, int b){
	if(board[a][b]==1 && a!=0 && b==0 && a!=board[0].length) {
		board[a+1][b]=6;
		board[a-1][b]=6;
		board[a][b+1]=6;
	}
	
	return board;
}
public static int[][]onlyBottom(int[][]board, int a, int b){
	if(board[a][b]==1 && a==board[0].length && b!=0 && b!=board.length) {
		board[a+1][b]=6;
		board[a-1][b]=6;
		board[a][b+1]=6;
	}
		return board;
}
public static int[][]onlyTop(int[][]board, int a, int b){
	if(board[a][b]==1 && a==0 && b!=0 && b!=0) {
		board[a+1][b]=6;
		board[a-1][b]=6;
		board[a][b+1]=6;
		}
	return board;
}


public static int[][]onlyRight(int[][]board, int a, int b){
	if(board[a][b]==1 && a!=board[0].length && b==board.length  && a!=0) {
		board[a+1][b]=6;
		board[a-1][b]=6;
		board[a][b+1]=6;
		}
	return board;
}
public static int[][]topLeftCorner(int[][]board, int a, int b){
	if(board[a][b]==1 && a==0 && b==0  && a!=board[0].length && b!=board.length) {
		board[a+1][b]=6;
		board[a][b+1]=6;
		}
	return board;
}
public static int[][]topRightCorner(int[][]board, int a, int b){
	if(board[a][b]==1 && a==0 && b==board.length  && a!=board[0].length && b!=0) {
		board[a-1][b]=6;
		board[a][b+1]=6;
		}
	return board;
}
public static int[][]bottomLeftCorner(int[][]board, int a, int b){
	if(board[a][b]==1 && a!=board[0].length && b==0  && a==0) {
		board[a-1][b]=6;
		board[a][b+1]=6;
		}
	return board;
}
public static int[][]bottomRightCorner(int[][]board, int a, int b){
	if(board[a][b]==1 && a!=board[0].length && b==board.length  && a!=0) {
		board[a-1][b]=6;
		board[a][b-1]=6;
		}
	return board;
}
public static boolean isTheEnd (int[][]visual, int player, int B1, int B2) {
	boolean end=false;
	int alive=B1+(2*B2);
	int dead=deadBoats(visual);
	if(alive==dead) {
		end=true;
		System.out.println("Player " + player+ " wins");
	}
	return end;
}


public static int deadBoats(int[][]visual) {
	int dead =0;
	for(int i=0; i<visual.length; i++) {
		for(int j=0; j<visual[0].length; j++) {
			if (visual[i][j]==7) {
				dead++;
			}
		}
	}
	
	return dead;
}
}
