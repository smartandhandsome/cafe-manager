## MAIN 컨벤션

- 패키지 구조는 도메인형 구조를 사용한다.
- 메서드가 Command인지 Query인지 명확히 한다. (분리기준: return 값이 있는지, CQS)
- 기본적인 코드레벨의 구조는 Layered Architecture를 사용
  - 레이어는 위에서 아래로만 참조할 수 있다.
  - 하위레이어에서 상위레이어의 객체를 참조할 수 없다.
  - 상위 레이어에서 참조는 인접한 하위 레이저로만 가능하다. (레이어를 건너 뛸 수 없다.) 
  - 동일 레이어에서 다른 객체를 참조할 수 없다. (단, Impl 레이어에서는 가능하다.)
- Presentation Layer
  - 외부 Client와 상호 작용하는 레이어.
  - 외부에서 들어온 값의 유효성을 검증한다(Bean Validation).
  - 외부의 요청에 알맞는 응답을 보낸다.
  - Service 레이어에 있는 적합한 객체를 선택하여 값을 전달한다.
- Service
  - 비즈니스 로직을 작성하는 레이어.
  - 아래에 있는 Impl 레이어에서 필요한 행위를 조합하여 비즈니스 로직을 완성시킨다.
- Impl
  - 비즈니스 로직을 작성하기 위한 상세 구현 로직을 갖고 있는 레이어.
  - 객체 재사용성 높게 설계한다.
  - @Component 어노테이션을 붙인다.
  - @Transational 어노테이션을 관리한다.
  - @Transational은 가시성을 위해 method 위에 붙인다.
- Persistence
  - 데이터의 저장을 담당하는 레이어
  - Adapter 패턴을 활용하여 특정 DB에 의존하지 않도록 한다.

## TEST 컨벤션

1. 모든 테스트는 단위테스트로 작성한다.
2. 테스트는 given, when, then 형태를 갖는다.
3. BDDMockito를 기반으로 작성한다.
4. Object Mother 패턴을 사용하여 Fixture를 관리한다.
5. Object Mother 패턴은 기본적으로 enum을 사용하여 구현한다.
6. 메서드 명은 한글과 언더스코어로 작성하고 @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)를 사용해서 언더스코어를 제거한다.
7. 메서드 명의 접미사를 "~할 수 있다."로 통일한다.


