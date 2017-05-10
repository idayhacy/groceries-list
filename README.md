# hello-world-xenon

1. `groceries-xenon$ mvn clean install`
2. `groceries-xenon$ java -jar target/groceries-xenon-0.0.1-SNAPSHOT-jar-with-dependencies.jar`
3. Create Users:

```
POST http://127.0.0.1:8000/groceries/users/
{
   "username":"yogibear",
   "firstname":"Yogi",
   "lastname":"Bear"
}

POST http://127.0.0.1:8000/groceries/users/
{
   "username":"booboo",
   "firstname":"Boo",
   "lastname":"Boo"
}

GET http://127.0.0.1:8000/groceries/users/
{
  "documentLinks": [
    "/groceries/users/yogibear",
    "/groceries/users/booboo"
  ],
  "documentCount": 2,
  "queryTimeMicros": 3000,
  "documentVersion": 0,
  "documentUpdateTimeMicros": 0,
  "documentExpirationTimeMicros": 0,
  "documentOwner": "4b57486c-f153-4b02-bfec-33c91d90c45e"
}
```