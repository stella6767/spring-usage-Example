package com.example.jpademo.domain.memberproduct;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MemberProductId2 implements Serializable {

    /**
     * 복합 기본 키
     * @EmbeddedId를 사용하는 방법이 있다.
     */

    @Column(name = "member_id")
    private String member;

    @Column(name = "product_id")
    private String product;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberProductId2 that = (MemberProductId2) o;
        return Objects.equals(member, that.member) && Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(member, product);
    }

}
