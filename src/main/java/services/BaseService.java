package services;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class BaseService {
    protected RequestSpecification requestSpecification;
    protected ResponseSpecification responseSpecification;
    protected Response response;
}
