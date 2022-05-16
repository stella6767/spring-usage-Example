package com.example.springevent.handler;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.availability.AvailabilityChangeEvent;
import org.springframework.boot.availability.LivenessState;
import org.springframework.boot.context.event.*;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ApplicationEventHandler {

    /**
     * 스프링에서 기본적으로 제공해주는 이벤트들
     * https://brunch.co.kr/@springboot/422
     * https://velog.io/@borab/Spring-boot-%EA%B0%9C%EB%85%90-%EC%A0%95%EB%A6%AC-1
     * https://www.baeldung.com/spring-liveness-readiness-probes
     * @param event
     */


    /**
     * ApplicationStartingEvent는 리스너 및 이니셜 라이저 등록을 제외하고는 실행 시작시 처리 전에 전송한다
     * @param event
     */

    @EventListener
    public void applicationStartingEventListner(ApplicationStartingEvent event){
        log.info("ApplicationStartingEvent!!!!!!!");
    }


    /**
     * ApplicationEnvironmentPreparedEvent는 컨텍스트에서 사용할 환경으로 알려져 있지만 컨텍스트가 생성되기 전에 전송한다
     * @param event
     */

    @EventListener
    public void applicationEnvironmentPreparedEventListner(ApplicationEnvironmentPreparedEvent event){
        log.info("ApplicationEnvironmentPreparedEvent!!!!!!!");
    }


    /**
     * ApplicationContext가 준비되고 ApplicationContextInitializer가 호출되었지만 Bean이 로드되기 전에 ApplicationContextInitializedEvent가 전송된다.
     * @param event
     */

    @EventListener
    public void applicationContextInitializedEventListner(ApplicationContextInitializedEvent event){
        log.info("ApplicationContextInitializedEvent!!!!!!!");
    }


    /**
     * ApplicationPreparedEvent는 Bean이 로드된 후 리프레시 되기전에 전송된다.
     * @param event
     */
    @EventListener
    public void applicationPreparedEventListner(ApplicationPreparedEvent event){
        log.info("ApplicationPreparedEvent!!!!!!!");
    }


    /**
     * ApplicationStartedEvent는 컨텍스트가 리플레시 되고 애플리케이션 및 커멘드 라인 러너가 호출 되기전에 전송된다.
     * @param event
     */
    @EventListener
    public void applicationStartedEventListner(ApplicationStartedEvent event){
        log.info("ApplicationStartedEvent!!!!!!!");
    }


    /**
     * AvailabilityChangeEvent는 LivenessState.CORRECT와 함께 바로 전송되어 애플리케이션이 살아있다는 것을 가리킨다.
     * @param event
     */

    @EventListener
    public void availabilityChangeEventListner(AvailabilityChangeEvent<LivenessState> event){
        log.info("AvailabilityChangeEvent!!!!!!!");

        switch (event.getState()) {
            case BROKEN:
                // notify others
                log.info("BROKEN");
                break;
            case CORRECT:
                // we're back
                log.info("CORRECT");
        }
    }


    /**
     * ApplicationReadyEvent는 모든 응용 프로그램 및 커맨드 라인 러너가 호출 된 후에 전송된다.
     * @param event
     */

    @EventListener
    public void applicationReadyEventListner(ApplicationReadyEvent event){
        log.info("ApplicationReadyEvent!!!!!!!");
    }

    /**
     * 시작시 예외가 있어 실패한 경우 나타냄
     * @param event
     */

    @EventListener
    public void availabilityChangeEventListner(ApplicationFailedEvent event){
        log.info("ApplicationFailedEvent!!!!!!!");
    }





}
