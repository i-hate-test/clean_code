# 클린코드 6장

## 정리

### 객체와 자료구조

#### 자료 추상화

```java
public class Point {
    public double x;
    public double y;
}
```

- 구체적인 값을 나타내고 있는 클래스
- 구현을 외부에 완전히 노출 시키고 있다.

```java
public interface PointInterface {
    double getX();
    double getY();
    void setCartesian(double x, double y);
    double getR();
    double getTheta();
    void setPolar(double r, double theta);
}
```

- 구체적인 값을 함수를 통해 조회, 설정을 할수 있도록 만든 클래스
- 구현을 외부에 숨기고 있다.

추상 인터페이스를 제공해, 사용자가 구현을 모른체 모든 자료의 핵심을 조작할 수 있어야 한다.
`PointerInterface` 를 구현하는 구현체가 `직교좌표계` 인지 `극좌표계` 인지 또 다른 `좌표계` 인지 우리는 알 수 없고, 알 필요도 없다 그저 외부에서 interface 만을 의존하여 핵심 내용을 사용할 수 있다.
그리고 그게 가능하게 만들어야만, 진정한 의미의 클래스다?? 추상화다가 아닌가??

> 진정한 의미의 클래스다는 무슨 말일까??

```java
public interface ConcreteVehicle {
    double getFuelTankCapacityInGallons();
    double getGallonsOfGasoline();
}
```

- 변수값을 읽어 반환할 뿐 이라는걸 명확하게 알 수 있다.
- 정보가 어디서 오는지 함수명의 통해 알 수 있다.

```java
public interface AbstractVehicle {
    double getPercentFuelRemaining();
}
```

- 백분율이라는 추상적인 개념을 통해 비율을 나타대고 있다.
- 정보가 어디서 오는지 전혀 드러나 있지 않다.

자료를 세세하게 표현하기 보다는, 추상적인 개념으로 표현하는 편이 좋다.
객체가 포함하는 자료(변수 등등)를 표현하고, 설정하는 가장 좋은 방법이 무엇일지 많은 고민을 해야한다.
단순하게 `getter`, `setter` 만 추가하는 방법은 제일 나쁜 방법이다.

#### 자료/객체 비대칭

앞선 예제에서 객체는 자료를 숨긴채 자료를 다루는 함수만 공개, 자료 구조는 자료를 그대로 공개하며 별다른 함수는 제공하지 않는다.

```java
public class Square {
    public Point topLeft;
    public double side;
}

public class Rectangle {
    public Point topLeft;
    public double height;
    public double width;
}

public class Circle {
    public Point center;
    public double radius;
}

public class Geometry {
    public static final double PI = 3.141592653589793;

    public double area(Object shape) throws Exception {

        if (shape instanceof Square s) {
            return s.side * s.side;
        } else if (shape instanceof Rectangle r) {
            return r.height * r.width;
        } else if (shape instanceof Circle c) {
            return PI * c.radius * c.radius;
        }
        throw new Exception();
    }
}
```

- 각 도형 클래스(Square, Rectangle, Circle)는 간단한 자료구조 이다.(자료만 제공하고 별다른 함수는 없다.)
    - ~~파이썬 데이터 클래스 같은데??~~
- 자바를 사용하면서, 별도의 클래스에 저렇게 필드값만 나열해서 사용하는 경우가 많을지는 의문이 든다.

```java
public interface Shape {
    double area();
}

public class RectangleImpl implements Shape {
    private Point topLeft;
    private double height;
    private double width;

    @Override
    public double area() {
        return height * width;
    }
}

public class CircleImpl implements Shape {
    private static final double PI = 3.141592653589793;
    private Point center;
    private double radius;

    @Override
    public double area() {
        return PI * radius * radius;
    }
}

public class SquareImpl implements Shape {
    private Point topLeft;
    private double side;

    @Override
    public double area() {
        return side * side;
    }
}
```

- 위에 절차 지향 코드를 객체 지향 코드로 변경
- 상황에 따라 객체 지향에서의 코드 변경점과 절차 지향에서의 코드 변경점이 달라지게 된다고 저자는 설명하고 있다.
- 새로운 메서드를 추가해야하는 상황에서는 객체 지향 코드에서는 모든 도형 클래스를 수정해야하는 변경점이 발생하며, 절차 지향 코드에서는 도형 클래스의 수정이 일어나지 않는다.
- 새로운 도형을 추가해야하는 경우, 절차 지향 코드에서는 변경점이 발생하는 반면에 객체 지향 코드에서는 변경점이 발생하지 않는다.
- 객체 지향 코드로 작성하는것이 100% 옳은 것은 아니다, 상황에 따라 객체 지향 구조를 사용할 수도 절차 지향 구조를 사용할 수 도 있고 이런 분변력이 필요하다.

저자는 늘 객체 지향이 100% 옳은 것은 아니라는걸 말하고 있는것 같다. 내가 코드를 작성했다면 무조건 후자(객체지향)의 방식대로 작성을 했을것 같다.
하나의 방식이 무조건 답이다 라는게 아니 라고 말하는 부분은 다시 한번 나를 돌아 보게 만드는 계기가 되는것 같아 좋았다.
> 개발에 있어 100% 옳은것은 없다는걸 늘 생각하자.

#### 디미터 법칙

> 서로 다른 객체가 어떤 필드(여기서는 자료?)를 갖고 있는지 알아서는 안된다.</br>
> 객체의 필드를 숨기고, 함수를 통해 참조하여 사용하도록 하고있는데 이게 디미터의 법칙이다.</br>
> 객체지향 생활 체조 9가지 원칙 4번째 원칙으로 한줄에 점(도트)을 하나만 찍는다. 라는 내용도 있다.

- 클래스 C 의 메서드 f 를 호출 가능 리스트
  1. 클래스 C
  2. f 가 생성한 객체
  3. f 인수로 넘어온 객체
  4. C 인스턴스 변수에 저장된 객체

```java
public class Member {
    private String name;
    private int age;
    private Team team;
    private Address address;

    // getter
}

public class Team {
    private String name;
    private int memberCount;
    private Address address;

    // getter
}

public class Address {
    private String region;
    private String details;

    // getter
}
```

- 예제 코드

```java
public class Example {
    public void memberGiftDelivery() {
        if (member.getTeam().getAddress()) {
            // ...
        }
        // ...
    }
}
```

- `Member` 가 `Team` 그리고 `Address` 까지 호출하고 있기 때문에 디미터의 법칙을 어겼다고 할 수 있다.

```java
public class Member {
    private String name;
    private int age;
    private Team team;
    private Address address;

    public boolean isTeamAddressNull() {
        return team.isAddressNull();
    }
}

public class Team {
    private String name;
    private int memberCount;
    private Address address;

    public boolean isAddressNull() {
        return this.address == null;
    }
}

public class Address {
    private String region;
    private String details;
}

public class Example {
    public void memberGiftDelivery() {
        if (member.isTeamAddressNull()) {
            // ...
        }

        // ...
    }
}
```

- `Member` 클래스는 인스턴스 변수에 저장된 `Team` 을 호출하고, `Team` 은 `Address` 를 호출하는 방식으로 진행되기 때문에 디미터의 법칙을 준수했다 할 수 있다.
- 디미터 법칙을 준수하면서, 내부에서 필드를 가지고 값을 판별하기 때문에 `getter` 의 사용 또한 필요가 없어졌다.

개발을 하다보면 도트(.) 유혹은 매우 강력하다. 도트만 찍으면 다 가져올 수 있고 매우 편리한것 같지만, 사실 도트를 찍어서 값을 다 가져올 수 있도록 만든 설계 자체가 잘못된 설계라는걸 알아야한다.
도트로 값을 가져올수 있다는건 그 두개의 객체간의 결합도가 있다는 뜻인데 이게 여러 객체에서 접근할 수 있도록 만들었다면 결합도가 지나치게 높은 좋지 못한 설계로 이루어진 코드라는걸 알 수 있다.

단, 도트를 많이 사용했다고 해서 무조건 디미터 법칙을 어긴 코드라는건 아니다. 자바를 사용하다 보면 stream 을 매우 많이 사용하는데 이 경우 과연 디미터 법칙을 어긴것일까?
stream 의 경우 도트를 많이 사용해도 stream 을 변환 하여 반환할 뿐 이고 객체의 내부 구현도 노출되지 않는다.


#### 자료 전달 객체(DTO)

```java
public record WorkHoursInquiryRequest(
        @NotBlank(message = "아이디는 필수 입력값 입니다.")
        String memberId,
        @Pattern(regexp = "^2\\d{3}-(0[1-9]|1[0-2])$", message = "잘못된 형식의 날짜입니다.")
        String date
) {

    @ConstructorProperties({"member-id", "date"})
    public WorkHoursInquiryRequest(String memberId, String date) {
        this.memberId = memberId;
        this.date = date;
    }

    public static WorkHoursInquiryRequest of(String memberId, String date) {
        return new WorkHoursInquiryRequest(memberId, date);
    }

    public LocalDateTime getStartDateTime() {
        LocalDate parsedDate = dateParse();
        return LocalDateTime.of(parsedDate, LocalTime.MIN);
    }

    public LocalDateTime getEndDateTime() {
        LocalDate parsedDate = dateParse();
        return LocalDateUtil.convertToDateOneDayLater(
                LocalDateTime.of(parsedDate.with(TemporalAdjusters.lastDayOfMonth()), LocalTime.MIN)
        );
    }

    private LocalDate dateParse() {
        return LocalDate.parse(this.date.concat("-01"));
    }

    public Long getMemberId() {
        return Long.valueOf(this.memberId);
    }
}

```

- 예전에 사이드 프로젝트를 할때 사용했던 `Request DTO` 다.
- 검증, 변환 등의 로직이 함께 들어가 있는데 의문이 들때가 많다. `DTO` 는 계층간의 데이터 전송을 위한 객체인데 변환이나 검증 등의 로직 들어가 있어도 되는걸까?
- 이게 저자가 말했던 잡종 구조의 객체는 아닐까? 라는 생각이 드는데 비즈니스 적으로 중요한 로직이 들어 있지는 않아 `활성 레코드` 로 생각할 수 도 있을것 같은데 조금 애매한 것 같기도 하다.

## 결론

- 상황에 맞게 자료구조 또는 객체를 사용하자.
- 저자가 말하는 자료구조 형식의 클래스를 사용할 일이 많을지는 잘 모르겠다.

## 토론

### 주제.1

- 정말 절차 지향 코드를 사용할 때가 있을까?

### 주제.2

- 추상화의 적절한 정도?

---

## REFERENCE

