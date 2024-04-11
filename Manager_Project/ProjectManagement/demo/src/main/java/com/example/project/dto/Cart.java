package com.example.project.dto;
import com.example.project.entity.product.Product;
import com.example.project.entity.product.Size;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Component
public class Cart {
    private Map<Product,Integer> product2Map=new HashMap<>();
    public Map<Product, Integer> getProducts() {
        return product2Map;
    }
    private Map<Product, Map<Size, Integer>> productsWithSizes = new HashMap<>();
    private Map<Product, Boolean> selectedProducts = new HashMap<>();

    public Cart(Map<Product, Integer> products) {
        this.product2Map = products;
    }

    public Cart() {
    }

    public boolean isProductInCart(Product product) {
        for (Map.Entry<Product, Integer> entry : product2Map.entrySet()) {
            if (entry.getKey().getId() == product.getId()) {
                return true;
            }
        }
        return false;
    }
    public boolean issize(Product product){
        for (Map.Entry<Product, Map<Size, Integer>> entry:productsWithSizes.entrySet()){
            if(entry.getKey().getId()==product.getId()){
                return true;
            }
        }
        return false;
    }

    private Map.Entry<Product, Integer> selectProductInCart(Product product) {
        for (Map.Entry<Product, Integer> entry : product2Map.entrySet()) {
            if (entry.getKey().getId() == product.getId()) {
                return entry;
            }
        }
        return null;
    }
    public void addProduct(Product product, Size size,int quantity) {
        // Check if the product already exists in the cart
        Map<Size, Integer> sizes = productsWithSizes.getOrDefault(product, new HashMap<>());
        if (sizes == null) {
            // If the product is not in the cart, initialize the sizes map for this product
            sizes = new HashMap<>();
            productsWithSizes.put(product, sizes);
        }

        // Update the quantity for the specific size of the product
//        sizes.put(size, sizes.getOrDefault(size, 0) + 1);
        sizes.put(size,quantity);
        productsWithSizes.put(product, sizes);
        // Update the quantity for the product in the product2Map
        Integer totalQuantityForProduct = product2Map.getOrDefault(product, 0) + 1;
        product2Map.put(product, totalQuantityForProduct);
    }
//    public int total(){
//        Integer quantity=0;
//        Integer totalQuantity = 0;
//        Double totalPrice = 0.0;
//        Product product = null;
//        for (Map.Entry<Product, Map<Size, Integer>> productEntry : productsWithSizes.entrySet()) {
//             product = productEntry.getKey();
//            Map<Size, Integer> sizes = productEntry.getValue();
//            for (Map.Entry<Size, Integer> sizeEntry : sizes.entrySet()) {
//                Size size = sizeEntry.getKey();
//                 quantity = sizeEntry.getValue();
//            }
//        }
//        if (totalQuantity == 0) {
//            return null; // Trả về null nếu không có sản phẩm trong giỏ hàng
//        }
//        return (int) (quantity*product.getPrices());
//    }
public Integer total() {
    Integer totalQuantity = 0;
    Double totalPrice = 0.0;

    for (Map.Entry<Product, Map<Size, Integer>> productEntry : productsWithSizes.entrySet()) {
        Product product = productEntry.getKey();
        Map<Size, Integer> sizes = productEntry.getValue();

        for (Map.Entry<Size, Integer> sizeEntry : sizes.entrySet()) {
            Size size = sizeEntry.getKey();
            Integer quantity = sizeEntry.getValue();

            totalQuantity += quantity;
            totalPrice += quantity * product.getPrices();
        }
    }

    if (totalQuantity == 0) {
        return null; // Trả về null nếu không có sản phẩm trong giỏ hàng
    }

    return (int) Math.round(totalPrice);
}

    //    public void changeQuantity(Product product, Size size, int newQuantity) {
//        // Cập nhật số lượng trong product2Map
//        if (newQuantity <= 0) {
//            // Xóa sản phẩm nếu newQuantity không hợp lệ (0 hoặc âm)
//            product2Map.remove(product);
//            productsWithSizes.remove(product);
//        } else {
//            product2Map.put(product, newQuantity);
//
//            // Cập nhật số lượng trong productsWithSizes
//            Map<Size, Integer> sizes = productsWithSizes.get(product);
//            if (sizes != null) {
//                if (sizes.containsKey(size)) {
//                    sizes.put(size, newQuantity);
//                } else {
//                    // Xử lý trường hợp size chưa tồn tại trong map
//                    sizes.put(size, newQuantity);
//                }
//            } else {
//                // Xử lý trường hợp sản phẩm chưa có trong productsWithSizes
//                sizes = new HashMap<>();
//                sizes.put(size, newQuantity);
//                productsWithSizes.put(product, sizes);
//            }
//        }
//    }
public void changeQuantity(Product product ,int newQuantity) {
    if (!isProductInCart(product)) {
        product2Map.put(product, newQuantity);
    } else {
        Map.Entry<Product, Integer> productInCart = selectProductInCart(product);
        if (productInCart != null) {
            if (newQuantity > 0) {
                product2Map.replace(productInCart.getKey(), newQuantity);
            } else {
                product2Map.remove(productInCart);
            }
        }
    }
}
    public void deleteProduct(Product product) {
        Map.Entry<Product, Integer> productInCart = selectProductInCart(product);
        if (productInCart != null) {
            product2Map.remove(productInCart.getKey());
            productsWithSizes.remove(productInCart.getKey());
        }
    }

    public int getTotalPrice() {
        int total = 0;
        for (Map.Entry<Product, Integer> entry : product2Map.entrySet()) {
            total += entry.getValue() * entry.getKey().getPrices();
        }
        return total;
    }
    public void processSelectedProducts(List<Integer> productIds) {
        // Giả sử rằng mỗi productId tương ứng với một sizeId theo thứ tự
        for (int i = 0; i < productIds.size(); i++) {
            int productId = productIds.get(i);
            System.out.println("vo toi day roi");
            System.out.println(productId);

            // Tìm sản phẩm trong giỏ hàng dựa trên ID
            Product productToProcess = findProductById(productId);

            if (productToProcess != null) {
                deleteProduct(productToProcess);
//                productsWithSizes.remove(productToProcess);
            }
        }
    }


    private Product findProductById(int productId) {
        for (Product product : product2Map.keySet()) {
            if (product.getId() == productId) {
                return product;
            }
        }
        return null;
    }
    private Size findSizeById(int sizeId) {
        // Duyệt qua mỗi sản phẩm trong productsWithSizes
        for (Map.Entry<Product, Map<Size, Integer>> productEntry : productsWithSizes.entrySet()) {
            // Lấy map của Size cho mỗi sản phẩm
            Map<Size, Integer> sizes = productEntry.getValue();
            // Duyệt qua mỗi Size trong map
            for (Size size : sizes.keySet()) {
                // Kiểm tra nếu id của Size trùng với sizeId cần tìm
                if (size.getId() == sizeId) {
                    return size; // Trả về Size nếu tìm thấy
                }
            }
        }
        return null; // Trả về null nếu không tìm thấy Size phù hợp
    }
    public Map<Product, Integer> getSelectedProductsInfo(List<Integer> productIds) {
        Map<Product, Integer> selectedProductsInfo = new HashMap<>();
        for (Integer productId : productIds) {
            Product product = findProductById(productId);
            if (product != null && product2Map.containsKey(product)) {
                selectedProductsInfo.put(product, product2Map.get(product));
            }
        }
        return selectedProductsInfo;
    }

    public int calculateTotalPrice(List<Integer> productIds) {
        int totalPrice = 0;
        for (Integer productId : productIds) {
            Product product = findProductById(productId);
            if (product != null && product2Map.containsKey(product)) {
                totalPrice += product.getPrices() * product2Map.get(product);
            }
        }
        return totalPrice;
    }


    public Map<Product, Map<Size, Integer>> getProductsWithSizes() {
        return productsWithSizes;
    }

    public void setProductsWithSizes(Map<Product, Map<Size, Integer>> productsWithSizes) {
        this.productsWithSizes = productsWithSizes;
    }
    public int getTotalQuantity() {
        int totalQuantity = 0;
        for (Integer quantity : product2Map.values()) {
            totalQuantity += quantity;
        }
        return totalQuantity;
    }

}
