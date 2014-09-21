package net.nullxstazer.trantla ;

import java.io.File;
import java.io.FileWriter ;
import java.io.IOException ;
import java.net.URL ;
import java.util.ArrayList ;
import org.jsoup.nodes.Document ;
import org.jsoup.nodes.Element ;
import org.jsoup.select.Elements ;

public class DomainModule extends Module
{
	private ArrayList <String> domains = new ArrayList <String> ( ) ;
	private FileWriter file = null ;

	public DomainModule ( Application application )
	{
		super ( application ) ;		
		
		try
		{
			File file = new File ( "DomainModule.txt" ) ;
		
			if ( file.exists ( ) )
				file.createNewFile ( ) ;
			
			this.file  = new FileWriter ( "DomainModule.txt" ) ;
		}
		catch ( IOException exception )
		{
			exception.printStackTrace ( ) ;
		}
	}
	
	private boolean existsDomain ( String domain )
	{
		for ( String domainArray : this.domains )
		{
			if ( domainArray.equals ( domain ) )
				return true ;
		}
		
		return false ;
	}

	@Override
	public void handleDocument ( Document document ) throws IOException , NullPointerException
	{
		if ( ! this.existsDomain ( document.baseUri ( ) ) )
		{
			this.domains.add ( document.baseUri ( ) ) ;

			this.file.append ( document.baseUri ( ) + System.lineSeparator ( ) ) ;
			this.file.flush ( ) ;
		}
		
		Elements elements = document.select ( "a" ) ;
		
		for ( Element element : elements )
		{
			String newLink = element.attr ( "href" ) ;
					
			if ( newLink.startsWith ( "#" ) )
				continue ;
			else if ( ! newLink.toLowerCase ( ).startsWith ( "http" ) )
				newLink = "http://" + ( new URL ( document.baseUri ( ) ) ).getHost ( ) + newLink ;
			
			this.getApplication ( ).getLinkManager ( ).applyLink ( newLink ) ;
		}
	}

	@Override
	public void onCommand ( String command )
	{
	}
}