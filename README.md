# 칼국수레인저

## 🧠AI 스터디메이트🧠



---

## 개발일정

![week2](deliverables/imgs/week2.png)

## 기획 의도

1. 언택트 사회가 되어감에 따라서 스터디 모임, 학교 수업까지도 비대면으로 진행되고 있습니다. 
2. 집에서 혼자 공부하는 것에 권태감과 어려움을 느끼는 사람이 많습니다. 그래서 요즘에는 캠스터디라는 것이 선풍적인 인기를 끌고 있습니다. 
3. 'AI 스터디메이트'는 서로를 감시하고, 자신을 보여줌으로써 조금 더 공부 자극을 얻고 집중할 수 있도록 하는 시스템입니다.
4. 저희는 이런 캠스터디라는 시스템과 스탑워치를 통해 자신의 공부 시간을 측정하는 서비스를 결합한 서비스를 기획하고자 했습니다.

## 주요 기능

1. AI 자동 측정 타이머(집중/비집중)
2. 커뮤니티 (시험별 게시판- 수능, 고시, 자격증,...)
3. 학습 패턴 분석 기능 (집중 시간, 집중 시간대, 방해요소, 과목별 공부시간,...)
4. 랭킹 기능  (시험별 그룹 순위/개인 순위)
5. 경쟁 (그룹별) 열품타 → 그룹 → 공부/공X 아바타 → 불이 타 + 시간 다른 사람이 볼 수 있음 몇 시간째 공부중인지(그룹 생성/어몽어스처럼 비밀코드 가지고 참여가능)

![imgs/Untitled.png](deliverables/imgs/Untitled.png)

## 시장조사

기존 서비스는 사용자가 직접 타이머를 조작해야하는 불편함이 존재.

사용자의 학습 태도를 자동적으로 판별하여 직접 조작 없이 시간을 재 편의성을 제공하고, 다양한 통계 자료 제공.

## 팀 컨벤션

- 프론트(FE): 아름, 하연, 봉현
- 백(BE): 기호, 찬규
- 서버 구성 3가지 방법:
    - 1. Django
    - 2. Flask
    - 3. 데이터단은 Django에서 받고, 웹 서버는 spring(자이썬이라는걸 사용하면 장고 안써도 될수도)으로
- 프론트 구성:
    - Vue.js
    - Vuetify, Bootstrap 등 프레임워크 사용X
    - css + javascript 제대로 활용하기
- 빌드 도구: Maven
- 개발 도구: Eclipse, Pycharm
- 배포 도구(나중에 - 연결하는 법 알아보기): Docker, Jenkins(자동 빌드)
- 라이브러리:
    - 프론트에서는 X
    - 인공지능단 : Tensorflow, Keras
    - 서버: 자이썬
    - Security(로그인시 보안, DB암호화), Scheduler, JWT token
    - 소셜 로그인(spring security oauth)

## 코드 컨벤션

### Java

[Naming Rules]

- Class는 Pascal 표기법
ex) MainController.java, UserDto.java
- Method, Variable은 Camel 표기법
ex) public void setUserName();
ex) private String userId();
- Method는 동사+명사 형태
ex) getUserId();
ex) selectAll();

[Statement Rules]

- if 문을 쓸 때 1줄인 경우 같은 라인에다가, 1줄 이상일 때는 예시처럼 사용

```java
if (true) statement1;

if (true) {
    statement1;
    statement2;
}
```

Python

[Naming Rules]

- Variable, function 은 snake 표기법
- function은 동사+명사 형태

### JavaScript

- Prettier - Code formatter

## 커밋 컨벤션

### GIT

- Develop 브랜치에서 작업
- Master는 건들지 않는다
- 기능마다 임시 branch인 feature생성해 작업

    ex) feature/front/user-hana, feature/back/user-chanchan

- 적어도 Story이슈 하나마다 커밋

[https://woowabros.github.io/experience/2017/10/30/baemin-mobile-git-branch-strategy.html](https://woowabros.github.io/experience/2017/10/30/baemin-mobile-git-branch-strategy.html)

```
Commit Message : 

[Jira 이슈 번호] 타이틀: 설명
예시 : [S04P12B207-15]Docs: 커밋 메세지 규칙 수정

Feat : 코드나 테스트를 추가했을 때 + 라이브러리 추가하는 경우
Fix : 버그를 수정했을 때
Remove : 코드를 제거했을 때
Update : 코드를 수정하는 경우
Docs : 문서를 수정했을 때
Style : 코드 포맷팅에 대한 부분 변경, CSS 등
Rename : 이름을 변경했을 때
Move : 코드를 이동할 때
Refac : 기능 변화 없이 코드 내부 구조 변경하는 경우
```



## 주요 기술 스택/도구

| 분류     | 기술 스택/도구 | 버전     | 비고                    |      |
| -------- | -------------- | -------- | ----------------------- | ---- |
| 언어     | Python         | 3.7.6    | Anaconda 가상환경       |      |
| 머신러닝 | Numpy          | 1.18.1   |                         |      |
|          | Scipy          | 1.4.1    |                         |      |
|          | Scikit-learn   | 0.22.1   |                         |      |
| 딥러닝   | Tensorflow     | 2.0.0    | Deep Learning Framework |      |
|          | Keras          | 2.2.4-tf | High Level API          |      |
| 시각화   | Matplotlib     | 3.1.3    |                         |      |
|          | Tensorboard    | 2.1.0    | TensorFlow 시각화 툴킷  |      |
| 기타     | Anaconda       | 4.8.2    | 패키지 관리 및 가상환경 |      |
|          | tqdm           | 4.42.1   | 반복문 진척도 시각화    |      |

