package net.nullxstazer.trantla;

import java.io.IOException;
import org.jsoup.nodes.Document ;

public abstract class Module
{
	private Application application = new Application ( ) ;
	
	public Module ( Application application )
	{
		this.application = application ;
	}

	public Application getApplication ( )
	{
		return this.application ;
	}
	
	public abstract void handleDocument ( Document document ) throws IOException , NullPointerException ;
	public abstract void onCommand ( String command ) ;
}