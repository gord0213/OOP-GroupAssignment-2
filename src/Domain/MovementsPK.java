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
	 *	@param	number	The movements number.
	 */
	public MovementsPK(String number)	{ this.number = number;		}

	/**
	 *	Constructor to build a primary key from a another MovementsPK argument.
	 *	@param	primarykey	A MovementsPK object.
	 */
	public MovementsPK(MovementsPK primarykey)	{ number = primarykey.getNumber();		}


	/* ACCESSORS	--------------------------------------------------	*/
	/**
	 *	Get the movements Number.
	 *	@return	The movements number.
	 */
	public String getNumber()	{ return number;		}


	/* BEHAVIOR	-----------------------------------------------------	*/
	/**
	 *	Convert this primary key object into a meaningful string.
	 *	@return	This object as a string.
	 */
	public String toString()	{ return	number;		}


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
	/** Movements number.																	*/
	private String number;

}	/*	End of Class:	MovementsPK.java				*/