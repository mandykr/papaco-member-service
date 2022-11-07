package com.papaco.papacomemberservice.acceptance;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.papaco.papacomemberservice.acceptance.MemberFixture.*;
import static com.papaco.papacomemberservice.acceptance.MemberSteps.*;

class MemberAcceptanceTest extends AcceptanceTest {

    /**
     *   Background
     *     Given 기술스택 생성되어 있음
     */
    @BeforeEach
    public void setup() {
        super.setUp();
        기술스택_생성되어_있음();
    }

    /**
     * Feature: 회원을 관리한다.
     *
     *   Scenario: 회원 정보를 관리
     *     When 회원 생성 요청
     *     Then 회원 생성됨
     *     When 회원 조회 요청
     *     Then 회원 조회됨
     *     When 회원 수정 요청
     *     Then 회원 수정됨
     */
    @DisplayName("회원을 관리한다")
    @Test
    void manage() {
        // create
        ExtractableResponse<Response> createResponse = 회원_생성_요청(1L);
        회원_생성됨(createResponse);

        // find
        ExtractableResponse<Response> findResponse = 회원_조회_요청(createResponse);
        회원_조회됨(findResponse);

        // update
        List<Map<String, Object>> 경력 = Arrays.asList(카카오, 네이버, 구글);
        List<Long> 기술스택 = createTechStacks(1L, 2L);
        ExtractableResponse<Response> updateResponse = 회원_수정_요청(createResponse, 경력, 기술스택, false);
        회원_수정됨(updateResponse);
    }

    /**
     * Feature: 리뷰어 목록을 검색한다.
     *
     *   Scenario: 리뷰어 목록을 검색
     *     When 리뷰어_A 등록 요청
     *     Then 리뷰어_A 등록됨
     *     When 리뷰어_B 등록 요청
     *     Then 리뷰어_B 등록됨
     *     When 리뷰어 목록 검색 요청
     *     Then 리뷰어 목록 검색됨
     */
    @DisplayName("리뷰어 목록을 검색한다")
    @Test
    void reviewers() {
        // register A
        List<Map<String, Object>> 경력 = Arrays.asList(카카오, 네이버, 구글);
        List<Long> 기술스택 = createTechStacks(1L, 2L);
        ExtractableResponse<Response> 리뷰어_A = 리뷰어_등록_요청(1L, 경력, 기술스택);
        리뷰어_등록됨(리뷰어_A);

        // register B
        경력 = Arrays.asList(카카오, 네이버);
        기술스택 = createTechStacks(1L, 2L, 3L, 4L, 5L);
        ExtractableResponse<Response> 리뷰어_B = 리뷰어_등록_요청(1L, 경력, 기술스택);
        리뷰어_등록됨(리뷰어_B);

        // search
        기술스택 = createTechStacks(1L, 2L);
        ExtractableResponse<Response> searchResponse = 리뷰어_목록_검색_요청(기술스택);
        리뷰어_목록_검색됨(searchResponse, 2);
    }
}
