package devops.training.draw;

import java.util.ArrayList;
import java.util.Arrays;

public class canvas {
    int width;
    int height;
    ArrayList<String> myCanvas;
    
    public canvas()
    {
    	this.width = 0;
    	this.height = 0;
    	myCanvas = new ArrayList<String>();
    }
    
    public void drawCanvas(int w, int h)
    {
    	/* draw new canvas */
    	if(w > 0 && h > 0)
    	{
    		myCanvas.clear();
        	this.width = w;
        	this.height = h;
    		drawCanvasWithCharacter('-', '|');
    	}
    	else
    	{
    		System.out.println("Invalid width or height of canvas");
    	}
    }
    
    public void printCanvas()
    {
    	System.out.println("\n");
        for (int i = 0; i < myCanvas.size(); i++)
        {
            System.out.println(myCanvas.get(i));
        }
    }
    
    public void drawSubLine(int x1, int y1, int x2, int y2)
    {
    	if(isCanvasExist() == false)
    	{
    		System.out.println("Canvas is not created. Please create new canvas first");
    		return;
    	}
    	/* check if all positions are valid, just accept a horizontal or vertical line*/
    	if(isInCanvas(x1, y1) && isInCanvas(x2, y2) && isHorizonOrVerticalLine(x1, y1, x2, y2))
    	{
    		/*draw here*/
    		drawLine(x1, y1, x2, y2);
    	}
    	else
    	{
    		System.out.println("Invalid positions for horizontal/vertical line");
    		return;
    	}
    }
    
    public void drawSubRectangle(int x1, int y1, int x2, int y2)
    {
    	if(isCanvasExist() == false)
    	{
    		System.out.println("Canvas is not created. Please create new canvas first");
    		return;
    	}
    	/* check if all positions are valid */
    	if(isInCanvas(x1, y1) && isInCanvas(x2, y2) && isHorizonOrVerticalLine(x1, y1, x2, y2) == false && isDuplicatePos(x1, y1, x2, y2) == false && x1 < x2)
    	{
    		drawRectangle(x1, y1, x2, y2);
    	}
    	else
    	{
    		System.out.println("Invalid positions for rectangle");
    		return;
    	}
    }
    
    public void drawFillBucket(int x, int y, char colour)
    {
    	if(isCanvasExist() == false)
    	{
    		System.out.println("Canvas is not created. Please create new canvas first");
    		return;
    	}
    	if(isInCanvas(x, y))
    	{
    	    /* check current index */
    		char prev = myCanvas.get(y).charAt(x);
    		if(prev == colour) return;
    		bucketFill(x, y, colour, prev);
    	}
    	else
    	{
    		System.out.println("Invalid positions for bucket fill");
    		return;
    	}
    }
    
    private boolean isCanvasExist()
    {
    	if(myCanvas.size() > 0 ) return true;
    	return false;
    }
    private boolean isDuplicatePos(int x1, int y1, int x2, int y2)
    {
    	if(x1 == x2 && y1 == y2) return true;
    	return false;
    }
    
    private boolean isHorizonOrVerticalLine(int x1, int y1, int x2, int y2)
    {
    	if((x1 == x2 && y1 != y2) || (y1 == y2 && x1 != x2)) return true;
    	return false;
    }
    
    private boolean isInCanvas(int x, int y)
    {
    	if((x > 0 && x <= this.width) && (y > 0 && y <= this.height)) return true;
    	return false;
    }
    
    private void drawCanvasWithCharacter(char hor_char, char ver_char)
    {
    	for(int i = 0; i < (this.height + 2); i++)
    	{
    		char[] tmp;
    		String line;
    		/* First or Last line */
    		if(i == 0 || i == (this.height + 1))
    		{
    		    tmp = new char[this.width + 2];
    		    Arrays.fill(tmp, hor_char);
    		    line = new String(tmp);
    		}
    		else
    		{
    			tmp = new char[this.width];
    			Arrays.fill(tmp, ' ');
    			line = String.valueOf(ver_char) + new String(tmp) + String.valueOf(ver_char);
    		}
    		myCanvas.add(line);
    	}
    }
    
    private void drawLine(int x1, int y1, int x2, int y2)
    {
    	int first;
    	int second;
    	/* horizontal line */
    	if(y1 == y2)
    	{
        	String pos = myCanvas.get(y1);
    		if(x1 < x2)
    		{
    			first = x1;
    			second = x2;
    	    }
    	    else
    	    {
    			first = x2;
    			second = x1;
    	    }
    		char[] subStr = new char[(second - first + 1)];
    		Arrays.fill(subStr, 'x');
    		String new_string = pos.substring(0, first) + new String(subStr) + pos.substring(second + 1);
    		myCanvas.set(y1, new_string);
    	}
    	else /* vertical line*/
    	{
    		if(y1 < y2)
    		{
    			first = y1;
    			second = y2;
    		}
    		else
    		{
    			first = y2;
    			second = y1;
    		}
    		for(int i = first; i <= second; i++ )
    		{
    			String pos = myCanvas.get(i);
    			pos = pos.substring(0, x1) + 'x' + pos.substring(x1+1);
    			myCanvas.set(i, pos);
    		}
    	}
    }

    private void drawRectangle(int x1, int y1, int x2, int y2)
    {
        drawLine(x1, y1, x2, y1);
        drawLine(x1, y2, x2, y2);
        drawLine(x1, y1, x1, y2);
        drawLine(x2, y1, x2, y2);
    }
    
    private void bucketFill(int x, int y, char colour, char prev)
    {
    	if(isInCanvas(x, y) == false) return;
    	
        String current = myCanvas.get(y);
        if(current.charAt(x) != prev) return;
    	/* Replace new one */
        current = current.substring(0, x) + colour + current.substring(x + 1);
        myCanvas.set(y, current);
        
        bucketFill(x + 1, y, colour, prev);
        bucketFill(x - 1, y, colour, prev);
        bucketFill(x, y + 1, colour, prev);
        bucketFill(x, y - 1, colour, prev);
    }
}