## RELEVANT INFORMATION
All API queries must be preceded by /api


### PRODUCTS

Displays all the information of the selected product

* ##### URL

	< /index/{id} >

* ##### Method:

	`GET`

* ##### URL Params
	
	* `id=[long]`


	* URL
	
			/index/1
 
* ##### Success Response:
          {
			"id": 1,
			"name": "pr1",
			"description": "barata barata1",
			tags": "fashion",
			"image": "\\images\\product_images\\product_default.png",
			"price": 15,
			"bought": false,
			"featured": true
		  }
		  
* ##### Error Response:

	**Code:** 404 NOT FOUND

## User
The following are queries regarding the user entity. These must be preceded by /seller.
All methods linked to User will return the same answers except for those indicated.

* ##### Success Response:

	* HttpStatus.OK

* ##### Error Response:

	* Code: 404 NOT FOUND

### TO OBTAIN USER DATA

Shows all the data of any user.

* ##### URL

	< /seller >

* ##### Method:

	`GET`

* ##### URL Params

	* Required:

		`id=[long]`

* ##### Success Response:
      {
        "id": 1,
		"name": "u1",
		"email": "b1@a.com",
		"locationX": "0000",
		"locationY": "0000",
		"medValoration": 4,
		"image": "\\images\\user_images\\user1.jpg",
		"roles": [
			"USER"
		],
		"products": [] 
      }

### CREATE USER
Adds a user to the system.

* ##### URL

	< /user/new >

* ##### Método:

	`POST`

* ##### Data Params
      {
        "name": "u9",
		"email": "b9@a.com",
		"locationX": "0000",
		"locationY": "0000",
		"image": "\\images\\user_images\\user_default.jpg"
      }	
		
* ##### Success Response:
      {
        "id": 9,
		"name": "u9",
		"email": "b9@a.com",
		"locationX": "0000",
		"locationY": "0000",
		"medValoration": 0,
		"image": "\\images\\user_images\\user1.jpg",
		"roles": [
			"USER"
		],
		"products": [] 
      }
	
### DELETE PRODUCT
Deletes a product of the system.

* ##### URL

	< /product/{id}/delete >

* ##### Method:

	`POST`
	
	* URL
	
		/product/1/delete

* ##### URL Params

	* Required:

		`id=[long]`
		
* ##### Success Response:
          {
			"id": 1,
			"name": "pr1",
			"description": "barata barata1",
			tags": "fashion",
			"image": "\\images\\product_images\\product_default.png",
			"price": 15,
			"bought": false,
			"featured": true
		  }

### VALORATIONS SELLER

Displays all the information from the valuations that other buyers have made to the seller

* ##### URL

	< /seller/{id}/valorations >

* ##### Method:

	`GET`

* ##### URL Params
	
	* `id=[long]`


	* URL
	
			/seller/1/valorations
 
* ##### Success Response:
[

	{
        "buyer": {
            "id": 2,
            "name": "u2",
            "email": "b2@a.com",
            "locationX": "0000",
            "locationY": "0000",
            "medValoration": 0,
            "image": "\\images\\user_images\\user2.jpg",
            "roles": [
                "USER"
            ],
            "products": [
                {},
                {},
                {}
            ]
        },
        "valoration": 5,
        "description": "all ok",
        "date": "19-March-2018"
    },
    {
        "buyer": {
            "id": 2,
            "name": "u2",
            "email": "b2@a.com",
            "locationX": "0000",
            "locationY": "0000",
            "medValoration": 0,
            "image": "\\images\\user_images\\user2.jpg",
            "roles": [
                "USER"
            ],
            "products": [
                {},
                {},
                {}
            ]
        },
        "valoration": 3,
        "description": "meh",
        "date": "21-March-2012"
    },
    {
        "buyer": {
            "id": 4,
            "name": "u4",
            "email": "b4@a.com",
            "locationX": "0000",
            "locationY": "0000",
            "medValoration": 0,
            "image": "\\images\\user_images\\user4.jpg",
            "roles": [
                "USER"
            ],
            "products": []
        },
        "valoration": 4,
        "description": "nani",
        "date": "19-March-2018"
    },
    {
        "buyer": {
            "id": 3,
            "name": "u3",
            "email": "b3@a.com",
            "locationX": "0000",
            "locationY": "0000",
            "medValoration": 0,
            "image": "\\images\\user_images\\user3.jpg",
            "roles": [
                "USER"
            ],
            "products": [
                {}
            ]
        },
        "valoration": 2,
        "description": "bad",
        "date": "19-March-2018"
    },
    {
        "buyer": {
            "id": 5,
            "name": "u5",
            "email": "b5@a.com",
            "locationX": "0000",
            "locationY": "0000",
            "medValoration": 0,
            "image": "\\images\\user_images\\user_default.jpg",
            "roles": [
                "USER"
            ],
            "products": []
        },
        "valoration": 5,
        "description": "perfect",
        "date": "24-October-2017"
    }
]
		  
* ##### Error Response:

	**Code:** 404 NOT FOUND
	
### CREATE PRODUCT
Create a new product to the system.

* ##### URL

	< /product/new >

* ##### Método:

	`POST`

* ##### Data Params
      {
       
       "name": "pr20",
		"description": "Bueno, bonito y barato",
		"tags": "fashion",
		"image": "\\images\\product_images\\product_default.jpg",
		"price": "10"
      }	
	  
		
* ##### Success Response:
    {
    
  		"id": 9,
        "name": "pr20",
		"description": "Bueno, bonito y barato",
		"tags": "fashion",
		"image": "\\images\\product_images\\product_default.jpg",
		"price": "10"
     	"bought": false,
		"featured": true
	}
	
### CHATS DETAILS

Displays all the chats details

* ##### URL

	< /chats >

* ##### Method:

	`GET`
 
* ##### Success Response:
[

    {
        "id": 1,
        "user2": {
            "id": 2
        }
    },
    {
        "id": 2,
        "user2": {
            "id": 3
        }
    },
    {
        "id": 0,
        "user2": {
            "id": 5
        }
    }


		  
### MESSAGES DETAILS

Displays all the details of the messages in a chat

* ##### URL

	< /chats/{id} >

* ##### Method:

	`GET`
	
* ##### URL Params
	
	* `id=[long]`


	* URL
	
			/chats/1
 
* ##### Success Response:
[

    {
        "transmitter": {
            "id": 1
        },
        "text": "hi"
    },
    {
        "transmitter": {
            "id": 1
        },
        "text": "how are u?"
    },
    {
        "transmitter": {
            "id": 2
        },
        "text": "fine thanks"
    }
]
}

### CREATE NEW MESSAGE
Creating a new text message in a chat

* ##### URL

	< /chats/{id}/new >

* ##### Método:

	`POST`

	* ##### URL Params
	
	* `id=[long]`


	* URL
	
			/chats/1/new
			
* ##### Data Params
   
    	{
        "text": "Hello, I am interested in your product"
    	}
	
		
* ##### Success Response:
   
    	{
		"transmitter": {
            "id": 1
        },
        "text": "Hello, I am interested in your product"
    
	
* ##### Error Response:

	**Code:** 404 NOT FOUND


### SEARCH TAGS

Search for all products that have the tag you are looking for

* ##### URL

	< /search/{tag} >

* ##### Method:

	`GET`
	
* ##### URL Params
	
	* `tag=[string]`


	* URL
	
			/search/fashion
 
* ##### Success Response:
{

	"id": 1,
        "name": "pr1",
        "description": "barata barata1",
        "tags": "fashion",
        "image": "\\images\\product_images\\product_default.png",
        "price": 15,
        "bought": false,
        "featured": true
    },
    {
        "id": 10,
        "name": "pr10",
        "description": "barata barata10",
        "tags": "fashion",
        "image": "\\images\\product_images\\product_default.png",
        "price": 10,
        "bought": false,
        "featured": false
    },
    {
        "id": 15,
        "name": "pr15",
        "description": "barata barata15",
        "tags": "fashion",
        "image": "\\images\\product_images\\product_default.png",
        "price": 15,
        "bought": false,
        "featured": false
    },
    {
        "id": 20,
        "name": "pr20",
        "description": "barata barata20",
        "tags": "fashion",
        "image": "\\images\\product_images\\product_default.png",
        "price": 20,
        "bought": false,
        "featured": false
    }


* ##### Error Response:

	**Code:** 404 NOT FOUND


### OBTAIN FEATURED ITEMS

Shows all the featured items.

* ##### URL

	< /featured >

* ##### Method:

	`GET`


* ##### Success Response:
      {

	"id": 1,
        "name": "pr1",
        "description": "barata barata1",
        "tags": "fashion",
        "image": "\\images\\product_images\\product_default.png",
        "price": 15,
        "bought": false,
        "featured": true
    }
    
* ##### Error Response:

	**Code:** 404 NOT FOUND
	
### MAKE AN OFFER
Counter offer for a product.

* ##### URL

	< /product/{id}/offer>

* ##### Method:

	`GET`
	
	* URL
	
		/product/1/delete

* ##### URL Params

	* Required:

		`id=[long]`
		
* ##### Data Params
      
      14
		
* ##### Success Response:
          {
	  
			"id": 1,
			"name": "pr1",
			"description": "barata barata1",
			tags": "fashion",
			"image": "\\images\\product_images\\product_default.png",
			"price": 15,
			"bought": false,
			"featured": true
		  }
* ##### Error Response:

	**Code:** 404 NOT FOUND
	**Code:** 401 NOT AUTHORIZED
	
		  
### BUY A PRODUCT
Buys a product

* ##### URL

	< /product/{id}/Buy>

* ##### Method:

	`POST`
	
	* URL
	
		/product/1/buy

		
		
* ##### Success Response:
          {
	  
			"id": 1,
			"name": "pr1",
			"description": "barata barata1",
			tags": "fashion",
			"image": "\\images\\product_images\\product_default.png",
			"price": 15,
			"bought": true,
			"featured": true
		  }
* ##### Error Response:

	**Code:** 404 NOT FOUND
	**Code:** 401 NOT AUTHORIZED
	
### SEND A VALORATION
Buys a product

* ##### URL

	< /user/{id}/valoration>

* ##### Method:

	`POST`
	
	* URL
	
		/user/{id}/valoration

		
* ##### Data Params
{

	"valoration": 5,
        "description": "all ok",
     
}
* ##### Success Response:
          {
			"id": 1,
			"name": "pr1",
			"description": "barata barata1",
			tags": "fashion",
			"image": "\\images\\product_images\\product_default.png",
			"price": 15,
			"bought": true,
			"featured": true
		  }
* ##### Error Response:

	**Code:** 404 NOT FOUND
	**Code:** 401 NOT AUTHORIZED
	
		  
