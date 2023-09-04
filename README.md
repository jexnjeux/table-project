
# 매장 예약 서비스 구현

## 프로젝트 소개
매장을 방문할 때 미리 방문 예약을 진행하는 기능을 구현하는 서비스입니다.

## API 명세서


### Member 관련 API
- 회원가입
  - POST "/api/auth/signup"
  - 파라미터: 아이디, 비밀번호, 이름, 휴대폰번호, 이메일, 멤버 타입
  - 정책:
    - 아이디 중복 체크
    - 비밀번호는 영문과 숫자를 모두 포함
    - 비밀번호 암호화 후 저장
   
      
- 로그인
  - POST "/api/auth/login
  - 파라미터: 아이디, 비밀번호
  - 정책: 
    - 로그인 성공 시 jwt 반환
 

### Store 관련 API
- 매장 추가
  - POST "/api/partner/store"
  - 파라미터: 매장명, 도로명 주소, 상세 주소, 매장 설명
  - 정책:
    - 매장명과 도로명 주소가 동일한 매장이 이미 등록되어있는 경우 실패 응답

- 매장 검색
  - GET "/api/store?q"
  - 파라미터: 매장명
  - 정책:
    - 검색어를 포함하고 있는 매장을 조회하여 size 10 페이징하여 매장정보 반환

- 매장 상세 검색
  - GET "/api/store/{id}"
  - 파라미터: 매장 아이디
  - 정책:
    -  매장 조회가 안되는 경우 실패 응답


### Reservation 관련 API
- 예약
  - POST "/api/user/reservation"
  - 파라미터: 멤버 아이디, 매장 아이디, 방문 예약자 수, 예약일자, 메모
  - 정책:
    - 유저에게 방문 예정인 예약이 있는 경우 실패 응답
    - 현재보다 이전 시점을 예약하려는 경우 실패 응답
    - 선택한 일자에 매장 예약이 있는 경우 실패 응답

- 예약 확인
  - PUT "/api/user/reservation/confirm?phoneNumber"
  - 파라미터: 휴대폰 번호
  - 정책:
    - 휴대폰 번호를 통해 멤버 조회 불가능한 경우 실패 응답
    - 조회한 멤버에게 예약 내역이 없는 경우 실패 응답
   
### Review 관련 API
- 리뷰 등록
  - POST "/api/user/review"
  - 파라미터: 예약 아이디, 멤버 아이디, 제목, 내용, 평점
  - 정책:
    - 리뷰를 남기려는 유저와 예약한 유저가 다른 경우 실패 응답
    - 취소된 예약에 리뷰를 등록하려는 경우 실패 응답
    - 방문이 1주일 지난 예약에 리뷰를 등록하려는 경우 실패 응답

## 기술 스택

![Static Badge](https://img.shields.io/badge/java-ea2c2f?style=for-the-badge)
![Static Badge](https://img.shields.io/badge/spring_boot-6DB33F?style=for-the-badge)
![Static Badge](https://img.shields.io/badge/h2_database-0c1de6?style=for-the-badge)
