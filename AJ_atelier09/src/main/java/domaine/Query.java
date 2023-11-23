package domaine;

public interface Query {
    /**
     * Gets the URL associated with the query.
     * @return The URL of the query.
     */
    String getUrl();

    /**
     * Sets the URL for the query.
     * @param url The URL to set for the query.
     */
    void setUrl(String url);

    /**
     * Gets the HTTP method (GET or POST) associated with the query.
     * @return The HTTP method of the query.
     */
    QueryMethod getMethod();

    /**
     * Sets the HTTP method for the query.
     * @param method The HTTP method to set for the query.
     */
    void setMethod(QueryMethod method);

    /**
     * The QueryMethod enum represents the HTTP methods supported by the Query interface.
     */
    public enum QueryMethod {
        /**
         * The GET HTTP method.
         */
        GET,

        /**
         * The POST HTTP method.
         */
        POST
    }
}
