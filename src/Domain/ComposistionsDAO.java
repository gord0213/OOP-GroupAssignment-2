package Domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import SQL.CoreDAO;
import SQL.CoreDAOImpl;
import SQL.DAOSysException;
import SQL.NoSuchEntityException;

public class ComposistionsDAO extends CoreDAOImpl<ComposistionsModel, ComposistionsPK>{

	
	public ComposistionsDAO() {
		this(CoreDAO.DRIVER_NAME, CoreDAO.URL, CoreDAO.USER, CoreDAO.PASSWORD);
		

	}
	public ComposistionsDAO(String drivername,
								String url,
								String user,
								String password){
		
		super(drivername, url, user, password);

	}
	
	@Override
	public ComposistionsModel dbSelectByPrimaryKey(ComposistionsPK primarykey)
			throws DAOSysException, NoSuchEntityException {
			
		return dbSelectByPrimaryKey(primarykey, SELECT_STM);
	}

	@Override
	public ComposistionsModel dbSelectByPrimaryKey(ComposistionsPK primarykey, String selectStm)
			throws DAOSysException, NoSuchEntityException {
		ComposistionsPK pk = (ComposistionsPK) primarykey;
		Connection connection = null;
		PreparedStatement preparedStm = null;
		ResultSet rs = null;
		ComposistionsModel model = null;
		boolean result = false;
		if (selectStm == null){
			selectStm = SELECT_STM;
		}
		try{
			connection = connectToDB();
			preparedStm = connection.prepareStatement(selectStm);
			preparedStm.setString(1, pk.getComposer());
			rs = preparedStm.executeQuery();
			result = rs.next();
			if(result){
				model = new ComposistionsModel();
				model.setPrimarykey(new ComposistionsPK(rs.getString(1), rs.getString(2)));
			}else{
				throw new NoSuchEntityException("Composistion composer for <" + primarykey + "> nor found in the database");
			}
			
		}catch(SQLException exception){
			throw new DAOSysException("dbSelectByPrimaryKey() SQL Exception\n" + exception.getMessage());
		}finally{
			try{
				releaseAll(rs, preparedStm, connection);
				
			}catch(Exception ex){
				System.out.println("ERROR releasing resources <" + ex.toString());
			}
		}
		return model;
	}

	@Override
	public Collection<ComposistionsPK> dbSelectAll() throws DAOSysException {
		
		return dbSelectAll(SELECT_DISTINCT_STM);
	}

	@Override
	public Collection<ComposistionsPK> dbSelectAll(String stm) throws DAOSysException {
		Connection connection = null;
		PreparedStatement preparedStm = null;
		ResultSet rs = null;
		ArrayList<ComposistionsPK> list = null;

		try	{
			connection = connectToDB();
			preparedStm = connection.prepareStatement(stm);
			rs = preparedStm.executeQuery();

			list = new ArrayList<ComposistionsPK>();
			while (rs.next())	{
				list.add(new ComposistionsPK(rs.getString(1), rs.getString(2)));
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

	@Override
	public void dbUpdate(ComposistionsModel data) throws DAOSysException {
		dbUpdate(data, ComposistionsDAO.UPDATE_STM);
		
	}

	@Override
	public void dbUpdate(ComposistionsModel data, String updateStm) throws DAOSysException {
		ComposistionsModel model = data;
		Connection connection = null;
		PreparedStatement preparedStm = null;
		try	{
			connection = connectToDB();
			preparedStm = connection.prepareStatement(updateStm);

			/*	Grab values from persistent fields to store in database	*/
			preparedStm.setString(1, model.getPrimarykey().toString());
			
 			int rowCount = preparedStm.executeUpdate();
			if (rowCount == 0)	{
 				throw new DAOSysException(
 					"Failed to store state for Conductor <" 
 							+ model.getPrimarykey()+ ">");
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

	@Override
	public int dbRemove(ComposistionsPK primarykey) throws DAOSysException {
		return dbRemove(primarykey, DELETE_STATEMENT);
	}

	@Override
	public int dbRemove(ComposistionsPK primarykey, String deleteStm) throws DAOSysException {
		ComposistionsPK pk = primarykey;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int result = 0;
		try{
			connection = connectToDB();
			preparedStatement = connection.prepareStatement(deleteStm);
			preparedStatement.setString(1, pk.getComposer());
			result = preparedStatement.executeUpdate();
			if (result == 0){
				throw new SQLException(
						"Failed to remove composer <"
								+ pk.toString() + ">.");
			}
		}catch (SQLException ex){
			throw new DAOSysException(
					"dbRemove() SQL Exception <" + pk.toString() + "> " + ex.getMessage());

		}	finally	{
			try	{
				releaseAll(null, preparedStatement, connection);
			} catch (SQLException sqlex)	{
				throw new DAOSysException(sqlex.toString());
			}
		}

		return result;
	}

	@Override
	public int dbCountTotalEntities() throws DAOSysException {
		
		Connection connection = null;
		PreparedStatement prStatement = null;
		ResultSet rs = null;
		int count = 0;
		
		try{
			connection = connectToDB();
			
			prStatement = connection.prepareStatement(ComposistionsDAO.SELECT_DISTINCT_STM, 
													  ResultSet.TYPE_SCROLL_INSENSITIVE,
													  ResultSet.CONCUR_UPDATABLE);
			rs = prStatement.executeQuery();
			
			rs.last();
			count = rs.getRow();
		}catch(SQLException ex){
			throw new DAOSysException(
						"dbCountTotalEntities() SQL Exception\n"
					  + ex.getMessage());
		}finally{
			try{
				releaseAll(rs, prStatement, connection);
			}catch(SQLException exc){
				throw new DAOSysException(exc.toString());
			}
		}
		
		return count;
	}
	
	private static final String SELECT_DISTINCT_STM = 
			"SELECT DISTINCT composer, compositionName FROM " + "Composition";
	private static final String DELETE_STATEMENT = 
			"DELETE FROM " + "Composition" + " WHERE composer = ?";
	
	private static final String UPDATE_STM =
			"UPDATE " + "Composition" + " SET " + "compositionName = ? " + "" + "where composer = ?";
	
	public static final String SELECT_STM = "SELECT " + "composer, " + "compositionName "
										  + "FROM " + "Composition " + "WHERE composer = ?";

	
	@Override
	public void dbInsert(ComposistionsModel model) throws DAOSysException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void dbInsert(ComposistionsModel model, String insertStm) throws DAOSysException {
		// TODO Auto-generated method stub
		
	}
}
