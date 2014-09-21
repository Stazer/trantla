package net.nullxstazer.trantla ;

import java.util.Scanner ;

public class CommandManager
{
	private Application application = null ;
	
	public CommandManager ( Application application )
	{
		this.application = application ;
	}
	
	@SuppressWarnings ( "resource" )
	public void main ( )
	{
		while ( this.application.isRunning ( ) )
		{
			//System.out.print ( "System: " ) ;
			
			Scanner scanner = new Scanner ( System.in ) ;
			
			String [ ] commands = scanner.nextLine ( ).toLowerCase ( ).split ( " " ) ;
			
			String command = commands [ 0 ] ;
			
			if ( command.startsWith ( "quit" ) || command.startsWith ( "exit" ) )
				this.application.setRunning ( false ) ;
			else if ( command.startsWith ( "status" ) )
			{
				System.out.println ( "Paused \"" + this.application.getPaused ( ) + "\"" ) ;
				
				System.out.println ( this.application.getLinkManager ( ).getUnvisitedLinks ( ).size ( ) + " Unvisited Links" ) ;
				System.out.println ( this.application.getLinkManager ( ).getVisitedLinks ( ).size ( ) + " Visited Links" ) ;
				
				System.out.println ( this.application.getWorker ( ).size ( ) + " Worker" ) ;
				
				for ( Worker worker : this.application.getWorker ( ) )
				{
					System.out.println ( "Identification: " + worker.getId ( ) + ", Name: " + worker.getName ( ) ) ;
				}
			}
			else if ( command.startsWith ( "spawn" ) )
			{
				int length = 1 ;
				
				if ( commands.length > 1 )
					length = Integer.parseInt ( commands [ 1 ] ) ;
					
				for ( int element = 0 ; element < length ; ++element )
				{
					Worker newWorker = new Worker ( this.application ) ;
					
					newWorker.setName ( "Worker " + ( this.application.getWorker ( ).size ( ) + 1 ) ) ;
					newWorker.start ( ) ;
										
					this.application.getWorker ( ).add ( newWorker ) ;
				}
			}
			else if ( command.startsWith ( "pause" ) )
				this.application.setPaused ( true ) ;
			else if ( command.startsWith ( "continue" ) )
				this.application.setPaused ( false ) ;
			else if ( command.startsWith ( "add" ) )
				this.application.getLinkManager ( ).getUnvisitedLinks ( ).add ( commands [ 1 ] ) ;
			else if ( command.startsWith ( "load" ) )
			{
				if ( commands.length > 1 )
				{
					String command2 = commands [ 1 ].toLowerCase ( ) ;
					
					if ( command2.startsWith ( "standardmodule" ) )
						this.application.setModule ( new StandardModule ( this.application ) ) ;
					else if ( command2.startsWith ( "moviestreamto" ) )
						this.application.setModule ( new MovieStreamToModule ( this.application ) );
					else if ( command2.startsWith ( "domainmodule" ) )
						this.application.setModule ( new DomainModule ( this.application ) );
				}				
			}
			else if ( command.startsWith ( "list" ) )
			{
				boolean filter = false ;
				
				if ( commands.length > 1 )
					filter = true ;
					
				for ( String link : this.application.getLinkManager ( ).getVisitedLinks ( ) )
				{
					if ( filter && link.contains ( commands [ 1 ] ) )
						System.out.println ( link ) ;
					else if ( ! filter )
						System.out.println ( link ) ;
				}
			}
			else
				this.application.getModule ( ).onCommand ( command ) ;
		}
	}
}