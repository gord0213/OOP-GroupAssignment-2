import java.util.Collection;

import Domain.*;
import SQL.DAOFactory;

public class test {

	
	
	
	
	public static void main(String args[])
	{
		
		
		
		
		try{
			
			
		Collection<Composer> temp = Composer.findAll();
		//System.out.println(temp.size() );
		//System.out.println(temp.toString());

		/*Collection<Composistions> compositions = Composistions.findAll(); 
		System.out.println("FUCK ME");
		
		System.out.println(compositions.size());	
		System.out.println(compositions.toString());*/

		try{
			Composistions comps ;
			ComposistionsDAO dao ;
			dao = (ComposistionsDAO) DAOFactory.getDAO("Domain.Composistions");

			
			
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		//Collection<Movements> move = Movements.findAll();
		
		
		
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		
	
	}// end of main
}// end of class
