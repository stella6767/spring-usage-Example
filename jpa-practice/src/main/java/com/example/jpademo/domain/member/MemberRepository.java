package com.example.jpademo.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


//인터페이스지만 JpaRepository를 상속받으면 메모리에 뜨는 건가?
public interface MemberRepository extends JpaRepository<Member,Long>, MemberCustomRepository {

    /**
     * https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#reference
     * JPA namingQuery = 메서드 이름만으로 쿼리 생성
     * @param username
     * @return
     */
    List<Member> findByUsername(String username);


    /**
     * 네이티브 쿼리,JPA Entity 명으로 정해라,  (대소문자 주의!)
     *  그리고 별칭을 줘라. 별칭을 안 쓰면, 만약 쓰는 데이터베이스가 cos라면
     * Native Query가 테이블명을 cos.post 뭐 이런식으로 찾아간다. 이유는 모르겠지만
     * local에서는 문제없는데 aws db를 쓰니 권한 때문인지 이상한데로 맵핑되네..
     * spring data jpa는 위치기반 파라미터와(?1) 이름기반 파라미터 바인딩(:username)을 모두 지원한다.
     * 기본 값은 위치 기반인데 이름기반 쓰려면, @Parame 어노테이션 사용
     * findByUsernameAndAgeNative(@Param("username) String username);
     * 만약 벌크성 수정 ,삭제 쿼리는 실행하는 서비스단 메서드에서 @Modifying 붙여줘야 됨
     * 벌크성 쿼리를 실행하고 나서 영속성 컨텍스트를 초기화하고 싶으면, @Modifying(clearAutomatically = true)
     * 초기화해줘야지 영속성 컨텍스트에 수정된 결과를 반환받을 수 있다. 벌크성만 유의!
     */
    @Query(value ="select m.* FROM Member m WHERE m.username = ?1 AND m.age = ?2", nativeQuery = true)
    Member findByUsernameAndAgeNative(String username, String age);


    /**
     * 스프링 데이터 JPA는 유연한 반환타입을 지원하는데, 결과가 한 건 이상이면 컬렉션 인터페이스,
     * 단건이면 반환 타입을 지정한다. 단건을 기대하고, 반환타입을 지정했는데 2건이상이면 null을 반환
     * 스프링 데이터 JPA는 쿼리 메서드에 페이징과 정렬기능을 사용할 수 있도록 2가지 특별한 파라미터를 제공한다
     * Sort, Pageable(내부에 sort 포함)  count query 사용해서 custom 가능
     * 힌트도 지원한다! @QueryHints, 쿼리 시 락을 걸려면, @Lock(Lockmode.PS~~~~
     */



    //jpql을 쓸려면 몇가지 주의점이 필요한데, 대소문자 구분, 엔티티 이름, 별칭은 필수
    /**
     * fetch join은 JPQL에서만 지원하는 성능 최적화 기능인데, 연관된 엔티티나 컬렉션을 한번에 같이 조회한다.
     * JOIN FETCH
     * 일반 조인을 사용하면 jpql 은 결과를 반환할 때, 연관관꼐까지 고려하지 않는다.
     * 단지 select 절에서 지정한 엔티티만 조회할 뿐이다.
     * 만약 연관된 컬렉션을 지연로딩 설정하면, 프록시나 아직 초기화되지 않은 컬렉션 래퍼를 반환하고
     * 즉시 로딩으로 설정하면, 컬렉션을 조회하기 위해 쿼리를 한 번 더 실행한다.
     * 반면 패치조인은 연관된 엔티티도 함께 조회한다. 성능 최적화
     * 패치 조인은 글로벌 로딩 전략보다 우선하기 때문에 lazy로 설정해도 한번에 조회한다.
     * 주의 점은 패치 조인 대상에는 별칭을 줄 수 없다.
     * 둘 이상의 컬렉션을 패치할 수 없다.
     * 컬렉션을 패치조인하면 페이징 api를 사용할 수 없다.
     * JPQL 은 표준 SQL 이 지원하는 대부분의 문법과 SQL 함수들을 지원하지만, 특정 DB에 종속적인 기능은
     * 지원하지 않는다. 예를 들어 hint, 인라인 뷰(from 절 서브쿼리), union, intersect, stored procedure
     * 이럴 때 native sql 을 사용해야 한다.
     * JPQL은 항상 데이터베이스를 조회한다.
     * JPQL로 조회한 엔티티는 영속 상태이다.
     * 영속성 컨텍스트에 이미 존재하는 엔티티가 있으면 기존 엔티티를 반환한다.
     */

}
