# blog-search
블로그 검색 시스템을 제공합니다.
본 프로젝트는 Hexagonal Architecture 적용하였습니다.
***
## package 구성
| 영역  | 패키지명                | 역할                                  |
|-----|---------------------|-------------------------------------|
| 외부  | adapter.in          | 외부영역으로 부터 받는 요청 구현체(Http, Message등) |
| 외부  | adapter.out         | 외부 영역으로 요청처리 구현체(DB, Message, Http) |
| 내부  | application.port    | UseCase를 정의한 interface)             | 
| 내부  | application.service | 비지니스 로직 흐름 구현체                      | 
| 내부  | domain              | 비지니스 로직 및 애그리거트 집합                  |


## 외부 라이브러리
* OpenFeign - Daum,Naver 에 Rest 요청을 위해 사용

## TODO
* DTO <-> Domain Entity 변환시 Mapstruct 적용 하여 코드를 줄일 필요 있음

## Executable Binary(Jar)
Link: [Download](https://drive.google.com/file/d/1eIdPD-WPDT6klcfEB6WHAkGe_4r_vMBW/view?usp=sharing)

```bash
java -jar blog-search.jar
```