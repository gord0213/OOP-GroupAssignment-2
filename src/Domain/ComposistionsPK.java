package Domain;

/**ConductorPK is the primary key class for a Conductor entity.
 * @author    James Lowell
 * @version   1.0.0 November 2016 */
public class ComposistionsPK implements java.io.Serializable{

	/* Conductor Entity PRIMARY KEY FIELDS ------------------------------	*/
	/**Composer name.*/
	private String composer;
	
	/**Composition name.*/
	private String compositionName;
	
	
	/**Default constructor.*/
	public ComposistionsPK(){
		//empty constructor
	}//end ComposistionsPK default constructor

	
	/**Constructor to primary keys from composer and composition name.
	 *	@param	number	The Conductor number.*/
	public ComposistionsPK(String composer, String compositionName){
		this.composer = composer;
		this.compositionName = compositionName;
	}//end ComposistionsPK constructor

	
	/**Constructor to build a primary key from a another ConductorPK argument.
	 *	@param	primarykey	A ConductorPK object.*/
	public ComposistionsPK(ComposistionsPK primarykey){
		composer = primarykey.getComposer();
		compositionName = primarykey.getCompositionName();
	}//end ComposistionsPK constructor
	
	
	/* ACCESSORS	--------------------------------------------------	*/
	/**Get the Conductor composer.
	 *	@return	The Composer.*/
	public String getComposer(){
		return composer;
	}//end getID method
	
	
	/**Get the Conductor composer name.
	 *	@return	The Composition Name.*/
	public String getCompositionName(){
		return compositionName;
	}//end getID method
	
	
	/* BEHAVIOR	-----------------------------------------------------	*/
	/**Convert this primary key object into a meaningful string.
	 *	@return	This object as a string.
	 *	ex. "composerName, compositionName"*/
	public String toString(){
		return	composer +", "+ compositionName;
	}//end toString method
	
	
	/**Implemenation of the "object" equals method.
	 *	@return	True if the fields of this primary key object equal the
	 *	contents of the fields from the passed primary key object, otherwise
	 *	false, they are not equal.*/
	public boolean equals(Object obj){
		if(obj instanceof ComposistionsPK){
			if(getComposer().equals(((ComposistionsPK) obj).getComposer()) && getCompositionName().equals(((ComposistionsPK) obj).getCompositionName()))
				return true;
		}//end if statement
		
		return	false;
	}//end equals method

	
	/**Implementation of the "object"hashCode()" method.
	 * Whenever it is invoked on the same object more than once during
	 * an execution of a Java application, the hashCode method
	 * must consistently return the same integer, provided no information
	 * used in equals comparisons on the object is modified.
	 *	@return	A hash code value for the object.*/
	public int hashCode(){
		return	(getComposer() + getCompositionName()).hashCode();
	}//end hashCode method
	
}//end ComposistionsPK class
