### 복간에 부쳐

이 책에 나오는 모든 지침은 로버트 C. 마틴이 이미 밝혔듯이 절대적이라 생각하면 안 되며, 언제든지 개선의 여지가 있다고 생각하는 편이 바람직하다. 여기서 핵심은 팀이나 공동체에서 서로 동의하는 합리적인 원칙을 세우기 위한 소통에 있다. Clean Code는 이런 소통을 위한 기초 지식을 제공하고 생각할 거리를 던져주는 책이므로 개발자 사이에서 가치를 발휘한다.

### 들어가면서

**깨끗한 코드를 작성하는 방법은 배우기가 어렵다.** 단순히 원칙과 패턴을 안다고 깨끗한 코드가 나오지 않는다. **고생을 해야 한다.** 스스로 연습하고 실패도 맛봐야 한다. 남들이 시도하다 실패하는 모습도 봐야 한다. 그들이 넘어지고 일어서는 모습도 봐야 한다. 결정을 내리느라 고민하는 모습, 잘못된 결정으로 대가를 치르는 모습도 봐야한다. 

💬어쩐지 리팩토링 하는데 왜 이렇게 힘들지? 왜 어렵지? 남들은 쉽게 하는거 같은데 나만 바본가 싶었는데 다행이다

## 1장 깨끗한 코드

우리 모두는 자신이 짠 쓰레기 코드를 쳐다보면서 나중에 손보겠다고 생각한 경험이 있다. 우리 모두는 대충 짠 프로그램이 돌아간다는 사실에 안도감을 느끼며 그래도 안 돌아가는 프로그램보다 돌아가는 쓰레기기가 좋다고 스스로를 위로한 경험이 있다. 다시 돌아와 나중에 정리하겠다고 다짐했었다. 물론 그때 그 시절 우리는 르블랑의 법칙을 몰랐다. **나중은 결코 오지 않는다.** 

💬리팩토링 하기 싫어 죽겠는데 이런 말 보니 싫어도 해야지.. 근데 르블랑의 법칙이 뭐지? 
[LeBlanc's Law (a.k.a Later Equals Never) - Yiming Chen](https://yiming.dev/clipping/2019/03/21/le-blanc's-law-a-k-a-later-equals-never/)

### 태도

일정에 쫓기더라도 대다수 관리자는 좋은 코드를 원한다. 그들이 일정과 요구사항을 강력하게 밀어붙이는 이유는 그것이 그들의 책임이기 때문이다. 좋은 코드를 사수하는 일은 바로 우리 프로그래머들의 책임이다. 

### 원초적 난제

나쁜 코드를 양산하면 기한을 맞추지 못한다. 오히려 엉망진창인 상태로 인해 속도가 곧바로 늦어지고, 결국 기한을 놓친다. 기한을 맞추는 유일한 방법은, 그러니까 빨리 가는 유일한 방법은, 언제나 코드를 최대한 깨끗하게 유지하는 습관이다. 

### 깨끗한 코드라는 예술?

깨끗한 코드와 나쁜 코드를 구분할 줄 안다고 깨끗한 코드를 작성할 줄 안다는 뜻은 아니다.

### 깨끗한 코드란?

깨끗한 코드는 세세한 사항까지 꼼꼼하게 처리하는 코드다.

(중략) 깨끗한 코드는 한 가지에 ‘집중’한다. 각 함수와 클래스와 모듈은 주변 상황에 현혹되거나 오염되지 않은 채 한길만 걷는다.

코드는 추측이 아니라 사실에 기반해야 한다. 반드시 필요한 내용만 담아야 한다. 코드를 읽는 사람에게 프로그래머가 단호하다는 인상을 줘야 한다. 

아무리 코드가 우아해도, 아무리 가독성이 높아도, 테스트 케이스가 없으면 깨끗하지 않다.

(중략) 또한 코드가 ‘문학적’이어야 한다고 말한다. 커누스가 기술한 문학적프로그래밍이 떠오르는 발언이다. 요점은 인간이 읽기 좋은 코드를 작성하라는 말이다. 

깨끗한 코드는 읽으면서 놀랄 일이 없어야 한다고 워드는 말한다. 맞는 말이다. 코드를 독해하느라 머리를 쥐어짤 필요가 없어야 한다. 읽으면서 짐작한 대로 돌아가는 코드가 깨끗한 코드다. 명백하고 단순해 마음이 끌리는 코드가 깨끗한 코드다. 각 모듈은 다음 무대를 준비한다. 모듈을 읽으면 다음에 벌어질 상황이 보인다. 이만큼 깨끗한 코드는 너무도 잘 짜놓은 코드라 읽는 이가 그 사실을 모르고 넘어간다. 모든 뛰어난 설계처럼 설계자가 코드를 어이 없을 정도로 단순하게 설계했기 때문이다. 

## 제 2장 의미있는 이름

### 의미있게 구분하라

컴파일러나 인터프리터만 통과하려는 생각으로 코드를 구현하는 프로그래머는 스스로 문제를 일으킨다.  (중략) 컴파일러를 통과할지라도 연속된 숫자를 덧붙이거나 불용어를 추가하는 방식은 적절하지 못하다. 이름이 달라야 한다면 의미도 달라져야 한다. 

### 클래스 이름

클래스 이름과 객체 이름은 명사나 명사구가 적합하다. Manager, Processor, Data, Info등과 같은 단어는 피하고, 동사는 사용하지 않는다.

### 메서드 이름

메서드 이름은 동사나 동사구가 적합하다. 접근자, 변경자, 조건자는 javabean표준에 따라 값 앞에 get, set, is를 붙인다. 생성자를 오버로딩할때는 정적 팩팩토리 메서드를 사용한다. 메서드는 인수를 설명하는 이름을 사용한다.

```java
Complex fulcrumPoint = new Complex(23.0); // 이 코드 보단
Complex fulcrumPoint = Complex.FromeRealNumber(23.0); // 이 코드가 더 좋다.
```

### 한 개념에 한 단어를 사용하라

동일 코드 기반에 controller, manager, driver를 섞어 쓰면 혼란스럽다. DeviceManager와 ProtocolController는 근본적으로 어떻게 다른가? 어째서 둘다 controller가 아닌가? 어째서 둘다 Manager가 아닌가? 정말 둘 다 Driver가 아닌가? 이름이 다르면 독자는 당연히 클래스도 다르고 타입도 다르리라 생각한다. 일관성 있는 어휘는 코드를 사용할 프로그래머가 반갑게 여길 선물이다. 

### 말장난을 하지마라

프로그래머는 코드를 최대한 이해하기 쉽게 짜야한다. 집중적인 탐구가 필요한 코드가 아니라 대충 훑어봐도 이해할 코드 작성이 목표다. 의미를 해독할 책임이 독자에게 있는 논문 모델이 아니라 의도를 밝힐 책임이 저자에게 있는 잡지 모델이 바람직하다. 

### 마치면서

사람들이 이름을 바꾸지 않으려는 이유 하나는 다른 개발자가 반대할까 두려워서다. 우리들 생각은 다르다. 오히려 (좋은 이름으로 바꿔주면) 반갑고 고맙다. 우리들 대다수는 자신이 짠 클래스 이름과 메서드이름을 모두 암기하지 못한다. 암기는 요즘 나오는 도구에게 맡기고, 우리는 문장이나 문단처럼 읽히는 코드 아니면 (정보를 표시하는 최선의 방법이 항상 문장만은 아니므로) 적어도 표나 자료 구조처럼 읽히는 코드를 따는 데만 집중해야 마땅하다. 여느 코드 개선 노력과 마찬가지로 이름 역시 나름대로 바꿨다가는 누군가 질책할지도 모른다. 그렇다고 코드를 개선하려는 노력을 중단해서는 안된다. 

## 제 3장 함수

### 작게 만들어라!

**블록과 들여쓰기**

다시말해, if문/else문/while문 등에 들어가는 블록은 한 줄이어야 한다는 의미미다. 대개 거기서 함수를 호출한다. 그러면 바깥을 감싸는 함수가 작아질 뿐 아니라, 블록 안에서 호출하는 함수 이름을 적절히 짓는다면, 코드를 이해하기 쉬워진다. 이 말은 중첩 구조가  생길만큼 함수가 커져서는 안된다는 뜻이다. 그러므로 함수에서 들여쓰기 수준은 1단이나 2단을 넘어서면 안 된다. 

### 한 가지만 해라!

> 함수는 한 가지를 해야 한다. 그 한 가지를 잘 해야 한다. 그 한가지만을 해야 한다.
> 

따라서, 함수가 ‘한 가지’만 하는지 판단하는 방법이 하나 더 있다. 단순히 다른 표현이 아니라 의미 있는 이름으로 다른 함수를 추출할 수 있다면 그 함수는 여러 작업을 하는 셈이다. 

### 함수당 추상화 수준은 하나로!

한 함수내에 추상화 수준을 섞으면 코드를 읽는 사람이 헷갈린다. 특정 표현이 근본 개념인지 아니면 세부사항인지 구분하기 어려운 탓이다. 하지만 문제는 이 정도로 그치지 않는다. 근본 개념과 세부사항을 뒤섞기 시작하면, 깨어진 창문처럼 사람들이 함수에 세부사항을 점점 더 추가한다.

🔽SRP관련 내용

[💠 완벽하게 이해하는 SRP (단일 책임 원칙)](https://inpa.tistory.com/entry/OOP-💠-아주-쉽게-이해하는-SRP-단일-책임-원칙)

### 서술적인 이름을 사용하라!

“코드를 읽으면서 짐작했던 기능을 각 루틴이 그대로 수행한다면 깨끗한 코드라 불러도 되겠다.”

이름이 길어도 괜찮다. 겁먹을 필요 없다. 길고 서술적인 이름이 짧고 어려운 이름보다 좋다. 길고 서술적인 이름이 길고 서술적인 주석보다 좋다. 

이름을 붙일 때는 일관성이 있어야 한다. 모듈 내에서 함수 이름은 같은 문구, 명사, 동사를 사용한다. includeSetupAndTeardownPages, includeSetupPages, includeSuiteSetupPage, includeSetupPage 등이 좋은 예다. 문체가 비슷하면 이야기를 순차적으로 풀어가기도 쉬워진다.

**삼항 함수**

인수가 3개인 함수는 인수가 2개인 함수보다 훨씬 더 이해하기 어렵다. 순서, 주춤, 무시로 야기되는 문제가 두 배 이상 늘어난다. 그래서 삼항 함수를 만들 때는 신중히 고려하라 권고한다.

**인수 객체**

인수가 2-3개가 필요하다면 일부를 독자적인 클래스 변수로 선언할 가능성을 짚어 본다. 예를 들어, 다음 두 함수를 살펴보자

```java
Circle makeCircle(double x, double y, double radius);
Circle makeCircle(Point center, double radius);
```

객체를 생성해 인수를 줄이는 방법이 눈속임이라 여겨질지 모르지만 그렇지 않다. 위 예제에서 x와 y를 묶었듯이 변수를 묶어 넘기려면 이름을 붙여야 하므로 결국은 개념을 표현하게 된다.

### 명령과 조회를 분리하라!

함수는 뭔가를 수행하거나 뭔가에 답하거나 둘 중 하나만 해야한다. 둘 다 하면 안된다. 객체 상태를 변경하거나 아니면 객체 정보를 반환하거나 둘 중 하나다. 둘 다 하면 혼란을 초래한다. 

- 3-7 예시 코드 최종 리팩토링 수정본
    
    ```java
    package fitnesse.html;
    import fitnesse.responders.run.SuiteResponder;
    import fitnesse.wiki.*;
    public class SetupTeardownIncluder {
        private PageData pageData;
        private boolean isSuite;
        private WikiPage testPage;
        private StringBuffer newPageContent;
        private PageCrawler pageCrawler;
        public static String render(PageData pageData) throws Exception {
            return render(pageData, false);
        }
        public static String render(PageData pageData, boolean isSuite)
        throws Exception {
            return new SetupTeardownIncluder(pageData).render(isSuite);
        }
        private SetupTeardownIncluder(PageData pageData) {
            this.pageData = pageData;
            testPage = pageData.getWikiPage();
            pageCrawler = testPage.getPageCrawler();
            newPageContent = new StringBuffer();
        }
        private String render(boolean isSuite) throws Exception {
            this.isSuite = isSuite;
            if (isTestPage())
                includeSetupAndTeardownPages();
            return pageData.getHtml();
        }
        private boolean isTestPage() throws Exception {
            return pageData.hasAttribute("Test");
        }
        private void includeSetupAndTeardownPages() throws Exception {
            includeSetupPages();
            includePageContent();
            includeTeardownPages();
            updatePageContent();
        }
        private void includeSetupPages() throws Exception {
            if (isSuite)
                includeSuiteSetupPage();
            includeSetupPage();
        }
        private void includeSuiteSetupPage() throws Exception {
            include(SuiteResponder.SUITE_SETUP_NAME, "-setup");
        }
        private void includeSetupPage() throws Exception {
            include("SetUp", "-setup");
        }
        private void includePageContent() throws Exception {
            newPageContent.append(pageData.getContent());
        }
        private void includeTeardownPages() throws Exception {
            includeTeardownPage();
            if (isSuite)
                includeSuiteTeardownPage();
        }
        private void includeTeardownPage() throws Exception {
            include("TearDown", "-teardown");
        }
        private void includeSuiteTeardownPage() throws Exception {
            include(SuiteResponder.SUITE_TEARDOWN_NAME, "-teardown");
        }
        private void updatePageContent() throws Exception {
            pageData.setContent(newPageContent.toString());
        }
        private void include(String pageName, String arg) throws Exception {
            WikiPage inheritedPage = findInheritedPage(pageName);
            if (inheritedPage != null) {
                String pagePathName = getPathNameForPage(inheritedPage);
                buildIncludeDirective(pagePathName, arg);
            }
        }
        private WikiPage findInheritedPage(String pageName) throws Exception {
            return PageCrawlerImpl.getInheritedPage(pageName, testPage);
        }
        private String getPathNameForPage(WikiPage page) throws Exception {
            WikiPagePath pagePath = pageCrawler.getFullPath(page);
            return PathParser.render(pagePath);
        }
        private void buildIncludeDirective(String pagePathName, String arg) {
            newPageContent
                .append("\n!include ")
                .append(arg)
                .append(" .")
                .append(pagePathName)
                .append("\n");
        }
    }
    ```
    

💬이렇게 까지 작게 메서드를 만들었다는게 신기하고 또 굳이 이렇게 까지 작게? 라는 생각과 약간의 거부감이 드는걸 봐선 아직은 내가 잘 모르기 때문에 그런거 같다.
