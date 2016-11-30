package Domain;


import SQL.CorePersistenceModel;

/**
 * MovementsModel represents the persistence model for a movements object.
 * @author Reg
 */
public class MovementsModel extends SQL.CorePersistenceModel<MovementsPK>	{
	/**
	 * Creates a new instance of MovementsModel
	 */
	public MovementsModel() { super();		}
	
	/**
	 * Creates a new instance of MovementsModel
	 * 
	 * @param number
	 * @param name
	 * @param address
	 * @param phoneno 
	 */
	public MovementsModel(String number,
								String name) {
		this(new MovementsPK(number), name);
	}

	/**
	 * Creates a new instance of MovementsModel
	 * 
	 * @param primarykey
	 * @param name
	 * @param address
	 * @param phoneno 
	 */
	public MovementsModel(MovementsPK primarykey,
								String name){
		super();
		setPrimarykey(primarykey);
		setName(name);
		
	}
	
	
	/* ACCESSORS	--------------------------------------------------	*/
	public String getNumber()				{ return getPrimarykey().getNumber();	}
 	public String getName()					{ return name;									}
	


	/* MODIFIERS	--------------------------------------------------	*/
	public void setName(String name)				{ this.name = name;					}


	
	/* ATTRIBUTES	--------------------------------------------------	*/
	/** Name of this movements.														*/
 	private String name;
 	/** Address for this movements.												*/


}

