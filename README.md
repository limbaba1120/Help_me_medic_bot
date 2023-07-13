[![header](https://capsule-render.vercel.app/api?type=waving&color=gradient&customColorList=4&animation=fadeIn&height=230&section=header&text=헬프미!%20메딕봇!&desc=2023학년도%201학기%20컴퓨터공학기초캡스톤디자인&fontSize=40&fontAlign=50&fontAlignY=33&descSize=20&descAlign=50&descAlignY=55)](https://github.com/2023-KDH-Capstone-Design)

## 💁🏻 About Team
> 경기대학교 소프트웨어경영대학 AI컴퓨터공학부  
> 2023학년도 1학기 컴퓨터공학기초캡스톤디자인
- 팀명: 헬프미! 메딕봇! 
- 지도교수: 김도훈

&nbsp;  

## 🚀 Project
- (2023.03 ~ 06) Medical Chat Bot for Foreigners: 외국인 대상 의료 정보 제공 대화형 챗봇 SW 서비스

  - 2023 컴퓨터공학기초캡스톤디자인 캡스톤 경진대회 금상 수상 🥇
  - 2023 한국정보기술학회 하계종합학술대회 및 대학생논문경진대회 동상 수상 🥉

## 📖 상세 내용

<img width="778" alt="image" src="https://github.com/limbaba1120/Help_me_medic_bot/assets/102224840/83185728-2b2a-4399-b692-35b2fc848db0">

**The purpose of this research** is to present a web-based chatbot service that aims to overcome language barriers in medical services for foreigners visiting or residing in Korea. Unlike existing chatbot service, the proposed web-based chatbot is powered by an open-source API, which offers translation services that enable users to access medical service in their native or preferred language. By improving the accessibility and awareness of Korean medical services to foreigners, this approach enhances the quality of healthcare provided to foreigners in Korea.

## 🛠️ 사용 기술 및 라이브러리

- Java Spring
- Spring JPA
- React
- Rasberry Pi

## 📜논문

[학술대회논문양식_최종.hwp](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/f472c363-b854-43d0-99f9-15ab320c5187/%E1%84%92%E1%85%A1%E1%86%A8%E1%84%89%E1%85%AE%E1%86%AF%E1%84%83%E1%85%A2%E1%84%92%E1%85%AC%E1%84%82%E1%85%A9%E1%86%AB%E1%84%86%E1%85%AE%E1%86%AB%E1%84%8B%E1%85%A3%E1%86%BC%E1%84%89%E1%85%B5%E1%86%A8_%E1%84%8E%E1%85%AC%E1%84%8C%E1%85%A9%E1%86%BC.hwp)

## 💡 깨달은 점




&nbsp;  
**Table of Contents**
  * [백엔드 디렉토리 구조](#백엔드-디렉토리-구조)
  * [프론트엔드 디렉토리 구조](#프론트엔드-디렉토리-구조)

&nbsp;  

## 백엔드 디렉토리 구조

- backend/config
  - 설정 파일

- backend/controller
  - REST API 요청 및 응답을 처리하는 Controller
  - /chatController
    - 클로바 챗봇 호출
  - /LoginController
    - 로그인 처리
  - /MapController
    - 주변 지역 및 장소 검색
  - /PostController
    - 게시글 작성, 수정 및 삭제
  - /TranslateController
    - 번역 처리
  - /UserController
    - 회원가입 및 회원 조회

- backend/domain
  - /chat
    - 챗봇 관련 서비스
  - /comment
    - 댓글 관련 엔티티 및 서비스
  - /login
    - 로그인 관련 서비스
  - /map
    - 지도 관련 서비스
  - /post
    - 게시글 관련 엔티티 및 서비스
  - /translate
    - 번역 관련 서비스
  - /user
    - 회원 관련 엔티티 및 서비스

&nbsp;  

## 프론트엔드 디렉토리 구조

- frontend/public
  - 공통적으로 사용되는 요소, 설정 (favicon, logo, app title 등)
  - api import (index.html)

- frontend/src
  - /assets
    - /images
      - 프로필 이미지 (서버 처리로 수정, 삭제 예정)
  - /components
    - /chat
      - 챗봇 작동에 사용되는 입력, 챗로그(+ 요청 챗버블, 응답 챗버블) 컴포넌트
    - /forum
      - 포럼에서 사용되는 댓글, 게시글, 작성 컴포넌트
    - /modal
      - 병원 정보(소개, 지도 등등)를 띄우는 모달 컴포넌트 (chat쪽으로 합쳐질 예정)
    - /ui
      - /ads
        - 광고 컴포넌트 (Carbonads)
      - /drawer
        - 반응형 측면 메뉴 컴포넌트
      - /navbar
        - 네비게이션 바 컴포넌트
  - /pages
    - React router로 제어되는 페이지 컴포넌트
  - /styles
    - tailwind 설정과 기타 커스텀 스타일 시트
  - /setupProxy.js
    - Cross Origin 오류 방지를 위한 proxy 설정 (API 접근 시 사용)
# Help_me_medic_bot
