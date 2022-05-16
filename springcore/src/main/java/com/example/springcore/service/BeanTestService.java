package com.example.springcore.service;



import com.example.springcore.domain.Bird;
import com.example.springcore.domain.Dog;
import com.example.springcore.domain.Horse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

@Slf4j
//@RequiredArgsConstructor
@Service
public class BeanTestService {

//    private final Map<String,Dog> dog;
//    private final List<Dog> doglist;

    ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();

    private final WebApplicationContext context;

    //private final ObjectMapper objectMapper;

    private final Dog dog1;
    private final Horse horse;

    public BeanTestService(WebApplicationContext context, Dog dog1, Horse horse) {
        this.context = context;
        this.dog1 = dog1;
        this.horse = horse; //컨테이너는 프로타입 빈을 생성하고 의존관계 주입, 초기화까지만
        //진행하고, 관리를 안함. 관리 책임은 prototype bean을 주입받은 클래스에게 있다.
        //그래서 @predestory 같은 메서드가 호출되지 않는다.

//        Bird bird = context.getBean("bird", Bird.class);
//        Bird bird2 = context.getBean("bird", Bird.class);
//
//        System.out.println("bird1" + bird);
//        System.out.println("bird2" + bird2);

    }
//
//    public BeanTestService(ObjectMapper objectMapper, @Qualifier("dog1") Dog dog1) {
//        this.objectMapper = objectMapper;
//        this.dog1 = dog1;
//    }

//    public BeanTestService(Map<String, Dog> dog, List<Dog> doglist) {
//        this.dog = dog;
//        this.doglist = doglist;
//
//        System.out.println(dog);
//        System.out.println(doglist);
//    }


    public void asyncTest(){

        taskExecutor.initialize();
        Runnable r = () ->{

            for (int i=0; i<10; i++){
//                dog1.setName(String.valueOf(i));
//                log.info(dog1.getName());

                dog1.gettingOld();
            }

        };

        taskExecutor.execute(r);

        for (int i=0; i<10; i++){
            dog1.gettingOld();
        }

    }

    public void 기본빈테스트(){
//        dog1.bark();
//        dog1.setName("점박이");
//
//        try {
//            String a = objectMapper.writeValueAsString(dog1);
//            log.info("???" + a);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
 //       }
        System.out.println(horse.toString());


        //horse.();

    }

//
//    private final Animal animal;
//
//    private final Turtle turtle;
//
//
//    public BeanTestService(@Qualifier("mydog") Dog dog, @Qualifier("bird") Animal animal, @Lazy Turtle turtle) {
//        this.dog = dog;
//        this.animal = animal;
//        this.turtle = turtle;
//    }



}