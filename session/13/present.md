# Chapter 13 동시성
## 리뷰
### java 친화적
java 에 익숙하지 않다면 저자가 제안하는 동시성과 관련된 고민에 공감하기 어려울 것 같다. 
### 실행 모델을 이해하라
실행 모델이라 함은, 동시성을 처리해야 하는 상황을 규정하는 것. \
대체로 해결해본 상황은 식사하는 철학자들, Mutual exclusion 을 해결해본 것 뿐이다.
## 스레드 스케줄링
- 스레드 스케줄링은 여러 스레드가 동시에 실행되는 상황에서 어떤 스레드를 실행할지 결정하는 정책에 관한 것.
- 처리율, CPU 이용률을 높이고 반환 시간, 대기 시간 등을 낮추기 위한 최적의 정책을 찾아야 함.
### 개념
- 처리율: 단위 시간당 처리하는 작업(스레드, 프로세스)의 양
- CPU 이용률: 주어진 시간 중 CPU가 이용된 시간의 비율
- 반환 시간: 스레드가 생성된 후 종료될 때까지 걸리는 시간
- 대기 시간: 스레드가 CPU를 사용하기 위해 대기하는 시간

### 스레드 스케줄링 방식
#### 선점형 스케줄링
- CPU를 점유하고 있는 스레드를 잠시 멈추고 강제로 빼앗아 사용할 수 있는 스케줄링 방식
1. FIFO
- First In First Out
2. 라운드 로빈
- 시분할 시스템: 컴퓨터 자원을 각 사용자들에게 시가적으로 분할하여 사용하도록 하는 시스템
- 시분할 시스템을 이용하여, 모든 스레드가 동일한 시간만큼 CPU 자원을 사용하는 방식.
- 시간 내에 종료되지 못한 프로세스는 대기 상태로 queue에 들어감. 다음 프로세스가 실행됨.
3. SRT
- Shortest Remaining Time
- 남은 실행 시간이 가장 짧은 프로세스를 먼저 실행하는 방식
4. 다단계 큐
- 여러 개의 큐를 사용하여 분류에 따라 스레드를 분류하는 방식
- 우선순위가 높은 큐부터 실행됨.
- 각 큐는 독자적인 우선순위를 가짐.
5. 다단계 피드백 큐
- 높은 우선순위의 큐는 CPU 사용 시간이 적게 할당되며, 낮은 우선순위의 큐는 많이 할당됨.
- CPU 사용 시간을 초과한 경우, 낮은 우선순위의 큐로 이동함.
- 짧은 시간이 소요되는 작업이 빠르게 처리되면서 동시에 많은 작업 시간이 소요되는 경우엔 점진적으로 보상받을 수 있는 시스템.
#### 비선점형 스케줄링
- 협력형 스케줄링이라고도 함
- CPU를 점유하고 있는 스레드가 스스로 양보할 때만 스케줄링이 이루어지는 방식
1. FCFS
- First Come First Served
2. SJF
- Shortest Job First
- 평균 대기 시간이 최소일 수 밖에 없지만, 기아 현상이 발생할 수 있음.
  - 기아 현상: 우선순위가 낮은 프로세스가 계속해서 CPU 자원을 할당받지 못하는 현상
  - Aging: 기아 현상을 해결하기 위해 우선순위를 높여주는 방식
  - 
3. HRN
- Highest Response Ratio Next
- Response Ratio: (대기 시간 + 실행 시간) / 실행 시간
### 플랫폼 별 스레드 스케줄링
#### Kernel-Level Threads vs. User-Level Threads
- Kernel-Level Threads
  - OS가 스레드를 관리하는 방식
  - 스레드 간의 동기화, 스케줄링 등을 OS가 관리함.
  - 기본적으로 preemptive 스케줄링을 사용함.
- User-Level Threads
  - 프로그램이 스레드를 관리하는 방식
  - 스레드 간의 동기화, 스케줄링 등을 프로그램이 관리함.
  - 기본적으로 cooperative 스케줄링을 사용함.
1. Java
- 아무 세팅도 없다면 기본적으로 OS의 스레드 스케줄링을 사용함. => KLT
- 다양한 구현체들로 스레드 스케줄링을 조절할 수 있음. => ULT
- jvm이 ult 들을 klt로 매핑해주는 방식으로 동작함.

2. Node.js
- 기본적으로 single - thread 모델
- 굳이 굳이 worker thread를 사용하여 자원을 공유하는 멀티 스레드를 구현해도 코드 상 특정 수행을 interrupt 할 수 없음.
- 동기적으로 실행되는 코드인데다가, thread 간 자원을 공유하는 방식이 message 형태로 통신하는 것이기 때문.
- 궁금한 점은, libuv library 는 c++ 로 구현되어 있고, kernel level thread 를 사용함. preemptive 일 수 있음.


### References
- https://letsmakemyselfprogrammer.tistory.com/98
- https://medium.com/traveloka-engineering/cooperative-vs-preemptive-a-quest-to-maximize-concurrency-power-3b10c5a920fe
- https://elixirforum.com/t/what-is-the-difference-between-preemptive-scheduling-in-java-and-elixir/58199/10