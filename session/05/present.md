## Chapter 5 형식 맞추기
### 코드 형식은 의사소통의 일환이다.
책에서 강조하고자 하는 내용

'돌아가는 코드'가 개발자의 일차적인 목표이지만, 아름답게 보이는 것은 더 중요하다. 

---
### 신문 기사처럼 작성하라
신문 기사처럼 작성하라는 것은, 코드의 가독성을 높이기 위한 것이다.
코드의 요약은 상단에 위치하고, 세부적인 내용은 아래로 내려가는 것이다.

### 컨벤션
결국 강조하고 싶은 것은 팀 간의 컨벤션이다.
- 코드 행간의 간격
```
import, Class, Method, Field 등의 선언 사이에는 빈 줄을 넣어라.
```
- 들여쓰기
```
코드간의 구분을 위해 사용
간혹 while, if 에서 무시하는 경우가 있음
p. 112 
```
- 행 길이 
```
전체 파일 1/3 정도는 40 줄에서 100줄을 조금 넘는다.
가장 긴 게 400줄 정도
= 200 자 정도로 작성해도 충분하다. 
p. 97
```
- 행의 빈 공간
```java
package com.wable.event_api.greeting;

import reactor.core.publisher.Mono;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class GreetingClient {
    private final WebClient client;

    public GreetingClient(WebClient.Builder builder) {
        this.client = builder.baseUrl("http://localhost:8085").build();
    }

    public Mono<String> getMessage() {
        return this.client.get().uri("/hello").accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Greeting.class)
                .map(Greeting::getMessage);
    }
}
```
- 가로 길이 (홀러리스=천공 카드의 80자 규칙) 
```
컴퓨터 화질이 좋아짐으로서 가로의 권장 길이는 120자.
젊은 친구들은 코드 크기를 줄여 200 까지 사용하기도 하지만 권장하지는 않는다.
```

### 가로 정렬
특이한 부분이라 뻈다.

- 위와 같은 가로 정렬은 별로 유용하지 못하다.
- 코드가 엉뚱한 부분을 강조해 진짜 의도가 가려진다.
- 변수 유형은 무시하고 변수 이름부터 읽게 되거나, 할당 연산자는 보이지 않고 피연산자에 눈이 간다.
```java
public class FitNesseExpediter implements ResponseSender {
    private   Socket          socket;
    private   InputStream     input;
    private   OutputStream    output;
    private   Request         request;
    private   Response        response;
    private   FitNesseContext context;
    protected long            requestParsingTimeLimit;
    private   long            requestProgress;
    private   long            requestParsingDeadline;
    private   boolean         hasError;

    public FitNesseExpediter(Socket          s,
                             FitNesseContext context) throws Exception
    {
        this.context =            context;
        socket =                  s;
        input =                   s.getInputStream();
        output =                  s.getOutputStream();
        requestParsingTimeLimit = 10000;
    }
}
```


### 종속 함수 (+인스턴스 변수)
1. 한 함수가 다른 함수를 호출한다면, 상관 관계에 있는 함수는 세로 배치, 가능하다면 먼저 호출되는 함수를 먼저 배치한다.
2. C++에서는 모든 인스턴수 변수를 클래스 마지막에 선언한다는 가위 규칙(scis-sors rule)을 적용한다. 
    하지만, 자바에서는 보통 클래스 맨 처음에 인스턴스 변수를 선언한다. 
    자바든 C++이든 잘 알려진 위치에서 인스턴스 변수를 모은다는 사실이 중요하다.

```java
public class WikiPageResponder implements SecureResponder {
    protected WikiPage page;
    protected PageData pageData;
    protected String pageTitle;
    protected Request request;
    protected PageCrawler crawler;

    public Response makeResponse(FitNesseContext context, Request request) throws Exception {
        String pageName = getPageNameOrDefault(request, "FrontPage");
        loadPage(pageName, context);
        if (page = null) 
            return notFoundResponse(context, request);
        else 
            return makePageResponse(context);
    }


    private String getPageNameOrDefault(Request request, String defaultPageName) 
    {
        String pageName = request.getResource();
        if (StringUtil.iSBLank(pageName)) 
            pageName = defaultPageName;
 
        return pageName;
    ｝

    protected void loadPage(String resource, FitNesseContext context) throws Exception {
        WikiPagePath path = PathParser.parse(resource);
        crawler = context.root getpageCrawler );
        crawler.setDeadEndStrategy(new VirtualEnabledPageCrawler());
        page = crawler.getPage(context.root, path);
        if (page != null) 
            pageData = page.getData();
    ｝
        
    private Response notFoundResponse(FitNesseContext context, Request request)
        throws Exception {
        return new NotFoundResponder().makeResponse(context, request);
    }

    private SimpleResponse makePageResponse (FitNesseContext context)
        throws Exception {
        pageTitle = PathParser.render(crawler.getFullPath(page));
        String html = makeHtml(context);

        SimpleResponse response = new SimpleResponse();
        response.setMaxAge(0);
        response.setContent(html);
        return response;
    }
}
```


### 팀 규칙
컨벤션(convention)

스타일은 일관적이고 매끄러워야 한다. 

온갖 스타일을 뒤섞어 소스 코드를 필요 이상으로 복잡하게 만드는 실수는 반드시 피한다.
```
- 이해하기 쉬워야 한다.
- 필요한 정보를 쉽게 찾을 수 있어야 한다.
- 잘못된 정보를 전달하지 않는다.
- 팀/조직 내에서는 컨벤션을 일관성 있게 적용해야 한다.

- 명명법(Naming Convention)
- 주석(Comment)
- 들여쓰기(Indent)
- 코드 리뷰 컨벤션
- README.md 컨벤션

등
```
그래서 사용하는 것이 ESLint, Prettier, CheckStyle, SonarQube 등이 있다.

SonarQube란 정적 코드 분석기로 컨벤션 등 규칙을 확인한다.

잘 만들어진 컨벤션 (구글 컨벤션) : https://google.github.io/styleguide/javaguide.html

REST API 컨벤션도 있어요 (MS) : https://github.com/microsoft/api-guidelines/blob/vNext/azure/Guidelines.md

### 밥아저씨
밥 아저씨란 상상속의 인물이라고 한다.

밥 아저씨가 작성한 코드는 굉장히 깔끔하다.

<img src="https://i.namu.wiki/i/d-lervrC9RJnUcoyP9AYIIJuTssWGm9M615lFHpsVN4zI63lZCLOUwSN28Mz-Pb9lDbhjfAQITuhysmaqaJBzYOLLUExJdpa65FZi1aT46ThfulQRS_RsT6q_GuGR9Q9Pv1vVErft4EqRQYa5On9Fg.webp" width="300px"/>

```java
public class CodeAnalyzer implements JavaFileAnalysis {
    private int lineCount;
    private int maxLineWidth;
    private int widestLineNumber;
    private LineWidthHistogram lineWidthHistogram;
    private int totalChars;

    public CodeAnalyzer() {
        lineWidthHistogram = new LineWidthHistogram();
    }

    public static List<File> findJavaFiles(File parentDirectory) {
        List<File> files = new ArrayList<File>();
        findJavaFiles(parentDirectory, files);
        return files;
    }

    private static void findJavaFiles(File parentDirectory, List<File> files) {
        for (File file : parentDirectory.listFiles()) {
            if (file.getName().endsWith(".java")) 
                files.add(file);
            else if (file.isDirectory()) 
                findJavaFiles(file, files);
        } 
    }

    public void analyzeFile(File javaFile) throws Exception { 
        BufferedReader br = new BufferedReader(new FileReader(javaFile)); 
        String line;
        while ((line = br.readLine()) != null)
            measureLine(line); 
    }

    private void measureLine(String line) { 
        lineCount++;
        int lineSize = line.length();
        totalChars += lineSize; 
        lineWidthHistogram.addLine(lineSize, lineCount);
        recordWidestLine(lineSize);
    }

    private void recordWidestLine(int lineSize) {
        if (lineSize > maxLineWidth) {
            maxLineWidth = lineSize;
            widestLineNumber = lineCount;
        }
    }

    public int getLineCount() {
        return lineCount;
    }

    public int getMaxLineWidth() {
        return maxLineWidth;
    }

    public int getWidestLineNumber() {
        return widestLineNumber;
    }

    public LineWidthHistogram getLineWidthHistogram() {
        return lineWidthHistogram;
    }

    public double getMeanLineWidth() {
        return (double) totalChars/lineCount;
    }

    public int getMedianLineWidth() {
        Integer[] sortedWidths = getSortedWidths(); 
        int cumulativeLineCount = 0;
        for (int width : sortedWidths) {
            cumulativeLineCount += lineCountForWidth(width); 
            if (cumulativeLineCount > lineCount/2)
                return width;
        }
        throw new Error("Cannot get here"); 
    }

    private int lineCountForWidth(int width) {
        return lineWidthHistogram.getLinesforWidth(width).size();
    }

    private Integer[] getSortedWidths() {
        Set<Integer> widths = lineWidthHistogram.getWidths(); 
        Integer[] sortedWidths = (widths.toArray(new Integer[0])); 
        Arrays.sort(sortedWidths);
        return sortedWidths;
    }
}
```