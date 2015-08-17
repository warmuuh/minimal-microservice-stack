package com.github.warmuuh.message;

import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.github.warmuuh.message.db.DatabaseModule;
import com.github.warmuuh.message.db.MessageDao;
import com.jayway.restassured.http.ContentType;

import spark.Spark;

public class MessagesIntegrationTest extends IntegrationTestBase{

	
	public static void setupMocks() {
		IntegrationTestBase.setup();
		db = new DatabaseModule(){
			@Override
			protected MessageDao createMessageDao() {
				return new MessageDaoMock();
			}
		};
	}
	
	
	@BeforeClass
	public static void setupCtx(){
		setupMocks();
		startApplication();
	}

	@AfterClass
	public static void tearDown(){
		Spark.stop();
	}
	
	@Test
	public void testGetMessages(){
		get("/user/1/messages/2").then()
		.body("text", hasItems("Test Message 1", "Test Message 2"));
	}
	
	@Test
	public void testSendMessages(){
		given()
			.contentType(ContentType.JSON)
			.body("{\"text\": \"Hello to you too2\"}")
		.when()
			.post("/user/1/messages/2")
		.then()
			.body("text", is("Hello to you too2"));
	}
}
