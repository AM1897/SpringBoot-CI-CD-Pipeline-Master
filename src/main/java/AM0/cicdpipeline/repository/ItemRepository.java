package AM0.cicdpipeline.repository;

import AM0.cicdpipeline.model.Item;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ItemRepository extends MongoRepository<Item, String> {
}
