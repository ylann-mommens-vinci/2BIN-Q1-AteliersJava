package domaine;

public class Query {
    public enum QueryMethod {
        GET, POST
    }

    private String url;
    private QueryMethod method;

    public Query(String url,QueryMethod method){
        this.url=url;
        this.method=method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public QueryMethod getMethod() {
        return method;
    }
    public void setMethod(QueryMethod method) {
        this.method = method;
    }
}
