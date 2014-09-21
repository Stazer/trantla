package net.nullxstazer.trantla ;

import java.io.IOException ;
import java.net.URL ;
import org.jsoup.Jsoup ;
import org.jsoup.nodes.Document ;

public class Worker extends Thread
{
	private Application application = null ;
	private boolean running = true ;
	
	Worker ( Application application )
	{
		this.application = application ;
	}
	
	public Application getApplication ( )
	{
		return this.application ;
	}

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
	
	@Override
	public void run ( )
	{
		while ( this.running && this.application.isRunning ( ) )
		{
			try
			{
				if ( ! this.application.isPaused ( ) )
				{
					String urlString = this.application.getLinkManager ( ).getNextUnvisitedLink ( ) ;
					
					if ( urlString != null )
					{					
						Document document = Jsoup.parse ( new URL ( urlString ) , 10000 ) ;
						
						this.application.getModule ( ).handleDocument ( document ) ;
					}
				}
			}
			catch ( IOException exception )
			{
				System.out.println ( this.getName ( ) + ": " + exception.getMessage ( ) ) ;
			}
			catch ( NullPointerException exception )
			{
			}
		}
	}
}