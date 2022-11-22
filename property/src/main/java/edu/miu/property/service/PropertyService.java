package edu.miu.property.service;

import edu.miu.property.dto.PropertyRequest;
import edu.miu.property.dto.PropertyResponse;
import edu.miu.property.dto.UpdateDto;
import edu.miu.property.model.Property;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PropertyService {

    Property add(PropertyRequest propertyRequest, List<MultipartFile> images, Double latitude, Double longitude);
    PropertyResponse getProperty(String id);
    String uploadFile(MultipartFile file);
    List<String> uploadMultipleFiles(List<MultipartFile> files);
    PropertyResponse updateProperty(UpdateDto updateDto);
    PropertyResponse updateStatus(String id);
    List<Property> getRent();
    List<Property> getSell();
    List<Property> getByOwner(String ownerEmail);


}
