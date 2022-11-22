package edu.miu.property.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import edu.miu.property.dto.PropertyRequest;
import edu.miu.property.dto.PropertyResponse;
import edu.miu.property.dto.UpdateDto;
import edu.miu.property.model.Property;
import edu.miu.property.repository.PropertyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class PropertyServiceImpl implements PropertyService{

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
        return p;

    }

    @Override
    public PropertyResponse getProperty(String id) {
        Property p = propertyRepo.findById(id).get();
        return PropertyResponse.builder()
                .category(p.getCategory())
                .type(p.getType())
                .bedrooms(p.getBedrooms())
                .title(p.getTitle())
                .description(p.getDescription())
                .price(p.getPrice())
                .status(p.getStatus())
                .ownerEmail(p.getOwnerEmail())
                .build();
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
    public PropertyResponse updateProperty(UpdateDto updateDto) {
        return null;
    }

    @Override
    public PropertyResponse updateStatus(String id) {
        return null;
    }

    @Override
    public List<Property> getRent() {
        return null;
    }

    @Override
    public List<Property> getSell() {
        return null;
    }

    @Override
    public List<Property> getByOwner(String ownerEmail) {
        return null;
    }
}
