package com.ruriel.simplestore.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long quantity;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name="product_id", nullable=false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="purchase_id", nullable = false)
    private Purchase purchase;
    public BigDecimal getSubTotal(){
        if(product != null && product.getPrice() != null && quantity != null)
            return product.getPrice().multiply(new BigDecimal(quantity));
        return BigDecimal.ZERO;
    }

    private void setId(Long id){}

}
