## Chapter 09 단위 테스트
### TDD 법칙 세가지
다들 잘 지키고 계신가요?
1. 실패하는 단위 테스트를 작성할 때까지 실제 코드를 작성하지 않는다.
2. 컴파일은 실패하지 않으면서 실행이 실패하는 정도로만 단위 테스트를 작성한다.
3. 현재 실패하는 테스트를 통과할 정도로만 실제 코드를 작성한다.

경험 상, 테스트가 실패하는 상태에서 코드를 짠 적이 얼마 없던 것 같아요. \
테스트를 성공시켜야겠다는 생각을 하면서 일단 테스트 코드를 짜고,
실제 코드를 설계대로 수정하면서 테스트를 성공시켰던 경험이 많습니다. \
정말 필요한 기능만 만들기 위한 습관을 들이기 위해서 3번 규치을 잘지켜야겠습니다.

### 깨끗한 테스트 코드
1. 바로 본론 들어가기
2. 테스트 언어 사용하기
3. 이중 표준 활용하기
```java
@Test
public void testGetPageHierarchyAsXml() throws Exception {
    makePages("PageOne", "PageOne.ChildOne", "PageTwo");

    submitRequest("root", "type:pages");

    assertResponseIsXML();
    assertResponseContains(
        "<name>PageOne</name>", "<name>PageTwo</name>", "<name>ChildOne</name>"
    );
}
```
교재에서 가져온 샘플 코드입니다. 저자의 세 조건이 해당 코드 어떤 부분에서 잘 드러나는지 생각해볼까요?

### F.I.R.S.T
깨끗한 테스트 코드를 만들기 위한 5가지 규칙과 관련된 경험을 공유해봐요.

**Fast**
> 장한: 테스트 속도를 신경썼던 경험으로 unit test인데 spring boot test를 사용하는 경우에 대한 피드백을 준 적이 있어요.

**Independent**
> 장한: mocking, stubbing 할 때 @BeforeEach() 를 반드시 활용하고 있어요.
```java
@BeforeEach
public void init() {
    MockitoAnnotations.initMocks(this);
    pointService = new PointServiceImpl(mockUserPointTable, mockPointHistoryTable);
}
```

**Repeatable**
> 신경써본 적이 없어요...

**Self-Validating**
> 장한: 로그를 찍은 경험이 있어요. 
> 테스트 중에 잠시 멈추고, 새로운 테스트 코드 작성 및 기존 클래스 수정을 했어야 함을 반성했어요.

```java
CountDownLatch latch = new CountDownLatch(threadCount);

// when
for (int i = 0; i < threadCount; i++) {
  System.out.println();
  Thread.sleep(100);
  int count = i;
  Runnable task =
      () -> {
        try {
          if (count % 2 == 0) {
            lockPointService.charge(userId, chargeAmount, count);
          } else {
            lockPointService.use(userId, useAmount, count);
          }
        } catch (Exception e) {
          System.out.println(e);
        } finally {
          latch.countDown();
          System.out.println("countdown " + latch.getCount());
        }
      };

  new Thread(task).start();
}
latch.await();
```
쓰레드 이터레이션을 돌려서 락으로 동시성 처리하는 로직이 잘 동작함을 검증하는 테스트 작성 중에 \
에러가 발생하니 service.charge() 메서드의 파라미터에 count를 넣고 내부적으로 프린트 찍어보는 코드 \
락을 통제하는 클래스와 서비스 클래스를 분리할 것.

**Timely**
