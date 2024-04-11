package com.example.project.controller.LoginRegisterController;
import com.example.project.dto.Cart;
import com.example.project.dto.UserDto;
import com.example.project.entity.UserProduct;
import com.example.project.entity.login_register.User;
import com.example.project.entity.product.Product;
import com.example.project.entity.product.Size;
import com.example.project.service.login_register.inter.LoginRegisterService;
import com.example.project.service.product.inter.ProductService;
import com.example.project.service.size.SizeService;
import com.example.project.service.user.inter.UserService;
import com.example.project.service.userProduct.inter.UserProductService;
import com.example.project.util.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/LoginRegister")
@SessionAttributes("cart")
public class LoginRegisterController {
    @ModelAttribute("cart")
    public Cart init() {
        return new Cart();
    }
    @Autowired
    private LoginRegisterService loginRegisterService;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserValidator userValidator;
    @Autowired
    private SizeService sizeService;
    @Autowired
    private UserProductService userProductService;
    @Autowired
    private UserService userService;
    @GetMapping("/auth")
    public String viewRegister(Model model, @RequestParam(value = "action", required = false) String action){
        model.addAttribute("register",new UserDto());
        model.addAttribute("login", new User());
        model.addAttribute("action", action == null ?  "login" : action);
        return "/LoginRegister/register";
    }
    @PostMapping("/register")
    public String doRegister(
                             @ModelAttribute("login") User user, Model model){
//        @Validated @ModelAttribute("register") UserDto userDto , BindingResult bindingResult ,
//        User userCon=convertDto(userDto);
//        userValidator.validate(userCon,bindingResult);
//        if(bindingResult.hasErrors()){
//            model.addAttribute("login", new User());
//            model.addAttribute("action", "register");
//            return "/LoginRegister/register";
//        }else {
            loginRegisterService.create(user);
            return "redirect:/LoginRegister/auth";//tren cai thang ma mapping
//        }
    }
    @PostMapping("/login")
    public String doLogin(@ModelAttribute("login") User user , HttpSession session, Model model,RedirectAttributes redirectAttributes) {
//        System.out.println("co vao");
            User user1 = loginRegisterService.findByUser(user.getUserName());
//        System.out.println(user1 + user.getUserName());
            if (user1 != null && user1.getPassword().equals(user.getPassword())) {
                session.setAttribute("userName", user1.getName()); // Lưu userName vào session
                session.setAttribute("userId", user1.getId()); // Lưu userId vào session
                session.setAttribute("user",user1);
//                System.out.println("vao duoc roi");
//                redirectAttributes.addFlashAttribute("userId", user1.getId());
                return "redirect:/LoginRegister/home";//tren cai thang ma mapping
            } else {
                // Đăng nhập thất bại, hiển thị lại trang đăng nhập với thông báo lỗi
//                System.out.println("khong duoc roi");
                model.addAttribute("error", "Tên đăng nhập hoặc mật khẩu không đúng");
                return "/LoginRegister/register";
            }
        }
    @GetMapping("/logout")
    public String doLogout(HttpSession session) {
        session.invalidate(); // This invalidates the session, clearing out all attributes
        return "redirect:/LoginRegister/auth"; // Redirects to the login page after logout
    }
    @GetMapping("/home")
    public String viewHome(@SessionAttribute(name = "cart", required = false) Cart cart,HttpSession session, Model model)
    {
        if (cart != null) {
            int totalQuantity = cart.getTotalQuantity();
            model.addAttribute("totalQuantity", totalQuantity);
        } else {
            // Nếu không có giỏ hàng, đặt tổng số lượng là 0
            model.addAttribute("totalQuantity", 0);
        }
        String userName = (String) session.getAttribute("userName");
        model.addAttribute("userName", userName);
        return "/PageHome/home";
    }
    @GetMapping("/list")
    public String viewList(@SessionAttribute(name = "cart", required = false) Cart cart,HttpSession session ,Model model)
    {
        if (cart != null) {
            int totalQuantity = cart.getTotalQuantity();
            model.addAttribute("totalQuantity", totalQuantity);
        } else {
            // Nếu không có giỏ hàng, đặt tổng số lượng là 0
            model.addAttribute("totalQuantity", 0);
        }
        String userName = (String) session.getAttribute("userName");
        model.addAttribute("userName", userName);
        model.addAttribute("products",productService.findAll());
//         System.out.println(userName);
        return "/Dainese/listProduct";
    }
    @GetMapping("/top")
    public String viewListTop(@SessionAttribute(name = "cart", required = false) Cart cart,HttpSession session ,Model model)
    {
        if (cart != null) {
        int totalQuantity = cart.getTotalQuantity();
        model.addAttribute("totalQuantity", totalQuantity);
        } else {
        // Nếu không có giỏ hàng, đặt tổng số lượng là 0
        model.addAttribute("totalQuantity", 0);
        }
        String userName = (String) session.getAttribute("userName");
        model.addAttribute("userName", userName);
        model.addAttribute("products",productService.findByCategory(1));
        return "/Dainese/listProduct";
    }
    @GetMapping("/bottom")
    public String viewListBottom(@SessionAttribute(name = "cart", required = false) Cart cart,HttpSession session ,Model model)
    {
        if (cart != null) {
            int totalQuantity = cart.getTotalQuantity();
            model.addAttribute("totalQuantity", totalQuantity);
        } else {
            // Nếu không có giỏ hàng, đặt tổng số lượng là 0
            model.addAttribute("totalQuantity", 0);
        }
        String userName = (String) session.getAttribute("userName");
        model.addAttribute("userName", userName);
        model.addAttribute("products",productService.findByCategory(2));
        return "/Dainese/listProduct";
    }
    @GetMapping("/profile")
    public String viewProfile(HttpSession session, Model model) {
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId != null) {
            User user = loginRegisterService.findById(userId);
            model.addAttribute("userOld", user);
            return "/Profile/profile";
        } else {
            return "redirect:/LoginRegister/login";
        }
    }
    @PostMapping("/profile")
    public String doProfile(@ModelAttribute("userOld") User user, HttpSession session) {
        loginRegisterService.update(user);
        // Cập nhật thông tin người dùng trong session sau khi cập nhật
        session.setAttribute("userName", user.getName());
        session.setAttribute("userId", user.getId());
        return "redirect:/LoginRegister/home";
}

     @GetMapping("/about")
     public String viewAbout(@SessionAttribute(name = "cart", required = false) Cart cart,HttpSession session, Model model){
         if (cart != null) {
             int totalQuantity = cart.getTotalQuantity();
             model.addAttribute("totalQuantity", totalQuantity);
         } else {
             // Nếu không có giỏ hàng, đặt tổng số lượng là 0
             model.addAttribute("totalQuantity", 0);
         }
        String userName = (String) session.getAttribute("userName");
         model.addAttribute("userName", userName);

        return "About/about";
     }
     @GetMapping("/contact")
     public String viewContact(@SessionAttribute(name = "cart", required = false) Cart cart,HttpSession session, Model model){
         if (cart != null) {
             int totalQuantity = cart.getTotalQuantity();
             model.addAttribute("totalQuantity", totalQuantity);
         } else {
             // Nếu không có giỏ hàng, đặt tổng số lượng là 0
             model.addAttribute("totalQuantity", 0);
         }
        String userName = (String) session.getAttribute("userName");
         model.addAttribute("userName", userName);
        return "Contact/contact";
     }
    @GetMapping("/detail/{id}")
    public String viewDetail(  @SessionAttribute(name = "cart", required = false) Cart cart,Model model, @PathVariable int id,HttpSession session)
    {
        Optional<Product> productOptional = productService.findById(id);

        if (productOptional.isPresent()){
            model.addAttribute("product",productOptional.get());
            System.out.println(productOptional.get().getSizes());
        }else {
            model.addAttribute("product", null);
        }
        if (cart != null) {
            int totalQuantity = cart.getTotalQuantity();
            model.addAttribute("totalQuantity", totalQuantity);
        } else {
            // Nếu không có giỏ hàng, đặt tổng số lượng là 0
            model.addAttribute("totalQuantity", 0);
        }
        String userName = (String) session.getAttribute("userName");
        model.addAttribute("userName", userName);
        return "/DetailProduct/chiTiet";
    }
    @PostMapping("/add/{productId}/{sizeId}")
    public String addToCart(@RequestParam("quantity")int quantity,@PathVariable int productId, @PathVariable int sizeId, @SessionAttribute(name = "cart", required = false) Cart cart, HttpSession session, Model model) {
        Optional<Product> productOptional = productService.findById(productId);
        Optional<Size> sizeOptional = sizeService.findById(sizeId);
        System.out.println(sizeOptional);
        System.out.println("Đã vào");

        if (productOptional.isPresent() && sizeOptional.isPresent()) {
            if (cart == null) {
                cart = new Cart(); // Tạo giỏ hàng mới nếu chưa có
                session.setAttribute("cart", cart);
            }
            // Logic thêm sản phẩm vào giỏ hàng
//            cart.addProduct(productOptional.get(), sizeOptional.get());
            cart.addProduct(productOptional.get(),sizeOptional.get(),quantity);
            // Điều hướng người dùng đến trang giỏ hàng
            return "redirect:/cart";
        } else {
            // Xử lý trường hợp sản phẩm hoặc kích cỡ không tồn tại
            model.addAttribute("errorMessage", "Invalid product or size");
            return "redirect:/errorPage"; // Giả định rằng bạn có một trang lỗi
        }
    }



    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable int id, @SessionAttribute(name = "cart", required = false) Cart cart){
        Optional<Product> productOptional = productService.findById(id);
        System.out.println(productOptional);
        if (productOptional.isPresent()){
            cart.deleteProduct(productOptional.get());
        }
        return "redirect:/cart";
    }
    //lay id
    @PostMapping("/processPayment")
    public String processPayment(@SessionAttribute(name = "user") User user,
                                       @SessionAttribute(name = "cart", required = false) Cart cart,
                                       @RequestParam("selectedProducts") List<Integer> selectedProductIds,
                                        @RequestParam("selectedSizes") List<Integer> selectedSizeIds,
                                       @RequestParam("productData") String quantity,
                                       Model model) {
        System.out.println(quantity);
        String numberOnly = quantity.replaceAll("\\D+", "");
        double quantityInt = Double.parseDouble(numberOnly);
        System.out.println(quantityInt);
        if (cart != null|| user!=null) {
            for (int i = 0; i < selectedProductIds.size(); i++) {
                UserProduct userProduct=new UserProduct();
                int productId = selectedProductIds.get(i);
                int sizeId = selectedSizeIds.get(i); // Lấy sizeId tương ứng
                System.out.println("vo toi day roi");
                System.out.println(productId);
                System.out.println(sizeId);
                Product product=productService.findProductById(productId);
                Size size=sizeService.findByIdd(sizeId);
                userProduct.setProduct(product);
                userProduct.setSize(size);
                userProduct.setUser(user);
                userProduct.setTime(LocalDate.now());
                userProduct.setStatus(false);
                userProductService.save(userProduct);
//                cart.processSelectedProducts(selectedProductIds, user, selectedSizeIds);
            }
            List<UserProduct>userProductList=userProductService.findAllByStatus();
            double total=0;
            for (UserProduct userProduct1:userProductList){
                total += (userProduct1.getProduct().getPrices()*quantityInt);
            }
            DecimalFormat formatter = new DecimalFormat("###,###,###");
            String formattedTotal = formatter.format(total) + " VND";

            // Thêm thông tin cần thiết vào model
            model.addAttribute("user", user);
            model.addAttribute("userProduct",userProductService.findAllByStatus());
            model.addAttribute("selectedProducts", cart.getSelectedProductsInfo(selectedProductIds));
            model.addAttribute("totalPrice", formattedTotal);
            // Điều hướng người dùng đến trang xác nhận thanh toán
            return "/Pay/finalPay";
        }
        return "redirect:/LoginRegister/register";
    }
    @PostMapping("/pay")
    public String doPay(@SessionAttribute(name = "user") User user,
                        @SessionAttribute(name = "cart", required = false) Cart cart,
                        @RequestParam("productIds") List<Integer> selectedProductIds,
                        Model model)
    {
        if(user!=null){
            List<UserProduct>userProductList=userProductService.findAllByStatus();
            for(UserProduct userProduct:userProductList){
                userProduct.setStatus(true);
                userProductService.save(userProduct);
            }
            cart.processSelectedProducts(selectedProductIds);
            return "/Thank/thank";
        }
        return "/LoginRegister/register";
    }
    @GetMapping("/thank")
    public String viewThank(){
        return "Thank/thank";
    }
    @GetMapping("/history")
    public String viewHistory(@SessionAttribute(name = "user") User user,@SessionAttribute(name = "cart", required = false) Cart cart,HttpSession session,Model model){
        productService.findProductById(user.getId());
        model.addAttribute("user",userProductService.findAllByTrue(user));
        if (cart != null) {
            int totalQuantity = cart.getTotalQuantity();
            model.addAttribute("totalQuantity", totalQuantity);
        } else {
            // Nếu không có giỏ hàng, đặt tổng số lượng là 0
            model.addAttribute("totalQuantity", 0);
        }
        String userName = (String) session.getAttribute("userName");
        model.addAttribute("userName", userName);
        return "/History/history";
    }
    @GetMapping("/deleteHistory")
    public String doDelete(@RequestParam("id") int id)
    {
        userProductService.deleteById(id);
        return"redirect:/LoginRegister/history";
    }
    @GetMapping("/new")
    public String viewNew(@SessionAttribute(name = "cart", required = false) Cart cart,HttpSession session, Model model){
        if (cart != null) {
            int totalQuantity = cart.getTotalQuantity();
            model.addAttribute("totalQuantity", totalQuantity);
        } else {
            // Nếu không có giỏ hàng, đặt tổng số lượng là 0
            model.addAttribute("totalQuantity", 0);
        }
        String userName = (String) session.getAttribute("userName");
        model.addAttribute("userName", userName);
        return "/New/news";
    }
    @GetMapping("/new1")
    public String viewNew1(@SessionAttribute(name = "cart", required = false) Cart cart,HttpSession session, Model model){
        if (cart != null) {
            int totalQuantity = cart.getTotalQuantity();
            model.addAttribute("totalQuantity", totalQuantity);
        } else {
            // Nếu không có giỏ hàng, đặt tổng số lượng là 0
            model.addAttribute("totalQuantity", 0);
        }
        String userName = (String) session.getAttribute("userName");
        model.addAttribute("userName", userName);
        return "/New/news1";
    }
    @GetMapping("/new2")
    public String viewNew2(@SessionAttribute(name = "cart", required = false) Cart cart,HttpSession session, Model model){
        if (cart != null) {
            int totalQuantity = cart.getTotalQuantity();
            model.addAttribute("totalQuantity", totalQuantity);
        } else {
            // Nếu không có giỏ hàng, đặt tổng số lượng là 0
            model.addAttribute("totalQuantity", 0);
        }
        String userName = (String) session.getAttribute("userName");
        model.addAttribute("userName", userName);
        return "/New/news2";
    }
    @GetMapping("/new3")
    public String viewNew3(@SessionAttribute(name = "cart", required = false) Cart cart,HttpSession session, Model model){
        if (cart != null) {
            int totalQuantity = cart.getTotalQuantity();
            model.addAttribute("totalQuantity", totalQuantity);
        } else {
            // Nếu không có giỏ hàng, đặt tổng số lượng là 0
            model.addAttribute("totalQuantity", 0);
        }
        String userName = (String) session.getAttribute("userName");
        model.addAttribute("userName", userName);
        return "/New/news3";
    }
    public User convertDto(UserDto userDto){
        User user = new User();
        user.setName(userDto.getName());
        user.setId(userDto.getId());
        user.setUserName(userDto.getUserName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        return user;
    }
}
