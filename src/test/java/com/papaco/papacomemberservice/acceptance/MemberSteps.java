package com.papaco.papacomemberservice.acceptance;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.papaco.papacomemberservice.acceptance.TechStackFixture.ALL_TECH_STACKS;
import static org.assertj.core.api.Assertions.assertThat;

public class MemberSteps {
    private static final String ENDPOINT = "/members";

    public static void 기술스택_생성되어_있음() {
        Map<String, Object> params = new HashMap<>();
        params.put("techStackNames", TechStackFixture.ALL_TECH_STACK_NAMES);

        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(params)
                .when().post("/techstacks")
                .then().log().all().extract();

        putTechStackIds(response);
    }

    private static void putTechStackIds(ExtractableResponse<Response> response) {
        response.as(List.class).forEach(item -> {
            Map<String, Object> stack = (HashMap<String, Object>) item;
            ALL_TECH_STACKS.forEach(s -> {
                if (stack.get("name").equals(s.get("name"))) {
                    s.put("id", stack.get("id"));
                }
            });
        });
    }

    public static ExtractableResponse<Response> 회원_생성_요청(Long accountId) {
        Map<String, Object> params = new HashMap<>();
        params.put("accountId", accountId);

        return RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(params)
                .when().post(ENDPOINT)
                .then().log().all().extract();
    }

    public static void 회원_생성됨(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    public static ExtractableResponse<Response> 회원_조회_요청(ExtractableResponse<Response> response) {
        String uri = response.header("Location");

        return RestAssured.given().log().all()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when().get(uri)
                .then().log().all().extract();
    }

    public static void 회원_조회됨(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(getId(response)).isNotNull();
    }

    private static long getId(ExtractableResponse<Response> response) {
        return response.jsonPath().getLong("id");
    }

    public static ExtractableResponse<Response> 회원_수정_요청(
            ExtractableResponse<Response> response, List<Map<String, Object>> careers, List<Long> techStackIds, boolean registeredReviewer) {
        String uri = response.header("Location");

        Map<String, Object> params = new HashMap<>();
        params.put("careers", careers);
        params.put("techStackIds", techStackIds);
        params.put("registeredReviewer", registeredReviewer);

        return RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(params)
                .when().put(uri)
                .then().log().all().extract();
    }

    public static void 회원_수정됨(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    public static ExtractableResponse<Response> 리뷰어_등록_요청(
            Long accountId, List<Map<String, Object>> careers, List<Long> techStackIds) {
        ExtractableResponse<Response> createResponse = 회원_생성_요청(accountId);
        return 회원_수정_요청(createResponse, careers, techStackIds, true);
    }

    public static void 리뷰어_등록됨(ExtractableResponse<Response> response) {
        회원_수정됨(response);
        assertThat(response.jsonPath().getBoolean("registeredReviewer")).isTrue();
    }

    public static ExtractableResponse<Response> 리뷰어_목록_검색_요청(List<Long> techStackIds) {
        Map<String, Object> params = new HashMap<>();
        params.put("techStackIds", techStackIds);

        return RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .queryParam("page", "0")
                .queryParam("size", "10")
                .body(params)
                .when().get(ENDPOINT + "/reviewers")
                .then().log().all().extract();
    }

    public static void 리뷰어_목록_검색됨(ExtractableResponse<Response> response, int contentSize) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.jsonPath().getList("content")).hasSize(contentSize);
    }
}
