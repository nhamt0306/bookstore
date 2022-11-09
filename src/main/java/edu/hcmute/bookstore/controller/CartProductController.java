package edu.hcmute.bookstore.controller;

import edu.hcmute.bookstore.config.LocalVariable;
import edu.hcmute.bookstore.dto.CartProductDTO;
import edu.hcmute.bookstore.dto.CartProductReponse;
import edu.hcmute.bookstore.model.CartProductEntity;
import edu.hcmute.bookstore.model.UserEntity;
import edu.hcmute.bookstore.security.principal.UserDetailService;
import edu.hcmute.bookstore.service.impl.CartProductServiceImpl;
import edu.hcmute.bookstore.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CartProductController {
    @Autowired
    CartProductServiceImpl cartProductService;
    @Autowired
    UserDetailService userDetailService;
    @Autowired
    ProductServiceImpl productService;

    @GetMapping("/user/cart/getAll")
    public ResponseEntity<?> getAllProductByUser()
    {
        UserEntity user = userDetailService.getCurrentUser();
        List<CartProductEntity> cartProductEntities = cartProductService.getAllProductByCartId(user.getId());
        List<CartProductReponse> cartProductDTOS = new ArrayList<CartProductReponse>();
        for(CartProductEntity cartProductEntity: cartProductEntities)
        {
            CartProductReponse cartProductDTO = new CartProductReponse(cartProductEntity.getQuantity(), cartProductEntity.getPrice(), cartProductEntity.getProductEntity().getProImage(), cartProductEntity.getProductEntity().getProName(), cartProductEntity.getProductEntity().getAuthorEntity().getAutName(), cartProductEntity.getProductEntity().getId());
            cartProductDTOS.add(cartProductDTO);
        };
        return ResponseEntity.ok(cartProductDTOS);
    }


    @PostMapping("/user/cart/add")
    public Object addProductToCart(@RequestBody CartProductDTO cartProductDTO) throws ParseException {
        CartProductEntity cartProductEntity = new CartProductEntity();

        cartProductEntity.setPrice(cartProductDTO.getPrice());
        cartProductEntity.setQuantity(cartProductDTO.getQuantity());

        cartProductEntity.setUpdate_at(new Timestamp(System.currentTimeMillis()));
        cartProductEntity.setCreate_at(new Timestamp(System.currentTimeMillis()));

        cartProductEntity.setCartEntity(userDetailService.getCurrentUser().getCartEntity());
        cartProductEntity.setProductEntity(productService.findProductById(cartProductDTO.getProductId()));
        cartProductService.save(cartProductEntity);
        return "Add to cart success!";
    }

    @PostMapping("/user/cart/increase/{id}")
    public Object increaseQuantityProductInCart(@PathVariable long id) throws ParseException {
        cartProductService.increaseQuantity(id, userDetailService.getCurrentUser().getId());
        return "Update product success!";
    }

    @DeleteMapping("/user/cart/{id}")
    public ResponseEntity<?> deleteProductById(@PathVariable long id)
    {
        cartProductService.deleteProductInCart(userDetailService.getCurrentUser().getId(), id);
        return ResponseEntity.ok(LocalVariable.messageDeleteCatSuccess);
    }

}
