package net.nullxstazer.trantla ;

import java.io.IOException ;
import java.util.ArrayList ;

public class Application
{
	private CommandManager commandManager = new CommandManager ( this ) ;
	private LinkManager linkManager = new LinkManager ( this ) ;
	private ArrayList <Worker> worker = new ArrayList <Worker> ( ) ;
	private boolean running = true ;
	private boolean paused = false ;
	private Module module = null ;
	
	public void setRunning ( boolean running )
	{
		this.running = running ;
	}
	public boolean getRunning ( )
	{
		return this.running ;
	}
	public boolean isRunning ( )
	{
		return this.running ;
	}
	
	public void setPaused ( boolean paused )
	{
		this.paused = paused ;
	}
	public boolean getPaused ( )
	{
		return this.paused ;
	}
	public boolean isPaused ( )
	{
		return this.paused ;
	}
	
	public void setModule ( Module module )
	{
		this.module = module ;
	}
	public synchronized Module getModule ( )
	{
		return this.module ;
	}
	
	public synchronized LinkManager getLinkManager ( )
	{
		return this.linkManager ;
	}
	
	public ArrayList <Worker> getWorker ( )
	{
		return worker;
	}
	
	public void main ( String [ ] arguments ) throws IOException
	{
		System.out.println ( "System: Trantla Multi Threaded Web Crawler" ) ;
		
		this.module = new StandardModule ( this ) ;
		
		this.commandManager.main ( ) ;
		
		System.out.println ( "System: Good bye" ) ;
	}
}