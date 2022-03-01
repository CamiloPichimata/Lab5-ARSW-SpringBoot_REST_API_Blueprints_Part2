package edu.eci.arsw.blueprints.filters;

import edu.eci.arsw.blueprints.model.Blueprint;

/**
 * 
 * @author Camilo Pichimata
 */
public interface BlueprintsFilter {

	/**
	 * Perform the filtering process for the consultation of blueprints
	 * @param blueprint to which the filtering is performed
	 * @return the blueprint with the filter applied
	 */
	public Blueprint filtering(Blueprint blueprint);
	
}
