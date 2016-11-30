/*
 *  @(#)Composor.java
 *
 *
 */

package Domain;

import SQL.*;

import java.util.*;

import SQL.CreateException;
import SQL.FinderException;
import SQL.NoSuchEntityException;

/**
 * Conductor class
 * Demonstrates the use of the provided DAO framework.
 * 	includes basic Conductor attributes plus
 *			Boat reference attribute and accessors
 *			and Lease reference attribute and accessors
 */
public class Composer		{
	/* STATIC PRE-OBJECT BEHAVIOR	-----------------------------------	*/
	/*	Basic Creators, finders, and removers									*/
	/* CREATORS	-----------------------------------------------------	*/
	/**
	 *	Create an instance of a new Conductor.
	 *	@return	An instance of a Conductor entity.
	 *	@throws sql.CreateException 
	 * @param	number	The Conductor number.
	 *	@param	name		The name of the Conductor. 
	 *	@param	address	The address for this Conductor.
	 *	@param	phoneno	The phone number for this Conductor.
	 */
	public static Composer create(String ComposorName)
								throws CreateException				{
		if (_debug) System.out.println("C:create:" + ComposorName);

		Composer model = new ComposorModel(ComposorName);
		CDAO dao = null;
		try	{
			dao = (ConductorDAO) DAOFactory.getDAO(className);
			dao.dbInsert(model);
			
			/* Initially this Conductor has no boats or leases			*/

		} catch (Exception sqlex)	{
			throw new CreateException(sqlex.getMessage());
		}

		return	new Conductor(model);
	}
	
	/* FINDERS	-----------------------------------------------------	*/
	/*	Finder methods are used to search for a particular instance
	 *	or a collection of instances, therefore finders either return
	 *	and instance to the entity, or a collection of instances.
	 */
	/**
	 *	Find a Conductor by primary key.
	 *	@return	An instance of a Conductor entity.
	 *	@throws sql.FinderException 
	 * @throws sql.NoSuchEntityException 
	 * @param	primarykey	The primary key for the Conductor to find.
	 */
	public static Conductor findByPrimarykey(ConductorPK primarykey)
								throws FinderException, NoSuchEntityException			{
		if (_debug) System.out.println("C:findByPrimarykey(" + primarykey + ")");

		ConductorModel model = null;
		Conductor Conductor = null;
		ConductorDAO dao = null;
		try	{
			dao = (ConductorDAO) DAOFactory.getDAO(className);
			model = (ConductorModel) dao.dbSelectByPrimaryKey(primarykey);
			Conductor = new Conductor(model);

//			/* Add boat references for this Conductor.				*/
//			Conductor.setBoats( ((ArrayList<Boat>) Boat.findByConductor(Conductor)) );

			/*	Add lease references for this Conductor.			*/

			
			
		} catch (Exception sqlex)	{
			throw new FinderException(sqlex.getMessage());
		}

		return Conductor;
	}

	/**
	 *	Find all Conductor entities.
	 *	@return	A collection of Conductor instances.
	 *	@throws	FinderException
	 * @throws	CreateException
	 */
	public static Collection<Conductor> findAll() throws FinderException, CreateException			{
		ArrayList<Conductor> listOfConductors = new ArrayList<Conductor>();
		ConductorDAO dao = null;
	
		try	{
			dao = (ConductorDAO) DAOFactory.getDAO(className);
			Collection<ConductorPK> c = dao.dbSelectAll();
			Iterator<ConductorPK> itr = c.iterator();
			while (itr.hasNext())	{
				ConductorPK cpk = itr.next();
				try	{
					Conductor conductor = Conductor.findByPrimarykey(cpk);
//					/* Add boat references for this Conductor.				*/
//					Conductor.setBoats(((ArrayList<Boat>) Boat.findByConductor(Conductor)));

//					/* Add leases for this Conductor							*/
					
					
					/* Add this Conductor to the list.						*/
					listOfConductors.add(conductor);

				} catch (Exception ex)	{
					System.err.println("Conductor: Error processing list <" + ex.toString());
				}
			}

		} catch (Exception sqlex)	{
			throw new CreateException(sqlex.getMessage());
		}

		
		return listOfConductors;
	}
	
	
	/* REMOVERS	-----------------------------------------------------	*/
	/**
	 *	Remove a Conductor by primary key.
	 *	@param	primarykey	The primary key for the Conductor to find.
	 *	@throws	NoSuchEntiryException
	 * @throws	DAOSysException
	 */
	private static int removeByPrimarykey(ConductorPK primarykey)
								throws	DAOSysException, NoSuchEntityException	{
		int rc = 0;
		ConductorDAO dao = null;
		
		/*	remove boats first etc...				*/
		
		dao = (ConductorDAO) DAOFactory.getDAO(className);
		rc = dao.dbRemove(primarykey);
		
		return rc;
	}

	
	/* CONSTRUCTORS	-----------------------------------------------	*/
	/**
	 *	Default constructor
	 */
	private Conductor()	{ super();		}

	/**
	 *	Parameterized constructor.
	 *	@param	name
	 *	@param	address
	 *	@param	phoneno
	 */
	private Conductor(String ID, String name)	{
		this(new ConductorModel(ID, name));
	}

	/**
	 *	Parameterized constructor.
	 *	@param	model	The persistence model for a Conductor object.
	 */
	private Conductor(ConductorModel model)	{
		setModel(model);

		/*	initially no Boat and no leases, but we do have empty collections		*/
		setListOfBoats(new ArrayList<Boat>());
//		setListOfLeases(new ArrayList<Lease>());
	}


	/* ACCESSORS	--------------------------------------------------	*/
	public ConductorModel getModel()				{ return model;												}
	public ConductorPK getPrimaryKey()			{ return getModel().getPrimarykey();					}
	public String getNumber()						{ return getModel().getPrimarykey().getid(); 	}
 	public String getName()							{ return getModel().getName();							}
	public ArrayList<Boat>	getListOfBoats()	{ return listOfBoats;										}
//	public ArrayList<Lease>	getListOfLeases()	{ return listOfLeases;										}

	private ArrayList<Boat> getBoats()	{
		ArrayList<Boat> list = new ArrayList<Boat>();
		try	{
			//list = (ArrayList<Boat>) Boat.findByConductor(this);
		} catch (Exception ex)	{
		}
		return list;
	}

//	private ArrayList<Lease> getLeases(){
//		ArrayList<Lease> list = new ArrayList<Lease>();
////		list = Lease.findByConductor(this);
//		return list;
//	}

	
	/* MODIFIERS	--------------------------------------------------	*/
	private void setModel(ConductorModel model)	{ this.model = model;								}

	private void setPrimarykey(ConductorPK pk)	{ getModel().setPrimarykey(pk);						}
	public void setName(String name)				{
		getModel().setName(name);
		update();
	}

	
	private void setListOfBoats(ArrayList<Boat> boats)		{ listOfBoats = boats;						}
//	private void setListOfLeases(ArrayList<Lease> leases)	{ listOfLeases = leases;					}


	/* BEHAVIOR	-----------------------------------------------------	*/
	/**
	 *	Implementation of the "object" equals method.  Conductors objects are equal
	 *	if their primary key's are equal.
	 *	@return	True if the fields of this primary key object equal the
	 *	contents of the fields from the passed primary key object, otherwise
	 *	false, they are not equal.
	 */
	public boolean equals(Object obj)	{
		return	obj instanceof Conductor
			&&	(getNumber().equals(((Conductor) obj).getNumber())
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
		return "number=" + getNumber()
				+ sep + "name=" + getName()
				+ sep + "listOfBoats=" + getListOfBoats()
//				+ sep + "listOfLeases=" + getListOfLeases()
				+ sep + "boats=" + getBoats()
//				+ sep + "leases=" + getLeases()
			;
	}

	/**
	 *	Get an iterator to the list of boats for this Conductor.
	 * 
	 * @return 
	 */
	public Iterator<Boat> boats()					{ return getBoats().iterator();								}

//	/**
//	 *	Get an iterator to the list of leases for this Conductor.
//	 * 
//	 * @return 
//	 */
//	public Iterator leases()				{ return getLeases().iterator();								}
	
	/**
	 *	Add a boat to this Conductor.
	 * 
	 * @param boat 
	 */
	public void addBoat(Boat boat)	{
		if (!getListOfBoats().contains(boat))	{
			getListOfBoats().add(boat);
		}
	}

	/**
	 *	Remove a boat from this Conductor
	 * 
	 * @param boat 
	 */
	public void removeBoat(Boat boat)	{
		getBoats().remove(boat);
	}

//	/**
//	 *	Add a lease to this Conductor
//	 * 
//	 * @param lease 
//	 */
//	public void addLease(Lease lease)	{
//		if (!getLeases().contains(lease))	{
//			getLeases().add(lease);
//		}
//	}

//	/**
//	 *	Remove a lease from this Conductor
//	 * 
//	 * @param lease 
//	 */
//	public void removeLease(Lease lease)	{
//		getLeases().remove(lease);
//	}
	
	

	/**
	 *	Remove a Conductor from the data store (by primary key).
	 * @return 
	 * @throws sql.NoSuchEntityException
	 * @throws sql.DAOSysException 
	 */
	public Conductor remove()	throws NoSuchEntityException, DAOSysException	{
		Conductor c = null;
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
		ConductorDAO dao = null;
		try	{
			dao = (ConductorDAO) DAOFactory.getDAO(className);
			setModel((ConductorModel)dao.dbLoad(getPrimaryKey()));

		} catch (Exception ex)	{
			throw new DAOSysException(ex.getMessage());
		}
	}


	/**
	 * Invoke this method to save the cached attribute values to the datastore.
	 */
	private void store()	throws DAOSysException		{
		if (_debug) System.out.println("C:store()");
		ConductorDAO dao = null;
		try	{
			dao = (ConductorDAO) DAOFactory.getDAO(className);
			dao.dbStore(getModel());
		} catch (Exception ex)	{
			throw new DAOSysException(ex.getMessage());
		}
	}



	/* ATTRIBUTES	--------------------------------------------------	*/
	private static final boolean _debug = false;

	/** Class name for static method purposes.								*/
	private static String className = "marina.Conductor";
	
	/** Persistence model for a Conductor object.								*/
	private ConductorModel model;

	
	/* REFERENCE ATTRIBUTES	-----------------------------------------	*/
 	/** Boat for this Conductor.													*/
	private ArrayList<Boat> listOfBoats;

//	/** Lease for this Conductor.													*/
//	private ArrayList<Lease> listOfLeases;


}	/*	End of CLASS:	Conductor.java				*/