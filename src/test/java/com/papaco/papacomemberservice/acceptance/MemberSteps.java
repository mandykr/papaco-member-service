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

    public static ExtractableResponse<Response> 회원_정보_생성_요청(Long accountId) {
        Map<String, Object> params = new HashMap<>();
        params.put("accountId", accountId);

        return RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(params)
                .when().post("/members")
                .then().log().all().extract();
    }

    public static ExtractableResponse<Response> 회원_정보_수정_요청(
            ExtractableResponse<Response> createResponse, List<Map<String, Object>> careers, List<String> techStacks) {
        long id = createResponse.jsonPath().getLong("id");

        Map<String, Object> params = new HashMap<>();
        params.put("careers", careers);
        params.put("techStacks", techStacks);

        return RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(params)
                .when().put("/members/{id}" + id)
                .then().log().all().extract();
    }

    public static void 회원_정보_생성됨(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    public static void 회원_정보_수정됨(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }
}
