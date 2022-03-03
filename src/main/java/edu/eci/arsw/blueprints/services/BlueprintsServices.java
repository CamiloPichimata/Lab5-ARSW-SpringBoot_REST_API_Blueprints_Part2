/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.services;

import edu.eci.arsw.blueprints.filters.BlueprintsFilter;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author hcadavid
 */
@Service
public class BlueprintsServices {
   
    @Autowired
    BlueprintsPersistence bpp=null;
    
    @Autowired
    BlueprintsFilter bpf=null;
    
    public void addNewBlueprint(Blueprint bp) throws BlueprintPersistenceException {
    	bpp.saveBlueprint(bp);
    }
    
    /**
     * 
     * @return all blueprints
     */
    public Set<Blueprint> getAllBlueprints(){
        return bpp.getAllBlueprints();
    }
    
    /**
     * 
     * @param author blueprint's author
     * @param name blueprint's name
     * @return the blueprint of the given name created by the given author
     * @throws BlueprintNotFoundException if there is no such blueprint
     */
    public Blueprint getBlueprint(String author,String name) throws BlueprintNotFoundException{
    	return bpp.getBlueprint(author, name);
    }
    
    /**
     * 
     * @param author blueprint's author
     * @return all the blueprints of the given author
     * @throws BlueprintNotFoundException if the given author doesn't exist
     */
    public Set<Blueprint> getBlueprintsByAuthor(String author) throws BlueprintNotFoundException{
    	System.out.println("Autor recibido : " + author);
    	return bpp.getBlueprintsByAuthor(author);
    }
    
    /**
     * 
     * @param author blueprint's author
     * @param name blueprint's name
     * @return the filtered blueprint of the given name created by the given author
     * @throws BlueprintNotFoundException if the given author doesn't exist
     */
    public Blueprint getFilteredBlueprint(String author, String name) throws BlueprintNotFoundException {
    	return bpf.filtering(bpp.getBlueprint(author, name));
    }
    
    /**
     * 
     * @param author blueprint's author
     * @param name blueprint's name
     * @param points New set of blueprint´s points
     * @throws BlueprintNotFoundException 
     */
    public void setBlueprint(String author, String name, List<Point> points) throws BlueprintNotFoundException {
    	bpp.setBlueprint(author, name, points);
    }
}
