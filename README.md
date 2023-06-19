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
