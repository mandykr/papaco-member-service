package com.papaco.papacomemberservice.acceptance;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class MemberSteps {
    private static final String ENDPOINT = "/members";

    public static ExtractableResponse<Response> 회원_정보_생성_요청(Long accountId) {
        Map<String, Object> params = new HashMap<>();
        params.put("accountId", accountId);

        return RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(params)
                .when().post(ENDPOINT)
                .then().log().all().extract();
    }

    public static ExtractableResponse<Response> 회원_정보_조회_요청(ExtractableResponse<Response> response) {
        String uri = response.header("Location");

        return RestAssured.given().log().all()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when().get(uri)
                .then().log().all().extract();
    }

    public static ExtractableResponse<Response> 회원_정보_수정_요청(
            ExtractableResponse<Response> response, List<Map<String, Object>> careers, List<Long> techStackIds) {
        String uri = response.header("Location");

        Map<String, Object> params = new HashMap<>();
        params.put("careers", careers);
        params.put("techStackIds", techStackIds);

        return RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(params)
                .when().put(uri)
                .then().log().all().extract();
    }

    public static void 회원_정보_생성됨(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    public static void 회원_정보_조회됨(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(getId(response)).isNotNull();
    }

    public static void 회원_정보_수정됨(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    private static long getId(ExtractableResponse<Response> response) {
        return response.jsonPath().getLong("id");
    }
}
