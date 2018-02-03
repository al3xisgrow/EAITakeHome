package server.dataAccess;



public class ElasticsearchFactory implements IDatabaseFactory {
    public ElasticsearchFactory() {}

    @Override
    public IDatabaseAPI getDatabase() throws DatabaseException {
        return ElasticsearchAPI.getInstance();
    }


}

