## RELEVANT INFORMATION
All API queries must be preceded by /api


### PRODUCTS

Displays all the information of the selected product

* ##### URL

	< /index>

* ##### Método:

	`GET`

* ##### URL Params
	
	* `id=int`


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

* ##### Método:

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


