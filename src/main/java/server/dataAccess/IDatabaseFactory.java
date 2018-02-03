package server.dataAccess;

public interface IDatabaseFactory {
    IDatabaseAPI getDatabase() throws DatabaseException;
}
