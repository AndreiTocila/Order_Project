package com.project.stockservice.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "stock")
public class Stock
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer quantity;

    @OneToOne
    @JoinColumn(name = "product_id", unique = true)
    private Product product;

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
        {
            return false;
        }
        Stock stock = (Stock) o;
        return getId() != null && Objects.equals(getId(), stock.getId());
    }

    @Override
    public int hashCode()
    {
        return getClass().hashCode();
    }
}
