# Tradium

Tradium is a new tradig web, where the user can upload products, search for items that other users upload, search for nearby commoditys, locate nearby sellers and check other sellers history.


# Entities
User: Where all configurations, products in the web and personal data is storaged.

Article:  More specific product data, it's price, details, labels, images.

Valoration: Different valorations of the users selling/buying.

Chat messages: Each conversation is saved in the DB so it can be revised everytime.

Users search history: Usefull way to provide recomended articles to the user.

## Advanced funcionalities
Negociation Bot: Funcionality that helps with a agreements when you can not speak with the other person.

SN sharing: An easy way to public your items in different Social networks

Personalized recommendations: So you can find relevant items based on older searchs.

Featured items: Princpal way of income, users can pay to have their items on the front page, an easy way to obtain visibility.

Chat: An easy and quick way of communication, essential in a trading web, the best way of reeching an agreement and negociate properly.

Map localization: Helpfull way to locate nearby items.

## Developers
Roberto Gallado Cava- r.gallardoc@alumnos.urjc.es. @RoberG

Manuel Antonio Romero Venegas- ma.romerov@alumnos.urjc.es @BadgerVM

Daniel García Cisneros- d.garciaci@alumnos.urjc.es. @dgarciaci

Ignacio Ruiz Ardisoni- i.ruiza@alumnos.urjc.es. @TaXiD

## Navigation Diagram

![alt text](https://github.com/RoberG/Tradium/blob/master/diagram.png)

## Development environment
To open our application, on Spring Tool Suite, you have to: 
Press File ->Import -> Existing Maven Projects -> Browse..(And select the folder where the project is placed).
Then press Finish, and the project is already imported.

To prepare the database with mysql. Open mysql workbench, create a new connection with the propierties:
Name: Local Instance MySQL 57, Connection Method: Standard (TCP/IP), Hostname: localhost, Port:3306, username:root, and password:pass.
Then press close, and run the connection.
In the connection, create a new schema, with the name: Test.

After that, come back to the STS, and in the project that u have imported, go to the folder tradium, and run the application, as a 
Spring boot app. 

And that's all!

## Java Class Diagram
![alt text](https://github.com/RoberG/Tradium/blob/master/classDiagram.png)




