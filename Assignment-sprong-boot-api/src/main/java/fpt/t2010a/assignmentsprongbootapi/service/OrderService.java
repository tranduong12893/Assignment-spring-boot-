package fpt.t2010a.assignmentsprongbootapi.service;

import fpt.t2010a.assignmentsprongbootapi.entity.Order;
import fpt.t2010a.assignmentsprongbootapi.entity.OrderDetail;
import fpt.t2010a.assignmentsprongbootapi.entity.Product;
import fpt.t2010a.assignmentsprongbootapi.repository.OrderRepository;
import fpt.t2010a.assignmentsprongbootapi.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService{
    final OrderRepository orderRepository;
    final ProductRepository productRepository;

    public Order findShoppingCartByUserId(int userId){
       return orderRepository.getShoppingCart(userId);
    }
    public Order addShoppingCart(int userId, String productId, int quantity) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if(!optionalProduct.isPresent()){
            return null;
        }
        Order order = orderRepository.getShoppingCart(userId);
        Set<OrderDetail> orderDetails = order.getOrderDetails();
        boolean exist = false;
        for(OrderDetail entry : orderDetails)
        {
//            if(entry.getProduct().getId().equals(productId)){
//                entry.setQuantity(entry.getQuantity() + quantity);
//                exist = true;
//            }
        }
        if(!exist){
            OrderDetail orderDetail = new OrderDetail();
            orderDetails.add(orderDetail);
        }
         return order;
    }
}
