package com.example.project.controller.Cart;

import com.example.project.dto.Cart;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.text.DecimalFormat;

@Controller
@RequestMapping("/cart")
public class CartController {
    @GetMapping
    public String showList(Model model, @SessionAttribute(value = "cart", required = false) Cart cart){
        Integer total=cart.total();
        DecimalFormat formatter = new DecimalFormat("###,###,###");
//        if (total == null || cart == null) {
//            // Nếu total hoặc cart bằng null, chuyển hướng đến trang khác
//            return "redirect:/cartTrong"; // Thay đổi "/trang-khac" thành URL của trang bạn muốn chuyển hướng đến
//        }
        if (total != null) {
            String formattedTotal = formatter.format(total) + " VND";
            model.addAttribute("total", formattedTotal);
        } else {
            model.addAttribute("total", "Giỏ hàng trống"); // Hoặc bạn có thể đặt một giá trị mặc định khác
        }
//        String formattedTotal = formatter.format(total) + " VND";
        model.addAttribute("cart", cart);
//        model.addAttribute("total",formattedTotal);
        return  "/cart";
    }
}
