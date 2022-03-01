package edu.eci.arsw.blueprints.filters;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;

@Service
public class UndersamplingFiltering implements BlueprintsFilter {

	@Override
	public Blueprint filtering(Blueprint blueprint) {
		Blueprint newBlueprint = new Blueprint(blueprint.getAuthor(), blueprint.getName());
		List<Point> list = blueprint.getPoints();
		for (int i=0; i<list.size(); i++) {
			newBlueprint.addPoint(list.get(i));
			i++;
		}
		return newBlueprint;
	}

}
