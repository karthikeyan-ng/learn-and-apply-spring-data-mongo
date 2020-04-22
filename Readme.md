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




  


