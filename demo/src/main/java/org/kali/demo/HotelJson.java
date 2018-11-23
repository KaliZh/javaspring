package org.kali.demo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Embeddable
public class HotelJson {
    private String id;
    private String name;
    private String catid;
    private double[] point;
    private String addr;
    private String img;
    @Embedded
    private Site site;
    public enum Services {
        restaurant, transfer, bar, fitness, beach
    }
    @ElementCollection(targetClass = Services.class)
    @Enumerated(EnumType.STRING)
    private Set<Services> services = new HashSet<>();
    @JsonCreator
    public HotelJson() {}
    public HotelJson(@JsonProperty(value = "id", required = true) String id,
                     @JsonProperty(value = "name", required = true) String name,
                     @JsonProperty(value = "addr", required = true) String addr,
                     @JsonProperty(value = "img", required = true) String img) {
        if (id.length() > 12 || name.length() > 255
                || addr.length() > 255 || img.length() > 255)
            throw new IllegalArgumentException("invalid size");
        this.id = id;
        this.name = name;
        this.addr = addr;
        this.img = img;
    }
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getCatid() {
        return catid;
    }
    public double[] getPoint() {
        return point;
    }
    public String getAddr() {
        return addr;
    }
    public String getImg() {
        return img;
    }
    public Site getSite() {
        return site;
    }
    public Set<Services> getServices() {
        return services;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setCatid(String catid) {
        this.catid = catid;
    }
    public void setPoint(double[] point) {
        this.point = point;
    }
    public void setAddr(String addr) {
        this.addr = addr;
    }
    public void setImg(String img) {
        this.img = img;
    }
    public void setSite(Site site) {
        this.site = site;
    }
    public void setServices(Set<Services> services) {
        this.services = services;
    }
}
