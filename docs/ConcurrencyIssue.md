## 나의 시나리오에서 발생할 수 있는 동시성 이슈

### 1. 포인트 충전 시 동시성 문제
> * 포인트 충전, 사용시 동시에 들어올 때 순차적으로 트랜잭션이 진행되어야 한다.
> * 멀티스레드인 경우가 생길 수 도 있지만 (내 계정을 누군가에게 빌려줬을때 두 명이 동시에 
> 이벤트를 발생시킨 경우), 거의 싱글스레드 방식으로 진행된다. 
> * 보통 UserPoint 테이블의 경우 조회가 더 많이 발생된다.
#### * 분산락 (Redisson 라이브러리 사용예정)
DB의 트랜잭션의 순차적 처리를 보장한다. 여러 서버 또는 프로세스 간에 동기화된 락을 관리한다.
</br> 레디스, Kafka 등을 이용하여 분산 시스템에서의 동시성 제어를 할 수 있다.
- 장점 :
</br> 1. 여러 서버나 클러스터 간에 동시에 데이터를 접근할 때의 순차적으로 실행시켜서 문제를 해결할 수 있다. 
</br> 2. lock 획득을 위해 대기하다가 획득 실패 시 예외처리를 진행할 수도 있다. 
(simple lock)
</br> 3. db커넥션을 물고 기다리는 것보다 db부하를 줄일 수 있다.

- 단점 :
  </br> 락 획득, 해제와 트랜잭션의 순서를 잘 적용해야한다. (그렇지 않으면 문제발생) 
#### * 낙관적락 (optimistic Lock)
만일 단일스레드에서 같은 작업을 동시에 여러번 요청한 경우, 
</br> 나머지 요청에 대해 예외처리 시켜줄 수 있다.  
- 장점 :
</br> 락은 충돌이 많이 일어나지 않을 것이라고 예상되는 곳에 사용하면 성능적으로 좋은 편이다.
- 단점 : 
</br> 1. 충돌이 자주 일어날 경우, rollback 이나 retry 에 대해 고려해야 함 
</br> > but. 포인트 충전은 상품재고 수정같은 경우보다는 충돌이 잦은편이 아니다.
</br> 2. 비관적 락의 경우 트랜잭션만 롤백해주면 되지만, 낙관적 락의 경우에는 충돌이 발생하면 개발자가 수동으로 롤백처리를 하나하나 해줘야 한다는 단점이 있다.

### 2. 상품 주문 시 동시성 문제 
> * 여러 스레드에서 동시에 상품재고 수정(update) 요청을 할 수 있다.
> * 인기 상품의 경우 수정이 잦을 수 있다. (충돌 빈도가 잦음)
#### * 비관적락 (DB 락)
비관적락은 데이터베이스 내에서 동작되며, 데이터에 대한 접근을 제한하는 방식으로 동작한다.
</br> 데이터를 읽거나 수정하기 전에 미리 락을 설정하여 다른 트랜잭션의 접근을 막는다.
- 장점 :
</br> 1. 구현이 간편한 편이다.
</br> 2. 데드락을 방지할 수 있다.
</br> 3. 데이터 일관성을 쉽게 유지할 수 있다.
- 단점 :
</br> 비관적 락은 충돌이 발생할거라고 생각하고 바로 Lock 을 걸어버리기 때문에 성능적인 측면에서 좋은 편은 아니다.
