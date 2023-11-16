package domaine;

public interface Query {
    public enum QueryMethod {
        GET, POST
    }
    
    String getUrl();

    void setUrl(String url);

    QueryImpl.QueryMethod getMethod();

    void setMethod(QueryImpl.QueryMethod method);
}
