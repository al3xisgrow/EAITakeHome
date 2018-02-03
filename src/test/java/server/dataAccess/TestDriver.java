package server.dataAccess;

public class TestDriver {
    // Run Tests
    public static void main(String[] args) {
        org.junit.runner.JUnitCore.main(
                "server.dataAccess.ElasticsearchAPITest", // Unit tests, 3/5 lightly tested
                "client.ServerAPITest" // Integration tests, not complete
        );
    }
}
