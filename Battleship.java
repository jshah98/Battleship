/*
Jinansh Shah
Battleship.java
Monday Jan 13, 2014
Program allows you to play battleship with the computer. The first screen is an introduction to the program.
The next screen is the main menu from which the you can choose to exit, play the game, turn assistant on/off, view instructions, or view high scores.
If the user chooses to exit, the program will display a goodbye screen which will close after you press any key.
Your score is automatically set to 0 when you run this program and so, if you choose to view the high scores, and 0 happens to be a higher score than the other highscores, or is a score in top ten, it will record your score as a new high score. This will only happen once (everytime you run the program). 
If you choose to view instructions, it will display the instructions until you enter any key.
If you wish to play the game, you will first be asked to deploy your ships. After, you deploy, the game begins. Game ends when all of the computer's or your ships are sunk or if you or the computer have already attacked 99 times (tie). If you 'hit' a ship, it is your turn again.
If you choose to turn assistant on, a person will appear everytime you do something invalid. If you choose to turn it off, it will only appear when notifications are necessary.

Name                        Type                         Purpose
c                           reference variable           enables programmer to execute methods in that class
choice                      char                         used to store user's main menu choice
playerScore                 int                          used to store user's score
platerName                  String                       used to store user's name
helper                      boolean                      used to determine whether to display notifications or not
numHighScores               int                          stores number of times user's high score was stored 
*/
import java.awt.*;
import hsa.*;
import java.io.*;

public class Battleship
{
    Console c;
    char choice;
    int playerScore;
    String playerName;
    boolean helper = true;
    int numHighScores = 0;

    private void title ()
    {
	c.clear ();
	c.setColor (Color.black);
	c.fillRect (0, 0, 640, 500);
	c.setFont (new Font ("Britannic Bold", 1, 128));
	c.setColor (Color.yellow);
	c.drawString ("Battleship", 40, 100);
	c.setTextBackgroundColor (Color.black);
	c.setTextColor (Color.white);
    }


    private void pauseProgram ()
    {
	c.setCursor (24, 1);
	c.print ("Press any key to continue...");
	c.getChar ();
    }


    public void intro ()
    {
	BattleshipIntro i = new BattleshipIntro (c);
	i.run ();
    }


    public void mainMenu ()
    {
	title ();
	c.setCursor (10, 26);
	c.println ("1. New Game");
	c.print (' ', 25);
	c.println ("2. Instructions");
	c.print (' ', 25);
	c.println ("3. Highscores");
	c.print (' ', 25);
	c.print ("4. ");
	if (helper == true)
	    c.println ("Turn off Assistance");
	else
	    c.println ("Turn on Assistance");
	c.print (' ', 25);
	c.println ("5. Exit");
	while (true)
	{
	    c.setCursor (16, 1);
	    c.print ("Please enter your choice: ");
	    choice = c.getChar ();
	    if (choice == '1' || choice == '2' || choice == '3' || choice == '4' || choice == '5')
		break;
	    else
	    {
		new Message ("Please enter '1', '2', '3', '4' or '5'!", "Error");
		c.setCursor (16, 1);
		c.println ();
	    }
	}
    }


    public void highScores ()
    {
	PrintWriter output;
	BufferedReader input;
	final int NUM = 10;
	String[] name = new String [NUM];
	int[] points = new int [NUM];
	String temp = "";
	int numberOfHighScores = 0;
	title ();
	try
	{
	    input = new BufferedReader (new FileReader ("battleship.txt"));
	    try
	    {
		for (int i = 0 ; i < NUM ; i++)
		{
		    name [i] = input.readLine ();
		    temp = input.readLine ();
		    try
		    {
			points [i] = Integer.parseInt (temp);
			if (points [i] <= playerScore && numHighScores == 0)
			{
			    if (i < 10)
			    {
				points [i + 1] = points [i];
				name [i + 1] = name [i];
			    }
			    newHighScore ();
			    points [i] = playerScore;
			    name [i] = playerName;
			    numHighScores = i + 1;
			    i++;
			    numberOfHighScores++;
			}
		    }
		    catch (NumberFormatException f)
		    {
			break;
		    }
		    numberOfHighScores++;
		}
	    }
	    catch (EOFException q)
	    {
		if (numHighScores <= 0)
		{
		    newHighScore ();
		    points [numHighScores - 1] = playerScore;
		    name [numHighScores - 1] = playerName;
		}
	    }
	}
	catch (IOException e)
	{
	    newHighScore ();
	    numberOfHighScores = 1;
	    points [0] = playerScore;
	    name [0] = playerName;
	    numHighScores++;
	}
	c.setFont (new Font ("Arial", 1, 16));
	c.setColor (Color.green);
	try
	{
	    output = new PrintWriter (new FileWriter ("battleship.txt"));
	    for (int i = 0 ; i < numberOfHighScores ; i++)
	    {
		temp = "";
		output.println (name [i]);
		temp += points [i];
		output.println (temp);
		if (choice == '3')
		{
		    c.setCursor (11 + i, 22);
		    c.print (name [i]);
		    c.drawString (temp, 380, 215 + i * 20);
		}
	    }
	    output.close ();
	}
	catch (IOException e)
	{
	}
	if (choice == '3')
	    pauseProgram ();
    }


    private void newHighScore ()
    {
	c.setFont (new Font ("Times New Roman", 1, 24));
	c.setColor (Color.orange);
	c.drawString ("New High Score!", 230, 350);
	c.setCursor (13, 20);
	c.print ("Score: " + playerScore);
	c.setCursor (11, 20);
	c.print ("Please enter your name: ");
	playerName = c.readLine ();
	title ();
    }


    public void instructions ()
    {
	title ();
	if (helper == true)
	    newMessage ("Welcome to Instructions!");
	c.setCursor (8, 1);
	c.println ("Goal: to sink all of your opponent's ships by correctly guessing thier location.");
	c.println ();
	c.print (' ', 10);
	c.println ("-use 'w', 'a', 's', and 'd' keys to chose where to attack/deploy ships");
	c.print (' ', 10);
	c.println ("-press 'x' to attack/deploy");
	c.print (' ', 10);
	c.println ("-game ends when player/computer loose all ships");
	c.print (' ', 10);
	c.println ("-press 'm' at anytime to return to main menu and restart the game");
	c.print (' ', 10);
	c.println ("-press spacebar to change orientation of ship");
	c.println ();
	c.println ();
	c.println ("-> 10 points for every 'hit'");
	c.println ("-> loose 2 point for every 'miss'");
	c.println ("-> loose 5 points for every time your ship is 'hit'");
	c.println ("-> 1 point for every time the computer does not 'hit'");
	pauseProgram ();
    }


    private void newMessage (String message)
    {
	for (int y = 0 ; y < 270 ; y++)
	{
	    c.setColor (Color.black);
	    c.fillOval (148 - 270 + y, 356, 124, 123);
	    c.setColor (Color.lightGray);
	    c.fillArc (188 - 270 + y, 425, 50, 90, 0, 180);
	    c.fillOval (197 - 270 + y, 380, 30, 45);
	    c.setColor (Color.gray);
	    c.fillRect (-270 + y, 365, 183, 20);
	    c.fillRect (-270 + y, 447, 175, 20);
	    for (int x = 0 ; x < 16 ; x++)
	    {
		c.drawOval (150 + x - 270 + y, 357 + x, 120 - x * 2, 120 - x * 2);
	    }
	    try
	    {
		Thread.sleep (1);
	    }
	    catch (Exception e)
	    {
	    }
	}


	c.setColor (Color.white);
	c.setFont (new Font ("Myriad Pro", Font.BOLD, 9));
	c.drawString (message, 5, 405);
	try
	{
	    Thread.sleep (1000);
	}


	catch (Exception e)
	{
	}


	for (int y = 0 ; y < 270 ; y++)
	{
	    c.setColor (Color.black);
	    c.fillOval (152 - y, 356, 124, 123);
	    c.setColor (Color.lightGray);
	    c.fillArc (188 - y, 425, 50, 90, 0, 180);
	    c.fillOval (197 - y, 380, 30, 45);
	    c.setColor (Color.gray);
	    c.fillRect (0 - y, 365, 183, 20);
	    c.fillRect (0 - y, 447, 175, 20);
	    for (int x = 0 ; x < 16 ; x++)
	    {
		c.drawOval (150 + x - y, 357 + x, 120 - x * 2, 120 - x * 2);
	    }
	    try
	    {
		Thread.sleep (1);
	    }
	    catch (Exception e)
	    {
	    }
	}
    }


    public void game ()
    {
	char temp = '-';
	int max = 5;
	int x1 = 40;
	int y1 = 150;
	int shipLength = 80;
	int tempShipLength;
	int whichShip, playerAttack = -1;
	int horizontal = shipLength;
	int counter = 0, counter2 = 0;
	int incrament = 0;
	int numberOfCompShipsHit = 0, numberOfPlayerShipsHit = 0;
	final int SHIPLOCATIONS = 17;
	final int HITS = 100;
	final int SHIPS = 5;
	int vertical[] = new int [SHIPS];
	int shipSunkCounter[] = new int [SHIPS];
	int playerAttacks[] = new int [HITS];
	int compAttacks[] = new int [HITS];
	int playerShipLoc[] = new int [SHIPLOCATIONS];
	int compShipLoc[] = new int [SHIPLOCATIONS];
	int playerHitShips[] = new int [SHIPLOCATIONS];
	int compHitShips[] = new int [SHIPLOCATIONS];
	boolean hit = false;
	String winner = "Game not over";
	String message = "";
	title ();
	playerScore = 0;
	playerName = null;
	for (int i = 0 ; i < SHIPS ; i++)
	{
	    vertical [i] = 20;
	}
	for (int y = 0 ; y < 2 ; y++)
	{
	    c.setColor (Color.blue);
	    c.fillRect (40 + y * 360, 150, 200, 200);
	    c.setColor (Color.black);
	    for (int x = 0 ; x <= 10 ; x++)
	    {
		c.drawLine (40 + y * 360, 150 + x * 20, 240 + y * 360, 150 + x * 20);
		c.drawLine (40 + y * 360 + x * 20, 150, 40 + 20 * x + y * 360, 350);
	    }
	}
	while (true)
	{
	    if (temp == 'm' || counter > 4 || temp == 'M')
		break;
	    c.setColor (Color.lightGray);
	    c.fillOval (x1, y1, horizontal, vertical [counter]);
	    temp = c.getChar ();
	    if (temp != 'x' && temp != 'X')
	    {
		for (int x = 0 ; x < shipLength / 20 ; x++)
		{
		    if (vertical [counter] == 20)
		    {
			c.setColor (Color.blue);
			c.fillRect (x1 + x * 20, y1, 20, 20);
			c.setColor (Color.black);
			c.drawRect (x1 + x * 20, y1, 20, 20);
		    }
		    else
		    {
			c.setColor (Color.blue);
			c.fillRect (x1, y1 + x * 20, 20, 20);
			c.setColor (Color.black);
			c.drawRect (x1, y1 + x * 20, 20, 20);
		    }
		}
	    }
	    for (int v = 0 ; v < counter ; v++)
	    {
		c.setColor (Color.lightGray);
		if (v == 0)
		{
		    counter2 = 0;
		    tempShipLength = 80;
		}
		else if (v == 1)
		{
		    counter2 = 4;
		    tempShipLength = 60;
		}
		else if (v == 2)
		{
		    counter2 = 7;
		    tempShipLength = 60;
		}
		else if (v == 3)
		{
		    counter2 = 10;
		    tempShipLength = 40;
		}
		else
		{
		    tempShipLength = 100;
		    counter2 = 12;
		}
		if (vertical [v] == 20)
		    c.fillOval ((playerShipLoc [counter2] % 10) * 20 + 40, (playerShipLoc [counter2] - playerShipLoc [counter2] % 10) * 2 + 150, tempShipLength, 20);
		else
		    c.fillOval ((playerShipLoc [counter2] % 10) * 20 + 40, (playerShipLoc [counter2] - playerShipLoc [counter2] % 10) * 2 + 150, 20, tempShipLength);
	    }
	    if (temp == 'd' || temp == 'D')
	    {
		if (x1 < 240 - shipLength && vertical [counter] == 20 || (x1 < 220 && horizontal == 20))
		    x1 = x1 + 20;
	    }
	    else if ((temp == 'w' || temp == 'W') && y1 >= 170)
		y1 = y1 - 20;
	    else if ((temp == 'a' || temp == 'A') && x1 >= 60)
		x1 = x1 - 20;
	    else if (temp == 's' || temp == 'S')
	    {
		if (y1 < 330 && vertical [counter] == 20 || (y1 < 350 - shipLength && horizontal == 20))
		    y1 = y1 + 20;
	    }
	    else
	    {
		if (temp == ' ' || temp == 'x' || temp == 'X')
		{
		    if (temp == 'x' || temp == 'X')
		    {
			deploy:
			{
			    if (counter == 0)
				whichShip = 0;
			    else if (counter == 1)
				whichShip = 4;
			    else if (counter == 2)
				whichShip = 7;
			    else if (counter == 3)
				whichShip = 10;
			    else
				whichShip = 12;
			    for (int l = 0 ; l < (shipLength / 20) ; l++)
			    {
				playerShipLoc [whichShip + l] = ((x1 - 40) / 20 + ((y1 - 150) / 20) * 10);
				if (vertical [counter] == 20)
				    x1 += 20;
				else
				    y1 += 20;
			    }
			    for (int i = 0 ; i < whichShip ; i++)
			    {
				for (int y = whichShip ; y < whichShip + shipLength / 20 ; y++)
				{
				    if (playerShipLoc [i] == playerShipLoc [y])
				    {
					if (vertical [counter] == 20)
					    x1 -= shipLength;
					else
					    y1 -= shipLength;
					{
					    if (helper == true)
						newMessage ("Can't deploy there again!");
					    break deploy;
					}
				    }
				}
			    }
			    counter++;
			    if (counter == 1 || counter == 2)
				shipLength = 60;
			    else if (counter == 3)
				shipLength = 40;
			    else
				shipLength = 100;
			    horizontal = shipLength;
			    if (counter < 5)
				vertical [counter] = 20;
			    x1 = 40;
			    y1 = 150;
			}
		    }
		    else
		    {
			if (vertical [counter] == 20)
			{
			    vertical [counter] = shipLength;
			    horizontal = 20;
			}
			else
			{
			    vertical [counter] = 20;
			    horizontal = shipLength;
			}
			x1 = 40;
			y1 = 150;
		    }
		}
	    }
	}
	if (helper == true && temp != 'm' && temp != 'M')
	    newMessage ("Computer deploying ships...");
	counter = 0;
	whichShip = 0;
	do
	{
	    vertical [0] = (int) (Math.random () * ((1) + 1));
	    if (vertical [0] == 1)
		incrament = 10;
	    else
		incrament = 1;
	    x1 = (int) (Math.random () * (max + 1)) + ((int) (Math.random () * (max + 1)) * 10);
	    for (int i = whichShip ; i < whichShip + 10 - max ; i++)
	    {
		compShipLoc [i] = x1;
		x1 += incrament;
	    }
	    for (int k = 0 ; k < whichShip ; k++)
	    {
		for (int i = whichShip ; i < whichShip + 10 - max ; i++)
		{
		    if (compShipLoc [k] == compShipLoc [i])
			counter = -1;
		}
	    }
	    counter++;
	    if (counter == 0)
	    {
		whichShip = 0;
		max = 5;
	    }
	    if (counter == 1)
	    {
		whichShip = 5;
		max = 6;
	    }
	    else if (counter == 2 || counter == 3)
	    {
		max = 7;
		if (counter == 2)
		    whichShip = 9;
		else
		    whichShip = 12;
	    }
	    else
	    {
		whichShip = 15;
		max = 8;
	    }
	}
	while (counter <= 4);
	if (helper == true && temp != 'm' && temp != 'M')
	    newMessage ("All ships deployed.");
	counter = 0;
	counter2 = 0;
	Font font = new Font ("Vrinda", 1, 28);
	String temp2 = "";
	c.setFont (font);
	c.setColor (Color.pink);
	c.drawString ("Your points: ", 246, 230);
	max = 0;
	tempShipLength = 0;
	for (int i = 0 ; i < SHIPS ; i++)
	    shipSunkCounter [i] = 0;
	game:
	{
	    numHighScores = 0;
	    while (true)
	    {
		if (temp == 'm' || temp == 'M')
		    break game;
		hit = true;
		x1 = 400;
		y1 = 150;
		do
		{
		    c.setFont (font);
		    c.setColor (Color.black);
		    c.drawString (temp2, 250, 260);
		    temp2 = "" + playerScore;
		    c.setColor (Color.red);
		    c.drawString (temp2, 250, 260);
		    if (counter > 99 || numberOfCompShipsHit == 17)
		    {
			if (counter > 99)
			    winner = "Tie!";
			else
			    winner = "You win!";
			break game;
		    }
		    c.setColor (Color.yellow);
		    c.fillRect (x1, y1, 20, 20);
		    temp = c.getChar ();
		    c.setColor (Color.blue);
		    c.fillRect (x1, y1, 20, 20);
		    c.setColor (Color.black);
		    c.drawRect (x1, y1, 20, 20);
		    for (int v = 0 ; v < counter ; v++)
		    {
			c.setColor (Color.cyan);
			for (int q = 0 ; q < numberOfCompShipsHit ; q++)
			{
			    if (v == compHitShips [q])
				c.setColor (Color.red);
			}
			c.fillRect ((playerAttacks [v] % 10) * 20 + 400, (playerAttacks [v] - playerAttacks [v] % 10) * 2 + 150, 20, 20);
		    }
		    if ((temp == 'd' || temp == 'D') && x1 < 580)
			x1 += 20;
		    else if ((temp == 'w' || temp == 'W') && y1 >= 170)
			y1 -= 20;
		    else if ((temp == 'a' || temp == 'A') && x1 >= 420)
			x1 -= 20;
		    else if ((temp == 's' || temp == 'S') && y1 < 330)
			y1 += 20;
		    else
		    {
			attack:
			{

			    if (temp == 'x' || temp == 'X')
			    {
				playerAttacks [counter] = (x1 - 400) / 20 + ((y1 - 150) / 20) * 10;
				int i = 0;
				while (true)
				{
				    while (playerAttacks [counter] != playerAttacks [i] && i < counter)
				    {
					i++;
				    }
				    if (i == counter)
					break;
				    else
				    {
					if (helper == true)
					    newMessage ("You already attacked there!");
					break attack;
				    }
				}
				playerScore -= 2;
				i = 0;
				while (i < SHIPLOCATIONS)
				{
				    if (compShipLoc [i] != playerAttacks [counter])
				    {
					c.setColor (Color.cyan);
					hit = false;
				    }
				    else
				    {
					c.setColor (Color.red);
					compHitShips [numberOfCompShipsHit] = counter;
					numberOfCompShipsHit++;
					hit = true;
					playerScore += 12;
					break;
				    }
				    i++;
				}
				c.fillRect (x1, y1, 20, 20);
				x1 = 400;
				y1 = 150;
				counter++;
			    }

			    for (int i = 0 ; i < SHIPS ; i++)
				vertical [i] = 0;
			    for (int y = 0 ; y < SHIPS ; y++)
			    {
				if (y == 0)
				{
				    whichShip = 0;
				    max = 5;
				}
				else if (y == 1)
				{
				    whichShip = 5;
				    max = 4;
				}
				else if (y == 2 || y == 3)
				{
				    max = 3;
				    if (y == 2)
					whichShip = 9;
				    else
					whichShip = 12;
				}
				else
				{
				    whichShip = 15;
				    max = 2;
				}
				for (int i = whichShip ; i < whichShip + max ; i++)
				{
				    for (int x = 0 ; x <= counter ; x++)
				    {
					if (playerAttacks [x] == compShipLoc [i])
					    vertical [y]++;
				    }
				}
			    }
			    if (vertical [0] == 5 && shipSunkCounter [0] == 0)
			    {
				newMessage ("Aircraft Carrier sunk!");
				shipSunkCounter [0]++;
			    }
			    else if (vertical [1] == 4 && shipSunkCounter [1] == 0)
			    {
				newMessage ("Battleship sunk!");
				shipSunkCounter [1]++;
			    }
			    else if (vertical [2] == 3 && shipSunkCounter [2] == 0)
			    {
				newMessage ("Submarine sunk!");
				shipSunkCounter [2]++;
			    }
			    else if (vertical [3] == 3 && shipSunkCounter [3] == 0)
			    {
				newMessage ("Destroyer sunk!");
				shipSunkCounter [3]++;
			    }
			    else
			    {
				if (vertical [4] == 2 && shipSunkCounter [4] == 0)
				{
				    newMessage ("Patrol Boat  sunk!");
				    shipSunkCounter [4]++;
				}
			    }
			}
		    }
		}
		while (temp != 'm' && temp != 'M' && hit)
		    ;
		hit = true;
		while (hit)
		{
		    c.setFont (font);
		    c.setColor (Color.black);
		    c.drawString (temp2, 250, 260);
		    temp2 = "" + playerScore;
		    c.setColor (Color.red);
		    c.drawString (temp2, 250, 260);
		    int i = 0;
		    while (true)
		    {
			compAttacks [counter2] = computerAttack ();
			while (compAttacks [counter2] != compAttacks [i] && i < counter2)
			{
			    i++;
			}
			if (i == counter2)
			    break;
			i = 0;
		    }
		    if (playerShipLoc [0] == compAttacks [counter2] || playerShipLoc [1] == compAttacks [counter2] || playerShipLoc [2] == compAttacks [counter2] || playerShipLoc [3] == compAttacks [counter2] || playerShipLoc [4] == compAttacks [counter2] || playerShipLoc [5] == compAttacks [counter2] || playerShipLoc [6] == compAttacks [counter2] || playerShipLoc [7] == compAttacks [counter2] || playerShipLoc [8] == compAttacks [counter2] || playerShipLoc [9] == compAttacks [counter2]
			    || playerShipLoc [10] == compAttacks [counter2] || playerShipLoc [11] == compAttacks [counter2] || playerShipLoc [12] == compAttacks [counter2] ||
			    playerShipLoc [13] == compAttacks [counter2] || playerShipLoc [14] == compAttacks [counter2] || playerShipLoc [15] == compAttacks [counter2] || playerShipLoc [16] == compAttacks [counter2])
		    {
			hit = true;
			playerScore -= 5;
			playerHitShips [numberOfPlayerShipsHit] = counter2;
			numberOfPlayerShipsHit++;
			c.setColor (Color.red);
			try
			{
			    Thread.sleep (500);
			}
			catch (Exception e)
			{
			}
		    }
		    else
		    {
			playerScore++;
			hit = false;
			c.setColor (Color.cyan);
		    }
		    c.fillRect ((compAttacks [counter2] % 10) * 20 + 40, (compAttacks [counter2] - (compAttacks [counter2] % 10)) * 2 + 150, 20, 20);
		    counter2++;
		    if (counter2 > 99 || numberOfPlayerShipsHit == 17)
		    {
			if (counter2 > 99)
			    winner = "Tie!";
			else
			    winner = "Computer wins!";
			break game;
		    }

		}
	    }
	}
	if (temp != 'm' && temp != 'M')
	{
	    newMessage ("Game Over!" + winner);
	    highScores ();

	}
    }



    private int computerAttack ()
    {
	return (int) (Math.random () * (99 + 1));
    }


    public void goodBye ()
    {
	title ();
	c.setCursor (8, 1);
	c.println ("Game created by: Jinansh Shah");
	c.println ();
	c.println ("Thank you for using this game. Hope you had fun!");
	c.println ("Bye!");
	pauseProgram ();
	c.close ();
    }


    public Battleship ()
    {
	c = new Console ("Battleship");
    }


    public static void main (String[] args)
    {
	Battleship b = new Battleship ();
	b.intro ();
	while (true)
	{
	    b.mainMenu ();
	    if (b.choice == '5')
		break;
	    else if (b.choice == '4')
	    {
		if (b.helper == true)
		    b.helper = false;
		else
		    b.helper = true;
	    }
	    else if (b.choice == '3')
		b.highScores ();
	    else if (b.choice == '2')
		b.instructions ();
	    else
		b.game ();
	}


	b.goodBye ();
    }
}


