package edu.miu.property.controller;

import edu.miu.property.dto.PropertyDto;
//import edu.miu.property.dto.*;
import edu.miu.property.dto.PropertyRequest;
import edu.miu.property.dto.UpdateDto;
import edu.miu.property.helper.ListMapper;
//import edu.miu.property.kafka.KafkaProducer;
import edu.miu.property.model.Category;
//import edu.miu.property.model.*;
import edu.miu.property.model.Property;
import edu.miu.property.service.PropertyService;
import edu.miu.property.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/property")
@Slf4j
public class PropertyController {

    @Autowired
    private PropertyService propertyService;


//    @Autowired
//    private KafkaProducer kafkaProducer;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
    public Property addProperty(
            @RequestPart("images")List<MultipartFile> images,
            @RequestPart("title")String title,
            @RequestPart("category") String category,
            @RequestPart("type")String type,
            @RequestPart("bedrooms")String bedrooms,
            @RequestPart("bathrooms")String bathrooms,
            @RequestPart("description")String description,
            @RequestPart("ownerEmail")String ownerEmail,
            @RequestPart("price")String price,
            @RequestPart("status")String status,
            @RequestPart("area")String area,
            @RequestPart("latitude")String latitude,
            @RequestPart("longitude")String longitude,
            @RequestPart("homeType")String homeType


    )throws Exception{
        PropertyRequest propertyRequest = new PropertyRequest(title
                 ,Category.valueOf(category),type,
                Integer.parseInt(bedrooms),
                Integer.parseInt(bathrooms),
                description,
                ownerEmail,Double.parseDouble(price),Boolean.parseBoolean(status),Double.parseDouble(area));
        log.info("property added --------------------------");
        return propertyService.add(propertyRequest,images,Double.parseDouble(latitude),
                Double.parseDouble(longitude));

    }

    @GetMapping("/category/{category}")
    public List<PropertyDto> getCategory(@PathVariable String category ){
        return propertyService.getCategory(category);

    }

    @PutMapping
    public void updateProperty(@RequestBody UpdateDto updateDto){
        propertyService.updateProperty(updateDto);
        log.info("property updated!");
    }

    @PutMapping("/{id}")
    public void updateStatus(@PathVariable String id){
        propertyService.updateStatus(id);
        log.info("Property status updated!");

    }

    @GetMapping("/owner/{email}")
    public List<PropertyDto> getByOwner (@RequestParam String email){
        return propertyService.getByOwner(email);
    }

    @GetMapping("/{id}")
    public Property getById (@PathVariable String id){
        return propertyService.getById(id);
    }


    @PostMapping("/nearby")
    public List<Property> getNearAvailable(@RequestBody Point location){
        return propertyService.getNearByAvailable(location);
    }

    @GetMapping("/reserved")
    public List<PropertyDto> getReserved(){
        return propertyService.getReserved();
    }

    @GetMapping("/available")
    public List<PropertyDto> getAvailable(){
        return propertyService.getAvailable();
    }


    @GetMapping
    public List<Property> findAll(){
        return propertyService.findAll();
    }

    @PostMapping("/publish")
    public ResponseEntity<String> publish(@RequestBody PropertyRequest request){
//        kafkaProducer.sendMessage(request);
        return ResponseEntity.ok("message sent to the topic");
    }









}
