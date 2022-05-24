package devops.training.maven;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import devops.training.draw.*;

public class parseCmd {

	int width;    /* width of canvas */
	int height;   /* height of canvas */
	int x1;       /* x1 position */
	int y1;       /* y1 position */
	int x2;       /* x2 position */
	int y2;       /* y2 position */
	char c;       /* colour charcater */

	private boolean parseCparams(String arg)
	{
		Pattern tmp = Pattern.compile("\\s+(\\d+)\\s+(\\d+)");
		Matcher m_params = tmp.matcher(arg);
		if(m_params.matches())
		{
			this.width = Integer.parseInt(m_params.group(1));
			this.height = Integer.parseInt(m_params.group(2));
			return true;
		}
		else
		{
			return false;
		}
	}
	
	private boolean parseLorRparams(String arg)
	{
		Pattern tmp = Pattern.compile("\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)");
		Matcher m_params = tmp.matcher(arg);
		if(m_params.matches())
		{
			this.x1 = Integer.parseInt(m_params.group(1));
			this.y1 = Integer.parseInt(m_params.group(2));
			this.x2 = Integer.parseInt(m_params.group(3));
			this.y2 = Integer.parseInt(m_params.group(4));
			return true;
		}
		else
		{
			return false;
		}
	}
	
	private boolean parseBparams(String arg)
	{
		Pattern tmp = Pattern.compile("\\s+(\\d+)\\s+(\\d+)\\s+([a-zA-Z])");
		Matcher m_params = tmp.matcher(arg);
		if(m_params.matches())
		{
			this.x1 = Integer.parseInt(m_params.group(1));
			this.y1 = Integer.parseInt(m_params.group(2));
			this.c  = m_params.group(3).charAt(0);
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean handleCommand(String cmd, canvas myCanvas)
	{
		/* remove all space at end of string */
		cmd = cmd.replaceAll("\\s+$", "");
		if(cmd.compareToIgnoreCase("Q") == 0)
		{
			System.out.println("Exiting...");
			System.exit(0);
		}
		String pattern = "([a-zA-Z])(.*)";
		/* Create an pattern object */
		Pattern r = Pattern.compile(pattern);
		/* Create matcher object */
		Matcher m = r.matcher(cmd);
		if(m.matches())
		{
			char type = m.group(1).charAt(0);
			switch(type)
			{
		        case 'c':
			    case 'C':
			    	if(parseCparams(m.group(2)))
			    	{
			    		myCanvas.drawCanvas(this.width, this.height);
			    		myCanvas.printCanvas();
			    		return true;
			    	}
			    	break;
			    case 'l':
			    case 'L':
			    	if(parseLorRparams(m.group(2)))
			    	{
			    		myCanvas.drawSubLine(this.x1, this.y1, this.x2, this.y2);
			    		myCanvas.printCanvas();
			    		return true;
			    	}
                    break;
			    case 'r':
			    case 'R':
			    	if(parseLorRparams(m.group(2)))
			    	{
			    		myCanvas.drawSubRectangle(this.x1, this.y1, this.x2, this.y2);
			    		myCanvas.printCanvas();
			    		return true;
			    	}
                    break;
			    case 'b':
			    case 'B':
			    	if(parseBparams(m.group(2)))
			    	{
			    		myCanvas.drawFillBucket(this.x1, this.y1, this.c);
			    		myCanvas.printCanvas();
			    		return true;
			    	}
			    	break;
			    default:
			    	break;
			}
		}
	    System.out.println("Invalid command. Please try again...");
		return false;
	}
}
