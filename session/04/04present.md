# 04장 Chapter 4 이나현

68p

주석은 오래될수록 코드에서 멀어진다. 오래될수록 완전히 그릇될 가능성도 커진다. 이유는 단순하다. 프로그래머들이 주석을 유지하고 보수하기란 현실적으로 불가능하니까.

```java
/**
 * Class Name : Globals.java Description : 시스템 구동 시 프로퍼티를 통해 사용될 전역변수를 정의한다.
 * Modification Information
 *
 * 수정일 수정자 수정내용 ------- -------- --------------------------- 2009.01.19 박지욱 최초
 * 생성 2014.05.20 강희상 UPLOAD_FILE_ID 추가 2014.05.20 강희상 THUMNAIL_PATH 추가
 *
 * @author 공통 서비스 개발팀 박지욱
 * @since 2009. 01. 19
 * @version 1.0
 * @see
 *
 */
```

💬10년 동안 업데이트 되지 않은 주석. 삭제해도 될까?

**주석은 나쁜 코드를 보완하지 못한다**

표현력이 풍부하고 깔끔하며 주석이 거의 없는 코드가, 복잡하고 어수선하며 주석이 많이 달린 코드보다 훨씬좋다. 자신이 저지른 난장판을 주석으로 설명하려 애쓰는 대신에 그 난장판을 깨끗이 치우는 데 시간을 보내라!

**코드로 의도를 표현하라!**

몇 초만 더 생각하면 코드로 대다수 의도를 표현할 수 있다. 많은 경우 주석을 달려는 설명을 함수로 만들어 표현해도 충분하다.

## 좋은 주석

- **법적인 주석**
    
    각 소스 파일 첫머리에 주석으로 들어가는 저작권 정보와 소유권 정보는 필요하고도 마땅하다.
    
    ```java
    // Copyright (C) 2003, 2004, 2005 by Object Montor, Inc. All rights reserved.
    // GNU General Public License 버전 2 이상을 따르는 조건으로 배포한다.
    ```
    
- **정보를 제공하는 주석**
    
    ```java
    // kk:mm:ss EEE, MMM, dd, yyyy 형식이다.
    Pattern timeMAtcher = Pattern.complie("\\d*:\\d*:\\d* \\w*, \\w* \\d*, \\d*");
    ```
    
- **의도를 설명하는 주석**
    
    ```java
    // 스레드를 대량 생성하는 방법으로 어떻게든 경쟁 조건을 만들려 시도한다.
    for (int i = 0; i < 25000; i++) {
    	WidgetBuilderThread widgetBuilderThread = new WidgetBuilderThread(widgetBuilder, text, parent, failFlag);
        Thread thread = new Thread(widgetBuilderThread);
        thread.start();
    }
    ```
    
    때때로 주석은 구현을 이해하게 도와주는 선을 넘어 결정에 깔린 의도까지 설명한다.
    
- **의미를 명료하게 밝히는 주석**
    
    때때로 모호한 인수나 반환값은 그 의미를 읽기 좋게 표현하면 이해하기 쉬워진다.
    
    ```java
    assertTrue(a.compareTo(a) == 0); // a == a
    assertTrue(a.compareTo(b) !== 0); // a != b
    ```
    
    주석이 올바른지 검증하기 쉽지 않다. 이것이 **의미를 명료히 밝히는 주석이 필요한 이유인 동시에 주석이 위험한 이유**이기도 하다.
    
- **결과를 경고하는 주석**
    
    때로 다른 프로그래머에게 결과를 경고할 목적으로 주석을 사용한다.
    
    ```java
    // SimpleFormat은 스레드에 안전하지 못하다.
    // 따라서 각 인스턴스를 독립적으로 생성해야 한다.
    SimpleDateFormat df = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z");
    df.setTimeZone(TimeZone.getTimeZone("GMT"));
    return df;
    ```
    
- **TODO 주석**
    
    TODO 주석은 프로그래머가 필요하다 여기지만 당장 구현하기 어려운 업무를 기술한다. 더 이상 필요 없는 기능을 삭제하라는 알림, 누군가에게 문제를 봐달라는 요청, 더 좋은 이름을 떠올려달라는 부탁, 앞으로 발생할 이벤트에 맞춰 코드를 고치라는 주의 등에 유용하다.
    
    ➡️ 주기적으로 TODO주석을 점검해 없애도 괜찮은 주석은 없애라고 권한다.
    
- **중요성을 강조하는 주석**
    
    ```java
    String listItemContent = match.group(3).trim();
    // 여기서 trim은 정말 중요하다. trim 함수는 문자열에서 시작 공백을 제거한다.
    // 문자열에서 시작 공백이 있으면 다른 문자열로 인식하기 떄문이다.
    ```
    
- **공개 API에서 Javadocs**
    
    공개 API를 구현한다면 반드시 훌륭한 Javadocs를 작성한다. 하지만 어느 주석과 마찬가지로 Javadocs 역시 독자를 오도하거나, 잘못 위치하거나, 그릇된 정보를 전달할 가능성이 존재한다.
    

## 나쁜 주석

일반적으로 대다수 주석은 허술한 코드를 지탱하거나, 엉성한 코드를 변명하거나, 미숙한 결정을 합리화하는 등 프로그래머가 주절거리는 독백에서 크게 벗어나지 못한다

- **주절거리는 주석**
    
    ```java
    	/*
    	기존 존재하는 로직
    	쓰는지 여부 모름
    	*/
    ```
    
- **같은 이야기를 중복하는 주석**
    
    ```java
    String newPasswd = param.get("newPwd") == null ? "" : (String) param.get("newPwd");	// 변경 비밀번호
    String conPasswd = param.get("conPwd") == null ? "" :(String)param.get("conPwd");	// 비밀번호 확인
    String userId = param.get("userid") == null ? "" : (String)param.get("userid"); 	// 유저ID
    ```
    
- **오해할 여지가 있는 주석**
    
    (코드보다 읽기 어려운) 주석에 담긴 '살짝 잘못된 정보'
    
- **의무적으로 다는 주석**
    
    ```java
    } catch (Exception e) {
    			// TODO: handle exception
    ```
    
- **이력을 기록하는 주석**
    
    ```java
    /* 20230717_lnh 임시 비밀번호 발급 이후 로그인 */
    ```
    
- **있으나 마나 한 주석**
    
    ```java
    	/**
    	 * 학습컨텐츠 정보 삭제
    	 * @param param
    	 * @throws Exception
    	 */
    	public void deleteIp(Map<String, Object> param) throws Exception {
    		delete("InformationProvideManagerDAO.deleteIp", param);
    	}
    ```
    
    지나친 참견이라 개발자가 주석을 무시하는 습관에 빠진다. 결국은 코드가 바뀌면서 주석은 거짓말로 변한다. **있으나 마나 한 주석을 달려는 유혹에서 벗어나 코드를 정리하라.**
    
- **무서운 잡음**
- **함수나 변수로 표현할 수 있다면 주석을 달지 마라**
    
    ```java
    // 전역목록 <smodule>에 속하는 모듈이 우리가 속한 하위 시스템에 의존하는가?
    if(smodule.getDependSubsystems().contains(subSysMod.getSubSystem()))
    ```
    
    주석을 없애고 다시 표현하면
    
    ```java
    ArrayList moduleDependees = smodule.getDependSubsystems();
    String ourSubSystem = subSysMod.getSubSystem();
    if (moduleDependees.contains(ourSubSystem))
    ```
    
- **위치를 표시하는 주석**
    
    반드시 필요할 때만, 아주 드물게 사용하는 편이 좋다. 남용하면 독자가 흔한 잡음으로 여겨 무시한다.
    
    💬반드시 필요할 때란 언제 일지? 다른 분들은 어떤 기준으로 위치를 표시하는 주석을 사용하시나요?
    
- **닫는 괄호에 다는 주석**
    
    중첩이 심하고 장황환 함수라면 의미가 있을지도 모르지만 (우리가 선호하는) 작고 캡슐화된 함수에는 잡음일 뿐이다. 닫는 괄호에 주석을 달아야겠다는 생각이 든다면 대신에 함수를 줄이려 시도하자.
    
- **공로를 돌리거나 저자를 표시하는 주석**
- **주석으로 처리한 코드**
- **HTML 주석**
- **전역 정보**
    
    주석을 달아야 한다면 근처에 있는 코드만 기술하라. 코드 일부에 주석을 달면서 시스템의 전반적인 정보를 기술하지 마라. 코드가 변해도 아래 주석이 변하리라는 보장은 전혀 없다.
    
- **너무 많은 정보**
- **모호한 관계**
    
    주석과 주석이 설명하는 코드는 둘 사이 관계가 명백해야 한다. 주석을 다는 목적은 코드만으로 설명이 부족해서다. 주석 자체가 다시 설명을 요구하니 안타깝기 그지없다.
    
- **함수 헤더**
    
    짦은 함수는 긴 설명이 필요 없다. 짧고 한 가지만 수행하며 이름을 잘 붙인 함수가 주석으로 헤더를 추가한 함수보다 훨씬 좋다.
    
- **비공개 코드에서 Javadocs**
    
    공개 API는 Javadocs가 유용하지만 공개하지 않을 코드라면 Javadocs는 쓸모가 없다.
    

💬각자 주석 규칙이 있는지? 또 주로 언제 주석을 작성하시나요?