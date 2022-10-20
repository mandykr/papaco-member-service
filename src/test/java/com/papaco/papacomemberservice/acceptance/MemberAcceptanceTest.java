package com.papaco.papacomemberservice.acceptance;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.papaco.papacomemberservice.acceptance.MemberFixture.*;
import static com.papaco.papacomemberservice.acceptance.MemberSteps.*;

class MemberAcceptanceTest extends AcceptanceTest {

    /**
     * Feature: 회원 정보를 관리한다.
     *
     *   Scenario: 회원 정보를 관리
     *     When 회원 정보 생성을 요청
     *     Then 회원 정보 생성됨
     *     When 회원 정보 조회 요청
     *     Then 회원 정보 조회됨
     *     When 회원 정보 수정을 요청
     *     Then 회원 정보 수정됨
     */
    @DisplayName("회원 정보를 관리한다")
    @Test
    void manage() {
        // create
        ExtractableResponse<Response> createResponse = 회원_정보_생성_요청(1L);
        회원_정보_생성됨(createResponse);

        // find
        ExtractableResponse<Response> findResponse = 회원_정보_조회_요청(createResponse);
        회원_정보_조회됨(findResponse);

        // update
        ArrayList<Map<String, Object>> careers = new ArrayList<>();
        careers.add(카카오);
        careers.add(네이버);
        careers.add(구글);
        List<Long> techStackIds = createTechStacks(1L, 2L);
        ExtractableResponse<Response> updateResponse = 회원_정보_수정_요청(createResponse, careers, techStackIds);
        회원_정보_수정됨(updateResponse);
    }
}
