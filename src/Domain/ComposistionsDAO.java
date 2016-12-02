package Domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import SQL.CoreDAO;
import SQL.CoreDAOImpl;
import SQL.DAOSysException;
import SQL.NoSuchEntityException;

public abstract class ComposistionsDAO extends CoreDAOImpl<ComposistionModel, ComposistionsPK>{

	
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
	public ComposistionModel dbSelectByPrimaryKey(ComposistionsPK primarykey)
			throws DAOSysException, NoSuchEntityException {
			
		return dbSelectByPrimaryKey(primarykey, SELECT_STM);
	}

	@Override
	public ComposistionModel dbSelectByPrimaryKey(ComposistionsPK primarykey, String selectStm)
			throws DAOSysException, NoSuchEntityException {
		ComposistionsPK pk = (ComposistionsPK) primarykey;
		Connection connection = null;
		PreparedStatement preparedStm = null;
		ResultSet rs = null;
		ComposistionModel model = null;
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
				model = new ComposistionModel();
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
		
		return dbSelectAll(DELETE_STATEMENT);
	}

	@Override
	public Collection<ComposistionsPK> dbSelectAll(String stm) throws DAOSysException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void dbUpdate(ComposistionModel data) throws DAOSysException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dbUpdate(ComposistionModel model, String updateStm) throws DAOSysException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int dbRemove(ComposistionsPK primarykey) throws DAOSysException {
		return dbRemove(primarykey, DELETE_STATEMENT);
	}

	@Override
	public int dbRemove(ComposistionsPK primarykey, String deleteStm) throws DAOSysException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int dbCountTotalEntities() throws DAOSysException {
		// TODO Auto-generated method stub
		return 0;
	}
	private static final String DELETE_STATEMENT = 
			"DELETE FROM " + "Composition" + " WHERE composer = ?";
	
	public static final String SELECT_STM = "SELECT" + "composer, " + "compositionName "
										  + "FROM " + "Composition " + "WHERE composer = ?";
}
