Airport-Manager, a school project pro class PA165 Enterprise Java.

## Running Airport-Manager
In folder Airport-Manager run:  `mvn clean install && mvn tomcat7:run`    
Airport-manager runs on: [`http://localhost:8082/pa165`  ](http://localhost:8082/pa165)

## Login credentials
### Role MANAGER:     
* _username_: `manager`   
* _password_: `manager`   

Manager has access to everything. Can create, update, delete all entities.

### Role CASHIER:     
* _username_: `cashier`   
* _password_: `cashier`   

Can view only Flights. Is able to create, update, delete flights with existing stewards, airplanes and airports. 

## Rest interface
Runs on: [`http://localhost:8082/pa165/rest`](http://localhost:8082/pa165/rest)

Project implements REST API for entities:
* **Steward** and methods GET, PUT, POST, DELETE
* **Airport** and methods GET, PUT, POST, DELETE
* **Flight** and method GET
* **Airplane** and method GET

| Title        | URL           | Method |  Params  | Data params (JSON) |
| ------------- |:------------:| :-----:|:--------:| :-----:|
| Get all flights | `/flights/` | GET   |  |    |
| Get all airplanes | `/airplanes/` | GET   |  |    |
| Get all stewards | `/stewards/` | GET   |  |    |
| Get steward | `/stewards/id/{id}` | GET   | Steward ID |    |
| Get steward | `/stewards/name/{firstname}/{lastname}` | GET   | First name, Last name |    |
| Delete steward | `/stewards/id/{id}` | DELETE | Steward ID |    |
| Create new steward | `/stewards/create` | POST |  | {"firstName": "firstName", "lastName":"lastName", "flights":[]}   |
| Update steward | `/stewards/id/{id}` | PUT | Steward ID | {"firstName": "firstNameUpdate", "lastName":"lastNameUpdate", "flights":[]}   |
| Get all airports| `/airports/` | GET   |  |    |
| Get airport | `/airports/id/{id}` | GET   | Airport ID |    |
| Get airport | `/airports/city/{city}` | GET   | Airport city |    |
| Get airport | `/airports/city/{country}` | GET   | Airport country |    |
| Get airport | `/airports/city/{iata}` | GET   | Airport iata |    |
| Get airport | `/airports/city/{name}` | GET   | Airport name |    |
| Delete airport | `/airports/id/{id}` | DELETE | Airport ID |    |
| Create new airport| `/airports/create` | POST |  | {"iata": "iata", "name": "name", "country": "country", "city": "city"}   |
| Update airport | `/airports/id/{id}` | PUT | Airport ID | {"iata": "iata2", "name": "name2", "country": "country2", "city": "city2"}   |

### Example usage commands:
```
curl -i -X GET http://localhost:8082/pa165/rest/airports
```
```
curl -i -X DELETE http://localhost:8082/pa165/rest/stewards/id/12
```
```
curl -X POST -i -H "Content-Type: application/json" --data '{"iata": "iata", "name": "name", "country": "country", "city": "city" }' http://localhost:8082/pa165/rest/airports/create
```
```
curl -i -X GET http://localhost:8082/pa165/rest/stewards/name/Peter/Marek
```

## Team members
* Richard Bariny - (učo: 461431,Github name: [Richardb953](https://github.com/Richardb953)) - team leader
* Karolína Božková (učo: 422243, Github name: [Kayeeec](https://github.com/Kayeeec))
* Andrea Navrátilová - (učo: 396484,Github name: [andrea-n](https://github.com/andrea-n)) 
* Jiří Krejčí - (učo: 359640,Github name: [xkrejci7](https://github.com/xkrejci7))
