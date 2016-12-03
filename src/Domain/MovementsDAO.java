package Domain;

import java.util.*;
import java.sql.*;

import SQL.CoreDAOImpl;
import SQL.CoreDAO;
import SQL.DAOSysException;
import SQL.NoSuchEntityException;

/**
 *	Data access object for movements data.  This class bridges the
 *	object to non-object datastore layer.
 * @author Reg
 */
public class MovementsDAO extends CoreDAOImpl<MovementsModel, MovementsPK>	{
	/**
	 * Creates a new instance of MovementsDAO
	 */
	public MovementsDAO() { this(CoreDAO.DRIVER_NAME, CoreDAO.URL, CoreDAO.USER, CoreDAO.PASSWORD);		}

	/**
	 *	Parameterized constructor.  When extending this class the
	 *	derived class must invoke one of this classes constructors
	 *	for proper initialization.
	 *	@param drivername 
	 * @param url 
	 * @param	user		Database user name.
	 *	@param	password	Database password for access.
	 */
	public MovementsDAO(String drivername,
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
	public void dbInsert(MovementsModel model)	throws DAOSysException {
		dbInsert(model, MovementsDAO.INSERT_STM);
	}

	
	/**
	 * Called by create() to insert entity state into the data store for a new entity.
	 *	@param	model	Persistence data model for the entity.
	 *	@param	insertStm	Data store statement for inserting into the data store.
	 *	@throws	DAOSysException
	 */
	public void dbInsert(MovementsModel model, String insertStm) throws DAOSysException		{
		//don't need this
	}

	/**
	 * Called by findByPrimaryKey() to retrieve an entity by the primary key.
	 *	@param	primarykey The primary key for an entity.
	 *	@return	The persistence model for the entity, else null entity data is not found.
	 * @throws sql.DAOSysException
	 * @throws sql.NoSuchEntityException 
	 */
	public MovementsModel dbSelectByPrimaryKey(MovementsPK primarykey)
				throws DAOSysException, NoSuchEntityException	{
		return dbSelectByPrimaryKey(primarykey, MovementsDAO.SELECT_STM);
	}

	/**
	 * Called by findByPrimaryKey() to retrieve an entity by the primary key.
	 *	@param	primarykey The primary key for an entity.
	 *	@param	selectStm	Data store statement to get the entity.
	 *	@return	The persistence model for the entity, else null entity data is not found.
	 */
	public MovementsModel dbSelectByPrimaryKey(MovementsPK primarykey, String selectStm)
				throws DAOSysException, NoSuchEntityException	{
		if (_debug)	System.out.println("CDAO:dbSelectByPrimaryKey(key, stm, model)");
		MovementsPK pk = (MovementsPK) primarykey;
		Connection connection = null;
		PreparedStatement preparedStm = null;
		ResultSet rs = null;
		boolean result = false;
		MovementsModel model = new MovementsModel();
		try	{
			connection = connectToDB();
			preparedStm = connection.prepareStatement(selectStm);
			preparedStm.setString(1, pk.getNumber());
			rs = preparedStm.executeQuery();

			result = rs.next();
			if (result)	{
				


			}	else	{
				throw new NoSuchEntityException("Movements ID for <"
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
	public Collection<MovementsPK> dbSelectAll()	throws DAOSysException {
		return dbSelectAll(MovementsDAO.SELECT_ALL_STM);
	}
	
	
	/**
	 * Called by findAll() to find all entities in the data store.
	 *	@param selectStm 
	 * @return	A collection of primary keys representing all of the entities.
	 */
	public Collection<MovementsPK> dbSelectAll(String selectStm)	throws DAOSysException {
		Connection connection = null;
		PreparedStatement preparedStm = null;
		ResultSet rs = null;
		ArrayList<MovementsPK> list = null;

		try	{
			connection = connectToDB();
			preparedStm = connection.prepareStatement(selectStm);
			rs = preparedStm.executeQuery();

			list = new ArrayList<MovementsPK>();
			while (rs.next())	{
				list.add(new MovementsPK(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
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
	public void dbUpdate(MovementsModel data)	throws DAOSysException	{
		dbUpdate(data, MovementsDAO.UPDATE_STM);
	}

	/**
	 * Called by update() to update state for an entity in the database.
	 *	@param	data	Persistence model for the entity.
	 *	@param	updateStm	Data store update statement.
	 *	@throws	DAOSysException
	 */
	public void dbUpdate(MovementsModel data, String updateStm)	throws DAOSysException {
		MovementsModel model = data;
		Connection connection = null;
		PreparedStatement preparedStm = null;
		try	{
			connection = connectToDB();
			preparedStm = connection.prepareStatement(updateStm);

			/*	Grab values from persistent fields to store in database	*/
			preparedStm.setString(1, model.getComposer());
			preparedStm.setString(2, model.getComposition());
			preparedStm.setString(3, model.getNumber());
			preparedStm.setString(4, model.getName());
			
 			int rowCount = preparedStm.executeUpdate();
			if (rowCount == 0)	{
 				throw new DAOSysException(
 					"Failed to store state for Movements <"
 					+ model.getNumber() + ">");
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
	public int dbRemove(MovementsPK primarykey)	throws DAOSysException	{
		return dbRemove(primarykey, MovementsDAO.DELETE_STM);
	}

	
	/**
	 * Called by remove() to remove the state for a Movements entity from the database.
	 *	@param	primarykey	The primary key of the Movements entity
	 *	to be removed from the data store.
	 *	@param	deleteStm	Statement to remove entity data from the data store.
	 *	@throws	DAOSysException
	 */
	public int dbRemove(MovementsPK primarykey, String deleteStm)	throws DAOSysException	{
		MovementsPK pk = primarykey;
		Connection connection = null;
		PreparedStatement preparedStm = null;
		int result = 0;
		
		try	{
			connection = connectToDB();
			preparedStm = connection.prepareStatement(deleteStm);
			preparedStm.setString(1, pk.getNumber());
			result = preparedStm.executeUpdate();

			if (result == 0)	{
				throw new SQLException(
						"Failed to remove Movements <"
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
										MovementsDAO.SELECT_DISTINCT_STM,
										ResultSet.TYPE_SCROLL_INSENSITIVE,
										ResultSet.CONCUR_UPDATABLE);
			rs = preparedStm.executeQuery();

			/*	Go to the last row and get its row number							*/
			rs.last();
			count = rs.getRow();

		}	catch (SQLException sex)	{
			throw new SQL.DAOSysException(
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
		"SELECT DISTINCT number FROM " + "MOVEMENTS";
	
	private static String DELETE_STM =
		"DELETE FROM " + "MOVEMENTS"
		+ " WHERE number = ?";
	
	private static String UPDATE_STM =
		"UPDATE " + "MOVEMENTS"
		+ " SET "
		+ "composer = ? "
		+ "composition = ?"
		+ "name = ? "
		+ "where number = ?";

	private static String SELECT_ALL_STM =
		"SELECT DISTINCT number " + "FROM " + "Movements";
	
	
	
	private static String SELECT_STM = "SELECT "
		+ "composer, "
		+ "composition,"
		+ " number, "
		+ " name "
		+ " FROM MOVEMENTS "
		+ " WHERE number = ?";
		
	private static String INSERT_STM = "INSERT INTO "
		+ "MOVEMENTS"
		+ " VALUES "
		+ "( ?, ?, ?, ? )";
	
	
}	/*	End of Class:	MovementsDAO.java				*/