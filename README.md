# V/O Aesthetic Lounge 💆‍♀️✨

V/O Aesthetic Lounge is an advanced wellness platform designed to elevate customer experiences through modern technology and a seamless user interface. It integrates online appointment bookings, product sales, live support, and powerful administrative tools to ensure smooth spa and beauty service operations.

### Live Demo
- [**V/O Aesthetic Lounge Demo**](https://voaestheticlounge-api.netlify.app)

---
## 🌐 Features

### 🧖‍♀️ Client-Side
- **Online Booking System** – Schedule spa and beauty treatments anytime, from anywhere.
- **Automatic Notifications & Reminders** – Stay on track with email alerts.
- **Secure Online Payments** – Integrated PayHere gateway.
- **Branch Contact** – Reach out to any lounge branch directly from the site.
- **Product Store** – Purchase wellness and beauty products online.
- **Member Profile** – View past treatments and track upcoming bookings.

### 🛠️ Admin Panel
- **User Management** – Add, update, and manage users.
- **Product Management** – Control inventory and product listings.
- **Service Management** – Add and update spa/beauty services.
- **Booking & Order Management** – Monitor and manage customer activities.

---

## 🏗️ Tech Stack

### Frontend
- **Bootstrap 5**
- **HTML5/CSS3**
- **JavaScript + AJAX**

### Backend
- **Spring Boot**
- **Spring MVC + Spring Data JPA**
- **MySQL**

### Integrations
- **PayHere Payment Gateway**
- **Email Notifications**



---

## 🖼️ Highlights Screenshots

### 🏠 Homepage  

Beautiful landing with featured banners, services, and products

![Screenshot (56)](https://github.com/user-attachments/assets/a5e39df0-ce20-47c5-b7b9-22f01f6e3d7a)


![Screenshot (57)](https://github.com/user-attachments/assets/ac509db4-9ec0-4234-b794-85040103799a)


![Screenshot (58)](https://github.com/user-attachments/assets/b4130bae-3051-40e6-9dc0-526abfbc18d1)


![Screenshot (59)](https://github.com/user-attachments/assets/6243aa1e-99a4-46af-8aee-31dad97bdf7b)


![Screenshot (60)](https://github.com/user-attachments/assets/176eb109-4c25-48c5-9d27-b495372baf74)


![Screenshot (61)](https://github.com/user-attachments/assets/e9d03d4a-b631-41e5-942d-5a0f7031090d)

### 📅 Booking System  
Effortless booking flow with real-time date & time slots


![Screenshot (78)](https://github.com/user-attachments/assets/a8636c9a-45b2-484c-bb35-3939bb1a09aa)


### 🛒 Product Store
Buy beauty & wellness items online


![Screenshot (72)](https://github.com/user-attachments/assets/1117e68f-d6c4-42b8-a23f-47c774861cf7)


### 🛒 Shoping Cart

smooth shopping cart


![Screenshot (76)](https://github.com/user-attachments/assets/091d7770-7ba1-4863-92d8-e250bbb15eea)

### 📜 Booking History
Members  can view their past bookings, including details such as service types, dates.


![Screenshot (62)](https://github.com/user-attachments/assets/70f6726b-6de8-460b-86ac-7d9268877876)


### 🔓 Secure Login


![Screenshot (54)](https://github.com/user-attachments/assets/f17668f4-ba45-496c-bd33-0831ff0c6040)


### 👥 New Member Registration


![Screenshot (55)](https://github.com/user-attachments/assets/acb53ffe-b1e9-4e49-b39f-75037da36cd1)



### 🛠️ For Admin
Manage users, services, products & view bookings


![Screenshot (79)](https://github.com/user-attachments/assets/9235d869-496c-4db1-8dc3-22fcc959c1ae)


![Screenshot (80)](https://github.com/user-attachments/assets/8c0e7dca-345b-4beb-903c-9a2a7d19988d)


![Screenshot (82)](https://github.com/user-attachments/assets/bfa13c1e-ab9a-4c7c-8ccc-7834007db77a)


![Screenshot (81)](https://github.com/user-attachments/assets/7834212b-3466-42d1-9b2f-d5aa1da2d13c)


===

## ⚙️ Tech Stack

| Layer         | Technology           |
|---------------|----------------------|
| Frontend      | Bootstrap 5, HTML, CSS, JS, AJAX |
| Backend       | Spring Boot (MVC + REST) |
| Database      | MySQL                |
| Authentication| JWT                  |
| Payment       | PayHere (or Cash)    |

---

## 🛠️ How to Run the Project

### 1️⃣ Clone the Repository
https://github.com/DanithaHk/VO-Aesthetic-Lounge-SpringBoot-API.git

### 2️⃣ Create MySQL Database
CREATE DATABASE vo_aesthetic_lounge;

### 3️⃣ Configure application.properties

spring.application.name                      = VO Aesthetic Lounge-SpringBoot API
spring.datasource.url                        = jdbc:mysql://localhost:3306/VOAestheticLounge?useSSL=false&allowPublicKeyRetrieval=true
spring.datasource.username                   = root
spring.datasource.password                   = Ijse@123
spring.jpa.properties.hibernate.dialect      = org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto                = update
spring.datasource.driver-class-name          = com.mysql.cj.jdbc.Driver
spring.jpa.open-in-view                      = false
spring.jpa.show-sql                          = true
jwt.secret                                   = 2D4A614E645267556B58703273357638792F423F4428472B4B6250655368566DF423F4428472B4B6250655368566D
spring.main.allow-bean-definition-overriding = true
server.port                                  = 8080

#### HikariCP settings
spring.datasource.hikari.maximum-pool-size   = 20
spring.datasource.hikari.minimum-idle        = 5
spring.datasource.hikari.idle-timeout        = 30000
spring.datasource.hikari.max-lifetime        = 1800000
spring.datasource.hikari.connection-timeout  = 30000

#### Mail settings
spring.mail.host                             = smtp.gmail.com
spring.mail.port                             = 587
spring.mail.username                         = 
spring.mail.password                         = 
spring.mail.properties.mail.smtp.auth        = true
spring.mail.properties.mail.smtp.starttls.enable = true

---


### 🎓 Developed By  
**Danitha Dinuwan**  
IJSE – Galle | 70th Batch  

Crafted with ❤️ by passionate & innovative students  

[![GitHub](https://img.shields.io/badge/GitHub-Danitha--Hk-181717?style=flat-square&logo=github&logoColor=white)](https://github.com/DanithaHk)

---
