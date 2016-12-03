package Domain;



/**
 * MovementsPK is the primary key class for a Movements entity.
 * @author    R. Dyer
 * @version   1.0.0 May 2002
 */
public class MovementsPK implements java.io.Serializable	{
	/**
	 *	Default constructor.
	 */
	public MovementsPK()	{}

	/**
	 *	Constructor to build a primary key from an Number.
	 * 	@param	composer the composer for the composition.
	 * 	@param	composition	the composition this movement is part of
	 *	@param	number	The movement's number.
	 *	@param	name	The movement's name.
	 */
	public MovementsPK(String composer, String Composition, String number, String name)	{
		this.composer = composer;
		this.composition = composition;
		this.name = name;
		this.number = number;		}

	/**
	 *	Constructor to build a primary key from a another MovementsPK argument.
	 *	@param	primarykey	A MovementsPK object.
	 */
	public MovementsPK(MovementsPK primarykey)	{
		composer = primarykey.getComposer();
		composition = primarykey.getComposition();
		name = primarykey.getName();
		number = primarykey.getNumber();		}


	/* ACCESSORS	--------------------------------------------------	*/
	/**
	 *	Get the movement's composition's composer.
	 *	@return	The movement's composition's composer.
	 */
	public String getComposer()	{ return composer;		}

	/**
	 *	Get the movement's composition.
	 *	@return	The movement's composition.
	 */
	public String getComposition()	{ return composition;		}

	/**
	 *	Get the movement's Number.
	 *	@return	The movement's number.
	 */
	public String getNumber()	{ return number;		}

	/**
	 *	Get the movement's Name.
	 *	@return	The movement's name.
	 */
	public String getName()	{ return name;		}

	

	/* BEHAVIOR	-----------------------------------------------------	*/
	/**
	 *	Convert this primary key object into a meaningful string.
	 *	@return	This object as a string.
	 */
	public String toString()	{ return	composer + ", " + composition + ", " + number + ", " + name;		}


	/**
	 *	Implemenation of the "object" equals method.
	 *	@return	True if the fields of this primary key object equal the
	 *	contents of the fields from the passed primary key object, otherwise
	 *	false, they are not equal.
	 */
	public boolean equals(Object obj)	{
		return	obj instanceof MovementsPK
			&&	getNumber().equals(((MovementsPK) obj).getNumber()
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
		return	getNumber().hashCode();
	}



	/*	Movements Entity PRIMARY KEY FIELDS ------------------------------	*/
	/** 	
	 *	the composer's name for the composition.
	 *	the composition name this movement is part of
	 *	The movement's number.
	 *	The movement's name.
	 */
	private String composer;
	private String composition;
	private String number;
	private String name;

}	/*	End of Class:	MovementsPK.java				*/