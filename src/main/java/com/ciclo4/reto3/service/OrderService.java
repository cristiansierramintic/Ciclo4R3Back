package com.ciclo4.reto3.service;

import com.ciclo4.reto3.model.Order;
import com.ciclo4.reto3.repository.OrderRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAll() {
        return orderRepository.getAll();
    }

    public Optional<Order> getOrder(int id) {
        return orderRepository.getOrder(id);
    }

    public Order create(Order order) {
        
        Optional<Order> orderIdMaxima = orderRepository.lastOrderId();
       
        if (order.getId() == null) {
            if (orderIdMaxima.isEmpty())
                order.setId(1);
            else
                order.setId(orderIdMaxima.get().getId() + 1);
        }
        
        Optional<Order> e = orderRepository.getOrder(order.getId());
        if (e.isEmpty()) {
            return orderRepository.create(order);            
        }else{
            return order;
        }        
    }

    public Order update(Order order) {

        if (order.getId() != null) {
            Optional<Order> orderAux = orderRepository.getOrder(order.getId());
            if (!orderAux.isEmpty()) {
                if (order.getStatus() != null) {
                    orderAux.get().setStatus(order.getStatus());
                }
                orderRepository.update(orderAux.get());
                return orderAux.get();
            } else {
                return order;
            }
        } else {
            return order;
        }
    }

    public boolean delete(int id) {
        Boolean aBoolean = getOrder(id).map(order -> {
            orderRepository.delete(order);
            return true;
        }).orElse(false);
        return aBoolean;
    }

    //Ordenes de pedido asociadas a los asesores de una zona
    public List<Order> findOrderByZone(String zone) {
        return orderRepository.findOrderByZone(zone);
    }
    

//    public List<Order> ordersSalesManByDate(String dateStr, int id) {
//        return orderRepository.ordersSalesManByDate(dateStr, id);
//    }
    
//    public List<Order> ordersSalesManByState(String state, Integer id) {
//        return orderRepository.ordersSalesManByState(state, id);
//    }
}
