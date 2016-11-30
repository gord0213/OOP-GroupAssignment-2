package Domain;

import SQL.CorePersistenceModel;

public class ComposistionModel extends CorePersistenceModel<ComposistionsPK>{

	public ComposistionModel(){ super(); }
	public ComposistionModel(String composer,
							 String compositionName){
		this (new ComposistionsPK(composer, compositionName));
	}
	public ComposistionModel(ComposistionsPK primaryKey){
		super(primaryKey);
	}
	public ComposistionsPK getPrimarykey(int i){
		return (ComposistionsPK) super.getPrimarykey();
	}
	public void setPrimarykey(ComposistionsPK pk){ super.setPrimarykey(pk);}

}
