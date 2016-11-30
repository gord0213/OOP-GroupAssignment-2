package Domain;

import SQL.*;

import java.util.*;

import SQL.CreateException;
import SQL.FinderException;
import SQL.NoSuchEntityException;

/**
 * Composistions class
 * Demonstrates the use of the provided DAO framework.*/
public class Composistions {
	
	/* ATTRIBUTES	--------------------------------------------------	*/
	private static final boolean _debug = false;

	/** Class name for static method purposes.								*/
	private static String className = "Domain.Composistions";
	
	/** Persistence model for a Conductor object.								*/
	private ComposistionModel model;
	

	/* STATIC PRE-OBJECT BEHAVIOR	-----------------------------------	*/
	/*	Basic Creators, finders, and removers									*/
	/* CREATORS	-----------------------------------------------------	*/
	/**
	 *	Create an instance of a new Composistion.
	 *	@return	An instance of a Composistion entity.
	 *	@throws sql.CreateException 
	 * @param	number	The Composistion number.
	 *	@param	name		The name of the Composistion. 
	 *	@param	address	The address for this Composistion.
	 *	@param	phoneno	The phone number for this Composistion.
	 */
	public static Composistions create(String number,
											String name,
											String address,
											String phoneno)
								throws CreateException				{
		if (_debug) System.out.println("C:create:" + number);

		ComposistionModel model = new ComposistionModel(number, name);
		ComposistionsDAO dao = null;
		try	{
			dao = (ComposistionsDAO) DAOFactory.getDAO(className);
			dao.dbInsert(model);
			
			/* Initially this Composistion has no boats or leases			*/

		} catch (Exception sqlex)	{
			throw new CreateException(sqlex.getMessage());
		}

		return	new Composistions(model);
	}//end create method
	
	
	/* CONSTRUCTORS	-----------------------------------------------	*/
	/**
	 *	Default constructor
	 */
	private Composistions()	{ super();		}

	/**
	 *	Parameterized constructor.
	 *	@param	name
	 *	@param	address
	 *	@param	phoneno
	 */
	private Composistions(String number, String name, String address, String phoneno)	{
		this(new ComposistionModel(number, name));
	}

	/**
	 *	Parameterized constructor.
	 *	@param	model	The persistence model for a Conductor object.
	 */
	private Composistions(ComposistionModel model)	{
		setModel(model);

		/*	initially no Boat and no leases, but we do have empty collections		*/
		setListOfBoats(new ArrayList<Boat>());
		//setListOfLeases(new ArrayList<Lease>());
	}
	
}//end Composistions class
