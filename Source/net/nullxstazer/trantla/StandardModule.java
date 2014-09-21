package net.nullxstazer.trantla ;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException ;
import java.net.URL ;
import org.jsoup.nodes.Document ;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class StandardModule extends Module
{
	private FileWriter file = null ;

	public StandardModule ( Application application )
	{
		super ( application ) ;		
		
		try
		{
			File file = new File ( "StandardModule.txt" ) ;
		
			if ( file.exists ( ) )
				file.createNewFile ( ) ;
			
			this.file  = new FileWriter ( "StandardModule.txt" ) ;
		}
		catch ( IOException exception )
		{
			exception.printStackTrace ( ) ;
		}
	}

	@Override
	public void handleDocument ( Document document ) throws IOException , NullPointerException
	{
		Elements elements = document.select ( "a" ) ;
		
		for ( Element element : elements )
		{
			String newLink = element.attr ( "href" ) ;
					
			if ( newLink.startsWith ( "#" ) )
				continue ;
			else if ( ! newLink.toLowerCase ( ).startsWith ( "http" ) )
				newLink = "http://" + ( new URL ( document.baseUri ( ) ) ).getHost ( ) + newLink ;
			
			if ( this.getApplication ( ).getLinkManager ( ).applyLink ( newLink ) )
			{
				this.file.append ( newLink + System.lineSeparator ( ) ) ;
				this.file.flush ( ) ;
			}
		}
	}

	@Override
	public void onCommand ( String command )
	{
	}
}