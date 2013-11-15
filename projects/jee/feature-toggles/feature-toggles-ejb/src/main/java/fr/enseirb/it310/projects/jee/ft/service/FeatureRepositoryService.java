package fr.enseirb.it310.projects.jee.ft.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import fr.enseirb.it310.projects.jee.ft.api.FeatureRepository;
import fr.enseirb.it310.projects.jee.ft.model.Feature;

// The @Stateless annotation eliminates the need for manual transaction demarcation
@Stateless
@Remote(value=FeatureRepository.class)
public class FeatureRepositoryService implements FeatureRepository {
	private static final Logger LOG = Logger.getLogger(FeatureRepositoryService.class.getName());

	@Override
	public void registerFeature(Feature f) {
		LOG.info("Registering " + f.getKey());
		// TODO register in DB
	}

	@Override
	public void removeFeature(Feature f) {
		LOG.info("Removing " + f.getKey());
		// TODO remove from DB
	}

	@Override
	public List<Feature> listFeatures() {
		LOG.info("Retrieving features");
		// TODO retrieve from DB
		return new ArrayList<Feature>();
	}
}
