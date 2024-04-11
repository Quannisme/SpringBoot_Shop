// document.addEventListener('DOMContentLoaded', function() {
//     var checkoutButton = document.getElementById('checkout-button');
//     checkoutButton.addEventListener('click', function() {
//     });
//     var continueShoppingButton = document.getElementById('continue-shopping');
//     continueShoppingButton.addEventListener('click', function() {
//     });
// });
// function updateTotal() {
//     var total = 0;
//     var items = document.querySelectorAll('.cart-item');

//     items.forEach(function(item) {
//         var priceElement = item.querySelector('.item-price');
//         var quantityElement = item.querySelector('.quantity-input');
//         var price = parseFloat(priceElement.textContent.replace('₫', '').replace('.', ''));
//         var quantity = parseInt(quantityElement.value);
//         total += price * quantity;
//     });
//     document.querySelector('.total-price').textContent = formatPrice(total) + '₫';
// }
// function formatPrice(price) {
//     return price.toLocaleString('vi-VN');
// }
// document.addEventListener('DOMContentLoaded', function() {
//     updateTotal();
//     var quantityInputs = document.querySelectorAll('.quantity-input');
//     quantityInputs.forEach(function(input) {
//         input.addEventListener('change', function() {
//             updateTotal();
//         });
//     });
// });
// document.querySelector('.continue-shopping-button').addEventListener('click', function() {
//     window.location.href = '/path-to-your-products-page.html'; 
// });
// document.addEventListener('DOMContentLoaded', function() {

//     document.querySelectorAll('.increase-quantity').forEach(button => {
//         button.addEventListener('click', function() {
//             let quantityInput = this.previousElementSibling;
//             let currentValue = parseInt(quantityInput.value, 10);
//             quantityInput.value = currentValue + 1;
//         });
//     });

//     document.querySelectorAll('.decrease-quantity').forEach(button => {
//         button.addEventListener('click', function() {
//             let quantityInput = this.nextElementSibling;
//             let currentValue = parseInt(quantityInput.value, 10);
//             if (currentValue > 1) {
//                 quantityInput.value = currentValue - 1;
//             }
//         });
//     });
//     document.querySelectorAll('.remove-item').forEach(button => {
//         button.addEventListener('click', function() {
//             this.parentElement.parentElement.remove();
            
//         });
//     });
// });
// document.addEventListener('DOMContentLoaded', function() {
//     document.querySelector('.checkout-button').addEventListener('click', function() {
//         window.location.href = 'trang_thanh_toan.html'; // link thanh toán 
//     });
// });
// // Mảng để lưu trữ sản phẩm trong giỏ hàng
// let cartItems = [];

// // Function để thêm sản phẩm vào giỏ hàng
// function addToCart(product) {
//     // Kiểm tra sản phẩm đã có trong giỏ hàng chưa
//     const existingProduct = cartItems.find(item => item.id === product.id);
//     if (existingProduct) {
//         // ud so luong
//         existingProduct.quantity++;
//         existingProduct.total = existingProduct.quantity * existingProduct.price;
//     } else {
//         // them sp vs so luong là 1
//         cartItems.push({ ...product, quantity: 1, total: product.price });
//     }
//     // Cập nhật UI
//     updateCartUI();
// }

// // Function để cập nhật UI của giỏ hàng
// function updateCartUI() {
//     const cartItemsContainer = document.querySelector('.shopping-cart-items');
//     cartItemsContainer.innerHTML = ''; // Xóa các mục hiện có để cập nhật
//     cartItems.forEach(item => {
//         // Thêm từng sản phẩm vào HTML
//         const itemHTML = `
//             <div class="shopping-cart-item">
//                 <div class="product-column">${item.name}</div>
//                 <div class="quantity-column">
//                     <button onclick="updateQuantity(${item.id}, 'minus')">-</button>
//                     <input type="text" value="${item.quantity}">
//                     <button onclick="updateQuantity(${item.id}, 'plus')">+</button>
//                 </div>
//                 <div class="total-column">${item.total}</div>
//                 <div class="remove-column">
//                     <button onclick="removeFromCart(${item.id})">🗑</button>
//                 </div>
//             </div>
//         `;
//         cartItemsContainer.innerHTML += itemHTML;
//     });
//     // Cập nhật tổng tiền
//     updateTotalPrice();
// }

// // Function để cập nhật UI của giỏ hàng
// function updateCartUI() {
//     const cartItemsContainer = document.querySelector('.shopping-cart-items');
//     cartItemsContainer.innerHTML = ''; // Xóa các mục hiện có để cập nhật
//     cartItems.forEach(item => {
//         // Thêm từng sản phẩm vào HTML
//         const itemHTML = `
//             <div class="shopping-cart-item" data-product-id="${item.id}">
//                 <div class="product-column">${item.name}</div>
//                 <div class="quantity-column">
//                     <button class="decrease-quantity">-</button>
//                     <input type="text" class="quantity-input" value="${item.quantity}">
//                     <button class="increase-quantity">+</button>
//                 </div>
//                 <div class="total-column">${formatPrice(item.total)}</div>
//                 <div class="remove-column">
//                     <button class="remove-item">🗑</button>
//                 </div>
//             </div>
//         `;
//         cartItemsContainer.innerHTML += itemHTML;
//     });
//     // Đăng ký sự kiện cho các nút và input mới thêm vào
//     registerItemEvents();
//     // Cập nhật tổng tiền
//     updateTotalPrice();
// }

// // Function để đăng ký sự kiện cho các nút và input
// function registerItemEvents() {
//     document.querySelectorAll('.shopping-cart-item').forEach(item => {
//         const productId = parseInt(item.getAttribute('data-product-id'), 10);
//         item.querySelector('.increase-quantity').addEventListener('click', () => updateQuantity(productId, 'plus'));
//         item.querySelector('.decrease-quantity').addEventListener('click', () => updateQuantity(productId, 'minus'));
//         item.querySelector('.quantity-input').addEventListener('change', updateTotalPrice);
//         item.querySelector('.remove-item').addEventListener('click', () => removeFromCart(productId));
//     });
// }

// // Khởi tạo UI khi trang web được tải
// document.addEventListener('DOMContentLoaded', function() {
//     updateCartUI();
//     // Các sự kiện khác...
// });
// function updateTotalPrice() {
//     const totalPriceElement = document.querySelector('.total-price');
//     const total = cartItems.reduce((sum, item) => sum + item.total, 0);
//     totalPriceElement.textContent = total + 'đ';
// }

// //  xóa sản phẩm khỏi giỏ hàng
// function removeFromCart(productId) {
//     cartItems = cartItems.filter(item => item.id !== productId);
//     updateCartUI();
// }
// document.addEventListener('DOMContentLoaded', updateCartUI);
// document.querySelectorAll('.increase-quantity').forEach(button => {
//     button.addEventListener('click', function() {
//         let quantityInput = this.previousElementSibling;
//         let currentValue = parseInt(quantityInput.value, 10);
//         quantityInput.value = currentValue + 1;
//     });
// });

// document.querySelectorAll('.decrease-quantity').forEach(button => {
//     button.addEventListener('click', function() {
//         let quantityInput = this.nextElementSibling;
//         let currentValue = parseInt(quantityInput.value, 10);
//         if (currentValue > 1) { 
//             quantityInput.value = currentValue - 1;
//         }
//     });
// });



