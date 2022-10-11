package com.papaco.papacomemberservice.acceptance;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberFixture {
    public static final Map<String, Object> 카카오;
    public static final Map<String, Object> 네이버;
    public static final Map<String, Object> 구글;

    static {
        카카오 = createCareer(
                "카카오",
                "개발자",
                LocalDate.of(2020, 01, 01),
                LocalDate.of(2020, 12, 01)
        );

        네이버 = createCareer(
                "네이버",
                "개발자",
                LocalDate.of(2021, 01, 01),
                LocalDate.of(2021, 12, 01)
        );

        구글 = createCareer(
                "구글",
                "개발자",
                LocalDate.of(2022, 01, 01)
        );
    }

    public static Map<String, Object> createCareer(
            String corporateName, String duty, LocalDate joinedDate, LocalDate leavedDate) {
        Map<String, Object> career = new HashMap<>();
        career.put("corporateName", corporateName);
        career.put("duty", duty);
        career.put("joinedDate", joinedDate);
        career.put("leavedDate", leavedDate);

        return career;
    }

    public static Map<String, Object> createCareer(String corporateName, String duty, LocalDate joinedDate) {
        return createCareer(corporateName, duty, joinedDate, null);
    }

    public static List<String> createTechStacks(String... techStacks) {
        return List.of(techStacks);
    }
}
