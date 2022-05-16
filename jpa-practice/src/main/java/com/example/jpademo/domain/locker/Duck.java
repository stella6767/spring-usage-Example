package com.example.jpademo.domain.locker;

import javax.persistence.*;

@Entity
@EntityListeners(DuckListener.class) //엔티티에 직접 등록하지 않고, 리스너 등록
public class Duck {

    @Id //Pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Table, auto_increment, Sequence
    private Long id;
    /**
     * 예를 들어, 모든 엔티티를 대상으로 삭제되면 삭제로그를 남기는 요구사항이 있다고 가정하자.
     * 이때 유용하게 사용할 수 있는 JPA 리스너 기능이 있다.
     * 이벤트는 엔티티에서 직접 받거나, 별도의 리스너를 등록해서 받을 수 있다.
     * 아래는 엔티티에 직접 적용하는 방법이다.
     */
//
//    @PostLoad
//    public void postLoad(){
//
//    }
//
//
//    @PreUpdate
//    public void preUpdate(){
//
//    }
//
//    @PrePersist
//    public void prePersist(){
//
//    }
//
//    @PostPersist
//    public void postPersist(){
//
//    }
//
//    @PostUpdate
//    public void postUpdate(){
//
//    }
//
//    @PreRemove
//    public void preRemove(){
//
//    }
//
//    @PostRemove
//    public void postRemove(){
//
//
//    }
}

class DuckListener{


    /**
     * 여러 리스너를 등록했을 떄, 이벤트 호출 순서는 다음과 같다
     * 1.기본 리스너
     * 2. 부모 클래스 리스너
     * 3. 리스너
     * 4. 엔티티
     * @param obj
     */

    @PostLoad
    public void postLoad(Object obj){

    }


    @PreUpdate
    public void preUpdate(Object obj){

    }

    @PrePersist
    public void prePersist(Object obj){

    }

    @PostPersist
    public void postPersist(Object obj){

    }

    @PostUpdate
    public void postUpdate(Object obj){

    }

    @PreRemove
    public void preRemove(Object obj){

    }

    @PostRemove
    public void postRemove(Object obj){


    }

}