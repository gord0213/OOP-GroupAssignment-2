package Domain;

import SQL.CorePersistenceModel;

public class ComposistionsModel extends CorePersistenceModel<ComposistionsPK>{

	public ComposistionsModel(){ super(); }
	public ComposistionsModel(String composer,
							 String compositionName){
		this (new ComposistionsPK(composer, compositionName));
	}
	public ComposistionsModel(ComposistionsPK primaryKey){
		super(primaryKey);
	}
	public void setPrimarykey(ComposistionsPK pk){ super.setPrimarykey(pk);}

}
