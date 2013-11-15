package fr.enseirb.it310.projects.jee.ft.api;

import java.util.List;

import fr.enseirb.it310.projects.jee.ft.model.Feature;

public interface FeatureRepository {
	public void registerFeature(Feature f);
	public void removeFeature(Feature f);
	public List<Feature> listFeatures();
}
