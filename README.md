# e-커머스 서비스
## Description

- `e-커머스 상품 주문 서비스`를 구현해 봅니다.
- 상품 주문에 필요한 메뉴 정보들을 구성하고 조회가 가능해야 합니다.
- 사용자는 상품을 여러개 선택해 주문할 수 있고, 미리 충전한 잔액을 이용합니다.
- 상품 주문 내역을 통해 판매량이 가장 높은 상품을 추천합니다.

## Requirements

- 아래 4가지 API 를 구현합니다.
    - 잔액 충전 / 조회 API
    - 상품 조회 API
    - 주문 / 결제 API
    - 인기 판매 상품 조회 API
- 각 기능 및 제약사항에 대해 단위 테스트를 반드시 하나 이상 작성하도록 합니다.
- 다수의 인스턴스로 어플리케이션이 동작하더라도 기능에 문제가 없도록 작성하도록 합니다.
- 동시성 이슈를 고려하여 구현합니다.
- 재고 관리에 문제 없도록 구현합니다.


## API Specs

### 기본과제

1️⃣ `**주요**` **잔액 충전 / 조회 API**

- 결제에 사용될 금액을 충전하는 API 를 작성합니다.
- 사용자 식별자 및 충전할 금액을 받아 잔액을 충전합니다.
- 사용자 식별자를 통해 해당 사용자의 잔액을 조회합니다.

2️⃣ `**기본` 상품 조회 API**

- 상품 정보 ( ID, 이름, 가격, 잔여수량 ) 을 조회하는 API 를 작성합니다.
- 조회시점의 상품별 잔여수량이 정확하면 좋습니다.

3️⃣ `**주요**` **주문 / 결제 API**

- 사용자 식별자와 (상품 ID, 수량) 목록을 입력받아 주문하고 결제를 수행하는 API 를 작성합니다.
- 결제는 기 충전된 잔액을 기반으로 수행하며 성공할 시 잔액을 차감해야 합니다.
- 데이터 분석을 위해 결제 성공 시에 실시간으로 주문 정보를 데이터 플랫폼에 전송해야 합니다. ( 데이터 플랫폼이 어플리케이션 `외부` 라는 가정만 지켜 작업해 주시면 됩니다 )

> 데이터 플랫폼으로의 전송 기능은 Mock API, Fake Module 등 다양한 방법으로 접근해 봅니다.
> 

4️⃣ `**기본` 상위 상품 조회 API**

- 최근 3일간 가장 많이 팔린 상위 5개 상품 정보를 제공하는 API 를 작성합니다.
- 통계 정보를 다루기 위한 기술적 고민을 충분히 해보도록 합니다.


## ERD
![스크린샷 2024-04-25 오후 11 57 27](https://github.com/thdus3009/hhplus_ecommerce/assets/63095234/234de39d-133b-4033-9cb3-770407823b87)


## MileStone
* dummy 데이터 생성 : ~ 4월7일
* 잔액 충전 API : ~ 4월7일
  * API 구현
  * 유닛 테스트
* 잔액 조회 API : ~ 4월8일
  * API 구현
  * 유닛 테스트
* 상품 조회 API : ~ 4월8일
  * API 구현
  * 유닛 테스트
* 상품 주문 API : ~ 4월9일
  * API 구현
  * 유닛 테스트
* 상위 상품 조회 API : ~ 4월10일
  * API 구현
  * 유닛 테스트
* 장바구니 API : ~ 4월12일
  * API 구현
  * 유닛 테스트

## Sequence Diagram
[시퀀스 다이어그램](https://github.com/thdus3009/hhplus_ecommerce/blob/main/docs/SequenceDiagram.md)

## Git Branch
[깃 브랜치 전략](https://github.com/thdus3009/hhplus_ecommerce/blob/main/docs/GitBranch.md)

## 동시성 문제
[프로젝트에서 발생할 수 있는 동시성 이슈](https://github.com/thdus3009/hhplus_ecommerce/blob/main/docs/ConcurrencyIssue.md)

## Swagger
![스크린샷 2024-04-26 오전 12 06 49](https://github.com/thdus3009/hhplus_ecommerce/assets/63095234/f5ea4601-42ea-4b97-b220-bdd30df2edae)

## 책임 분리를 통한 애플리케이션 설계
### 발생할 수 있는 문제들
1. 주문 관련 정보를 외부 데이터 플랫폼에 전달하는 요구사항이 필요할 경우, 기존 로직으로 구현했을때 발생하는 문제 
</br> -> 외부 API의 처리 시간이 길어지면 전체적인 주문관련 API 속도 또한 느려지게 된다. (강력하게 결합된 상태)

2. `@EventListener`를 사용할 경우 발생하는 문제
</br> -> 트랜잭션 경계와는 상관없이 동작한다. `@EventListener`의 경우 publishEvent() 메서드가 호출되는 시점에서 바로 이벤트를 publishing 한다. exception이 발생한다면 중간 EventListener부분은 Rollback되지 않는 상황 발생 


### 해결 방법
1. `@TransactionalEventListener`를 사용 + phase 옵션을 AFTER_COMMIT으로 설정해서 해당 트랜잭션이 모두 완료된 이후 실행되게 해서 
</br> 트랜잭션 내의 실패케이스의 경우 실행되지 않게 한다.

2. 해당 외부 API의 호출을 '비동기'로 처리해서 OrderUsecase(상품 주문)의 처리시간에 영향을 주지 않는다.
</br> (@EventListener, @TransactionalEventListener을 비동기로 사용하려면 따로 `@Async`를 추가해 주어야한다.)




