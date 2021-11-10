
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import junit.framework.Assert;
public class TestAutomate {
	
	String access_token;
	@Test(priority=1)
	public void getAccessToken() {
		
	Response response=((Object) given()).when().header("Authorization", "Basic dXBza2lsbHNfcmVzdF9hZG1pbl9vYXV0aF9jbGllbnQ6dXBza2lsbHNfcmVzdF9hZG1pbl9vYXV0aF9zZWNyZXQ=")
	.log().all()
	.post("http://rest-api.upskills.in/api/rest_admin/oauth2/token/client_credentials");
	response.prettyPrint();
	System.out.println("Status Code is " +response.statusCode());
	System.out.println(access_token);
	
	}
	
	
	private Object given() {
		// TODO Auto-generated method stub
		return null;
	}


	@Test(priority=2, dependsOnMethods="getAccessToken")
	public void getAdminLogin() {
		 String payload = "{\n" +
		            "    \"username\":\"upskills_admin\",\n" +
		            "    \"password\":\"Talent4$$\"\n" +
		            "}";
		Response response=((Object) given()).headers("Content-Type", "application/json").body(payload).auth().oauth2(access_token)
		.when().post("http://rest-api.upskills.in/api/rest_admin/login");
		
		response.prettyPrint();
		//System.out.println("Status Code is " +response.statusCode());
		Assert.assertEquals(response.statusCode(), 200);
		}
	}

