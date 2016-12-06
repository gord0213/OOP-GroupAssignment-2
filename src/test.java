import java.util.Collection;

import Domain.*;
import SQL.DAOFactory;

public class test {

	
	
	
	
	public static void main(String args[])
	{
		
		
		
		
		try{
			
		Collection<Composer> temp = Composer.findAll();
		System.out.println(temp.size() );
		System.out.println(temp.toString());

		Collection<Composistions> compositions = Composistions.findAll(); 
		System.out.println(compositions.size());	
		System.out.println(compositions.toString());

		
		Collection<Movements> move = Movements.findAll();
		System.out.println(move.size());	
		System.out.println(move.toString());
		
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		
	
	}// end of main
}// end of class
