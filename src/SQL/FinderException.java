/*
 * FinderException.java
 */

package SQL;

/**
 *
 * @author Reg
 */
public class FinderException extends java.sql.SQLException	{
	public FinderException()					{ super(DEFAULT_MESSAGE);			}
	public FinderException(String msg)		{ super(msg);							}

	private final static String DEFAULT_MESSAGE = "Object not found";
}
