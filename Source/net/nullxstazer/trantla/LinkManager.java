package net.nullxstazer.trantla ;

import java.util.ArrayList ;

public class LinkManager
{
	private Application application = null ;
	private ArrayList <String> unvisitedLinks = new ArrayList <String> ( ) ;
	private ArrayList <String> visitedLinks = new ArrayList <String> ( ) ;
	
	public LinkManager ( Application application )
	{
		this.application = application ;
	}
	
	public Application getApplication ( )
	{
		return this.application ;
	}
	
	public synchronized ArrayList <String> getUnvisitedLinks ( )
	{
		return this.unvisitedLinks ;
	}
	
	public synchronized ArrayList <String> getVisitedLinks ( )
	{
		return this.visitedLinks ;
	}
	
	private synchronized boolean existsUnvisitedLink ( String link )
	{
		for ( String element : this.unvisitedLinks )
		{
			if ( element.equals ( link ) )
				return true ;
		}
		
		return false ;
	}
	
	private synchronized boolean existsVisitedLink ( String link )
	{
		for ( String element : this.visitedLinks )
		{
			if ( element.equals ( link ) )
				return true ;
		}
		
		return false ;
	}
	
	public synchronized boolean applyLink ( String link )
	{
		if ( ! this.existsUnvisitedLink ( link ) && ! this.existsVisitedLink ( link ) )
		{
			this.unvisitedLinks.add ( link ) ;
			return true ;
		}
		
		return false ;
	}
	
	public synchronized String getNextUnvisitedLink ( )
	{
		if ( this.unvisitedLinks.isEmpty ( ) )
			return null ;
		
		String link = this.unvisitedLinks.get ( 0 ) ;
		
		this.unvisitedLinks.remove ( 0 ) ;
		
		if ( ! this.existsVisitedLink ( link ) )
			this.visitedLinks.add ( link ) ;
		
		return link ;
	}
}