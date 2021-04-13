<p align="center">
    <img src="exec/frontend/src/assets/img/logo_bl.png"/>
</p>
<p align="center">
    함께 공부해요, Study with me 💻🧠
</p>


> 서비스명: SWITHME(Study with me)<br>
> 개요: AI를 활용한 집중시간 자동 측정, 학습 패턴 분석 서비스 <br>
> 개발기간: 2021.02.22 ~ 2021.04.09<br>


## 기획 배경
> 1. 온라인 모임 15배 증가 [출처: 여가 액티비티 플랫폼 프립, ‘인포그래픽 데이터’]
> 2. 코로나19로 인한 비대면 사회로의 급격한 전환
> 3. 온라인 교육 서비스 급증


## 주요 기능

> 1. AI 타이머
>> 집중, 비집중 여부를 파악하여 집중 시간일때에만 타이머를 작동시켜 공부 시간을 측정해줍니다.

> 2. 커뮤니티
>> 9개(초중고, 수능, 대학교, 대학원, 취업, 공무원시험, 자격증, 어학, 기타)의 카테고리로 구분된 시험별 게시판을 통해 수험생 커뮤니티를 형성합니다.

> 3. 학습 패턴 분석 기능 (방해요인, 연속 집중 시간, 주요 집중 시간대, 방해요소, 과목별 공부시간,...)
> 4. 경쟁 (그룹별) 열품타 → 그룹 → 공부/공X 아바타 → 불이 타 + 시간 다른 사람이 볼 수 있음 몇 시간째 공부중인지(그룹 생성/어몽어스처럼 비밀코드 가지고 참여가능)

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

