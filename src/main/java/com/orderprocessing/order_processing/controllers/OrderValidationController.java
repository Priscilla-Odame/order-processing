package com.orderprocessing.order_processing.controllers;

import com.orderprocessing.order_processing.entities.Order;
import com.orderprocessing.order_processing.enums.Side;
import com.orderprocessing.order_processing.requests.OrderRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderValidationController {
    @PostMapping("/order/validate")
    public ResponseEntity<Boolean> validateOrder(OrderRequest orderRequest){

        Double clientBalance = 340.50;
        int clientQuantity = 300;
        String clientProduct = "AAPL";

        if (!(orderRequest.getSide() == Side.BUY) && ((orderRequest.getQuantity() * orderRequest.getPrice()) <= clientBalance)) {
            System.out.println("Invalid Buy order!!! Respond to the order");

            return new ResponseEntity<Boolean>(false, HttpStatus.OK);

        } else if (!(orderRequest.getSide() == Side.SELL && (orderRequest.getQuantity() <= clientQuantity && orderRequest.getProduct() == clientProduct))) {
            System.out.println("Invalid Sell order!!! Respond to the order");
            return new ResponseEntity<Boolean>(false, HttpStatus.OK);

        }
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }
}