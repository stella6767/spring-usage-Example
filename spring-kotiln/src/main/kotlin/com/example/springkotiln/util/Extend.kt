package com.example.springkotiln.util



abstract class Animal(
   protected val species:String,
   protected open val legCount:Int  //property를 override 할 때는 open을 붙여주라.
) {

    abstract fun move()
    abstract fun legCount(): Int;


}


class Cat(
        species: String, legCount: Int
) : Animal(species, legCount) {


    override fun move() {
        println("고양이가 사뿐 사뿐 걸어가~~")
    }

    override fun legCount(): Int {
        return super.legCount + this.legCount;
    }

}


class Penguin(species: String
              ) : Animal(species, legCount=2) , Flyable, Swinable {


    override fun move() {
        println("펭귄이 움직인다..")
    }


    override val legCount: Int
        get() {
           return super.legCount + this.legCount
        }

    override fun legCount(): Int {

        TODO("Not yet implemented")
    }



    override fun fly() {

    }

//    override val swimAbility: Int
//        get() = 3

    override fun act() {
        super<Swinable>.act()
        super<Flyable>.act()
    }
}



interface Swinable {

    val swimAbility: Int
        get() = 3

    fun act(){
        println("어푸 어푸")
    }
    
}



interface Flyable {

    /**
     * default 키워드 없어도 됨.
     */

    fun act() {
        println("파닥 파닥")
    }

    fun fly(); //추상메서드
}


/**
 * 코틀린에서는 기본적으로 일반 클래스는 final 를 붙여주기 때문에 open 해줘야 된다.
 */


open class Base(
        open val number:Int=100
){

    init {
        println("Base class")
        println(number)
    }

}


class Derived(
        override val number: Int
): Base(number){

    init {
        println("Derived class")
    }
}



fun main(){


    /**
     * 상위 클래스의 생성자 및 init 블럭에서는 하위 클래스의 field(final이 아닌)에 접근하면 이상한 값이 나옴
     *
     * 상속 관련 키워드
     * final: override를 할 수 없게 한다. default로 보이지 않게 존재
     * open: override를 열어준다.
     * abstract: 반드시 override를 해줘야 한다.
     * 상속 또는 구현을 사용할 때 : 을 사용
     * 상위 클래스 상속을 구현할 때 생성자를 반드시 호출
     * override 필수
     * 추상 맴버가 아니면 기본적으로 override 불가능 (open을 해줘야 됨)
     * 상위 클래스의 생성자 또는 초기화 블럭에서 open 프로퍼티를 사용하면 예기치 못한 버그가 생길 수 있음
     *
     */

    val derived:Base = Derived(300)




}

