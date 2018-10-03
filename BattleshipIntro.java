/*

*/

import java.awt.*;
import hsa.Console;
import java.lang.*;

public class BattleshipIntro extends Thread
{
    private Console c;

    public void intro ()
    {
	c.setFont (new Font ("Britannic Bold", 1, 128));
	for (int x = 0 ; x <= 620 ; x++)
	{
	    c.setColor (Color.black);
	    c.fillRect (-620 + x, 0, 640, 500);
	    c.setColor (Color.yellow);
	    c.drawString ("Battleship", -580 + x, 100);
	    try
	    {
		Thread.sleep (5);
	    }
	    catch (Exception e)
	    {
	    }
	}
	c.setColor (Color.gray);
	for (int x = 0 ; x <= 6 ; x++)
	{
	    c.drawLine (0, 120 + x, 640, 120 + x);
	    try
	    {
		Thread.sleep (50);
	    }
	    catch (Exception e)
	    {
	    }
	}
	for (int x = -255 ; x <= 290 ; x++)
	{
	    c.setColor (Color.black);
	    c.fillRect (x - 1, 264, 252, 72);
	    c.setColor (Color.gray);
	    c.fillArc (x, 265, 250, 70, 180, 180);
	    c.fillArc (x, 295, 250, 15, 0, 180);
	    c.setColor (Color.black);
	    c.drawOval (x, 285, 250, 30);
	    c.setColor (Color.gray);
	    c.fillRect (x + 55, 265, 35, 35);
	    c.fillRect (x + 120, 280, 28, 15);
	    try
	    {
		Thread.sleep (10);
	    }
	    catch (Exception e)
	    {
	    }
	}
	try
	{
	    Thread.sleep (2000);
	}
	catch (Exception e)
	{
	}
	for (int x = 0 ; x < 95 ; x++)
	{
	    c.setColor (Color.black);
	    c.fillOval (638 - x * 2, 178 + x, 25, 25);
	    c.setColor (Color.orange);
	    c.fillOval (640 - x * 2, 180 + x, 20, 20);
	    try
	    {
		Thread.sleep (10);
	    }
	    catch (Exception e)
	    {
	    }
	}
	c.setColor (Color.yellow);
	for (int x = 0 ; x <= 90 ; x++)
	{
	    c.fillArc (-180, -250, 1000, 1000, 0, x);
	    c.fillArc (-180, -250, 1000, 1000, 90, x);
	    c.fillArc (-180, -250, 1000, 1000, 180, x);
	    c.fillArc (-180, -250, 1000, 1000, 270, x);
	    try
	    {
		Thread.sleep (2);
	    }
	    catch (Exception e)
	    {
	    }
	}
    }


    public BattleshipIntro (Console con)
    {
	c = con;
    }


    public void run ()
    {
	intro ();
    }
}


