/*
 * DAOSysException.java
 */

package SQL;

/**
 *
 * @author Reg
 */
public class DAOSysException extends Exception	{
	public DAOSysException()					{ super(DEFAULT_MESSAGE);			}
	public DAOSysException(String msg)		{ super(msg);							}

	private final static String DEFAULT_MESSAGE = "DAO Error";
}