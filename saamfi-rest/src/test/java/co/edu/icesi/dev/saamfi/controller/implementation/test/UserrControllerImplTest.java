package co.edu.icesi.dev.saamfi.controller.implementation.test;

//
//import org.json.simple.JSONObject;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.web.server.LocalServerPort;
//
//import co.edu.icesi.dev.uccareapp.auth.AuthApplication;
//import io.restassured.RestAssured;
//
//@SpringBootTest(classes = AuthApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//class UserrControllerImplTest {
//
//	public static final int SUCCESS_LOGIN_CODE = 200;
//	public static final int NOT_SUCCEEDED_LOGIN = 401;
//
//	@Value("${local.server.port}")
//	private int serverPort;
//
//	@LocalServerPort
//	int port;
//
//	private String username;
//
//	private String password;
//
//	public void buildScenarioUserDatabase() {
//		username = "34567";
//		password = "jackspassword";
//
//	}
//
//	@Test
//	public void testScenario0() {
//
//		System.out.println("**************************" + port);
//		System.out.println("value *************************** " + serverPort);
//		RestAssured.port = port;
//		buildScenarioUserDatabase();
//		JSONObject request = new JSONObject();
//
//		request.put("username", username);
//		request.put("password", password);
//		RestAssured.given().header("Content-Type", "application/json").body(request.toJSONString()).when()
//				.post("http://localhost:9091/uccareapi/auth/login/generatetoken").then().statusCode(SUCCESS_LOGIN_CODE);
//
//	}
//
//}
