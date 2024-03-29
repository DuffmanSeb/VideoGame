package game_engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class Input implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener
{
	private GameContainer gc;
	
	private final int NUM_KEYS = 256;
	private boolean[] keys = new boolean[NUM_KEYS];
	private boolean[] keysLast = new boolean[NUM_KEYS];
	
	private final int NUM_BUTTONS = 5;
	private boolean[] buttons = new boolean[NUM_BUTTONS];
	private boolean[] buttonsLast = new boolean[NUM_BUTTONS];
	
	private int mousex, mousey;
	private int scroll;
	
	public Input(GameContainer gc)
	{
		this.gc = gc;
		mousex = 0;
		mousey = 0;
		scroll = 0;
		
		gc.getWindow().getCanvas().addKeyListener(this);
		gc.getWindow().getCanvas().addMouseMotionListener(this);
		gc.getWindow().getCanvas().addMouseListener(this);
		gc.getWindow().getCanvas().addMouseWheelListener(this);
	}
	
	public void update()
	{
		scroll = 0;
		
		for(int i = 0; i < NUM_KEYS; i++)
		{
			keysLast[i] = keys[i];
		}
		
		for(int i = 0; i < NUM_BUTTONS; i++)
		{
			buttonsLast[i] = keys[i];
		}
	}
	
	public boolean isKey(int keyCode)
	{
		return keys[keyCode];
	}
	
	public boolean isKeyup(int keyCode)
	{
		return !keys[keyCode] && keysLast[keyCode];
	}

	public boolean isKeydown(int keyCode)
	{
		return keys[keyCode] && !keysLast[keyCode];
	}
	
	public boolean isButton(int button)
	{
		return buttons[button];
	}
	
	public boolean isButtonUp(int button)
	{
		return !buttons[button] && buttonsLast[button];
	}

	public boolean isButtonDown(int button)
	{
		return buttons[button] && !buttonsLast[button];
	}


	@Override
	public void mouseWheelMoved(MouseWheelEvent e) 
	{
		scroll = e.getWheelRotation();
	}

	public int getMousex() {
		return mousex;
	}

	public int getMousey() {
		return mousey;
	}

	public int getScroll() {
		return scroll;
	}

	@Override
	public void mouseDragged(MouseEvent e) 
	{
		mousex = (int)(e.getX() / gc.getScale());
		mousey = (int)(e.getY() / gc.getScale());
	}

	@Override
	public void mouseMoved(MouseEvent e)
	{
		mousex = (int)(e.getX() / gc.getScale());
		mousey = (int)(e.getY() / gc.getScale());
	}

	@Override
	public void mouseClicked(MouseEvent e) 
	{

	}

	@Override
	public void mouseEntered(MouseEvent e) 
	{

	}

	@Override
	public void mouseExited(MouseEvent e) 
	{

	}

	@Override
	public void mousePressed(MouseEvent e) 
	{
		buttons[e.getButton()] = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) 
	{
		buttons[e.getButton()] = false;
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{
		keys[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) 
	{
		
	}
}
