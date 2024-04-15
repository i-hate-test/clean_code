# chapter 11

## 정리

### 시스템 제작과 사용을 분리

#### 지연 초기화(lazy initialization)

```java
public Service getService() {
    if(service == null)
        service = new MyServiceImpl(...);
    return service;
}
```

- 장점
    - 실제로 필요할때 까지 객체를 생성하지 않기 때문에 불필요한 부하가 걸리지 않는다.
    - 어떤 경우에도 `null` 포인터를 반환하지 않는다.
- 단점
    - `SRP(단일책임원칙)` 를 깨트릴 가능성이 있다.
    - `MyserviceImpl` 이 모든 상황에 적합한 객체인지 알 수 없다.

#### 의존성 주입(DI)
- 사용과 제작을 분리하는 강력한 메커니즘 중 하나.
- `DI(의존성 주입)` 은 `IOC(제어의 역전)` 기법을 의존성 관리에 적용한 메커니즘 이다.
    - 스프링을 사용해 봤다면, 정말정말 많이 들어봤을법한 단어와 내용이다.

### 확장

#### EJB, EJB2
- `EJB(Enterprise Java Bean)` 는 스프링이 나오기 전 자바 웹 백엔드에서 널리 사용되던 기술
- `EJB` 의 경우 너무 나도 복잡하고, 관심사를 적절히 분리하지 못하여, 유기 적인 성장이 어려웠고 결국 개발 시장에서 사장되고 말았다.

##### 횡단 관심사
- `EJB` 의 경우에도 몇몇 영역에서는 거의 완벽하게 관심사를 분리하기도 했다.
    - 트랜잭션, 보안 등등
- `EJB` 가 처리하는 트랙잭션 등의 방식은 `AOP(Aspect Oriented Programming, 관점지향프로그래밍)` 을 예견했다고 보기도 한다.
- 관점지향프로그래밍은 횡단 관심사를 대처하여 모듈성을 확보하는 일반적인 방법론이다.

### 자바 프록시

```java
import java.util.*;

public interface Bank {
    Collection<Account> getAccounts();
    void setAccounts(Collection<Account> accounts);
}
```
- 프록시로 감쌀 `Bank Interface`

```java
import java.util.*;

public class BankImpl implements Bank {
    private List<Account> accounts;

    public Collection<Account> getAccounts() {
        return accounts
    }

    public void setAccounts(Collection<Account> accounts) {
        this.accounts = new ArrayList<Account>();
        for (Account account : accounts) {
            this.accounts.add(account);
        }
    }
}
```
- 비즈니스 로직을 구현하는 `BankImpl` 클래스 `POJO(Plain Old Java Object)` 로 구현되어 있다.

```java
// 리플렉션 라이브러리를 사용한 Handler 클래스
...
```

- 단순 예제지만 프록시의 단점(복잡, 많은양의 코드)을 명확하게 보여준다.

프록시를 사용하면 깨끗한 코드를 작성하기 어렵다.

### 순수 자바 AOP 프레임워크
- 대부분의 프록시 코드는 도구로 자동화가 가능하다.
- 스프링 AOP, JBoss AOP 등과 같은 여러 자바 프레임워크에서 내부적으로는 프록시를 사용하고 있다.

### AspectJ 관점
- 관심사를 관점으로 분리하는 가장 강력한 도구다.
- 최근에 나온 `AspectJ 애너테이션 폼`  은 새로운 도구와 새로운 언어라는 부담을 어느 정도 완화한다.

### 테스트 주도 시스템 아키텍처 구축
- 애플리케이션 도메인을 `POJO` 로 작성할 수 있다면, 진정한 테스트 주도 아키텍처 구축이 가능해진다.
- `BDUF` 를 추구할 필요가 없다.
  > 구현을 시작하기 전에 앞으로 벌어질 모든 사항을 설계하는 기법
  > 선형설계와 혼동하지 않도록 주의
- 초창기 `EJB` 의 경우 기술을 너무 많이 넣느라 관심사를 분리하지 못한 대표적인 `API` 중 하나.
- 설계가 아주 멋진 `API` 조차 정말 필요하지 않다면 오히려 해가 된다.(과유 불급)

최선의 시스템 구조는 각기 POJO 객체로 구현되는 모듈화 된 도메인으로 구성된다.
서로 다른 영역에서 서로 최소한의 영향만 미치도록 관점이나, 도구를 사용해 통합해야 한다.

### 명백한 가치가 있을 때 표준을 현명하게 사용하라
- ~~JPA??(ㅋㅋㅋ)~~
- EJB2 는 단지 표준이라는 이유만으로 많이 사용되었다.
- 여러 형태로 과하게 포장된 표준에 집착하여 정작 중요한 고객의 가치는 뒷전으로 밀려난 사례

표준을 사용하면 `아이디어` 와 `컴포넌트` 를 재 사용하기 쉽고, 적절한 경험을 가진 사람을 구하기 쉬우며 여러 장점이 많지만, 때론 그 표준이 왜 제정 되었는지 잊어버리는 경우가 발생하기도 한다.

## 결론

코드 뿐만 아니라 시스템 역시 깨끗해야 한다. 깨끗하지 못한 아키텍처는 도메인 논리를 흐리고 기민성을 떨어뜨린다.
도메인 논리가 흐려지면 버그가 발생할 확률이 높아지고 그로인해 제품의 품질이 떨어진다.
기민성이 떨어지면 생산성이 매우 낮아지게 된다.
모든 추상화 단계는 의도를 명확하게 전달해야 하고, `POJO` 로 작성하고 관심사를 분리해야한다.

---

## REFERENCE
