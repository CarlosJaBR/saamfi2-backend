package co.edu.icesi.dev.saamfi.controller.implementation.test;

//import org.json.simple.JSONObject;
//import org.junit.Before;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import co.edu.icesi.dev.uccareapp.auth.AuthApplication;
//import io.restassured.RestAssured;
//
//@SpringBootTest(classes = AuthApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class AuthControllerImplTest {
//
//	public static final int SUCCESS_LOGIN_CODE = 200;
//
//	public static final int NOT_SUCCEEDED_LOGIN = 401;
//
//	private String username;
//
//	private String password;
//
//	@Value("${local.server.port}")
//	private int serverPort;
//
////	@LocalServerPort
////	private int port;
//
//	public AuthControllerImplTest() {
//		// TODO Auto-generated constructor stub
//	}
//
//	public void buildScenarioUserDatabase() {
//		username = "34567";
//		password = "jackspassword";
//
//	}
//
//	public void buildScenarioUserLDAP() {
//		username = "12345";
//		password = "bobspassword";
//
//	}
//
//	public void buildScenarioWrongCredentials() {
//
//		username = "34567";
//		password = "wrongpassword";
//	}
//
//	@Before
//	public void setUp() throws Exception {
//
//		String port = System.getProperty("server.port");
//		RestAssured.port = Integer.valueOf(port);
//		System.out.println("**************************" + port);
//		System.out.println("************************** Rest " + RestAssured.port);
//	}
//
//	/**
//	 * Test authentication with database user
//	 */
//
//	@Test
//	public void testScenario0() {
//
////		System.out.println("**************************" + port);
////		System.out.println("value *************************** " + serverPort);
////		RestAssured.port = port;
//
//		String port = System.getProperty("server.port");
//		RestAssured.port = Integer.valueOf(port);
//		System.out.println("**************************" + port);
//		System.out.println("************************** Rest " + RestAssured.port);
//
//		buildScenarioUserDatabase();
//		JSONObject request = new JSONObject();
//
//		request.put("username", username);
//		request.put("password", password);
//		RestAssured.given().header("Content-Type", "application/json").body(request.toJSONString()).when()
//				.post("http://localhost:8002/uccareapi/auth/login/generatetoken").then().statusCode(SUCCESS_LOGIN_CODE);
//
//	}
//
//	/**
//	 * Test authentication with LDAP user
//	 */
//	@Test
//	public void testScenario1() {
//
//		buildScenarioUserLDAP();
//		JSONObject request = new JSONObject();
//
//		request.put("username", username);
//		request.put("password", password);
//		RestAssured.given().header("Content-Type", "application/json").body(request.toJSONString()).when()
//				.post("http://localhost:8002/uccareapi/auth/login/generatetoken").then().statusCode(SUCCESS_LOGIN_CODE);
//
//	}
//
//	@Test
//	public void testScenario2() {
//
//		buildScenarioWrongCredentials();
//		JSONObject request = new JSONObject();
//
//		request.put("username", username);
//		request.put("password", password);
//		RestAssured.given().header("Content-Type", "application/json").body(request.toJSONString()).when()
//				.post("http://localhost:8002/uccareapi/auth/login/generatetoken").then()
//				.statusCode(NOT_SUCCEEDED_LOGIN);
//
//	}
//
//}
