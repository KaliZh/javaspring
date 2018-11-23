package org.kali.demo;

import javax.persistence.Embeddable;

@Embeddable
public class Site {
    private String label;
    private String url;
    public String getLabel() {
        return label;
    }
    public String getUrl() {
        return url;
    }
    public void setLabel(String label) {
        this.label = label;
    }
    public void setUrl(String url) {
        this.url = url;
    }
}

