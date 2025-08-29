package org.example.restapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// lomBok Data 애노테이션을 붙이면 각 변수의 get,set 함수, 기본 생성자를 자동으로 생성해준다.
@Data
@AllArgsConstructor // 전체 파라미터를 받는 생성자를 생성해준다.
@NoArgsConstructor // AllArgsConstructor를 사용하면 기본 생성자가 없어지는데, 기본 생성자를 생성해주는 애노테이션이다.
public class BookQueryParam {
    private String category;
    private String issuedYear;
    private String issuedMonth;
    private String issuedDay;
}
