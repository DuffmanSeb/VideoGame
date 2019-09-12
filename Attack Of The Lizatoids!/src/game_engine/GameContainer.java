package game_engine;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class GameContainer implements Runnable 
{
	private Thread thread;
	private Window window;
	private Renderer renderer;
	private Input input;
	
	private boolean running = false;
	private final double UPDATE_CAP = 1.0/61.0;
	private int width = 1666, height = 971;
	private float scale = 1f;
	private String title = "Attack Of The Lizatoids! Alpha v0.10";
	
    public void Gamecontainer()
    {
    	
    }
    
    public void start()
    {
    	window = new Window(this);
    	renderer = new Renderer(this);
    	input = new Input(this);
    	
    	thread = new Thread(this);
    	thread.run();
    }
    
    public void stop()
    {
    	
    }
    
    public void run()
    { 
    	running = true;
    	
    	boolean render = false;
    	double firstTime = 0;
    	double lastTime = System.nanoTime() / 1000000000.0;
    	double passedTime = 0;
    	double unprocessedTime = 0; 
    	
    	double frameTime = 0;
    	int frames = 0;
    	int fps = 0;
    	
    	while(running)
    	{		
    		render = false;
    		
    		firstTime = System.nanoTime() / 1000000000.0;
    		passedTime = firstTime - lastTime;
    		lastTime = firstTime;
    		
    		unprocessedTime += passedTime;
    		frameTime += passedTime;
    		
    		while(unprocessedTime >= UPDATE_CAP)
    		{
    			unprocessedTime -= UPDATE_CAP;
    			render = true;
    			
    			//TODO: Update game
    			System.out.println("x1" + input.getMousex() + " y1" + input.getMousey());

    			input.update();
    			
    			if(frameTime >= 1.0)
    			{
    				frameTime = 0;
    				fps = frames;
    				frames = 0;
    				System.out.println("FPS: " + fps);
    			}
    		}
    		
    		if(render)
    		{
    			renderer.clear();
    			//TODO: Render game
    			window.update();
    			frames++;
    		}
    		else
    		{
    			try
    			{
    				Thread.sleep(1);
    			}
    		    catch (InterruptedException e)
    			{
    		    	e.printStackTrace();
    			}
    	    }
     }
    	
     dispose();
    }
    
    private void dispose()
    {
    	
    }
    
    public static void main(String args[])
    {
    	GameContainer gc = new GameContainer();
    	gc.start();
    }

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Window getWindow() {
		return window;
	}
}
