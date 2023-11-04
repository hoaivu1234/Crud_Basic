package com.example.demo.controller;

import com.example.demo.models.Product;
import com.example.demo.models.ResponseObject;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController // cho biết ProductController là 1 Restfull Controller , nơi có thể định nghĩa các phương thức xử lí các yêu cầu http
@RequestMapping(path = "/api/v1/Products") // định nghĩa 1 url hcho tất cả các yêu cầu http mà ProductController xử lí, khi truy cập vào url thì các phương thức trong lớp sẽ dduwoj gọi

public class ProductController {
    @Autowired // chú thích rằng trường repository sẽ được tự động tiêm 1 lớp ProductRepository vào, có thể sủ dụng ProductRepository mà sau này ko cần ta lại thử công
    private ProductRepository repository;
    @GetMapping("") // đánh dấu phương thúc getAllProduct là 1 phuwowg thức xử lí yêu câu Get và nó sẽ tự đọng gọi khi truy cập vào url
    List<Product> getAllProducts(){
        return repository.findAll(); // findALl trả ề tất cả các bản ghi Product từ database
    }
    // tóm lại khi người dùng truy cập vào url và gửi 1 yêu cầu get thì phuwog thức getAllProducts sẽ dc gọi và trả về tất cả các product thông qua ProductRepository
    @GetMapping("/{id}")// tìm kiếm sản phẩm thông qua id
    ResponseEntity<ResponseObject> findById(@PathVariable Long id){ // ResponseEntity là 1 lớp để đại diện cho response trả về từ restfull api, dùng để viết trạn thái, đặt kiểu dl trả về
        Optional<Product> foundProduct = repository.findById(id); // Optinal để kiểm tra và xử lí gt null
        return foundProduct.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Query product successfully", foundProduct)
                        //you can replace "ok" with your defined "error code"
                ):
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "Cannot find product with id = "+id, "")
                );
    }

    //insert new product
    @PostMapping("/insert")
    ResponseEntity<ResponseObject> insertProduct(@RequestBody Product newProduct) {
        //2 products must not have the same name !
        List<Product> foundProducts = repository.findByProductName(newProduct.getProductName().trim());
        if(foundProducts.size() > 0) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("failed", "Product name already taken", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Insert Product successfully", repository.save(newProduct))
        );
    }
    @PutMapping("/{id}")
    ResponseEntity<ResponseObject> updateProduct(@RequestBody Product newProduct, @PathVariable Long id) {
        Product updatedProduct = repository.findById(id)
                .map(product -> {
                    product.setProductName(newProduct.getProductName());
                    product.setYear(newProduct.getYear());
                    product.setPrice(newProduct.getPrice());
                    return repository.save(product);
                }).orElseGet(() -> {
                    newProduct.setId(id);
                    return repository.save(newProduct);
                });
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Update Product successfully", updatedProduct)
        );
    }
    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObject> deleteProduct(@PathVariable Long id) {
        boolean exists = repository.existsById(id); // kiểm tra xem có product nào có id đã truyền vào ko, nếu có return true
        if(exists) {
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Delete product successfully", "")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", "Cannot find product to delete", "")
        );
    }
}
