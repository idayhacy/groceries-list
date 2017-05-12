# Groceries List App

An app that allows users (say a family) to put down requests for groceries items, and then compile the groceries list per store, or per user

## 1. Compile ##
`groceries-xenon$ mvn clean install`

## 2. Run ##

`groceries-xenon$ java -jar target/groceries-xenon-0.0.1-SNAPSHOT-jar-with-dependencies.jar` 

## 3. Create Users ## 

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

## 4. Create Stores ## 

```
POST http://127.0.0.1:8000/groceries/stores/
{
   "nickname":"safeway",
   "actualname":"Safeway"
}

POST http://127.0.0.1:8000/groceries/stores/
{
   "nickname":"target",
   "actualname":"Target"
}


GET http://127.0.0.1:8000/groceries/stores/
{
  "documentLinks": [
    "/groceries/stores/target",
    "/groceries/stores/safeway"
  ],
  "documentCount": 2,
  "queryTimeMicros": 1999,
  "documentVersion": 0,
  "documentUpdateTimeMicros": 0,
  "documentExpirationTimeMicros": 0,
  "documentOwner": "4b57486c-f153-4b02-bfec-33c91d90c45e"
}
```

## 5. Create Groceries Items Wishlist ##  

```
POST http://127.0.0.1:8000/groceries/requests/
{
   "requestingusername":"yogibear",
   "storenickname":"Safeway",
   "item":"dish soap",
   "quantity":"2",
   "units":"bottles"
}

GET http://127.0.0.1:8000/groceries/requests/
{
  "documentLinks": [
    "/groceries/requests/33ea192026a0ec7554f2ff4e342e8"
  ],
  "documentCount": 1,
  "queryTimeMicros": 999,
  "documentVersion": 0,
  "documentUpdateTimeMicros": 0,
  "documentExpirationTimeMicros": 0,
  "documentOwner": "4b57486c-f153-4b02-bfec-33c91d90c45e"
}

GET http://127.0.0.1:8000/groceries/requests/33ea192026a0ec7554f2ff4e342e8
{
  "requestingusername": "yogibear",
  "storenickname": "Safeway",
  "item": "dish soap",
  "quantity": "2",
  "units": "bottles",
  "documentVersion": 0,
  "documentKind": "net:ycahyadi:groceries:xenon:ItemRequestService:ItemRequestState",
  "documentSelfLink": "/groceries/requests/33ea192026a0ec7554f2ff4e342e8",
  "documentUpdateTimeMicros": 1494442320971000,
  "documentUpdateAction": "PUT",
  "documentExpirationTimeMicros": 0
}
```

## 6. Update Groceries Items Wishlist ## 
Notice the documentVersion incremented

```
PUT http://127.0.0.1:8000/groceries/requests/33ea192026a0ec7554f2ff4e342e8
{
  "requestingusername": "yogibear",
  "storenickname": "Safeway",
  "item": "dish soap",
  "quantity": "4",
  "units": "bottles",
}

GET http://127.0.0.1:8000/groceries/requests/33ea192026a0ec7554f2ff4e342e8
{
  "requestingusername": "yogibear",
  "storenickname": "Safeway",
  "item": "dish soap",
  "quantity": "3",
  "units": "bottles",
  "documentVersion": 1,
  "documentKind": "net:ycahyadi:groceries:xenon:ItemRequestService:ItemRequestState",
  "documentSelfLink": "/groceries/requests/33ea192026a0ec7554f2ff4e342e8",
  "documentUpdateTimeMicros": 1494442320971000,
  "documentUpdateAction": "PUT",
  "documentExpirationTimeMicros": 0
}
```

## 7. Lookup groceries list by stores ## 
(NOTE: Not yet working)

```
GET http://127.0.0.1:8000/groceries/list/stores/safeway

```