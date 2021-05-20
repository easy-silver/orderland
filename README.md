# 회원/주문 API

## 사용 기술
* Spring Boot 2.3.10
* Spring Data JPA
* Spring Security 
* Spring Validation
* Swagger UI

## 주요 기능
* 회원가입
    * 유효성 검증
* 회원 로그인(인증)
* 단일 회원 상세 정보 조회
* 단일 회원의 주문 목록 조회
* 여러 회원 목록 조회 
    * 페이지네이션으로 일정 단위로 조회
    * 이름, 이메일을 이용하여 검색 기능

## 테스트 데이터 정보
- member_no : 1
- username : 이지은
- email: timo@gmail.com

## Swagger UI
http://localhost:8080/swagger-ui.html

## 기타
- 테이블 생성 SQL 파일: create.sql
- Gradle 빌드 스크립트: build.gradle
- 단위 테스트 코드 작성