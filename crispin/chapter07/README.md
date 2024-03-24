# 클린코드 7장

## 정리

### 오류 처리

오류 처리 코드로 인해 프로그램의 중요 핵심 로직을 파악하기 어렵다면, 좋은 코드라고 할 수 없다.
깨끗한 코드와 오류 처리는 연관성이 없어 보이지만, 위와 같은 이유로 인해 긴밀한 연괸상을 갖고 있을 수 있다 말할 수 있다.
#### 오류 코드 보다 예외를 사용하라

```java
public class DeviceController {

    public void sendShutDown() {
        DeviceHandle handle = getHandle(DEV1);

        if (handle != DeviceHandle.INVALID) {
            retrieveDeviceRecord(handle);
            if (record.getStatus() != DEVICE_SUSPENDED) {
                pauseDevice(handle);
                clearDeviceWorkQueue(handle);
                closeDevice(handle);
            } else {
                logger.log("Device suspended. Unable to shut down");
            }
        } else {
            logger.log("Invalid handle for: " + DEV1.toString());
        }
    }
}
```
- 예외를 사용하지 않고, 오류 확인 코드를 통해 함수의 호출에 있어 오류가 있는지 확인하는 로직

```Java
public class DeviceControllerTryCatch {

    public void sendShutDown() {
        try {
            tryToShutDown();
        } catch (DeviceShutDownError e) {
            logger.log(e);
        }
    }

    private void tryToShutDown() throws DeviceShutDownError {
        DeviceHandle handle = getHandle(DEV1);
        DeviceRecord record = retrieveDeviceRecord(handle);

        pauseDevice(handle);
        clearDeviceWorkQueue(handle);
        closeDevice(handle);
    }
    private DeviceHandle getHandle(DeviceId id) {
        throw new DeviceShutDownError("Invalid handle for: " + id.toString());
    }
}
```
- 예외를 사용하여, 오류를 발견하면 예외를 던지는 코드

두 개의 코드를 비교해 봤을때 확실히 예외를 사용하는 코드가 매우 깔끔하다는걸 알 수 있다.
예외를 사용하지 않고 오류 처리를 해본적이 없어, 크게 와 닿지는 않았지만 코드만 보더라도 예외 처리를 통한
오류 처리가 확실히 더 깔끔하고 역할에 따라 코드가 잘 분리되어 있는걸 알 수 있다.

#### Try-Catch-Finally 문 부터 작성하라

`try` 블록에서 발생한 오류는 어느 시점에서 실행되고 있던 실행을 중단하고 `catch` 블록으로 넘어가게 된다.
`try` 블록은 `트랜젝션` 과 비슷하다. `try` 블록에서 무슨 일이 생기든지 `catch` 블록은 프로그램의 상태를 일관성 있게 유지해야 한다. 이러한 이유로 예외가 발생할 코드를 작성할 때는 `Try-Catch-Finally` 문으로 시작하는 편이 좋다.

```java
public class ExceptionTest {
    @Test(expected = StorageException.class)
    public void retrieveSectionShouldThrowOnInvalidFileName() {
        sectionStore.retrieveSection("invalid - file");
    }
}
```
- 예외 상황 테스트 를 위한 테스트 코드 작성

```java
public List<RecordedGrip> retrieveSection(String sectionName) {
	try {
		FileInputStream stream = new FileInputStream(sectionName);
	} catch (Exception e) {
		throw new StorageException("retrieval error", e);
	}
	return new ArrayList<RecordedGrip>();
}
```
- 예외 상황 테스트 통과를 위한 코드 작성
- 예외가 발생했을때, `Exception` 종류의 예외를 `catch` 하여 특정 예외로 변환하여 예외처리 진행

```java
public List<RecorededGrip> retrieveSection(String sectionName) {
    try {
		FileInputStream stream = new FileInputStream(sectionName);
	} catch (FileNotFoundException e) {
		throw new StorageException("retrieval error", e);
	}
	return new ArrayList<RecordedGrip>();
}
```
- 예외 처리를 할때는 `Exception` 으로 넓은 범위의 예외를 처리하는것 보다. 위 코드와 같이 좁은 범위의 예외를 처리할수 있도록 코드를 작성하는것이 좋다.

먼저 강제로 예외를 발생시키는 테스트 케이스를 작성한 후 테스트를 통과하는 코드를 작성하는 것을 권장한다.
이런식으로 코드를 작성하게 되면 자연스럽게 `try` 블록의 `트랙잭션` 범위 부터 구현하게 되므로 `트랜잭션` 본질을 유지하기 쉽다.

오히려 `트랙잭션` 과 엮어서 설명하는게 더 이해하기 어려운것 같기도 하다.

#### UnChecked Exception 을 사용하라

자바에서는 예외의 종류가 두가지가 있다. `Checked Exception` 과 `UnChecked Exception` 다른 말로는
`Checked Exception` 의 경우 프로그래머가 꼭 예외를 처리해야 하며, `UnChecked Exception` 의 경우 필수로 예외를 처리하지 않아도 된다.

예전에는 `Checked Exception` 을 사용하는것이 멋진 코드 작성 방법이라 생각되던 시절이 있었으나,
지금은 `Checked Exception` 을 사용하면 여러 문제가(OCP, 캡슐화 등) 발생하여, `Checked Exception` 보다
`UnChecked Exception` 을 사용하는것이 좀 더 좋다.

`Checked Exception` 을 사용하게 되면 상위 메서드의 선언부에 하위 메서드의 예외를 정의해줘야 하는 문제
가 OCP 를 위반하고 캡슐화를 지키지 못한다고 저자는 설명하고 있다.

#### 호출자를 고려해 예외 클래스를 정의하라

```java
ACMEPort port = new ACMEPort();

try {
	port.open();
} catch (DeviceResponseException e) {
	reportPortError(e);
	logger.log("Device response exception", e);
} catch (ATM1212UnlockedException e) {
	reportPortError(e);
	logger.log("Device response exception", e);
} catch (GMXError e) {
	reportPortError(e);
	logger.log("Device response exception", e);
}
```
- 특정 API 라이브러리를 호출할때 발생할 수 있는 예외를 처리한 코드
- 코드 중복이 발생하고, 예외가 다른 예외가 추가되게 되면 해당 라이브러리를 호출하는 모든 곳의 코드를 수정해야 한다.

```java
public class LocalPort {

    private ACMEPort innerPort;

    public LocalPort(int portNumber) {
        this.innerPort = new ACMEPort(portNumber);
    }

    public void open() {
        try {
            port.open();
        } catch (DeviceResponseException e) {
            reportPortError(e);
            logger.log("Device response exception", e);
        } catch (ATM1212UnlockedException e) {
            reportPortError(e);
            logger.log("Device response exception", e);
        } catch (GMXError e) {
            reportPortError(e);
            logger.log("Device response exception", e);
        }
    }
}
```
- 특정 `wrapper` 클래스로 감싸서 사용하는 경우 코드의 변경점을 줄일 수 있다.
- 사용하던 API 라이브러리가 변경되더 라도 쉽게 갈아끼울수 있게 된다.

`wrapper` 클래스로 감싸는것 보다, 인터페이스로 추상화 하고 구현체를 만들어 사용하는건 별로인가?

#### null 을 반환도 전달도 하지 마라

이건 정말정말 많이 들었던 내용이다. `null` 을 그대로 `return` 하지도 말고, `매개변수` 로 전달 하지도 마라.
`null != 비어있는값(Collections.EMPTY_LIST)` null 값은 절대 비어있는 값과 같은것이 아니다.
자바는 `null safety` 한 언어가 절대 아니기 때문에 특히나 `null` 을 조심해서 다뤄야 한다.

자바 8에서 추가된 `Optional` 등을 사용해서 프로그래머가 적극적으로 `null` 처리를 해야만 한다.

## 결론

좋은 코드는 읽기에 좋기만 한 코드가 좋은 코드가 아니다, 정말 좋은 코드는 읽기도 좋아야 하지만 안정성도 높아야 한다. 오류 처리와 프로그램의 중심이 되는 코드를 분리하여 독립적인 추론이 가능한 코드가 유지보수에도 좋고 좋은 코드라고 할 수 있다.

---

## REFERENCE
