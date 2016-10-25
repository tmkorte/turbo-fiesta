package works.rivet.example;

public class HelloResponse {
    private final String _queryParam;
    private final String _myQueryParam;

    public HelloResponse(String queryParam, String myQueryParam) {
        _queryParam = queryParam;
        _myQueryParam = myQueryParam;
    }

    public String getQueryParam() {
        return _queryParam;
    }

    public String getMyQueryParam() {
        return _myQueryParam;
    }
}
