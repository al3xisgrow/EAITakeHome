# EAITakeHome
Coding challenge for EAI

Build a RESTful API for an address book with an Elasticsearch data store.

Since this is a public repository and I am not sure that EAI wants me to share their coding challenge's spec, I will keep this README short.


What I've completed (though I wish I had more time to test it):
   * Server: 
      * HTTP handlers
      * Server main file
      * Services 
      * Database API's & interfaces (NOTE: BUT NOT DATA ACCESS OBJECTs)
      * Exception classes
   * Client: 
      * Communicator (talks to server)
      * API (though since the server's part isn't completely finished, not as useful as it will be eventually)
      * Exception classes
   * Shared: 
      * Model class
      * Model's input validation
      * Request and response classes
      * Serialization (/utils/*)
   * Deliverables:
      * Some data is backed by Elasticsearch
      * Bounds of input values to model class are defined
      * API is successfully exposed over HTTP
      * Host/port for Elasticsearch server is configurable to allow an evaluator to run it locally
      * Unit tests are started that would verify the storage/retrieval of the code were it debugged completely
      
      
What I would do if I had more time:
   * TEST, TEST, TEST - Finish the Junit integration & unit tests that I've started working on
   * Finish integration of Elasticsearch & Contact data access object
   * Finish the User Interface (/client/Main) 
   * Deliverables:
      * Test storage/retrieval of data in Elasticsearch - If there were time to do this, 3 (almost 4)/5 API calls would be finished.
      * Make it easier for someone to test my API
   

   
