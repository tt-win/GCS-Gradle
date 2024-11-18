package console.infrastructure.storage.enums;

public enum DataSourceType {
    CAFFEINE("Local Cache"),
    REDIS("Redis快取"),
    CASSANDRA("Cassandra資料庫"),
    ORACLE("Oracle資料庫");

    private final String description;

    DataSourceType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
