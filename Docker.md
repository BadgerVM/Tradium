Docker is available to download [here](https://docs.docker.com/install/#supported-platforms). 

- To run the app you simply have to open a terminal in Docker folder and execute "docker-compose up".
To access the app you have to open your navigator and go to https://localhost:8080 .

- To stop the app you have to execute "docker-compose down" on user terminal.

- The docker-compose.yml file uses a mysql image sets up in port 3306 (Don't forget to stop your local mysql), and a Tradium image, sets up in port 8080.


#### Scripts (Win 7 and above only)

You can use those scripts for build Tradium image and publish it in the repo on dockerhub site.

First of all you have to give permissions to Docker to manipulate you master disk (usually C://). 
Then use right button on them and execute with powershell.


Scripts:

- create_image.ps1 : builds the application image using the Dockerfile.  

- publish_image.ps1 publishes the image into the dockerhub repo. You need to be logged with the account that appears in all docker files (dgaciaci) or modify them in order to use yours.
