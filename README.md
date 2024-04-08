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
![스크린샷 2024-04-08 오전 10 39 09](https://github.com/thdus3009/hhplus_ecommerce/assets/63095234/782a861c-f51b-4265-8a0e-2cdd513a7415)


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
### 잔액 충전
![스크린샷 2024-04-05 오후 2 49 14](https://github.com/thdus3009/hhplus_ecommerce/assets/63095234/85719f35-0279-4edb-a0f1-8577cda4d91c)
### 잔액 조회
![스크린샷 2024-04-05 오후 2 54 49](https://github.com/thdus3009/hhplus_ecommerce/assets/63095234/de82a26e-3583-4e3d-b317-1bdb20d6eb90)
### 상품 조회
![스크린샷 2024-04-05 오후 3 03 44](https://github.com/thdus3009/hhplus_ecommerce/assets/63095234/1c5fdf24-d30f-4601-a7c9-ed3a065b8939)
### 상품 주문
![스크린샷 2024-04-05 오후 3 39 40](https://github.com/thdus3009/hhplus_ecommerce/assets/63095234/8870531c-020c-4a26-abed-d871b5f56154)
### 상위 상품 조회
![스크린샷 2024-04-05 오후 3 44 26](https://github.com/thdus3009/hhplus_ecommerce/assets/63095234/133895f8-c94f-4bab-95e6-936ef0549acc)


## Swagger
![스크린샷 2024-04-08 오후 7 54 36](https://github.com/thdus3009/hhplus_ecommerce/assets/63095234/836f50ca-f369-4dd5-b748-9c06bc019e3a)







