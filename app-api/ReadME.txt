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
        "property_value": 200000
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
        "property_value": 150000,
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
    "propert_value": 150000,
    "tags": [
        "Under Contract",
        "Fair Condition"
    ]
}

Notes: The id in the URI is the id of the house. Look on figma on how to
display all the information.


Request 3:
URI: /investments/{id}
Body:
{
    "user_id" : 1,
    "money_invested_all_time" : 1500000,
    "money_invested_this_month" : 5000,
    "interest_earned_all_time": 405,
    "interest_earned_this_month": 25,
    "money_invested_month_stamp" : [
        "YYYY-MM-DD" : (some value here),
                .
                .
                .
    ]

    "interest_earned_month_stamp" : [
        "YYYY-MM-DD": (some value here),
                .
                .
                .
    ]
}

Notes: The time stamps will be used to generate the graphs seen on the front end

House Model:
    Attributes:
        1. House Id
        2. Street Addy
        3. House Zipcode
        4. City
        5. Property Value
        6. Money Raised
        7. Loan asking price
        8. Tags
            - Possibly an array of strings for each tag, or enums?

    Methods:
        1. You need methods to get all the attributes
        2. You need methods to update the following information
            - Money Raised
            - Tags

User Model:
    Attributes:
        1. User id
        2. First Name
        3. Last Name
        4. SSN

    Methods:
        1. Need to have both setter and getter methods to modify all
        the above listed attributes of the user model


POST Requests:






