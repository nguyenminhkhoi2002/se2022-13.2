# se2022- Nhóm 13.2: Tìm hiểu về Blockchain ứng dụng.
## Đề tài: Tìm hiểu về Blockchain và code một app kết nối với sàn giao dịch Binance, sử dụng Binance API để thiết kế 1 thuật toán tự động trading.
## Phần I: Nội dung cơ bản về Blockchain
### Khái niệm
* Blockchain là công nghệ chuỗi – khối, cho phép truyền tải dữ liệu một cách an toàn dựa trên hệ thống mã hóa vô cùng phức tạp (tương tự như cuốn sổ cái kế toán của một công ty, nơi mà tiền được giám sát chặt chẽ và ghi nhận mọi giao dịch trên mạng ngang hàng). 
* Mỗi khối (block) đều chứa thông tin về thời gian khởi tạo và được liên kết với khối trước đó, kèm theo đó là một mã thời gian và dữ liệu giao dịch. Dữ liệu khi đã được mạng lưới chấp nhận thì sẽ không có cách nào thay đổi được. Blockchain được thiết kế để ***chống lại việc gian lận, thay đổi của dữ liệu***.
### Công nghệ Blockchain - sự kết hợp giữa 3 loại công nghệ
* **Mật mã học**: để đảm bảo tính minh bạch, toàn vẹn và riêng tư thì công nghệ Blockchain đã sử dụng public key và hash function.
* **Mạng ngang hàng**: mỗi một nút trong mạng được xem như một client và cũng là server để lưu trữ bản sao ứng dụng.
* **Lý thuyết trò chơi**: tất cả các nút tham gia vào hệ thống đều phải tuân thủ luật chơi đồng thuận (giao thức PoW, PoS,…) và được thúc đẩy bởi động lực kinh tế.
### Hệ thống Blockchain chia làm 3 loại chính:
* **Public**: bất kỳ ai cũng có quyền đọc và ghi dữ liệu trên Blockchain. Quá trình xác thực giao dịch trên Blockchain này đòi hỏi phải có rất nhiều nút tham gia.
* **Private**: người dùng chỉ có quyền đọc dữ liệu, quyền ghi thuộc về tổ chức thứ ba ( phải tuyệt đối tin cậy).
* **Permissioned (hay còn gọi là Consortium)**: một dạng của Private nhưng bổ sung thêm 1 số tính năng khác, đây là sự kết hợp giữa Public và Private.
### Các đặc điểm nổi bật của Blockchain
* **Không thể làm giả, không thể phá hủy các chuỗi Blockchain**: theo như lý thuyết thì chỉ có máy tính lượng tử mới có thể giải mã Blockchain và công nghệ Blockchain biến mất khi không còn Internet trên toàn cầu.
* **Bất biến**: dữ liệu trong Blockchain không thể sửa (có thể sửa nhưng sẽ để lại dấu vết) và sẽ lưu trữ mãi mãi.
* **Bảo mật**: các thông tin, dữ liệu trong Blockchain được phân tán và an toàn tuyệt đối.
* **Minh bạch** : ai cũng có thể theo dõi dữ liệu Blockchain đi từ địa chỉ này tới địa chỉ khác và có thể thống kê toàn bộ lịch sử trên địa chỉ đó.
* **Hợp đồng thông minh** : là hợp đồng kỹ thuật số được nhúng vào đoạn code if-this-then-that (IFTTT), cho phép chúng tự thực thi mà không cần bên thứ ba.
### Blockchain hoạt động như thế nào
* Ứng dụng được biết đến và thảo luận nhiều nhất về công nghệ Blockchain chính là đồng tiền điện tử. Bitcoin là một đơn vị tiền tệ kỹ thuật số với mã là BTC, cũng giống như đô la Mỹ bản thân nó không mang giá trị, nó chỉ có giá trị bởi vì có một cộng đồng đồng ý sử dụng nó làm đơn vị giao dịch hàng hóa và dịch vụ.
* Để theo dõi số lượng Bitcoin mà mỗi người sở hữu trong các tài khoản nhất định và theo dõi các giao dịch phát sinh từ đó thì chúng ta cần đến một cuốn sổ kế toán, trong trường hợp này nó chính là Blockchain và đây thực tế là một tệp kỹ thuật số theo dõi tất cả các giao dịch Bitcoin.
### Nguyên lý mã hóa
* Sổ cái được duy trì bởi các máy tính trong mạng ngang hàng được kết nối với nhau.
* Mạng lưới Bitcoin là **mạng lưới phân tán** và ** không cần bên thứ ba đóng vai trò trung gian xử lý giao dịch**. 
* Hệ thống Blockchain không yêu cầu sự tin cậy mà được bảo đảm bởi độ tin cậy có được thông qua các hàm mã hóa toán học đặc biệt.
* Để thực hiện các giao dịch Blockchain, cần có ví tiền điện tử. Ví này được bảo vệ bằng cặp khóa bảo mật: ***khóa riêng tư (private key) và khóa công khai (public key)***.
* Nếu một thông điệp được mã hóa bằng một khóa công khai cụ thể thì chỉ chủ sở hữu của khóa riêng tư là một cặp với khóa công khai này mới có thể giải mã và đọc nội dung thông điệp.
### Quy tắc sổ cái
* Mỗi nút trong Blockchain đều đang **lưu giữ một bản sao** của sổ kế toán. Do vậy, mỗi nút đều biết số dư tài khoản của bạn là bao nhiêu. Hệ thống Blockchain chỉ ghi lại mỗi giao dịch được yêu cầu chứ không hề theo dõi số dư tài khoản của bạn.
* Để biết số dư trên ví điện tử của mình thì bạn cần xác thực và xác nhận tất cả các giao dịch đã diễn ra trên mạng lưới mà có liên quan tới ví điện tử của bạn.
### Nguyên lý tạo khối
* Các giao dịch sau khi được gửi lên trên mạng lưới Blockchain sẽ được nhóm vào các khối và các giao dịch trong cùng 1 khối (block) được coi là đã xảy ra cùng thời điểm. Các giao dịch chưa được thực hiện trong 1 khối được coi là chưa được xác nhận.
* Để được thêm vào Blockchain, mỗi khối phải chứa một đoạn mã đóng vai trò như một đáp án cho một vấn đề toán học phức tạp được tạo ra bằng **hàm mã hóa băm không thể đảo ngược**.
* Cách duy nhất để giải quyết vấn đề toán học như vậy là **đoán các số ngẫu nhiên**, những số khi mà kết hợp với nội dung khối trước tạo ra một kết quả đã được hệ thống định nghĩa. Điều này nhiều khi có thể mất khoảng một năm cho một máy tính điển hình với một cấu hình cơ bản có thể đoán đúng các con số đáp án của vấn đề toán học này.
* Mạng lưới quy định mỗi khối được tạo ra sau một quãng thời gian là **10 phút** một lần, bởi vì trong mạng lưới luôn có một số lượng lớn các máy tính đều tập trung vào việc đoán ra dãy số này. Nút nào giải quyết được vấn đề toán học như vậy sẽ được quyền gắn khối tiếp theo lên trên chuỗi và gửi nó tới toàn bộ mạng lưới.
