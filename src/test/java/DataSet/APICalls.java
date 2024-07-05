package DataSet;

public enum APICalls {
    addPlaceAPI("place/add/json"),
    getPlaceAPI("place/get/json"),
    deletePlaceAPI("place/delete/json");
    private final String apiCall;

    APICalls(String apiCall) {
        this.apiCall = apiCall;
    }

    public String getApiCall(){
        return apiCall;
    }
}
