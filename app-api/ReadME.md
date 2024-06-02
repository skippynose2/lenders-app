This folder is for the source code of the API.
The API will be built using maven. This file will contain infomration
about the pathing of the api request, the body, and the response of each path.


GET Requests:

Request 1:
URI: /allhouses
Body: 
[
    {
        "id": 1,
        "addy_street": "138 Valley View Drive",
        "addy_zipcode": 1456,
        "addy_city": "ROCHESTER",
        "money_raised": 50000,
        "target_money": 100000,
        "tags": [
            "Under Contract",
            "Fair Condition"
        ]
    },
    {
        "id": 2,
        "addy_street": "140 Alexander Street",
        "addy_zipcode": 29072,
        "addy_city": "Lexington",
        "money_raised": 50000,
        "target_money": 100000,
        "tags": [
            "Under Contract",
            "Fair Condition"
        ]
    }
]

Notes: The body is essentially just a list of houses, with there ids, street
addy, the money raised, the target loan, and tags

Request 2:
URI: /house/{id}
Body:
{
    "id": 1,
    "addy_street": "138 Valley View Drive",
    "addy_zipcode": 1456,
    "addy_city": "ROCHESTER",
    "money_raised": 50000,
    "target_money": 100000,
    "tags": [
        "Under Contract",
        "Fair Condition"
    ]
}

Notes: The id in the URI is the id of the house. Look on figma on how to
display all the information.




POST Requests:






