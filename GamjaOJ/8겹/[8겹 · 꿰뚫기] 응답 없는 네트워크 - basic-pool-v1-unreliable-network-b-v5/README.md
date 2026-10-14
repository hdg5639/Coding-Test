<!-- GamjaOJ solution: 5b60db5fca5c05d92f8a7e03955a20b732d7c34b3e63025ab12aef9d215a5750 -->

# [8겹 · 꿰뚫기] 응답 없는 네트워크 - basic-pool-v1-unreliable-network-b-v5

[문제 링크](https://gamjaoj.gamjabox.cloud/?problem=basic-pool-v1-unreliable-network-b-v5#practice)

### 성능 요약

| 언어 | 결과 | 테스트별 최대 실행 시간 | 최대 메모리 |
| --- | --- | --- | --- |
| Java | AC | 2421 ms | 29.72 MiB |

실행 시간은 Runner가 측정한 wall time입니다. 메모리는 Runner 호스트에서 관측한 컨테이너 cgroup 최고 사용량이며 JVM·런타임과 파일 캐시를 포함합니다. 측정된 테스트별 최댓값입니다.

### 분류

시뮬레이션 · 이벤트 처리 · 우선순위 큐 · 최단 경로 · 상태 관리 · 큐

### 생각의 겹

8겹 · 꿰뚫기 (검토 추정)

발상 4/5 · 구현 5/5 · 경계 5/5

요청 예약·시간 진행·조회 API를 분리하고 송신자의 재시도, 패킷의 이동, 목적지의 중복 실행 이력을 호출 사이에 보존해야 해요. 같은 시각의 순서와 최종 결과 뒤의 잔여 트래픽, 재초기화까지 정확하게 연결해야 해요.

### 제출 일자

2026년 10월 14일 23:28:05 KST

### 풀이 코드

[UserSolution.java](UserSolution.java)

---
GamjaOJ에서 자동으로 기록한 정답 풀이입니다.
