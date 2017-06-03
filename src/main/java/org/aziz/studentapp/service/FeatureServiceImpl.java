package org.aziz.studentapp.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aziz.studentapp.model.Feature;

import java.net.URL;
import java.util.*;

public class FeatureServiceImpl implements FeatureService {
    private String NODE_ID;

    public FeatureServiceImpl() {
        NODE_ID = this.getEnv();
    }

    @Override
    public Map<String, List<Feature>> retrieveFeatures() {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, List<Feature>> features;

        try {
            URL url = new URL("https://featuresservice.herokuapp.com/myfeatures");
            features = objectMapper.readValue(url,
                    new TypeReference<Map<String, List<Feature>>>() {
                    });
            return features;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Map<String, List<Feature>> findAll() {
        return this.retrieveFeatures();
    }

    public boolean groupExists() {
        return this.findAll().keySet().contains(NODE_ID);
    }

    public boolean isActive(String featureTitle) {
        if (this.groupExists()) {
            for (Feature feature : this.findAll().get(NODE_ID)) {
                if (feature.getTitle().equals(featureTitle)) {
                    if (feature.getStatus()) {
                        return true;
                    }
                }
            }
        } else {
            NODE_ID = "";
        }
        return false;
    }

    public String getNODE_ID() {
        return NODE_ID;
    }

    public void setNODE_ID(String NODE_ID) {
        this.NODE_ID = NODE_ID;
    }

    private String getEnv() {
        String group = "";
        Map<String, String> env = System.getenv();
        for (String envName : env.keySet()) {
            if (envName.equals("GROUP")) {
                group = env.get(envName);
            }
        }
        System.out.println(group);
        return group;
    }
}
