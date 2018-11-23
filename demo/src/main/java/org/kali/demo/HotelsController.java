package org.kali.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/hotels")
public class HotelsController {
    @Autowired
    private HotelsRepository hotelsRepository;
    @GetMapping("/save")
    public String saveHotelByJsonString(String hotelJsonString) {
        ObjectMapper mapper = new ObjectMapper();
        HotelJson newHotelJson;
        try {
            newHotelJson = mapper.readValue(hotelJsonString, HotelJson.class);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
        if (hotelsRepository.existsById(newHotelJson.getId()))
            return null;
        return hotelsRepository.save(new Hotels(newHotelJson.getId(), newHotelJson)).getHotelId();
    }
    @GetMapping("/update")
    public void updateHotelByJsonString(String hotelJsonString) {
        ObjectMapper mapper = new ObjectMapper();
        HotelJson newHotelJson;
        try {
            newHotelJson = mapper.readValue(hotelJsonString, HotelJson.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        if (!hotelsRepository.existsById(newHotelJson.getId()))
            return;
        hotelsRepository.save(new Hotels(newHotelJson.getId(), newHotelJson));
    }
    @GetMapping("/get")
    public HotelJson getHotelJsonByHotelId(@RequestParam(value = "id") String hotelId) {
        if (hotelId == null)
            return null;
        Optional<Hotels> hotel = hotelsRepository.findById(hotelId);
        if (!hotel.isPresent())
            return null;
        return hotel.get().getHotelJson();
    }
    @GetMapping("/delete")
    public void deleteHotelByHotelId(@RequestParam(value = "id") String hotelId) {
        if (hotelId == null)
            return;
        hotelsRepository.deleteById(hotelId);
    }
    @GetMapping("/all")
    public Set<HotelJson> getAllHotelJsons() {
        Set<HotelJson> res = new HashSet<>();
        for (Hotels hotel : hotelsRepository.findAll())
            res.add(hotel.getHotelJson());
        return res;
    }
}
