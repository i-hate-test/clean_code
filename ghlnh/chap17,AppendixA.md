## 17장 냄새와 휴리스틱

### G5:중복

여러 모듈에서 일련의 switch/case나 if/else 문으로 똑같은 조건을 거듭 확인하는 중복이다. 이런 중복은 다형성으로 대체해야 한다. 

- 여러  if문 다형성으로 대체하는 방법 관련 참고 글
    
    [[리팩토링] 다형성을 이용한 IF 문 제거하기](https://happy-coding-day.tistory.com/entry/리팩토링-다형성을-이용한-IF-문-제거하기)
    
    [Java 리팩토링 - 조건문을 다형성으로 바꾸기](https://hungseong.tistory.com/94)
    

더더욱 미묘한 유형은 알고리즘이 유사하나 코드가 서로 다른 중복이다. 중복은 중복이므로 TEMPLATE METHOD 패턴이나 STRATEGY  패턴으로 중복을 제거한다.

- Template Pattern템플릿 패턴🆚Strategy Pattern전략 패턴
    
    [Template Pattern(템플릿 패턴) VS Strategy Pattern(전략 패턴)](https://happy-coding-day.tistory.com/entry/Template-Pattern템플릿-패턴-VS-Strategy-Pattern전략-패턴)
    

### G6: 추상화 수준이 올바르지 못하다

추상화로 개념을 분리할 때는 철저해야 한다. 모든 저차원 개념은 파생 클래스에 넣고, 모든 고차원 개념은 기초 클래스에 넣는다. 

---

## 부록 A 동시성 Ⅱ

*서버 살펴보기*

다중 스레드 프로그램을 깨끗하게 유지하려면 잘 통제된 몇 곳으로 스레드 관리를 모아야 한다. 아니, 스레드를 관리하는 코드는 스레드만 관리해야 한다. 왜냐고? 비동시성 문제까지 뒤섞지 않더라도 동시성 문제는 그 자체만으로도 추적하기 어려운 탓이다. 

*결론*

동시성은 그 자체가 복잡한 문제이므로 다중 스레드 프로그램에서는 단일 책임원칙이 특히 중요하다.