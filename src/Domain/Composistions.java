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
	
	/** Persistence model for a Composistion object.								*/
	private ComposistionModel model;
	

	/* STATIC PRE-OBJECT BEHAVIOR	-----------------------------------	*/
	/*	Basic Creators, finders, and removers									*/
	/* CREATORS	-----------------------------------------------------	*/
	
	/**Create an instance of a new Composistion.
	 *	@return	An instance of a Composistion entity.
	 *	@throws sql.CreateException 
	 *  @param	composer			The Composistion composer.
	 *	@param	compositionName		The name of the Composistion.*/
	public static Composistions create(String composer, String compositionName) throws CreateException{
		if (_debug) 
			System.out.println("C:create:" + composer +" and "+ compositionName);

		ComposistionModel model = new ComposistionModel(composer, compositionName);
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
	
	
	/* FINDERS	-----------------------------------------------------	*/
	
	/*	Finder methods are used to search for a particular instance
	 *	or a collection of instances, therefore finders either return
	 *	and instance to the entity, or a collection of instances.
	 */
	
	/**Find a Composistion by primary key.
	 *	@return	An instance of a Composistion entity.
	 *	@throws sql.FinderException 
	 *  @throws sql.NoSuchEntityException 
	 *  @param	primarykey	The primary key for the Composistion to find.*/
	public static Composistions findByPrimarykey(ComposistionsPK primarykey) throws FinderException, NoSuchEntityException{
		if (_debug) 
			System.out.println("C:findByPrimarykey(" + primarykey + ")");

		ComposistionModel model = null;
		Composistions Composistion = null;
		ComposistionsDAO dao = null;
		try	{
			dao = (ComposistionsDAO) DAOFactory.getDAO(className);
			model = (ComposistionModel) dao.dbSelectByPrimaryKey(primarykey);
			Composistion = new Composistions(model);
			
		}catch(Exception sqlex){
			throw new FinderException(sqlex.getMessage());
		}//end try/catch statement

		return Composistion;
	}//end findByPrimarykey method
	
	
	/**Find all Composistion entities.
	 *	@return	A collection of Composistion instances.
	 *	@throws	FinderException
	 *  @throws	CreateException*/
	public static Collection<Composistions> findAll() throws FinderException, CreateException			{
		ArrayList<Composistions> listOfComposistions = new ArrayList<Composistions>();
		ComposistionsDAO dao = null;
	
		try{
			dao = (ComposistionsDAO) DAOFactory.getDAO(className);
			Collection<ComposistionsPK> c = dao.dbSelectAll();
			Iterator<ComposistionsPK> itr = c.iterator();
			while(itr.hasNext()){
				ComposistionsPK cpk = itr.next();
				try{
					Composistions Composistion = Composistions.findByPrimarykey(cpk);

					listOfComposistions.add(Composistion);

				}catch(Exception ex){
					System.err.println("Composistion: Error processing list <" + ex.toString());
				}//end try/catch statement
			}//end while statement

		}catch(Exception sqlex){
			throw new CreateException(sqlex.getMessage());
		}//end try/catch statement

		return listOfComposistions;
	}//end findAll method
	
	
	/* REMOVERS	-----------------------------------------------------	*/
	/**Remove a Composistion by primary key.
	 *	@param	primarykey	The primary key for the Composistion to find.
	 *	@throws	NoSuchEntiryException
	 * @throws	DAOSysException */
	private static int removeByPrimarykey(ComposistionsPK primarykey) throws DAOSysException, NoSuchEntityException{
		int rc = 0;
		ComposistionsDAO dao = null;
		
		dao = (ComposistionsDAO) DAOFactory.getDAO(className);
		rc = dao.dbRemove(primarykey);
		
		return rc;
	}//end removeByPrimarykey method
	
	
	/* CONSTRUCTORS	-----------------------------------------------	*/
	/** Default constructor*/
	private Composistions(){
		super();
	}//end Composistions constructor

	
	/** Parameterized constructor.
	 *	@param	composer
	 *	@param	composerName*/
	private Composistions(String composer, String composerName)	{
		this(new ComposistionModel(composer, composerName));
	}//end Composistions constructor

	
	/**Parameterized constructor.
	 *	@param	model	The persistence model for a Composistion object.*/
	private Composistions(ComposistionModel model)	{
		setModel(model);

		/*	initially no Boat and no leases, but we do have empty collections		
		setListOfBoats(new ArrayList<Boat>());
		//setListOfLeases(new ArrayList<Lease>());*/
	}//end Composistions constructor
	
	
	/* ACCESSORS	--------------------------------------------------	*/
	public ComposistionModel getModel(){
		return model;
	}//end getModel method
	
	public ComposistionsPK getPrimaryKey(){
		return getModel().getPrimarykey();
	}//end getPrimaryKey method
	
	public String getComposer(){
		return getModel().getPrimarykey().getComposer();
	}//end getComposer method
	
 	public String getCompositionName(){
 		return getModel().getPrimarykey().getCompositionName();
 	}//end getCompositionName method
 	
 	
 	/**
 	 * Boat method from example
 	 * */
 	
 	
	/* MODIFIERS	--------------------------------------------------	*/
	private void setModel(ComposistionModel model){
		this.model = model;
	}//end setModel method

	private void setPrimarykey(ComposistionsPK pk){
		getModel().setPrimarykey(pk);
	}//end setPrimarykey method
	
	/**
	 * Boat method from example
	 * */
	
	
	/* BEHAVIOR	-----------------------------------------------------	*/
	/**
	 *	Implementation of the "object" equals method.  Composistionss objects are equal
	 *	if their primary key's are equal.
	 *	@return	True if the fields of this primary key object equal the
	 *	contents of the fields from the passed primary key object, otherwise
	 *	false, they are not equal.
	 */
	public boolean equals(Object obj)	{
		return	obj instanceof Composistions 
				&&	(getComposer().equals(((Composistions) obj).getComposer()) 
						&& getCompositionName().equals(((Composistions) obj).getCompositionName()));
	}//end equals method
	
	
	/**
	 *	Implementation of the "object"hashCode()" method.
	 * Whenever it is invoked on the same object more than once during
	 * an execution of a Java application, the hashCode method
	 * must consistently return the same integer, provided no information
	 * used in equals comparisons on the object is modified.
	 *	@return	A hash code value for the object.
	 */
	public int hashCode() {
		return	getComposer().concat(getCompositionName()).hashCode();
	}//end hashCode method
	
	
	/**
	 *	Flush cached attribute values to the datastore.
	 *	Catch and report any errors.
	 */
	public void update()	{
		if (_debug)
			System.out.println("C:update()");
		
		try{
			store();
		}catch(Exception ex){
			System.out.println("C: Error in update(), <" + ex.toString() + ">");
		}//end try/catch statement
		
	}//end update method
	
	
	public String toString(){
		return this.toString(", ");
	}//end toString method
	
	public String toString(String sep)	{
		return "composer=" + getComposer() + sep + "composition name=" + getCompositionName();
	}//end toString method
	
	/**
	 * Boat method from example
	 * */
	
	/**
	 * Boat method from example
	 * */
	
	
	/**
	 *	Remove a Composistions from the data store (by primary key).
	 * @return 
	 * @throws sql.NoSuchEntityException
	 * @throws sql.DAOSysException 
	 */
	public Composistions remove() throws NoSuchEntityException, DAOSysException{
		Composistions c = null;
		
		if (removeByPrimarykey(getPrimaryKey()) > 0)	{
			c = this;
		}//end if statement

		return c;
	}//end remove method
	
	
	/**
	 * Invoke this method to refresh the cached attribute values
	 * from the database.
	 */
	private void load() throws DAOSysException		{
		if (_debug)
			System.out.println("C:load()");
		
		ComposistionsDAO dao = null;
		
		try	{
			dao = (ComposistionsDAO) DAOFactory.getDAO(className);
			setModel((ComposistionModel)dao.dbLoad(getPrimaryKey()));

		}catch(Exception ex){
			throw new DAOSysException(ex.getMessage());
		}//end try/catch statement
		
	}//end load method


	/**
	 * Invoke this method to save the cached attribute values to the datastore.
	 */
	private void store() throws DAOSysException{
		if (_debug)
			System.out.println("C:store()");
		
		ComposistionsDAO dao = null;
		try{
			dao = (ComposistionsDAO) DAOFactory.getDAO(className);
			dao.dbStore(getModel());
			
		}catch (Exception ex){
			throw new DAOSysException(ex.getMessage());
		}//end try/catch statement
		
	}//end load method

	
}//end Composistions class
