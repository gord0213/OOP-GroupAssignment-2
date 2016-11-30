package Domain;

import SQL.CorePersistenceModel;

public class ComposerModel extends CorePersistenceModel<ComposerPK> {
	
	/**
	 * Creates a new instance of ComposerModel
	 */
	public ComposerModel() { super();		}
	
	/**
	 * Creates a new instance of ComposerModel
	 * 
	 * @param id
	 * @param name
	 * @return 
	 */
	public ComposerModel(int id,String name) {
		this(new ComposerPK(id), name);
	}

	/**
	 * Creates a new instance of ComposerModel
	 * 
	 * @param primarykey
	 * @param name
	 */
	public ComposerModel(ComposerPK primarykey,
								String name
								) {
		super();
		setPrimarykey(primarykey);
		setComposerName(name);
		
	}
	
	
	/* ACCESSORS	--------------------------------------------------	*/
	//public String getNumber()				{ return getPrimarykey().getNumber();	}
	public int getId(){ return id;}
 	public String getComposerName()					{ return name;									}
									


	/* MODIFIERS	--------------------------------------------------	*/
	public void setId(int id){this.id=id;}
 	public void setComposerName(String name)				{ this.name = name;					}
	

	
	/* ATTRIBUTES	--------------------------------------------------	*/
	/** Name of this Composer.														*/
 	private String name;
 	/** Id for this Composer.												*/
 	private int id;
 	

}



