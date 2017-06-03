package org.aziz.studentapp.model;

import java.io.Serializable;

public class Feature implements Serializable {
    private String title;
    private boolean status;

    public Feature(String title, boolean status) {
        this.title = title;
        this.status = status;
    }

    public Feature() {

    }

    public String getTitle() {
        return title;
    }

    public boolean getStatus() {
        return status;
    }

}
