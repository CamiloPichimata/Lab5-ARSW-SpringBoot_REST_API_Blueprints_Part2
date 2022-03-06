/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;

// import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

/**
 *
 * @author hcadavid
 */
@Service
public class InMemoryBlueprintPersistence implements BlueprintsPersistence{

    private final Map<Tuple<String,String>,Blueprint> blueprints=new ConcurrentHashMap<>();

    public InMemoryBlueprintPersistence() {
        //load stub data
        Point[] pts=new Point[]{new Point(140, 140),new Point(115, 115)};
        Blueprint bp=new Blueprint("_authorname_", "_bpname_ ",pts);
        blueprints.put(new Tuple<>(bp.getAuthor(),bp.getName()), bp);
        
        Point[] pointsCamilo = new Point[] {new Point(10, 10), new Point(12, 15), new Point(30, 54), 
				new Point(30, 54), new Point(30, 54), new Point(3, 5), new Point(10,12)};
		Blueprint bp1 = new Blueprint("Camilo", "Blueprint 1", pointsCamilo);
		blueprints.put(new Tuple<>(bp1.getAuthor(),bp1.getName()), bp1);

		Point[] pointsAndres = new Point[] {new Point(13, 13), new Point(50, 54), new Point(50, 54), 
				new Point(50, 54), new Point(3, 5), new Point(10,12), new Point(15, 10)};
		Blueprint bp2 = new Blueprint("Andres", "Blueprint 2", pointsAndres);
		blueprints.put(new Tuple<>(bp2.getAuthor(),bp2.getName()), bp2);
		
		Point[] pointsCamilo1 = new Point[] {new Point(10, 10), new Point(30, 54), new Point(12,12)};
		Blueprint bp3 = new Blueprint("Camilo", "Blueprint3", pointsCamilo1);
		blueprints.put(new Tuple<>(bp3.getAuthor(),bp3.getName()), bp3);
    }    
    
    @Override
    public void saveBlueprint(Blueprint bp) throws BlueprintPersistenceException {
        if (blueprints.containsKey(new Tuple<>(bp.getAuthor(),bp.getName()))){
            throw new BlueprintPersistenceException("The given blueprint already exists: "+bp);
        }
        else{
            blueprints.put(new Tuple<>(bp.getAuthor(),bp.getName()), bp);
        }
    }

    @Override
    public Blueprint getBlueprint(String author, String bprintname) throws BlueprintNotFoundException {
        return blueprints.get(new Tuple<>(author, bprintname));
    }

	@Override
	public Set<Blueprint> getBlueprintsByAuthor(String author) {
		Set<Blueprint> setBlueprintsByAuthor = new HashSet<Blueprint>();
		blueprints.forEach((k,v) -> {
			String authorTemp = v.getAuthor();
			if (authorTemp.equals(author)) {
				setBlueprintsByAuthor.add(v);
			}
		});
		
		return setBlueprintsByAuthor;
	}

	@Override
	public Set<Blueprint> getAllBlueprints() {
		Set<Blueprint> setAllBlueprints = new HashSet<Blueprint>();
		blueprints.forEach((k,v) -> {
			setAllBlueprints.add(v);
		});
		
		return setAllBlueprints;
	}

	@Override
	public void setBlueprint(String author, String bpname, List<Point> points) throws BlueprintNotFoundException {
		Blueprint bp = getBlueprint(author, bpname);
		bp.setPoints(points);
	}
    
}
