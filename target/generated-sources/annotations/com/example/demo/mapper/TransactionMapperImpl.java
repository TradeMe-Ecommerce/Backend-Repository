package com.example.demo.mapper;

import com.example.demo.DTO.TransactionDTO;
import com.example.demo.entity.Order;
import com.example.demo.entity.Product;
import com.example.demo.entity.Transaction;
import com.example.demo.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-23T18:02:35-0500",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.42.50.v20250729-0351, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class TransactionMapperImpl implements TransactionMapper {

    @Override
    public TransactionDTO toDTO(Transaction transaction) {
        if ( transaction == null ) {
            return null;
        }

        TransactionDTO transactionDTO = new TransactionDTO();

        transactionDTO.setUserId( transactionUserId( transaction ) );
        transactionDTO.setProductId( transactionProductId( transaction ) );
        transactionDTO.setOrderId( transactionOrderId( transaction ) );
        transactionDTO.setId( transaction.getId() );
        transactionDTO.setStatus( transaction.getStatus() );
        transactionDTO.setPrice( transaction.getPrice() );
        transactionDTO.setPaymentMethod( transaction.getPaymentMethod() );
        transactionDTO.setAmount( transaction.getAmount() );

        return transactionDTO;
    }

    @Override
    public Transaction toEntity(TransactionDTO transactionDTO) {
        if ( transactionDTO == null ) {
            return null;
        }

        Transaction transaction = new Transaction();

        transaction.setUser( transactionDTOToUser( transactionDTO ) );
        transaction.setProduct( transactionDTOToProduct( transactionDTO ) );
        transaction.setOrder( transactionDTOToOrder( transactionDTO ) );
        transaction.setId( transactionDTO.getId() );
        transaction.setStatus( transactionDTO.getStatus() );
        transaction.setPrice( transactionDTO.getPrice() );
        transaction.setPaymentMethod( transactionDTO.getPaymentMethod() );
        transaction.setAmount( transactionDTO.getAmount() );

        return transaction;
    }

    private long transactionUserId(Transaction transaction) {
        if ( transaction == null ) {
            return 0L;
        }
        User user = transaction.getUser();
        if ( user == null ) {
            return 0L;
        }
        long id = user.getId();
        return id;
    }

    private long transactionProductId(Transaction transaction) {
        if ( transaction == null ) {
            return 0L;
        }
        Product product = transaction.getProduct();
        if ( product == null ) {
            return 0L;
        }
        long id = product.getId();
        return id;
    }

    private long transactionOrderId(Transaction transaction) {
        if ( transaction == null ) {
            return 0L;
        }
        Order order = transaction.getOrder();
        if ( order == null ) {
            return 0L;
        }
        long id = order.getId();
        return id;
    }

    protected User transactionDTOToUser(TransactionDTO transactionDTO) {
        if ( transactionDTO == null ) {
            return null;
        }

        User user = new User();

        user.setId( transactionDTO.getUserId() );

        return user;
    }

    protected Product transactionDTOToProduct(TransactionDTO transactionDTO) {
        if ( transactionDTO == null ) {
            return null;
        }

        Product product = new Product();

        product.setId( transactionDTO.getProductId() );

        return product;
    }

    protected Order transactionDTOToOrder(TransactionDTO transactionDTO) {
        if ( transactionDTO == null ) {
            return null;
        }

        Order order = new Order();

        order.setId( transactionDTO.getOrderId() );

        return order;
    }
}
