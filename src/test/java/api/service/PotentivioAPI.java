package api.service;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.rest.SerenityRest;
import org.json.JSONObject;

import java.io.File;

import static net.serenitybdd.rest.RestRequests.given;

public class PotentivioAPI {
    private static final String POTENTIVIO_BASEURL = "https://potentivio.my.id";

    private String bearerToken;

    //Getters and Setters
    public String getTokenArtist() {
        return bearerToken;
    }

    //artist
    public void setTokenArtist() {

        RequestSpecification request = given();

        String payload = "{\n" +
                "    \"email\": \"testing@gmail.com\",\n" +
                "    \"password\": \"testing\"\n" +
                "}";

        request.header("Content-Type", "application/json");
        Response responsePostMethod = request.body(payload)
                .post(POTENTIVIO_BASEURL + "/login/artist");

        responsePostMethod.prettyPeek();

        String jsonString = responsePostMethod.getBody().asString();
        int responseCode = responsePostMethod.statusCode();

        if (responseCode != 200) {
            this.bearerToken = "null";
        } else {
            this.bearerToken = JsonPath.from(jsonString).get("data.token");
        }
    }


    //cafe-owner

    public String getTokenCafeOwner() {
        return bearerToken;
    }

    public void setTokenCafeOwner() {

        RequestSpecification request = given();

        String payload = "{\n" +
                "    \"email\": \"testing-cafe@gmail.com\",\n" +
                "    \"password\": \"testing-cafe\"\n" +
                "}";

        request.header("Content-Type", "application/json");
        Response responsePostMethod = request.body(payload)
                .post(POTENTIVIO_BASEURL + "/login/cafe-owner");

        responsePostMethod.prettyPeek();

        String jsonString = responsePostMethod.getBody().asString();
        int responseCode = responsePostMethod.statusCode();

        if (responseCode != 200) {
            this.bearerToken = "null";
        } else {
            this.bearerToken = JsonPath.from(jsonString).get("data.token");
        }
    }

    // no-login-artist
    public String getTokenNoLogin() {
        return bearerToken;
    }

    public void setTokenNoLogin(String aNull) {

        RequestSpecification request = given();

        String payload = "{\n" +
                "  \"email\": \"\",\n" +
                "  \"password\": \"\"\n" +
                "}";

        request.header("Content-Type", "application/json");
        Response responsePostMethod = request.body(payload)
                .post(POTENTIVIO_BASEURL + "/login/artist");

        responsePostMethod.prettyPeek();

        String jsonString = responsePostMethod.getBody().asString();
        int responseCode = responsePostMethod.statusCode();

        if (responseCode != 200) {
            this.bearerToken = "null";
        } else {
            this.bearerToken = JsonPath.from(jsonString).get("data.token");
        }
    }

    // no-login-cafe-owner
    public String getTokenNoLoginCafe() {
        return bearerToken;
    }

    public void setTokenNoLoginCafe(String aNull) {

        RequestSpecification request = given();

        String payload = "{\n" +
                "  \"email\": \"\",\n" +
                "  \"password\": \"\"\n" +
                "}";

        request.header("Content-Type", "application/json");
        Response responsePostMethod = request.body(payload)
                .post(POTENTIVIO_BASEURL + "/login/cafe-owner");

        responsePostMethod.prettyPeek();

        String jsonString = responsePostMethod.getBody().asString();
        int responseCode = responsePostMethod.statusCode();

        if (responseCode != 200) {
            this.bearerToken = "null";
        } else {
            this.bearerToken = JsonPath.from(jsonString).get("data.token");
        }
    }

    public static void postRegisterArtist() {
        JSONObject bodyJSON = new JSONObject();
        bodyJSON.put("artist_name", "testing2");
        bodyJSON.put("email", "testing2@gmail.com");
        bodyJSON.put("password", "testing");
        bodyJSON.put("address", "jakarta");

        SerenityRest.given()
                .header("Content-type", "application/json")
                .body(bodyJSON.toString())
                .post(POTENTIVIO_BASEURL + "/artist");
    }

    public static void failedPostRegisterArtist() {
        JSONObject bodyJSON = new JSONObject();
        bodyJSON.put("artist_name", "satria the star");
        bodyJSON.put("email", "satriaaaa44@gmail.com");
        bodyJSON.put("password", "satria123");
        bodyJSON.put("address", "jl. panglima no.11 - jakarta timur");

        SerenityRest.given()
                .header("Content-type", "application/json")
                .body(bodyJSON.toString())
                .post(POTENTIVIO_BASEURL + "/artist");
    }

    public static void postRegisterCafeOwner() {
        JSONObject bodyJSON = new JSONObject();
        bodyJSON.put("cafe_name", "coffee semesta");
        bodyJSON.put("owner", "satria");
        bodyJSON.put("email", "cafetest1234@gmail.com");
        bodyJSON.put("password", "password123");
        bodyJSON.put("address", "jl. yosudarso no.12 - jakarta utara");

        SerenityRest.given()
                .header("Content-type", "application/json")
                .body(bodyJSON.toString())
                .post(POTENTIVIO_BASEURL + "/cafe");
    }


    public void postLoginArtist() {
        JSONObject bodyJSON = new JSONObject();
        bodyJSON.put("email", "testing@gmail.com");
        bodyJSON.put("password", "testing");

        SerenityRest.given()
                .header("Content-type", "application/json")
                .body(bodyJSON.toString())
                .post(POTENTIVIO_BASEURL + "/login/artist");
    }

    public void failedPostLoginArtist() {
        JSONObject bodyJSON = new JSONObject();
        bodyJSON.put("email", "testinggg@gmail.com");
        bodyJSON.put("password", "satria1234");

        SerenityRest.given()
                .header("Content-type", "application/json")
                .body(bodyJSON.toString())
                .post(POTENTIVIO_BASEURL + "/login/artist");
    }

    public void postLoginCafeOwner() {
        JSONObject bodyJSON = new JSONObject();

        bodyJSON.put("email", "cafesemestasss@gmail.com");
        bodyJSON.put("password", "password123");

        SerenityRest.given()
                .header("Content-type", "application/json")
                .body(bodyJSON.toString())
                .post(POTENTIVIO_BASEURL + "/login/cafe-owner");
    }

    public void getAllArtist(String token) {
        if (token.equalsIgnoreCase("null")) {
            SerenityRest.given()
                    .get(POTENTIVIO_BASEURL + "/artist");
        } else {
            SerenityRest.given()
                    .header("Authorization", "Bearer " + token)
                    .get(POTENTIVIO_BASEURL + "/artist");
        }
        System.out.println(token);
    }

    public void unsuccessGetAllArtist() {
        SerenityRest.given()
                .get(POTENTIVIO_BASEURL + "/artist");
    }

    //
    public void getProfileArtist(String token) {
        if (token.equalsIgnoreCase("null")) {
            SerenityRest.given()
                    .get(POTENTIVIO_BASEURL + "/artist/profile");
        } else {
            SerenityRest.given()
                    .header("Authorization", "Bearer " + token)
                    .get(POTENTIVIO_BASEURL + "/artist/profile");
        }
        System.out.println(token);
    }

    public void unsuccessGetProfileArtist() {
        SerenityRest.given()
                .get(POTENTIVIO_BASEURL + "/artist/profile");
    }


    public void getDetailArtist(String token) {
        if (token.equalsIgnoreCase("null")) {
            SerenityRest.given()
                    .get(POTENTIVIO_BASEURL + "/artist/19");
        } else {
            SerenityRest.given()
                    .header("Authorization", "Bearer " + token)
                    .get(POTENTIVIO_BASEURL + "/artist/19");
        }
        System.out.println(token);
    }

    public void unsuccessGetDetailArtist() {
        SerenityRest.given()
                .get(POTENTIVIO_BASEURL + "/artist/19");
    }

    public void putUpdateArtist(String token) {
        if (token.equalsIgnoreCase("null")) {
            SerenityRest.given()
                    .get(POTENTIVIO_BASEURL + "/artist/19");
        } else {
            SerenityRest.given()
                    .header("Authorization", "Bearer " + token)
                    .header("Content-type", "multipart/form-data; boundary=<calculated when request is sent>")
                    .multiPart("id_category", "1")
                    .multiPart("id_genre", "1")
                    .multiPart("phone_number", "089525525505")
                    .multiPart("price", "600000")
                    .multiPart("description", "artis keren banget")
                    .multiPart("account_number", "01019191818")
                    .multiPart("avatar", new File("/Users/zatihulwani/Downloads/avatar/avatar2.png"))
                    .multiPart("rating", "0")
                    .put(POTENTIVIO_BASEURL + "/artist/19");
        }

        System.out.println(token);
    }

        public void deleteArtist(String token) {
            if (token.equalsIgnoreCase("null")) {
                SerenityRest.given()
                        .get(POTENTIVIO_BASEURL + "/artist/19");
            } else {
                SerenityRest.given()
                        .header("Authorization", "Bearer " + token)
                        .get(POTENTIVIO_BASEURL + "/artist/19");
            }
            System.out.println(token);
        }

        }


