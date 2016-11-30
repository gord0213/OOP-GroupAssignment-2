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
	 * @param composer
	 * @param composition
	 * @param number
	 * @param name 
	 */
	public MovementsModel(String composer, String composition, String number, String name) {
		this(new MovementsPK(composer, composition,number, name));
	}

	/**
	 * Creates a new instance of MovementsModel
	 * 
	 * @param primarykey
	 * @param name
	 * @param address
	 * @param phoneno 
	 */
	public MovementsModel(MovementsPK primarykey){
		super();
		setPrimarykey(primarykey);
		
		
	}
	
	
	/* ACCESSORS	--------------------------------------------------	*/
	public String getComposer()				{ return getPrimarykey().getComposer();}
	public String getComposition()			{ return getPrimarykey().getComposition();}
	public String getNumber()				{ return getPrimarykey().getNumber();}
 	public String getName()					{ return getPrimarykey().getName();}
	


	/* MODIFIERS	--------------------------------------------------	*/


	
	/* ATTRIBUTES	--------------------------------------------------	*/
											

}

