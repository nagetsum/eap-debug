package sample.jdbc.rs;

public class Status {
    private final String dataSourceName;
    private final boolean isValid;

    public Status(String dataSourceName, boolean isValid) {
        this.dataSourceName = dataSourceName;
        this.isValid = isValid;
    }

    public String getDataSourceName() {
        return dataSourceName;
    }

    public boolean isValid() {
        return isValid;
    }
}
