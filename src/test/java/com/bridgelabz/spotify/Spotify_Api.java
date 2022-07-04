package com.bridgelabz.spotify;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
public class Spotify_Api {
	String token ;
	String user_id="31zypkepj73qg5qghrgbsyxlzemm";
	String playlist_id="1ju2QCIoXYu82EkUB4bZu4";
	String tracks;
	@BeforeTest
	public void getToken() {
		token ="Bearer BQA5R_zwtHJV6Vl1KivwLBIZxv4Xe5QW9ZhLVBAwkaDRKZfvubN-DdQimCwup1kI3m3Fog2dM6S4JDsfxdv5mk9_2ZEcQwTmXfk2oA6ZVENfHKqb6yzQF1w7oIh4vDMGgHQkdsX2x3tYT-y4AWPJMhDtxtyB1rPd5-wdRb8YBhqahseG1CYvyVQ781qzQ3PvN1QGoDVIiJoScsCHFGcu64pwlJ7HVV_vuMYAzUOOk5L5IIpynzKtQSgdtKl80rUZ0md-Q32gx3Ja5DtGDOPMM7Pqp6PLd6J6g4aBeQbTmnw2y0E4WMpzyrQFA65Z3LexIq14m4SKuUiW";

	}
	@BeforeTest
	public void getTracks() {
		tracks="spotify:track:39ujbBjTwwqUFySaCYDMMT";
		
		//spotify:track:6nZiYSBwPQ7fYnVWkkkj4g
	
	}
	@Test(priority=1)
	public void testGet_CurrentUsersProfile() {
		Response response = RestAssured.given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.when()
				.get(" http://api.spotify.com/v1/me");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	}

	@Test(priority=2)
	public void testGet_Users_Profile() {
		Response response =RestAssured.given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.when()
				.get("	https://api.spotify.com/v1/users/"+user_id+"");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);

	}
	
	
	@Test
	public void createPlaylist() {
		Response response = RestAssured.given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.body("{\r\n"
						+ "  \"name\": \"july today review\",\r\n"
						+ "  \"description\": \"New playlist description\",\r\n"
						+ "  \"public\": false\r\n"
						+ "}")
				.when()
				.post("https://api.spotify.com/v1/users/"+user_id+"/playlists");
		response.prettyPrint();
		response.then().assertThat().statusCode(201);
	}
	@Test
	public void add_Items_to_Playlist() {
		Response response =RestAssured.given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.queryParams("uris", tracks)
			
				.when()
				.post("	https://api.spotify.com/v1/playlists/"+playlist_id+"/tracks");
		response.prettyPrint();
		response.then().assertThat().statusCode(201);

	}
	@Test
	public void RemovePlaylistItem() {
		Response response = RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.body("{\"tracks\":[{\"uri\":\"spotify:track:1iZLpuGMr4tn1F5bZu32Kb\"}]}")
				.when()
				.delete("https://api.spotify.com/v1/playlists/"+playlist_id+"/tracks");
response.prettyPrint();
response.then().assertThat().statusCode(200);

	}
	
	@Test
	public void get_Playlist() {
		Response response =RestAssured.given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.when()
				.get("	https://api.spotify.com/v1/playlists/"+playlist_id+"");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);

	}
	
	@Test
	public void get_Users_Playlists() {
		Response response =RestAssured.given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.when()
				.get("	https://api.spotify.com/v1/users/"+user_id+"/playlists");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);

	}
	
	@Test
	public void get_Playlist_Items() {
		Response response =RestAssured.given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.when()
				.get("	https://api.spotify.com/v1/playlists/"+playlist_id+"/tracks");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);

	}
	
	@Test
	public void get_Current_Users_Playlists() {
		Response response =RestAssured.given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/me/playlists");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);

	}
	
	@Test
	public void change_Playlist_Details() {
		Response response =RestAssured.given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.body("{\r\n"
						+ "  \"name\": \"Arjun playlist\",\r\n"
						+ "  \"description\": \"Updated playlist description\",\r\n"
						+ "  \"public\": false\r\n"
						+ "}")
				.when()
				.put("	https://api.spotify.com/v1/playlists/"+playlist_id+"");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);

	}
	
	@Test
	public void updatePlaylistItems() {
		Response response = RestAssured.given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.body("{\r\n"
						+ "  \"range_start\": 1,\r\n"
						+ "  \"insert_before\": 3,\r\n"
						+ "  \"range_length\": 2\r\n"
						+ "}")
				.when()
				.put("https://api.spotify.com/v1/playlists/"+playlist_id+"/tracks");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	}
	@Test
	public void searchforItem() {
		Response response = RestAssured.given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.queryParam("q","Arijit singh")
				.queryParam("type","track")
				.when()
				.get("https://api.spotify.com/v1/search");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	}
	
	@Test
	public void GetTrackAudioAnalysis() {
		Response response = RestAssured.given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/audio-analysis/5Ox43gIWUNW6pAgx3F3oi7");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	}
	
	@Test
	public void getTracksAudioFeatures() {
		Response response = RestAssured.given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/audio-features");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	}
	
	@Test
	public void getTrackAudioFeatures() {
		Response response = RestAssured.given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/audio-features/5Ox43gIWUNW6pAgx3F3oi7");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	}
	
	@Test
	public void getSeveralTracks() {
		Response response = RestAssured.given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/tracks?ids=5Ox43gIWUNW6pAgx3F3oi7");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	}
	
	@Test
	public void get_Track() {
		Response response = RestAssured.given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/tracks/5Ox43gIWUNW6pAgx3F3oi7");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
}
	
	@Test
	public void getPlaylistCoverImage() {
		Response response = RestAssured.given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/playlists/"+playlist_id+"/images");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	}

}