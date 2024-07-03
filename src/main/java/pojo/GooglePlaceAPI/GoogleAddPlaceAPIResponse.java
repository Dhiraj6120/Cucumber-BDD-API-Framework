package pojo.GooglePlaceAPI;

public class GoogleAddPlaceAPIResponse {
    /*{
    "status": "OK",
    "place_id": "a903d31fbcd5fff1a00621ed92cc0136",
    "scope": "APP",
    "reference": "4a33b52d003137006b3e7393a7ce41204a33b52d003137006b3e7393a7ce4120",
    "id": "4a33b52d003137006b3e7393a7ce4120"
}*/

    private String status;
    private String place_id;
    private String scope;
    private String reference;
    private String id;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPlace_id() {
        return place_id;
    }

    public void setPlace_id(String place_id) {
        this.place_id = place_id;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
