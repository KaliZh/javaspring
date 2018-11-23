package org.kali.demo;

import javax.persistence.*;

@Entity
public class Hotels {
    @Id
    private String hotelId;
    @Embedded
    private HotelJson hotelJson;
    public Hotels() {}
    public Hotels(String hotelId, HotelJson hotelJson) {
        this.hotelId = hotelId;
        this.hotelJson = hotelJson;
    }
    public String getHotelId() {
        return hotelId;
    }
    public HotelJson getHotelJson() {
        return hotelJson;
    }
    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }
    public void setHotelJson(HotelJson hotelJson) {
        this.hotelJson = hotelJson;
    }
}
