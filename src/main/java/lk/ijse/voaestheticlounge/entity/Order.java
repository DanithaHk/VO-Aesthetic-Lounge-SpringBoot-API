package lk.ijse.voaestheticlounge.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {



        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        @JoinColumn(name = "user_id", nullable = false)
        private User user;  // Reference to User entity

        @Column(name = "order_date", nullable = false)
        private LocalDate orderDate;

        @Column(name = "total_price", nullable = false)
        private Double totalPrice;
        @Column(name = "orderCode")
        private String orderCode;
        @Column(name = "status", nullable = false)
        private String status;

        public Order() {
        }

        public Order(Long id, User user, LocalDate orderDate, Double totalPrice, String orderCode, String status) {
                this.id = id;
                this.user = user;
                this.orderDate = orderDate;
                this.totalPrice = totalPrice;
                this.orderCode = orderCode;
                this.status = status;
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public User getUser() {
                return user;
        }

        public void setUser(User user) {
                this.user = user;
        }

        public LocalDate getOrderDate() {
                return orderDate;
        }

        public void setOrderDate(LocalDate orderDate) {
                this.orderDate = orderDate;
        }

        public Double getTotalPrice() {
                return totalPrice;
        }

        public void setTotalPrice(Double totalPrice) {
                this.totalPrice = totalPrice;
        }

        public String getOrderCode() {
                return orderCode;
        }

        public void setOrderCode(String orderCode) {
                this.orderCode = orderCode;
        }

        public String getStatus() {
                return status;
        }

        public void setStatus(String status) {
                this.status = status;
        }
}

