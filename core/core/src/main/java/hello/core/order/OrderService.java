package hello.core.order;

/**
 * 주문 결과 반환
 */

public interface OrderService {
    Order createOrder(Long id, String itemName, int itemPrice);
}
