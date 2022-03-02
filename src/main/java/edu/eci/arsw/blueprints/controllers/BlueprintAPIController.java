/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.controllers;

import java.security.PublicKey;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultRowSorter;

import org.aspectj.weaver.NewConstructorTypeMunger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.services.BlueprintsServices;

/**
 *
 * @author hcadavid
 */

@RestController
//@RequestMapping(value = "/blueprints")
public class BlueprintAPIController {
	
	@Autowired
	BlueprintsServices bps;
	
	/**
	 *  
	 * @return Conjunto de todos los planos almacenados.
	 */
    @RequestMapping(method = RequestMethod.GET, value = "/blueprints")
    public ResponseEntity<?> manejadorGetRecursoAllBlueprints() {
    	try {
    		Set<Blueprint> data = bps.getAllBlueprints();
    		return new ResponseEntity<>(data, HttpStatus.ACCEPTED);
    	} catch (Exception e) {
			Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, e);
			//e.printStackTrace();
			//return new ResponseEntity<>("Error: No se ha encontrado el recurso solicitado", HttpStatus.NOT_FOUND);
			return new ResponseEntity<>("Error: Se ha presentado un error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    
    /**
     * 
     * @param author Nombre del Autor del plano.
     * @return Conjunto de todos los planos almacenados para el autor especificado.
     */
    @RequestMapping(method = RequestMethod.GET, value = "/blueprints/{author}")
    public ResponseEntity<?> manejadorGetRecursoBlueprintsByAutor(@PathVariable String author) {
    	try {
    		Set<Blueprint> data = bps.getBlueprintsByAuthor(author);
    		if (data.isEmpty()) {
    			throw new BlueprintNotFoundException("No se han encontrado Blueprints para el autor: " + author);
    		}
    		return new ResponseEntity<>(data, HttpStatus.ACCEPTED);
    		
    	} catch (BlueprintNotFoundException ex) {
    		Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
			//ex.printStackTrace();
			return new ResponseEntity<>("Error 404: No se han encontrado Blueprints para el autor: " + author, HttpStatus.NOT_FOUND);
			//return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
		} catch (Exception e) {
			Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, e);
			//e.printStackTrace();
			return new ResponseEntity<>("Error: Se ha presentado un error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
    
    /**
     * 
     * @param author Nombre del autor del plano.
     * @param bpname Nombre del plano.
     * @return Plano con el nombre y autor especificados.
     */
    @RequestMapping(method = RequestMethod.GET, value = "/blueprints/{author}/{bpname}")
    public ResponseEntity<?> manejadorGetRecursoBlueprint(@PathVariable String author, @PathVariable String bpname) {
    	try {
    		Blueprint data = bps.getBlueprint(author, bpname);
    		if (data == null) {
    			throw new BlueprintNotFoundException("No se ha encontrado un Blueprint llamado '" + bpname + "' para el autor '" + author + "'");
    		}
    		return new ResponseEntity<>(data, HttpStatus.ACCEPTED);
    		
    	} catch (BlueprintNotFoundException ex) {
    		Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("Error 404: No se ha encontrado un Blueprint llamado '" + bpname + "' para el autor '" + author + "'", HttpStatus.NOT_FOUND);
			
		} catch (Exception e) {
			Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, e);
			return new ResponseEntity<>("Error: Se ha presentado un error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
    
    
    @RequestMapping(method = RequestMethod.POST, value = "/blueprints/post", consumes = {"*/*"})	
    public ResponseEntity<?> manejadorPostRecursoNewBlueprint(@RequestBody Blueprint newBlueprint){
        try {
        	bps.addNewBlueprint(newBlueprint);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Se ha presentado un error al realizar el registro", HttpStatus.FORBIDDEN);            
        }        
    }
    
    @RequestMapping(method = RequestMethod.PUT, value = "/blueprints/{author}/{bpname}")
    public ResponseEntity<?> manejadorPutRecursoBlueprint(@PathVariable String author, @PathVariable String bpname, @RequestBody Blueprint setBlueprint) {
    	try {
    		//bps.setBlueprint(author, bpname, setBlueprint);
    		
    		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    		
    	} catch (/*BlueprintNotFound*/Exception ex) {
    		Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("Error 404: No se ha encontrado un Blueprint llamado '" + bpname + "' para el autor '" + author + "'", HttpStatus.NOT_FOUND);
			
		} /*catch (Exception e) {
			Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, e);
			return new ResponseEntity<>("Error: Se ha presentado un error", HttpStatus.INTERNAL_SERVER_ERROR);
		}*/
	}
}

