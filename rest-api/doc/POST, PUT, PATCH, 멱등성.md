## POST

RFC 문서에서는 POST를 다음과 같이 정의하고 있어요.

> The POST method requests that the target resource process the representation enclosed in the request according to the
> resource's own specific semantics.

이해하기 조금 어려운데요. 한국어로는 "POST 요청의 요청 본문은 타깃 리소스의 정의대로 처리된다"로 번역할 수 있습니다. 쉽게 말하자면 클라이언트가 요청을 보내면 서버에서 모든 걸 알아서 처리해야 된다는 뜻이죠.

위 정의에 의하면 **클라이언트는 리소스의 URI를 모르는 상태에서 리소스를 생성**할 수 있어요. 예를 들어, 새로운 결제를 생성하기 위해 클라이언트는 `/payments`라는 결제 컬렉션의 URI로 POST
요청을 보냅니다. 그럼 서버는 `/payments` 아래에 `/payments/1`와 같이 새로운 결제 리소스를 만들어요. POST 메서드로 리소스를 생성하면 서버에서 정의한 로직대로 리소스의 URI(및 ID)를
발급합니다.

또 POST의 다른 특징은 **멱등성(idempotentency)이 없다**는 점입니다. **같은 POST 요청을 여러 번 보내면 여러 개의 리소스가 생성돼요.** 예를 들어, 같은 내용의 요청을
`/payments`로 3번 보내면 `/payments/1`, `/payments/2`, `/payments/3`와 같이 똑같은 데이터를 가진 리소스 3개가 생성돼요.

POST 메서드를 떠올리면 주로 리소스 생성을 생각하는데, 사실 POST 메서드는 **서버에서 정의한 다양한 작업**을 모두 처리할 수 있는 메서드에요. 실제로 RFC 문서에도 폼에 입력된 데이터를 불러올 때,
블로그에 메시지를 업로드할 때, 이미 존재하는 리소스에 데이터를 추가할 때 모두 POST 메서드를 사용할 수 있다고 하고요.

## PUT

PUT은 POST와 자주 같이 언급되는 메서드인데요. RFC 문서에 의하면 PUT과 POST의 차이점은 다음과 같습니다.

> The target resource in a POST request is intended to handle the enclosed representation according to the resource's
> own semantics, whereas the enclosed representation in a PUT request is defined as replacing the state of the target
> resource.

PUT 요청에서는 **클라이언트가 리소스의 정확한 URI를 알아야 됩니다.** `/payments`로 PUT 요청을 보내면 `/payments/1`와 같은 리소스가 생성되지 않아요. PUT 메서드를 사용할 때는
`/payments/1`와 같은 특정 리소스의 URI로 요청을 보내야 리소스가 생성돼요. 그래서 PUT 메서드로 리소스를 생성하려면 클라이언트가 URI를 만드는 방법을 알고 있어야 돼요. 또 POST 요청과 달리
PUT 요청은 멱등합니다.

그래서 **PUT 메서드는 주로 리소스를 업데이트할 때 사용**해요. 리소스 업데이트할 때는 이미 생성된 리소스의 정확한 URI를 알고 있고, 안전하게 멱등한 요청을 보내고 싶기 때문이죠. 그러나 PUT 메서드는 *
*리소스 전체**를 업데이트해야 된다는 특징이 있어요. 즉, 리소스 일부만 수정하고 싶어도 전체 필드 데이터를 요청 본문에 포함해야 돼요.

## PATCH

PATCH도 리소스 업데이트할 때 사용하는데요. PATCH 메서드는 RFC 5789에 다음과 같이 정의되어 있어요.

> The PATCH method requests that a set of changes described in the request entity be applied to the resource identified
> by the Request-URI.

PATCH 메서드도 PUT 메서드와 같이 특정 리소스 URI를 정확히 알고 있어야 돼요. 그러나 PUT은 서버에 있는 리소스를 완전히 대체하지만, PATCH는 클라이언트의 요청에 따라 리소스를 수정하고 **부분
업데이트**를 합니다. 일반적으로 PATCH 요청은 변경이 필요한 필드만 요청 본문으로 보내고, 변경하고 싶지 않은 필드는 요청 본문에서 생략해요. 하지만 필요에 따라 다른 방법으로 PATCH 요청을 제공할 수도
있어요.

PATCH 메서드는 RFC 2616에 정의된 기준으로 멱등하지 않고 안전하지 않아요. 예를 들어, 두 개의 PATCH 메서드가 동시에 호출되면 리소스의 데이터가 손실될 수 있고 클라이언트가 원하지 않는 결과가 나올
수도 있어요.

그럼 언제 PUT을 사용하고, 언제 PATCH를 사용하면 좋을까요? 클라이언트의 상황에 따라 유연하게 사용하면 됩니다. RFC 문서는 PATCH에 넣을 데이터가 PUT에 넣을 데이터보다 크다면, PUT이 더 적합한
메서드라고 기술하고 있어요. POST와 비교하기는 더 어려워요. POST 메서드는 PUT, PATCH와 비슷한 기능을 모두 수행할 수 있기 때문이에요.

## 멱등성

멱등(Idempotent)하다는 것은 첫 번째 수행을 한 뒤 여러 차례 적용해도 결과를 변경시키지 않는 작업 또는 기능의 속성을 뜻해요. 즉, 멱등한 작업의 결과는 한 번 수행하든 여러 번 수행하든 같습니다.

예를 들어, 어떤 숫자에 1을 곱하는 연산은 여러 번 수행해도 처음 1을 곱한 것과 같은 숫자가 되기 때문에 멱등해요. 마찬가지로 숫자의 절대값을 계산하는 절대값 함수는 같은 값에 대해 여러 번 수행해도 처음과 항상
같은 숫자가 돌아오기 때문에 멱등 함수라고 불러요.

## HTTP 메서드의 멱등성

HTTP 메서드에도 멱등성이 있어요. 예를 들어 GET은 여러 번 호출해도 같은 결과가 돌아오고, 리소스에 변화를 일으키지 않기 때문에 멱등성이 보장된 메서드예요.

| 메서드     | 멱등성 |
|---------|-----|
| CONNECT | X   |
| DELETE  | O   |
| GET     | O   |
| HEAD    | O   |
| OPTIONS | O   |
| POST    | X   |
| PUT     | O   |
| PATCH   | X   |
| TRACE   | O   |

GET, PUT처럼 리소스를 조회하거나 대체하는 메서드는 멱등해요. PUT은 여러 번 호출해도 매번 같은 리소스로 업데이트되기 때문에 결과가 달라지지 않죠. DELETE 역시 여러 번 호출해도 삭제된 리소스에 대한
결과는 달라지지 않아요. 반면 서버 데이터를 변경하는 POST, PATCH는 호출할 때마다 응답이 달라지기 때문에 멱등한 메서드가 아니에요. 이렇게 멱등하지 않은 메서드에 멱등성을 제공하려면 서버에서 멱등성을
구현해야 해요.

### HTTP 메서드의 안전성과 멱등성은 어떻게 다를까요?

HTTP 메서드의 주요 속성에는 멱등성 외에도 안전성이 있어요. 안전성이 보장된 메서드는 리소스를 변경하지 않아요. GET, HEAD, OPTIONS는 안전한 메서드죠. 안전성이 보장된 메서드는 멱등성도 보장하지만,
멱등성을 지닌 메서드가 항상 안전성을 보장하지는 않아요. 예를 들어 PUT과 DELETE는 멱등한 메서드지만, 리소스에 변화를 일으키기 때문에 안전한 메서드는 아니에요.

### API 관점에서 멱등성

멱등한 API라면 두 번 이상 요청해도 결과는 처음 요청과 똑같이 돌아와요. **단순히 돌아온 값이 같을 뿐 아니라 서버 상태(DB)에도 영향을 미치지 않아요.**
이렇게 시스템에 의도하지 않은 문제를 일으키지 않고 요청을 재시도할 수 있기 때문에, 멱등성은 결함 없고 안전한 API를 만드는데 중요해요.

사용자가 결제하는 시점에 네트워크 오류나 타임아웃으로 인해 결과를 받지 못하는 시나리오를 한 번 생각해 볼까요? 멱등성이 보장되지 않은 결제 API라면 실제로 결제가 성공했는지 수동으로 확인해야 하고, 확인해 보니
실제로 결제가 되지 않았다면 고객이 같은 결제를 다시 시도해야 해요. 반면 결제 API가 멱등하다면 다시 같은 요청을 보내지 않고 전에 받지 못한 결과만 다시 받을 수 있을 때 편리할 거예요. 또 실수로 중복 요청이
되더라도(일명 ‘따닥’) 실제로는 결제가 되지 않아서 안심하고 여러 번 요청할 수 있어요.

## ref

https://docs.tosspayments.com/blog/rest-api-post-put-patch
https://docs.tosspayments.com/blog/what-is-idempotency