package net.nullxstazer.trantla;

import java.io.File ;
import java.io.FileWriter;
import java.io.IOException ;
import org.jsoup.nodes.Document ;
import org.jsoup.nodes.Element ;
import org.jsoup.select.Elements ;

public class MovieStreamToModule extends Module
{
	private FileWriter file = null ;
		
	public MovieStreamToModule ( Application application )
	{
		super ( application ) ;
		
		application.getLinkManager ( ).getUnvisitedLinks ( ).add ( "http://movie-stream.to/" ) ;
		
		try
		{
			File file = new File ( "MovieStreamToModule.txt" ) ;
		
			if ( file.exists ( ) )
				file.createNewFile ( ) ;
			
			this.file = new FileWriter ( "MovieStreamToModule.txt" ) ;
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
			
			if ( newLink.contains ( "movie-stream.to" ) )
				this.getApplication ( ).getLinkManager ( ).applyLink ( newLink ) ;
			else if ( newLink.contains ( "streamcloud.eu" ) )
			{
				if ( this.getApplication ( ).getLinkManager ( ).applyLink ( newLink ) )
				{
					Elements newElements = document.select ( "h2" ) ;
					
					this.file.append ( newElements.first ( ).text ( ) + "     " + newLink + System.lineSeparator ( ) ) ;
					this.file.flush ( ) ;
				}
			}
		}
	}

	@Override
	public void onCommand ( String command )
	{
	}
}