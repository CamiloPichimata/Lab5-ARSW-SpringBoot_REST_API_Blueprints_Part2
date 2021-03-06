/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.persistence;

import java.util.List;
import java.util.Set;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;

/**
 *
 * @author hcadavid
 */
public interface BlueprintsPersistence {
    
    /**
     * 
     * @param bp the new blueprint
     * @throws BlueprintPersistenceException if a blueprint with the same name already exists,
     *    or any other low-level persistence error occurs.
     */
    public void saveBlueprint(Blueprint bp) throws BlueprintPersistenceException;
    
    /**
     * 
     * @param author blueprint's author
     * @param bprintname blueprint's name
     * @return the blueprint of the given name and author
     * @throws BlueprintNotFoundException if there is no such blueprint
     */
    public Blueprint getBlueprint(String author,String bprintname) throws BlueprintNotFoundException;

    /**
     * 
     * @param author blueprint's author
     * @return Blueprints of the given author
     */
    public Set<Blueprint> getBlueprintsByAuthor(String author);
    
    /**	
     * 
     * @return all blueprints
     */
    public Set<Blueprint> getAllBlueprints();
    
    /**
     * 
     * @param author blueprint´s author
     * @param name blueprint's name
     * @param points New set of blueprint´s points
     * @throws BlueprintNotFoundException if there is no such blueprint
     */
    public void setBlueprint(String author, String name, List<Point> points) throws BlueprintNotFoundException;
    
}
