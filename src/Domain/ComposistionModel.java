package Domain;

import SQL.CorePersistenceModel;

public class ComposistionModel extends CorePersistenceModel<ComposistionsPK>{

	public ComposistionModel(){ super(); }
	public ComposistionModel(String name,
							 String composistion){
		this (new ComposistionsPK(name, composistion));
	}
	public ComposistionModel(ComposistionsPK primaryKeyName){
		super(primaryKeyName);
	}
	public ComposistionsPK getPrimarykey(int i){
		if (i == 0){
			return (ComposistionsPK) super.getPrimarykey();
		}else{
			return (ComposistionsPK) super.
		}
	}
	public void setPrimarykey(ComposistionsPK pk){ super.setPrimarykey(pk);}

}
