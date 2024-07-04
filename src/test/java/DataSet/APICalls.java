package DataSet;

import groovyjarjarantlr4.v4.codegen.model.SrcOp;

public enum APICalls {
    addPlaceAPI("place/add/json"),
    getPlaceAPI("place/get/json");
    private String apiCall;

    APICalls(String apiCall) {
        this.apiCall = apiCall;
    }

    public String getApiCall(){
        return apiCall;
    }
}
