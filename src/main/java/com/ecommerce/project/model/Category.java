package com.ecommerce.project.model;

import jakarta.persistence.*;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long categoryId;

  private String categoryName;

  @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
  List<Product> productList;
}
