package edu.miu.property.service;

import edu.miu.property.dto.PropertyDto;
import edu.miu.property.dto.PropertyRequest;
import edu.miu.property.dto.PropertyResponse;
import edu.miu.property.dto.UpdateDto;
import edu.miu.property.model.Property;
import org.springframework.data.geo.Point;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PropertyService {

    Property add(PropertyRequest propertyRequest, List<MultipartFile> images, Double latitude, Double longitude);
    PropertyResponse getProperty(String id);

    // get all reserved
    // get all available
    String uploadFile(MultipartFile file);
    List<String> uploadMultipleFiles(List<MultipartFile> files);
    void updateProperty(UpdateDto updateDto);
    PropertyResponse updateStatus(String id);
    List<PropertyDto> getCategory(String category);
//    List<Property> getSell();
    List<PropertyDto> getByOwner(String ownerEmail);
    List<Property> getNearByAvailable(Point location);

    List<PropertyDto> getReserved();

    List<PropertyDto> getAvailable();

//    List<PropertyDto> findPropertiesByBedRoomsGreaterThanEqual(int numberRooms);



}
