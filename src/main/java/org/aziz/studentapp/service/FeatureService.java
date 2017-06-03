package org.aziz.studentapp.service;

import org.aziz.studentapp.model.Feature;

import java.util.*;

public interface FeatureService {

    Map<String, List<Feature>> retrieveFeatures();

    boolean isActive(String featureTitle);

    Map<String, List<Feature>> findAll();

    boolean groupExists();

    String getNODE_ID();

    void setNODE_ID(String NODE_ID);

}
