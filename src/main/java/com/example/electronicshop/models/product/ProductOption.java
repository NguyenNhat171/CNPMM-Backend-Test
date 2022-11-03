package com.example.electronicshop.models.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "product_option")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductOption {
    @Id
    private String id;
    @NotBlank(message = "Name is required")
    @TextIndexed(weight = 9)
    private String name;
    private List<ProductVariant> variants = new ArrayList<>();
    private BigDecimal extraFee;
    @DocumentReference(lazy = true)
    @JsonIgnore
    private Product product;
    @Transient
    private Long inStock;

    public ProductOption(String name, BigDecimal extraFee) {
        this.name = name;
        this.extraFee = extraFee;
    }

    public Long getInStock() {
        return variants.stream().map(ProductVariant::getStock)
                .reduce(0L, Long::sum);
    }
}