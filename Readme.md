# Learn MongoDB

#### Key Annotations
* `@Document`
* `@Field`
* `@Id`
* `@Indexed`
* `@TextIndexed`
* `@Transient`
* `@PersistenceConstructor`

## References
Make use of online MongoDB cloud platform to create a Database and querying with it by using https://cloud.mongodb.com

## MongoTemplate

### Insert
Use `POST` `/legostore/api` to insert json to MongoDB  

> Use `insert()` method from `MongoTemplate` when you don't have an `Id` is null. A new `Id` will get generated.  

> If the object has an `Id` value: If `Id` value not already present in the collection, will use it to insert. 
>Else MongoDB will throw a `DuplicateKeyException`

 
```json
{
	"name": "NASA Apollo Saturn V",
	"difficulty": "MEDIUM",
	"theme": "NASA",
	"reviews": [
		{
			"userName": "Joe",
			"rating": 9
		}
	],
	"deliveryInfo": {
		"deliveryDate": "2020-04-21",
		"deliveryFee": "50",
		"inStock": true
	},
	"nbParts": 454
}
```

### InsertAll
For Bulk insert use `mongoTemplate.insertAll(Collection<T>)`  
For example: 
```java
this.mongoTemplate.insertAll(
    List.of(mcLarenSenna, skyPolice, milleniumFalcon, mindstormsEve));
```

### Fetch All
Use `GET` `/all` to get all the values from MongoDB Collection
```json
[
    {
        "id": "5e9f369cda730013c3acb0e0",
        "name": "NASA Apollo Saturn V",
        "difficulty": "MEDIUM",
        "theme": "NASA",
        "reviews": [
            {
                "userName": "Joe",
                "rating": 9
            }
        ],
        "deliveryInfo": {
            "deliveryDate": "2020-04-21",
            "deliveryFee": 50,
            "inStock": true
        },
        "nbParts": 0
    }
]
```

### Update a document
Use `PUT` `/legostore/api` to Update a Document to MongoDB. Here I have changed `userName` value.  

> If the object has no `Id (Id is null)`, a new `Id` will get generated.  

> If the object has `Id` value
>   * If Id value not already present, will use it
>   * If Id value present, will update the document.   

```json
{
    "id": "5e9f369cda730013c3acb0e0",
    "name": "NASA Apollo Saturn V",
    "difficulty": "MEDIUM",
    "theme": "NASA",
    "reviews": [
        {
            "userName": "Karthi",
            "rating": 9
        }
    ],
    "deliveryInfo": {
        "deliveryDate": "2020-04-21",
        "deliveryFee": 50,
        "inStock": true
    },
    "nbParts": 0
}
```

### Delete a Document

Use `DELETE` `/legostore/api/{id}` to Delete a Document to MongoDB.  
>Example: `/legostore/api/5e9f3533da730013c3acb0df`  

To verify this Delete, execute `/all`

### Drop all the Documents from the MongoDB Collection

Use `mongoTemplate.dropCollection(LegoSet.class)`  

## Difference Between MongoTemplate and MongoRepository

| MongoTemplate                                                    | MongoRepository                                                                      |
|------------------------------------------------------------------|--------------------------------------------------------------------------------------|
| +More flexible and powerful (more complex queries, aggregations) | +Easier to use because they are a higher abstraction (90% cases)                     |
| -Low level; You need to know how Mongo queries work              | +Friendly syntax for filtering data                                                  |
|                                                                  | + Build on top of JPA, consistent syntax, same methods as repositories over SQL      |
|                                                                  | - Do not work on all use cases; Sometimes you need to fall back to the MongoTemplate |

## MongoRepository

In order to create a Mongo Repository, refer below code snippet.
```java
import com.techstack.mongo.model.LegoSet;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LegoSetRepository extends MongoRepository<LegoSet, String> {
}
```

### Method Name Composition
Spring Data can provide proxy implementation of queries based on method names:

- **findBy**LastName
- **findBy**Age**LessThan**
- **findBy**Active**True**

For more information refer [JPA Query Methods and Query Creation](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation)




