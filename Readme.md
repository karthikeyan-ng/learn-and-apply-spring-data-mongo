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

## Insert
Use `POST` `legostore/api` to insert json to MongoDB
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