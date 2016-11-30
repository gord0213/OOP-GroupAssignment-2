package Domain;

import java.util.Collection;

import SQL.CoreDAO;
import SQL.CoreDAOImpl;
import SQL.DAOSysException;
import SQL.NoSuchEntityException;

public class ComposistionsDAO extends CoreDAOImpl<ComposistionModel, ComposistionsPK>{

	
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
	public void dbInsert(ComposistionModel model) throws DAOSysException {
		dbInsert(model, ComposistionsDAO.Insert_STM);
		
	}

	@Override
	public void dbInsert(ComposistionModel model, String insertStm) throws DAOSysException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ComposistionModel dbSelectByPrimaryKey(ComposistionsPK primarykey)
			throws DAOSysException, NoSuchEntityException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComposistionModel dbSelectByPrimaryKey(ComposistionsPK primarykey, String selectStm)
			throws DAOSysException, NoSuchEntityException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<ComposistionsPK> dbSelectAll() throws DAOSysException {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return 0;
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

}
