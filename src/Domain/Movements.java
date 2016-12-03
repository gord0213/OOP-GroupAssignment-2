package Domain;

import SQL.*;

import java.util.*;

import SQL.CreateException;
import SQL.FinderException;
import SQL.NoSuchEntityException;

/**
 * movements class
 * Demonstrates the use of the provided DAO framework.
 * 	includes basic movements attributes plus
 *			Boat reference attribute and accessors
 *			and Lease reference attribute and accessors
 */
public class Movements		{
	/* STATIC PRE-OBJECT BEHAVIOR	-----------------------------------	*/
	/*	Basic Creators, finders, and removers									*/
	/* CREATORS	-----------------------------------------------------	*/
	/**
	 *	Create an instance of a new movements.
	 *	@return	An instance of a movements entity.
	 *	@throws sql.CreateException 
	 * 	@param	composer the composer for the composition.
	 * 	@param	composition	the composition this movement is part of
	 *	@param	number	The movement's number.
	 *	@param	name	The movement's name.
	 */
	public static Movements create(String composer, String composition, String number,
											String name)
								throws CreateException				{
		if (_debug) System.out.println("C:create:" + composer + ", " + composition + ", " + number + ", " + name);

		MovementsModel model = new MovementsModel(composer, composition, number, name);
		MovementsDAO dao = null;
		try	{
			dao = (MovementsDAO) DAOFactory.getDAO(className);
			dao.dbInsert(model);
			
			/* Initially this movements has no boats or leases			*/

		} catch (Exception sqlex)	{
			throw new CreateException(sqlex.getMessage());
		}

		return	new Movements(model);
	}
	
	/* FINDERS	-----------------------------------------------------	*/
	/*	Finder methods are used to search for a particular instance
	 *	or a collection of instances, therefore finders either return
	 *	and instance to the entity, or a collection of instances.
	 */
	/**
	 *	Find a movements by primary key.
	 *	@return	An instance of a movements entity.
	 *	@throws sql.FinderException 
	 * @throws sql.NoSuchEntityException 
	 * @param	primarykey	The primary key for the movements to find.
	 */
	public static Movements findByPrimarykey(MovementsPK primarykey)
								throws FinderException, NoSuchEntityException			{
		if (_debug) System.out.println("C:findByPrimarykey(" + primarykey + ")");

		MovementsModel model = null;
		Movements movements = null;
		MovementsDAO dao = null;
		try	{
			dao = (MovementsDAO) DAOFactory.getDAO(className);
			model = (MovementsModel) dao.dbSelectByPrimaryKey(primarykey);
			movements = new Movements(model);

	


			

			
			
		} catch (Exception sqlex)	{
			throw new FinderException(sqlex.getMessage());
		}

		return movements;
	}

	/**
	 *	Find all movements entities.
	 *	@return	A collection of movements instances.
	 *	@throws	FinderException
	 * @throws	CreateException
	 */
	public static Collection<Movements> findAll() throws FinderException, CreateException			{
		ArrayList<Movements> listOfCustomers = new ArrayList<Movements>();
		MovementsDAO dao = null;
	
		try	{
			dao = (MovementsDAO) DAOFactory.getDAO(className);
			Collection<MovementsPK> c = dao.dbSelectAll();
			Iterator<MovementsPK> itr = c.iterator();
			while (itr.hasNext())	{
				MovementsPK cpk = itr.next();
				try	{
					Movements movements = Movements.findByPrimarykey(cpk);
//					/* Add boat references for this movements.				*/
//					movements.setBoats(((ArrayList<Boat>) Boat.findByCustomer(movements)));

//					/* Add leases for this movements							*/
					
					
					/* Add this movements to the list.						*/
					listOfCustomers.add(movements);

				} catch (Exception ex)	{
					System.err.println("Movements: Error processing list <" + ex.toString());
				}
			}

		} catch (Exception sqlex)	{
			throw new CreateException(sqlex.getMessage());
		}

		
		return listOfCustomers;
	}
	
	
	/* REMOVERS	-----------------------------------------------------	*/
	/**
	 *	Remove a movements by primary key.
	 *	@param	primarykey	The primary key for the movements to find.
	 *	@throws	NoSuchEntiryException
	 * @throws	DAOSysException
	 */
	private static int removeByPrimarykey(MovementsPK primarykey)
								throws	DAOSysException, NoSuchEntityException	{
		int rc = 0;
		MovementsDAO dao = null;
		
		/*	remove boats first etc...				*/
		
		dao = (MovementsDAO) DAOFactory.getDAO(className);
		rc = dao.dbRemove(primarykey);
		
		return rc;
	}

	
	/* CONSTRUCTORS	-----------------------------------------------	*/
	/**
	 *	Default constructor
	 */
	private Movements()	{ super();		}

	/**
	 *	Parameterized constructor.
	 *	@param	name

	 */
	private Movements(String composer, String composition, String number, String name)	{
		this(new MovementsModel(composer, composition, number, name));
	}

	/**
	 *	Parameterized constructor.
	 *	@param	model	The persistence model for a movements object.
	 */
	private Movements(MovementsModel model)	{
		setModel(model);


	}


	/* ACCESSORS	--------------------------------------------------	*/
	public MovementsModel getModel()				{ return model;}
	public MovementsPK getPrimaryKey()				{ return getModel().getPrimarykey();}
	public String getComposer()						{ return getModel().getPrimarykey().getComposer();}
 	public String getComposition()					{ return getModel().getComposition();}
	public String getNumber()						{ return getModel().getPrimarykey().getNumber();}
 	public String getName()							{ return getModel().getName();}
					



	
	/* MODIFIERS	--------------------------------------------------	*/
	private void setModel(MovementsModel model)	{ this.model = model;								}







	/* BEHAVIOR	-----------------------------------------------------	*/
	/**
	 *	Implementation of the "object" equals method.  Customers objects are equal
	 *	if their primary key's are equal.
	 *	@return	True if the fields of this primary key object equal the
	 *	contents of the fields from the passed primary key object, otherwise
	 *	false, they are not equal.
	 */
	public boolean equals(Object obj)	{
		return	obj instanceof Movements
			&&	(getNumber().equals(((Movements) obj).getNumber())
			);
	}

	/**
	 *	Implementation of the "object"hashCode()" method.
	 * Whenever it is invoked on the same object more than once during
	 * an execution of a Java application, the hashCode method
	 * must consistently return the same integer, provided no information
	 * used in equals comparisons on the object is modified.
	 *	@return	A hash code value for the object.
	 */
	public int hashCode() {
		return	getNumber().concat(
								getName()
								
							).hashCode();
	}

	/**
	 *	Flush cached attribute values to the datastore.
	 *	Catch and report any errors.
	 */
	public void update()	{
		if (_debug) System.out.println("C:update()");
		try	{
			store();
		} catch (Exception ex)	{
			System.out.println("C: Error in update(), <" + ex.toString() + ">");
		}
	}

	
	public String toString()	{ return this.toString(", ");				}
	public String toString(String sep)	{
		return "composer=" + getComposer()
				+ sep + "composition" + getComposition()
				+ sep + "number=" + getNumber()
				+ sep + "name=" + getName();
	}



	
	

	/**
	 *	Remove a movements from the data store (by primary key).
	 * @return 
	 * @throws sql.NoSuchEntityException
	 * @throws sql.DAOSysException 
	 */
	public Movements remove()	throws NoSuchEntityException, DAOSysException	{
		Movements c = null;
		if (removeByPrimarykey(getPrimaryKey()) > 0)	{
			c = this;
		}

		return c;
	}

	/**
	 * Invoke this method to refresh the cached attribute values
	 * from the database.
	 */
	private void load() throws DAOSysException		{
		if (_debug) System.out.println("C:load()");
		MovementsDAO dao = null;
		try	{
			dao = (MovementsDAO) DAOFactory.getDAO(className);
			setModel((MovementsModel)dao.dbLoad(getPrimaryKey()));

		} catch (Exception ex)	{
			throw new DAOSysException(ex.getMessage());
		}
	}


	/**
	 * Invoke this method to save the cached attribute values to the datastore.
	 */
	private void store()	throws DAOSysException		{
		if (_debug) System.out.println("C:store()");
		MovementsDAO dao = null;
		try	{
			dao = (MovementsDAO) DAOFactory.getDAO(className);
			dao.dbStore(getModel());
		} catch (Exception ex)	{
			throw new DAOSysException(ex.getMessage());
		}
	}



	/* ATTRIBUTES	--------------------------------------------------	*/
	private static final boolean _debug = false;

	/** Class name for static method purposes.								*/
	private static String className = "Domain.Movements";
	
	/** Persistence model for a movements object.								*/
	private MovementsModel model;

	


}