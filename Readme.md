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

### Custom Queries
For some business cases, you can't achieve your expectations using Query by method (like findBy). For those cases,
use `@Query` annotation.  

`@Query("{'_id':'123'}")`  

## Query DSL
Don't always use `@Query` annotation which contains statically typed queries. Because, if you change your Class property
you have to change your query accordingly.  

To solve this issue, we can use QueryDSL.

- QueryDSL allows us to write queries that are type safe.
- No more magic Strings

### How it works?
- Scans all classes annotated with `@Document` and their children.
- Generates "Query Types" during the process goal of Maven build.
    - For Example: for a `LegoSet` class ==> QLegoSet class.
- We can then use query types in out code to build complex queries in a 
type safe manner.
 
### Query DSL Maven Dependencies
To start using QueryDSL for Mongo we need to bring in:
- Library Dependencies
```xml
<dependency>
    <groupId>com.querydsl</groupId>
    <artifactId>querydsl-mongodb</artifactId>
    <version>4.0.0</version>
</dependency>
<dependency>
    <groupId>com.querydsl</groupId>
    <artifactId>querydsl-apt</artifactId>
    <version>4.0.0</version>
</dependency>
```
- Plugin    
```xml
<plugin>
    <groupId>com.mysema.maven</groupId>
    <artifactId>apt-maven-plugin</artifactId>
    <version>1.1.3</version>
    <executions>
        <execution>
            <goals>
                <goal>process</goal>
            </goals>
            <configuration>
                <outputDirectory>target/generated-sources/java</outputDirectory>
                <processor>
                    org.springframework.data.mongodb.repository.support.MongoAnnotationProcessor
                </processor>
            </configuration>
        </execution>
    </executions>
</plugin>
```


