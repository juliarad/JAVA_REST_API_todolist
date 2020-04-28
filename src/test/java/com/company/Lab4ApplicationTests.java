package com.company;



import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest
class Lab4ApplicationTests {

	@Test
		public void postTest()  {
			Item item=new Item("Item", "TO_DO");
			given().log()
					.body().contentType("application/json").body(item)
					.when().post("/items")
					.then().log().body().statusCode(HttpStatus.CREATED.value());
		}

		@Test
		public void putTest() {
			long id = new Item("Task1", "TO_DO").getId();
			Item item = new Item("Task1_1","PENDING");
			given().pathParam("id", id).log()
					.body().contentType("application/json").body(item)
					.when().put("/items/{id}")
					.then().log().body().statusCode(HttpStatus.OK.value())
					.and().body("name", equalTo("Task1_1")).and().body("status", equalTo("PENDING"));
		}

		@Test
		public void deleteTest() {
			long id = new Item("Task1", "TO_DO").getId();

			given().pathParam("id",  id).log().body().contentType("application/json")
					.when().delete("/items/{id}")
					.then().log().body()
					.statusCode(HttpStatus.OK.value()).and().body("name", equalTo("Task1"));
		}

		@Test
		public void getOneTest() {
			long id = new Item("Task_AA", "IN_Process").getId();
			given().pathParam("id", id)

					.when().get("/items/{id}")

					.then().log().body().statusCode(HttpStatus.OK.value())
					.and().body("name", equalTo("Task_AA"));
		}

		@Test
		public void getAllTest() {
			new Item("Task_AA", "IN_Process");
			new Item("Task_BB", "PENDING");
			when().get("/items")
					.then().log().body()
					.statusCode(HttpStatus.OK.value())
					.and().body("get(0).name", equalTo("Task_AA"))
					.and().body("get(1).name", equalTo("Task_BB"));
		}

	}