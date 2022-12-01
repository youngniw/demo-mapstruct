# demo-mapstruct
[SpringBoot] Mapstruct 활용 예제 프로젝트

---
<h3>- 간략한 실습 프로젝트 소개</h3>

- 사용자 추가/조회, 게시물 추가/조회/수정/삭제의 기능을 수행하는 프로젝트
- DTO와 Entity 간의 매핑을 수동이 아닌 mapstruct를 이용하여 자동으로 수행

<br/>
<h3>- 개발 환경 및 기술 스택</h3>

- Spring Boot
    - Lombok
    - Spring Web
    - Spring Data JPA
    - Mapstruct
- Gradle
- Java 11
- H2

<br/> 
<h3>- Mapper</h3>

- @Mapper(componentModel = "spring")
  - @Mapper 어노테이션의 componentModel 옵션 값으로 "spring"을 제공함으로써 mapstruct가 spring에서 작동될 수 있게 함

<br/>

[ 예시 ]
1. UserMapper.java
   - User <-> UserDto
     - toEntity 메서드: UserDto 에 없는 필드를 제외하고는 매핑
     - toDto 메서드: UserDto 의 isCreatedToday 필드 값으로 오늘 생성되었는 지 여부를 담음
       - @Mapping 어노테이션의 expression 옵션으로 User 객체의 createdDate와 오늘 날짜가 동일한지 비교한 결과 값 저장

<br/>

2. SignUpUserMapper.java
   - User <- SaveUserDto
     - toEntity 메서드: 사용자 추가를 위한 정보를 담는 SignUpUserDto 에 없는 필드를 제외하고는 매핑

<br/>

3. PostMapper.java
   - Post <-> PostDto
     - toEntity 메서드: updatedDate 컬럼을 제외하고 매핑
     - toDto 메서드: 작성자인 writer 의 생성일 createdDate와 수정일 updatedDate를 제외하고 매핑
     - partialUpdate 메서드: Post 엔티티 클래스의 제목과 내용인 title과 content 필드만 Setter를 제공함으로써 제목과 내용만이 수정되게 함 

     - Post <- SavePostDto
       - toEntity 메서드: 게시글 추가 시 회원 번호, 제목, 내용 값만을 받아 엔티티로 매핑함<br/>
       (회원 번호를 통해 생성한 User 인스턴스를 인수로 받아 엔티티의 writer 값에 연결)

     - Post <- UpdatePostDto
       - partialUpdate 메서드: Post 엔티티 클래스의 제목과 내용를 사용자가 수정한 값을 포함하는 UpdatePostDto의 값으로 매핑 및 수정
