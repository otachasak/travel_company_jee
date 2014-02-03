package cz.cvut.fel.jee.travel_company.entities;

public class EntityNotFoundException extends Exception{
	private static final long serialVersionUID = 1L;

	public EntityNotFoundException(String entityName, Long id) {
		super("Entity " + entityName + " with id:" + id + " was not found");
	}

}
