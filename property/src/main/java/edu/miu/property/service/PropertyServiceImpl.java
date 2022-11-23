package edu.miu.property.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import edu.miu.property.dto.PropertyDto;
import edu.miu.property.dto.PropertyRequest;
import edu.miu.property.dto.PropertyResponse;
import edu.miu.property.dto.UpdateDto;
import edu.miu.property.helper.ListMapper;
import edu.miu.property.model.Property;
import edu.miu.property.repository.PropertyRepo;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PropertyServiceImpl implements PropertyService{

    @Autowired
    private  MongoTemplate mongoTemplate;

    @Autowired
    private ListMapper<Property,PropertyDto> propertyToPropertyDto;

    @Autowired
    private ModelMapper modelMapper;


    @Value("${AWS_S3_BUCKET_NAME}")
    private String bucketName;

    private AmazonS3 amazonS3;

    public PropertyServiceImpl(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    @Autowired
    private PropertyRepo propertyRepo;

    @Override
    public Property add(PropertyRequest propertyRequest, List<MultipartFile> images, Double latitude, Double longitude) {
        Double[] location = new Double[2];
        location[0] = longitude;
        location[1] = latitude;

        List<String> imageUrls =  uploadMultipleFiles(images);


                Property p = Property.builder()
                .category(propertyRequest.getCategory())
                .type(propertyRequest.getType())
                .bedrooms(propertyRequest.getBedrooms())
                .title(propertyRequest.getTitle())
                .description(propertyRequest.getDescription())
                .price(propertyRequest.getPrice())
                .status(propertyRequest.getStatus())
                .ownerEmail(propertyRequest.getOwnerEmail())
                .images(imageUrls)
                .location(location)
                .build();

        propertyRepo.save(p);

        Property property = Property.builder()
                .category(propertyRequest.getCategory())
                .type(propertyRequest.getType())
                .bedrooms(propertyRequest.getBedrooms())
                .title(propertyRequest.getTitle())
                .description(propertyRequest.getDescription())
                .price(propertyRequest.getPrice())
                .status(propertyRequest.getStatus())
                .ownerEmail(propertyRequest.getOwnerEmail())
                .images(imageUrls)
                .location(location)
                .build();

//        modelMapper.map(p,PropertyDto.class)
        return property;

    }

    @Override
    public PropertyResponse getProperty(String id) {
        Property p = propertyRepo.findById(id).get();
        return modelMapper.map(p,PropertyResponse.class);

    }

    @Override
    public String uploadFile(MultipartFile file) {
        String filenameExtension = StringUtils.getFilenameExtension(file.getOriginalFilename());

        String key = System.currentTimeMillis() + "_" + filenameExtension;

        ObjectMetadata metaData = new ObjectMetadata();
        metaData.setContentLength(file.getSize());
        metaData.setContentType(file.getContentType());

        try {
            amazonS3.putObject("property-managment", key, file.getInputStream(), metaData);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An exception occured while uploading the file");
        }

//        amazonS3.setObjectAcl(buketName, key, CannedAccessControlList.PublicRead);
        URL url = amazonS3.getUrl("property-managment", key);

        return url.toString();
    }

    @Override
    public List<String> uploadMultipleFiles(List<MultipartFile> files) {
        List<String> urlList = new ArrayList<>();

        files.forEach(file -> {
            urlList.add(uploadFile(file));
        });

        return urlList;
    }

    @Override
    public void updateProperty(UpdateDto updateDto) {
        Property property = propertyRepo.findById(updateDto.getId()).get();
//        modelMapper.map(property,PropertyResponse.class);
         propertyRepo.save(property);


    }

    @Override
    public PropertyResponse updateStatus(String id) {
        Property p = propertyRepo.findById(id).get();
        p.setStatus(!p.getStatus());
        return modelMapper.map(p,PropertyResponse.class);
    }

    @Override
    public List<PropertyDto> getCategory(String category) {
        return  (List<PropertyDto>) propertyToPropertyDto.mapList(propertyRepo.findByCategory(category),new PropertyDto());
    }

//    @Override
//    public List<Property> getSell() {
//        return null;
//    }

    @Override
    public List<PropertyDto> getByOwner(String ownerEmail) {
        List<Property> properties = propertyRepo.findPropertiesByOwnerEmail(ownerEmail);
//         return properties.stream()
//                .map(p -> modelMapper.map(p,PropertyDto.class)).collect(Collectors.toList());

         return  (List<PropertyDto>) propertyToPropertyDto.mapList(properties,new PropertyDto());

    }

    @Override
    public List<Property> getNearByAvailable(Point location) {
        List<Property> properties = new ArrayList<>();
        //location = new Point(-73.99171, 40.738868);

        Query getAvailable = new Query(Criteria.where("status").is(false));
        NearQuery getNear = NearQuery.near(location).maxDistance(new Distance(10, Metrics.MILES));
        getNear.query(getAvailable);

        GeoResults<Property> nearProperties = mongoTemplate.geoNear(getNear, Property.class);
        if(nearProperties.getContent().size() > 0){
            log.warn("Found near by properties!");
            nearProperties.forEach(p -> properties.add(p.getContent()));
        } else {
            log.warn("Could not find near by available properties");
        }


        return properties;
    }

    @Override
    public List<PropertyDto> getReserved() {
        List<Property> properties = propertyRepo.findAll();
        List<Property> reserved = new ArrayList<>();
        properties.stream()
                .filter(property -> !property.getStatus())
                .collect(Collectors.toList()).addAll(reserved);
        return (List<PropertyDto>) propertyToPropertyDto.mapList(reserved,new PropertyDto());
    }

    @Override
    public List<PropertyDto> getAvailable() {
        List<Property> properties = propertyRepo.findAll();
        List<Property> available = new ArrayList<>();
        properties.stream()
                .filter(property -> property.getStatus() == true)
                .collect(Collectors.toList()).addAll(available);
        return (List<PropertyDto>) propertyToPropertyDto.mapList(available,new PropertyDto());
    }

    List<PropertyDto> findPropertiesByPriceBetween(double min, double max){
        List<Property> properties = propertyRepo.findPropertiesByPriceBetween(min, max);

        return (List<PropertyDto>) propertyToPropertyDto.mapList(properties,new PropertyDto());
    }
    List<PropertyDto> findPropertiesByHomeType(String homeType){
        List<Property> properties = propertyRepo.findPropertiesByHomeType(homeType);

        return (List<PropertyDto>) propertyToPropertyDto.mapList(properties,new PropertyDto());
    }
    List<PropertyDto> findPropertiesByBedRoomsGreaterThanEqual(int numberRooms){
        List<Property> properties = propertyRepo.findPropertiesByBedroomsGreaterThanEqual(numberRooms);

        return (List<PropertyDto>) propertyToPropertyDto.mapList(properties,new PropertyDto());
    }



}
