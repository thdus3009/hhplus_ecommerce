//package com.ecommerce.stub.point;
//
//import com.ecommerce.domain.point.entity.UserPoint;
//import com.ecommerce.domain.point.repository.UserPointRepository;
//
//import java.util.Map;
//import java.util.Optional;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.atomic.AtomicLong;
//
//public class UserPointRepositoryStub implements UserPointRepository {
//    Map<Long, UserPoint> store = new ConcurrentHashMap<>();
//    // 멀티스레드 환경에서 안전하게 고유 id를 생성하기 위해 atomic 타입 사용
//    AtomicLong idSeq = new AtomicLong(1L);
//
//    public void clear(){
//        store.clear();
//        idSeq.set(1L);
//
//        store.put(
//                idSeq.getAndAdd(1L),
//                new UserPoint(
//                        1L,
//                        15000L
//                )
//        );
//    }
//    @Override
//    public Optional<UserPoint> findByUserId(Long userId) {
//        return Optional.ofNullable(store.get(userId));
//    }
//
//    @Override
//    public UserPoint save(UserPoint userPoint) {
//        UserPoint userPoint1 = new UserPoint(
//                userPoint.getId(),
//                userPoint.getUserId(),
//                userPoint.getPoint(),
//                userPoint.getCreatedAt(),
//                userPoint.getUpdatedAt()
//        );
//        return store.put(userPoint.getId(), userPoint1);
//    }
//}
