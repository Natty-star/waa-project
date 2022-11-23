package edu.miu.property.repository;

import edu.miu.property.dto.PropertyDto;
import edu.miu.property.model.Category;
import edu.miu.property.model.Property;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepo extends MongoRepository<Property,String> {
//    @Query("select p from Property p where p.getCategory = 'RENT'")

    List<Property> findByCategory(String category);
    List<Property> findPropertiesByOwnerEmail(String ownerEmail);

    List<Property> findPropertiesByPriceBetween(double a,double b);
    List<Property> findPropertiesByHomeType(String homeType );
    List<Property> findPropertiesByBedroomsGreaterThanEqual(int numberOfRooms);



//    List<Property> findPropertiesByLocation(Point location);

}
