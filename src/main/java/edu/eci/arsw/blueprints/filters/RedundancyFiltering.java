package edu.eci.arsw.blueprints.filters;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;

//@Service
public class RedundancyFiltering implements BlueprintsFilter {

	@Override
	public Blueprint filtering(Blueprint blueprint) {
		Blueprint newBlueprint = new Blueprint(blueprint.getAuthor(), blueprint.getName());
		List<Point> list = blueprint.getPoints();
		Point point1 = list.get(0);
		newBlueprint.addPoint(point1);
		Point point2;
		for (int i=1; i<list.size(); i++) {
			point2 = list.get(i);
			if (!point1.equals(point2)) {
				newBlueprint.addPoint(point2);
			}
			point1 = point2;
		}
		return newBlueprint;
	}

}
