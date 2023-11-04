package com.example.demo.database;

import com.example.demo.models.Product;
import com.example.demo.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // đánh dâu lớp l 1 lớp cấu hình
public class Database {
    private static final Logger logger = LoggerFactory.getLogger(Database.class);
    @Bean // đánh dấu 1 phương thức initDatabase trong 1 lớp cấu hình và chỉ ra rằng pt đó sẽ rtar về 1 dối tượng CommandlineRunner mà Sptring Container sẽ quản lí
    CommandLineRunner initDatabase(ProductRepository productRepository){ // khởi tạo dl mẫu vào lưu vào database thông qua productRepository
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                Product productA = new Product("Macbook",2020,2000.0,"");
                Product productB = new Product("Iphone 1pro",2020,2000.0,"");
                logger.info("Insert data" + productRepository.save(productA));
                logger.info("Insert data" + productRepository.save(productB));
            }
        };
    }
    // khi ứng dụng Sprig bôt được chạy pt iitDatabase sẽ được goi tự động và sẽ thêm 2 dk=l mẫu vào kho dl
}
