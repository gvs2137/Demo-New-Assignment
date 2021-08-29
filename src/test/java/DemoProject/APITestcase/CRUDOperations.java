package DemoProject.APITestcase;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import org.testng.annotations.Test;
import DemoProject.Basepackage.BaseclassforAPI;
import DemoProject.Basepackage.payload;


public class CRUDOperations extends BaseclassforAPI
{
	public String id ;
	public String id1;
//POST API	
@Test(priority=1)
public void PostAPI()
{
	RestAssured.baseURI=envprop.getProperty("URL");
	given().log().all().header("Accept","application/json").header("Content-Type","application/json").header("Authorization",envprop.getProperty("Authkey"))
	.body(payload.payloadbody())
	.when().post(envprop.getProperty("getbody1"))
	.then().log().all().assertThat().statusCode(200);
}

//GET API
@Test(priority=2)
public void GetAPI()
{
	RestAssured.baseURI=envprop.getProperty("URL");
	String getresponse=given().log().all().header("Accept","application/json").header("Authorization",envprop.getProperty("Authkey"))
	.when().get(envprop.getProperty("getbody"))
	.then().log().all().assertThat().statusCode(200).extract().asString();
	JsonPath res=new JsonPath(getresponse);
	id =res.getString("id");
	id1=id.substring(1, id.length()-1);
	System.out.println(id1);
	
}

//DELETE API
@Test(priority=4)
public void DeleteAPI()
{
	RestAssured.baseURI=envprop.getProperty("URL");
	given().log().all().header("Accept","application/json").header("Authorization",envprop.getProperty("Authkey"))
	.when().delete(envprop.getProperty("getbody2")+id1)
	.then().log().all().assertThat().statusCode(200);
	
}

//PUTAPI
@Test(priority=3)
public void PutAPI()
{
	RestAssured.baseURI=envprop.getProperty("URL");
	given().log().all().header("Accept","application/json").header("Authorization",envprop.getProperty("Authkey"))
	.header("Content-Type","application/json")
	.body(payload.payloadbody())
	.when().put(envprop.getProperty("getbody2")+id1)
	.then().log().all().assertThat().statusCode(200);
}

}
