# Bot trade crypto
Bot cung cấp khả năng spot trade được kết nối với binance và sử dụng thuật toán đã được cài đặt từ trước</br>
## Download bot v1.0
[<img src="https://user-images.githubusercontent.com/118048533/210755608-f6f1be92-4d3a-4128-b4ab-73d1845edbeb.png" alt="TradeLog" width="55"></img>](https://github.com/nguyenminhkhoi2002/test/releases/download/v2.0/application.zip)
### Hoạt động của ứng ụng được mô tả như sau 
* Màn hình đăng nhâp</br>
<img src="https://user-images.githubusercontent.com/118048533/210740895-c69ae9b0-77c2-4156-a751-3a7f86b37800.png" alt="login" width="400"></img> </br>
Tại đây ứng dụng yêu cầu người dùng nhập `ApiKey` và `SecretKey` ,  Truy cập [Binance Support](https://www.binance.com/en/support/faq/how-to-create-api-360002502072) và làm theo hướng dẫn để lấy keys cá nhân</br>
* Màn hình Client </br>
Sau khi đăng nhập thành công , người dùng được chuyển hướng tới màn hình chính </br>
<img src="https://user-images.githubusercontent.com/118048533/210746734-11b2ca7d-12ed-4d91-a49e-2a58245f9e20.png" alt="client" width="500"></img> </br>
>Màn hình này hiển thị các thành phần như sau:</br>
  1. Số Tether hiện có (đây là loại tiền được đảm bảo bởi USD) 
  2. Loại crypto và số dư tương ứng còn trong ví của người dùng 
  3. Cung cấp chức năng xem bảng giá chi tiết
  4. Giá cả hiện tại của crypto nếu người dùng không muốn xem trực tiếp
  5. Độ biến động của đồng tiền (tính theo %) so với ngày hôm qua màu sắc thể hiện độ tăng, giảm (xanh là tăng, đỏ là giảm)
  6. Bot cung cấp các lựa chọn cho các loại crypto để trade gồm `BTC`, `ETH`,`BNB`,`LTC`
* Màn hình trade </br>
<img src="https://user-images.githubusercontent.com/118048533/210750239-6cd76866-a40e-4ee2-877d-130e187728df.jpg" alt="trading" width="550"></img> </br>
Quá trình trade của ứng dụng được hiển thị qua đồ thị theo thời gian thực để người dùng có thể quan sát và đánh giá tính khả thi của bot(mất nhiều thời gian)</br>
Người dùng có thể dừng quá trình này lại nếu cảm thấy không hiệu quả hoặc hết tiền</br>
<img src="https://user-images.githubusercontent.com/118048533/210753291-98d9fe86-773b-4b84-a1ab-8d6a296b2782.png" alt="client" width="550"></img> </br>
Bot ngay lập tức cập nhật số dư còn lại trong ví của user
* Kết thúc quá trình trade , lịch sử trade sẽ được ghi vào file TradeLog.txt để đảm bảo tính minh bạch
<img src="https://user-images.githubusercontent.com/118048533/210754119-d31d6cf9-e8a9-43f2-a38f-a651bad9b0c0.png" alt="TradeLog" width="550"></img> </br>

