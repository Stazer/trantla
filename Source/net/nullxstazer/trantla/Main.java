package net.nullxstazer.trantla ;

import java.io.IOException ;

public class Main
{
	public static void main ( String [ ] arguments )
	{
		try
		{
			Application Application = new Application ( ) ;
		
			Application.main ( arguments ) ;
		}
		catch ( IOException exception )
		{
			exception.printStackTrace ( ) ;
		}
	}
}