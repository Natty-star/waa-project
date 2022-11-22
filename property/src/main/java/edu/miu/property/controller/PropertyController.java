package edu.miu.property.controller;

import edu.miu.property.dto.PropertyRequest;
import edu.miu.property.model.Category;
import edu.miu.property.model.Property;
import edu.miu.property.service.PropertyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/property")
@Slf4j
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @PostMapping(value = "/create",consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
    public Property addProperty(
            @RequestPart("images")List<MultipartFile> images,
            @RequestPart("title")String title,
            @RequestPart("category") String category,
            @RequestPart("type")String type,
            @RequestPart("bedrooms")String bedrooms,
            @RequestPart("description")String description,
            @RequestPart("ownerEmail")String ownerEmail,
            @RequestPart("price")String price,
            @RequestPart("status")String status,
            @RequestPart("latitude")String latitude,
            @RequestPart("longitude")String longitude

    )throws Exception{
        PropertyRequest propertyRequest = new PropertyRequest(title
                 ,Category.valueOf(category),type,
                Integer.parseInt(bedrooms),description,
                ownerEmail,Double.parseDouble(price),Boolean.parseBoolean(status));
        log.info("property added --------------------------");
        return propertyService.add(propertyRequest,images,Double.parseDouble(latitude),
                Double.parseDouble(longitude));



    }






}
