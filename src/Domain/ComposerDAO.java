package Domain;

import java.util.*;
import java.sql.*;

import SQL.CoreDAOImpl;
import SQL.CoreDAO;
import SQL.DAOSysException;
import SQL.NoSuchEntityException;

/**
 *	Data access object for Composer data.  This class bridges the
 *	object to non-object datastore layer.
 * @author Md Junayed Miraz
 */
public class ComposerDAO extends CoreDAOImpl<ComposerModel, ComposerPK>{
	
	/**
	 * Creates a new instance of ComposerDAO
	 */
	public ComposerDAO() { this(CoreDAO.DRIVER_NAME, CoreDAO.URL, CoreDAO.USER, CoreDAO.PASSWORD);		}

	/**
	 *	Parameterized constructor.  When extending this class the
	 *	derived class must invoke one of this classes constructors
	 *	for proper initialization.
	 *	@param drivername 
	 * @param url 
	 * @param	user		Database user name.
	 *	@param	password	Database password for access.
	 */
	public ComposerDAO(String drivername,
						String url,
						String user,
						String password)	{
		super(drivername, url, user, password);
	}

	
	/* ACCESSORS	-----------------------------------------------	*/

	
	/* MUTATORS	--------------------------------------------------	*/

	
	/* BEHAVIOR	--------------------------------------------------------	*/
	/**
	 * Called by create() to insert entity state into the data store for a new entity.
	 *	@param	model	Persistence data model for the entity.
	 *	@throws	DAOSysException
	 */
	public void dbInsert(ComposerModel model)	throws DAOSysException {
		dbInsert(model, ComposerDAO.INSERT_STM);
	}

	
	/**
	 * Called by create() to insert entity state into the data store for a new entity.
	 *	@param	model	Persistence data model for the entity.
	 *	@param	insertStm	Data store statement for inserting into the data store.
	 *	@throws	DAOSysException
	 */
	public void dbInsert(ComposerModel model, String insertStm) throws DAOSysException		{
		PreparedStatement preparedStm = null;
		Connection connection = null;

		try	{
			connection = connectToDB();
			preparedStm = connection.prepareStatement(insertStm);
			preparedStm.setInt(1, model.getId());
			preparedStm.setString(2, model.getComposerName());
			preparedStm.executeUpdate();

		}	catch (SQLException sex)	{
			throw new DAOSysException("Error adding customer <" + model.getId() + "> " + sex.getMessage());

		}	finally	{
			try	{
				releaseAll(null, preparedStm, connection);
			} catch (Exception ex)	{
				System.err.println("Error releasing resources <" + ex.toString());
			}
		}
	}

	/**
	 * Called by findByPrimaryKey() to retrieve an entity by the primary key.
	 *	@param	primarykey The primary key for an entity.
	 *	@return	The persistence model for the entity, else null entity data is not found.
	 * @throws sql.DAOSysException
	 * @throws sql.NoSuchEntityException 
	 */
	public ComposerModel dbSelectByPrimaryKey(ComposerPK primarykey)
				throws DAOSysException, NoSuchEntityException	{
		return dbSelectByPrimaryKey(primarykey, ComposerDAO.SELECT_STM);
	}

	/**
	 * Called by findByPrimaryKey() to retrieve an entity by the primary key.
	 *	@param	primarykey The primary key for an entity.
	 *	@param	selectStm	Data store statement to get the entity.
	 *	@return	The persistence model for the entity, else null entity data is not found.
	 */
	public ComposerModel dbSelectByPrimaryKey(ComposerPK primarykey, String selectStm)
				throws DAOSysException, NoSuchEntityException	{
		if (_debug)	System.out.println("CDAO:dbSelectByPrimaryKey(key, stm, model)");
		ComposerPK pk = (ComposerPK) primarykey;
		Connection connection = null;
		PreparedStatement preparedStm = null;
		ResultSet rs = null;
		boolean result = false;
		ComposerModel model = new ComposerModel();
		try	{
			connection = connectToDB();
			preparedStm = connection.prepareStatement(selectStm);
			preparedStm.setInt(1, pk.getId());
			rs = preparedStm.executeQuery();

			result = rs.next();
			if (result)	{
				model.setPrimarykey(new ComposerPK(rs.getInt(1)));
				model.setComposerName(rs.getString(2));
				

			}	else	{
				throw new NoSuchEntityException("Composer ID for <"
						+ primarykey + "> not found in the database.");
			}

		}	catch (SQLException sex)	{
			throw new DAOSysException(
				"dbSelectByPrimaryKey() SQL Exception\n"
				+ sex.getMessage());

		}	finally	{
			try	{
				releaseAll(rs, preparedStm, connection);
			} catch (Exception ex)	{
				System.err.println("Error releasing resources <" + ex.toString());
			}
		}

		return model;

	}

	/**
	 * Called by findAll() to find all entities in the data store.
	 *	@return	A collection of primary keys representing all of the entities.
	 */
	public Collection<ComposerPK> dbSelectAll()	throws DAOSysException {
		return dbSelectAll(ComposerDAO.SELECT_ALL_STM);
	}
	
	
	/**
	 * Called by findAll() to find all entities in the data store.
	 *	@param selectStm 
	 * @return	A collection of primary keys representing all of the entities.
	 */
	public Collection<ComposerPK> dbSelectAll(String selectStm)	throws DAOSysException {
		Connection connection = null;
		PreparedStatement preparedStm = null;
		ResultSet rs = null;
		ArrayList<ComposerPK> list = null;

		try	{
			connection = connectToDB();
			preparedStm = connection.prepareStatement(selectStm);
			rs = preparedStm.executeQuery();

			list = new ArrayList<ComposerPK>();
			while (rs.next())	{
				list.add(new ComposerPK(rs.getInt(1)));
			}

		}	catch (SQLException sex)	{
			throw new DAOSysException(
						"dbSelectAll() SQL Exception\n"
						+ sex.getMessage());
		}	finally	{
			try	{
				releaseAll(rs, preparedStm, connection);
			} catch (Exception ex)	{
				System.err.println("Error releasing resources <" + ex.toString());
			}
		}

		return list;
	}


	/**
	 * Called by update() to update state for an entity in the database.
	 *	@param	data	Persistence model for the entity.
	 *	@throws	DAOSysException
	 */
	public void dbUpdate(ComposerModel data)	throws DAOSysException	{
		dbUpdate(data, ComposerDAO.UPDATE_STM);
	}

	/**
	 * Called by update() to update state for an entity in the database.
	 *	@param	data	Persistence model for the entity.
	 *	@param	updateStm	Data store update statement.
	 *	@throws	DAOSysException
	 */
	public void dbUpdate(ComposerModel data, String updateStm)	throws DAOSysException {
		ComposerModel model = data;
		Connection connection = null;
		PreparedStatement preparedStm = null;
		try	{
			connection = connectToDB();
			preparedStm = connection.prepareStatement(updateStm);

			/*	Grab values from persistent fields to store in database	*/
			preparedStm.setString(1, model.getComposerName());
			
 			int rowCount = preparedStm.executeUpdate();
			if (rowCount == 0)	{
 				throw new DAOSysException(
 					"Failed to store state for Customer <"
 					+ model.getId() + ">");
 			}

		}	catch (SQLException sex)	{
			throw new DAOSysException(
					"dbUpdate() SQL Exception <"
					+ sex.getMessage() + ">");

		}	finally	{
			try	{
				releaseAll(null, preparedStm, connection);
			} catch (Exception ex)	{
				System.err.println("Error releasing resources <" + ex.toString());
			}
		}
	}

	/**
	 * Called by remove() to remove the state for an entity from the data store.
	 *	@param	primarykey	The primary key of the entity to be removed.
	 *	@return 
	 * @throws	DAOSysException
	 */
	public int dbRemove(ComposerPK primarykey)	throws DAOSysException	{
		return dbRemove(primarykey, ComposerDAO.DELETE_STM);
	}

	
	/**
	 * Called by remove() to remove the state for a Customer entity from the database.
	 *	@param	primarykey	The primary key of the Customer entity
	 *	to be removed from the data store.
	 *	@param	deleteStm	Statement to remove entity data from the data store.
	 *	@throws	DAOSysException
	 */
	public int dbRemove(ComposerPK primarykey, String deleteStm)	throws DAOSysException	{
		ComposerPK pk = primarykey;
		Connection connection = null;
		PreparedStatement preparedStm = null;
		int result = 0;
		
		try	{
			connection = connectToDB();
			preparedStm = connection.prepareStatement(deleteStm);
			preparedStm.setInt(1, pk.getId());
			result = preparedStm.executeUpdate();

			if (result == 0)	{
				throw new SQLException(
						"Failed to remove Customer <"
						+ pk.toString() + ">.");
			}

		}	catch (SQLException sex)	{
			throw new DAOSysException(
					"dbRemove() SQL Exception <" + pk.toString() + "> " + sex.getMessage());

		}	finally	{
			try	{
				releaseAll(null, preparedStm, connection);
			} catch (SQLException sqlex)	{
				throw new DAOSysException(sqlex.toString());
			}
		}

		return result;
	}


	/**
	 * Called by getTotalEntities().
	 *	@return	The total number of entities in the data store.
	 *	@throws	DAOSysException
	 */
	public int dbCountTotalEntities()	throws DAOSysException	{
		Connection connection = null;
		PreparedStatement preparedStm = null;
		ResultSet rs = null;
		int count = 0;

		try	{
			connection = connectToDB();
			/*	Request a resultset that is scrollable to easily count rows	*/
			preparedStm = connection.prepareStatement(
										ComposerDAO.SELECT_DISTINCT_STM,
										ResultSet.TYPE_SCROLL_INSENSITIVE,
										ResultSet.CONCUR_UPDATABLE);
			rs = preparedStm.executeQuery();

			/*	Go to the last row and get its row number							*/
			rs.last();
			count = rs.getRow();

		}	catch (SQLException sex)	{
			throw new DAOSysException(
						"dbCountTotalCustomers() SQL Exception\n"
						+ sex.getMessage());

		}	finally	{
			try	{
				releaseAll(rs, preparedStm, connection);
			} catch	(SQLException sqlex)	{
				throw new DAOSysException(sqlex.toString());
			}
		}

		return count;
	}

	
	
	/* ATTRIBUTES	-----------------------------------------------	*/
	private final static boolean _debug = false;

	private static String SELECT_DISTINCT_STM =
		"SELECT DISTINCT number FROM " + "Customer";
	
	private static String DELETE_STM =
		"DELETE FROM " + "Customer"
		+ " WHERE number = ?";
	
	private static String UPDATE_STM =
		"UPDATE " + "Customer"
		+ " SET "
		+ "name = ? "
		+ "address = ? "
		+ "phoneNo = ? "
		+ "where number = ?";

	private static String SELECT_ALL_STM =
		"SELECT DISTINCT number " + "FROM " + "Customer";
	
	
	
	private static String SELECT_STM = "SELECT "
		+ " number, "
		+ " name, "
		+ " address, "
		+ " phoneNo "
		+ " FROM CUSTOMER "
		+ " WHERE number = ?";
		
	private static String INSERT_STM = "INSERT INTO "
		+ "Customer"
		+ " VALUES "
		+ "( ?, ?, ?, ? )";
	
	
}	/*	End of Class:	ComposerDAO.java				*/
	

