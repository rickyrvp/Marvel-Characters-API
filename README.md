README

Marvel-Characters-API utilizes the Marvel Comics API to:
1.  Serve an endpoint that returns all the Marvel character ids only, in a JSON array of numbers.
    * Accessed via "/characters"
 2. Serve an endpoint that contains the real-time character information from the Marvel API /v1/public/characters/{characterId} with flexibility to translate the description to a desired language via query parameter.
    * Accessed via "/character/{characterID}?languageCode={languageID}"
    * Included character information: id, name, description, thumbnail
    * Default languageCode if not provided is English
    
The API includes Swagger specification that can be viewed with Swagger UI or imported to Postman

## Configs & API authentication

To run **locally** have your private and public keys set in your system's environment variables.

* ${MARVEL_API_PUBLIC_KEY} = < YOUR-PUBLIC-KEY >
* ${MARVEL_API_PRIVATE_KEY} = < YOUR-PRIVATE-KEY >

Third party API
1. Jyandex - translation API
2. Swagger - visualisation API
