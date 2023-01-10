# se2022- Nhóm 13.2: Tìm hiểu về Blockchain ứng dụng.
## Đề tài: Tìm hiểu về Blockchain và code một app kết nối với sàn giao dịch Binance, sử dụng Binance API để thiết kế 1 thuật toán tự động trading.
## Phần I: Nội dung cơ bản về Blockchain
### I. Khái niệm
* Blockchain là công nghệ chuỗi – khối, cho phép truyền tải dữ liệu một cách an toàn dựa trên hệ thống mã hóa vô cùng phức tạp (tương tự như cuốn sổ cái kế toán của một công ty, nơi mà tiền được giám sát chặt chẽ và ghi nhận mọi giao dịch trên mạng ngang hàng). 
* Mỗi khối (block) đều chứa thông tin về thời gian khởi tạo và được liên kết với khối trước đó, kèm theo đó là một mã thời gian và dữ liệu giao dịch. Dữ liệu khi đã được mạng lưới chấp nhận thì sẽ không có cách nào thay đổi được. Blockchain được thiết kế để ***chống lại việc gian lận, thay đổi của dữ liệu***.
* ![image](https://user-images.githubusercontent.com/89736041/211306927-c0a95a18-cdfb-4a0d-8a32-ab011ac7788e.png)
### Công nghệ Blockchain - sự kết hợp giữa 3 loại công nghệ
* **Mật mã học**: để đảm bảo tính minh bạch, toàn vẹn và riêng tư thì công nghệ Blockchain đã sử dụng public key và hash function.
* **Mạng ngang hàng**: mỗi một nút trong mạng được xem như một client và cũng là server để lưu trữ bản sao ứng dụng.
* **Lý thuyết trò chơi**: tất cả các nút tham gia vào hệ thống đều phải tuân thủ luật chơi đồng thuận (giao thức PoW, PoS,…) và được thúc đẩy bởi động lực kinh tế.
### Hệ thống Blockchain chia làm 3 loại chính:
* **Public**: bất kỳ ai cũng có quyền đọc và ghi dữ liệu trên Blockchain. Quá trình xác thực giao dịch trên Blockchain này đòi hỏi phải có rất nhiều nút tham gia.
* **Private**: người dùng chỉ có quyền đọc dữ liệu, quyền ghi thuộc về tổ chức thứ ba ( phải tuyệt đối tin cậy).
* **Permissioned (hay còn gọi là Consortium)**: một dạng của Private nhưng bổ sung thêm 1 số tính năng khác, đây là sự kết hợp giữa Public và Private.
* ![image](https://user-images.githubusercontent.com/89736041/211307142-11174ebc-3706-4706-a399-27d608eb2991.png)
### Các phiên bản của công nghệ Blockchain
* **Công nghệ Blockchain 1.0**: Tiền tệ và Thanh toán: Ứng dụng chính của phiên bản này là tiền mã hoá: bao gồm việc chuyển đổi tiền tệ, kiều hối và tạo lập hệ thống thanh toán kỹ thuật số. Đây cũng là lĩnh vực quen thuộc với chúng ta nhất mà đôi khi khá nhiều người lầm tưởng Bitcoin và Blockchain là một.
* **Công nghệ Blockchain 2.0**: Tài chính và Thị trường: Ứng dụng xử lý tài chính và ngân hàng: mở rộng quy mô của Blockchain, đưa vào các ứng dụng tài chính và thị trường. Các tài sản bao gồm cổ phiếu, chi phiếu, nợ, quyền sở hữu và bất kỳ điều gì có liên quan đến thỏa thuận hay hợp đồng.
* **Công nghệ Blockchain 3.0**: Thiết kế và Giám sát hoạt động: Đưa Blockchain vượt khỏi biên giới tài chính, và đi vào các lĩnh vực như giáo dục, chính phủ, y tế và nghệ thuật. 
### II. Các đặc điểm nổi bật của Blockchain
* **Không thể làm giả, không thể phá hủy các chuỗi Blockchain**: theo như lý thuyết thì chỉ có máy tính lượng tử mới có thể giải mã Blockchain và công nghệ Blockchain biến mất khi không còn Internet trên toàn cầu.
* **Bất biến**: dữ liệu trong Blockchain không thể sửa (có thể sửa nhưng sẽ để lại dấu vết) và sẽ lưu trữ mãi mãi.
* **Bảo mật**: các thông tin, dữ liệu trong Blockchain được phân tán và an toàn tuyệt đối.
* **Minh bạch** : ai cũng có thể theo dõi dữ liệu Blockchain đi từ địa chỉ này tới địa chỉ khác và có thể thống kê toàn bộ lịch sử trên địa chỉ đó.
* **Hợp đồng thông minh** : là hợp đồng kỹ thuật số được nhúng vào đoạn code if-this-then-that (IFTTT), cho phép chúng tự thực thi mà không cần bên thứ ba.
### III. Blockchain hoạt động như thế nào
* Ứng dụng được biết đến và thảo luận nhiều nhất về công nghệ Blockchain chính là đồng tiền điện tử. Bitcoin là một đơn vị tiền tệ kỹ thuật số với mã là BTC, cũng giống như đô la Mỹ bản thân nó không mang giá trị, nó chỉ có giá trị bởi vì có một cộng đồng đồng ý sử dụng nó làm đơn vị giao dịch hàng hóa và dịch vụ.
Để theo dõi số lượng Bitcoin mà mỗi người sở hữu trong các tài khoản nhất định và theo dõi các giao dịch phát sinh từ đó thì chúng ta cần đến một cuốn sổ kế toán, trong trường hợp này nó chính là Blockchain và đây thực tế là một tệp kỹ thuật số theo dõi tất cả các giao dịch Bitcoin.

* Tệp sổ cái này không được lưu trữ trong một máy chủ trung tâm, như trong một ngân hàng hoặc trong một trung tâm dữ liệu mà ngược lại nó được phân phối trên toàn thế giới thông qua một mạng lưới các máy tính ngang hàng với vai trò lưu trữ dữ liệu và thực thi các tính toán. Mỗi máy tính này đại diện cho một “nút” của mạng lưới Blockchain và mỗi nút đều có một bản sao của tệp sổ cái này.
### Nguyên lý mã hóa
* Trên thực tế, cuốn sổ cái luôn được duy trì bởi các máy tính trong mạng ngang hàng được kết nối với nhau. Vì thế, nó sẽ có một số điểm khác biệt:
* Trong hệ thống ngân hàng, chúng ta chỉ biết các giao dịch và số dư tài khoản của riêng mình thì trên Blockchain của bitcoin bạn có thể xem các giao dịch của tất cả mọi người.
* Mạng lưới Bitcoin là mạng lưới phân tán không cần bên thứ ba đóng vai trò trung gian xử lý giao dịch.
* Hệ thống Blockchain được thiết kế theo cách không yêu cầu sự tin cậy và bảo đảm bởi độ tin cậy có được thông qua các hàm mã hóa toán học đặc biệt.
* Để có thể thực hiện các giao dịch trên Blockchain, bạn cần một phần mềm sẽ cho phép bạn lưu trữ và trao đổi các đồng Bitcoin của bạn gọi là ví tiền điện tử. Ví tiền điện tử này sẽ được bảo vệ bằng một phương pháp mã hóa đặc biệt đó là sử dụng một cặp khóa bảo mật duy nhất: khóa riêng tư (private key) và khóa công khai (public key).
* Nếu một thông điệp được mã hóa bằng một khóa công khai cụ thể thì chỉ chủ sở hữu của khóa riêng tư là một cặp với khóa công khai này mới có thể giải mã và đọc nội dung thông điệp.
* Khi mã hóa một yêu cầu giao dịch bằng khóa riêng tư, có nghĩa là bạn đang tạo ra một chữ ký điện tử được các máy tính trong mạng lưới Blockchain sử dụng để kiểm tra chủ thể gửi và tính xác thực của giao dịch. Chữ ký này là một chuỗi văn bản và là sự kết hợp của yêu cầu giao dịch và khóa riêng tư của bạn.
* Nếu một ký tự đơn trong thông điệp yêu cầu giao dịch này bị thay đổi thì chữ ký điện tử sẽ thay đổi theo. Vì thế, hacker khó có thể thay đổi yêu cầu giao dịch của bạn hoặc thay đổi số lượng Bitcoin mà bạn đang gửi.
* Để gửi Bitcoin (BTC), bạn cần chứng minh rằng bạn sở hữu khóa riêng tư của một chiếc ví điện tử cụ thể bởi bạn cần sử dụng nó để mã hóa thông điệp yêu cầu giao dịch. Sau khi tin nhắn của bạn đã được gửi đi và được mã hóa thì bạn không cần phải tiết lộ khóa riêng tư của bạn nữa.
### Quy tắc sổ cái
* Mỗi nút trong Blockchain đều đang lưu giữ một bản sao của sổ kế toán. Do vậy, mỗi nút đều biết số dư tài khoản của bạn là bao nhiêu. Hệ thống Blockchain chỉ ghi lại mỗi giao dịch được yêu cầu chứ không hề theo dõi số dư tài khoản của bạn.
Để biết số dư trên ví điện tử của mình thì bạn cần xác thực và xác nhận tất cả các giao dịch đã diễn ra trên mạng lưới mà có liên quan tới ví điện tử của bạn.
Việc xác minh “số dư” này được thực hiện nhờ các tính toán dựa vào liên kết đến các giao dịch trước đó.
* Các liên kết này được xem như là giá trị đầu vào, các nút trong mạng lưới sẽ xác minh xem tổng số tiền của các giao dịch này bằng hoặc vượt quá 10 BTC không. Tất cả điều này được thực hiện tự động trong ví điện tử của Mary và được kiểm tra bởi các nút trên mạng lưới Bitcoin, Mary chỉ gửi một giao dịch 10 bitcoin tới ví của John bằng khóa công khai của John.
* Thực tế là các nút sẽ kiểm tra tất cả các giao dịch có liên quan đến ví tiền điện tử bạn sử dụng trước đó để gửi Bitcoin (BTC) thông qua việc tham chiếu các lịch sử giao dịch. Có một bản ghi sẽ lưu trữ số BTC chưa được dùng và được các nút mạng lưu giữ giúp đơn giản hóa và tăng tốc quá trình xác minh. Vì thế, các ví tiền điện tử tránh được tình trạng chi tiêu đúp giao dịch.
Mã nguồn trên mạng lưới Bitcoin là nguồn mở, có nghĩa là bất kỳ ai có máy tính kết nối được internet đều có thể tham gia vào mạng lưới và thực hiện giao dịch.
* Tuy nhiên, nếu có bất kỳ một lỗi nào trong mã nguồn được sử dụng để phát thông báo yêu cầu giao dịch thì các Bitcoin liên quan sẽ bị mất vĩnh viễn.
Hãy nhớ rằng, sẽ không có bộ phận hỗ trợ khách hàng hoặc không hề có bất cứ ai có thể giúp bạn khôi phục lại một giao dịch bị mất hoặc quên mật khẩu ví tiền điện tử của bạn vì đây là mạng phân tán. Vì thế, bạn cần phải lưu trữ mật khẩu hoặc khóa riêng tư của ví của bạn cực kỳ cẩn thận và an toàn.
### Nguyên lý tạo khối
* Các giao dịch sau khi được gửi lên trên mạng lưới Blockchain sẽ được nhóm vào các khối và các giao dịch trong cùng 1 khối (block) được coi là đã xảy ra cùng thời điểm. Các giao dịch chưa được thực hiện trong 1 khối được coi là chưa được xác nhận.
* ![image](https://user-images.githubusercontent.com/89736041/211307510-b5485279-6bb2-44f2-9891-05b0dcb2fb13.png)
* Mỗi nút có thể nhóm các giao dịch với nhau thành một khối và gửi nó vào mạng lưới như một hàm ý cho các khối tiếp theo được gắn vào sau đó. Bất kỳ nút nào cũng có thể tạo ra một khối mới. Vậy, câu hỏi đặt ra là: hệ thống sẽ đồng thuận với khối nào? khối nào sẽ là khối tiếp theo?
Để được thêm vào Blockchain, mỗi khối phải chứa một đoạn mã đóng vai trò như một đáp án cho một vấn đề toán học phức tạp được tạo ra bằng hàm mã hóa băm không thể đảo ngược.
* Cách duy nhất để giải quyết vấn đề toán học như vậy là đoán các số ngẫu nhiên, những số khi mà kết hợp với nội dung khối trước tạo ra một kết quả đã được hệ thống định nghĩa. Điều này nhiều khi có thể mất khoảng một năm cho một máy tính điển hình với một cấu hình cơ bản có thể đoán đúng các con số đáp án của vấn đề toán học này.
* Mạng lưới quy định mỗi khối được tạo ra sau một quãng thời gian là 10 phút một lần, bởi vì trong mạng lưới luôn có một số lượng lớn các máy tính đều tập trung vào việc đoán ra dãy số này. Nút nào giải quyết được vấn đề toán học như vậy sẽ được quyền gắn khối tiếp theo lên trên chuỗi và gửi nó tới toàn bộ mạng lưới.
Vậy điều gì sẽ xảy ra nếu hai nút giải quyết cùng một vấn đề cùng một lúc và truyền các khối kết quả của chúng đồng thời lên mạng lưới? Trong trường hợp này, cả hai khối được gửi lên mạng lưới và mỗi nút sẽ xây dựng các khối kế tiếp trên khối mà nó nhận được trước tiên.
Tuy nhiên, hệ thống Blockchain luôn yêu cầu mỗi nút phải xây dựng trên chuỗi khối dài nhất mà nó nhận được. Vì vậy, nếu có sự mơ hồ về việc block nào là khối cuối cùng thì ngay sau khi khối tiếp theo được giải quyết thì mỗi nút sẽ áp dụng vào chuỗi dài nhất.
Do xác suất việc xây dựng các block đồng thời là rất thấp nên hầu như không có trường hợp nhiều khối được giải quyết cùng một lúc và nhiều lần tạo ra các khối nối đuôi khác nhau. Do đó, toàn bộ chuỗi-khối sẽ nhanh chóng ổn định và hợp nhất lại khi mà mọi nút đều đồng thuận.
### Thuật toán bảo mật Blockchain

* Nếu có bất kỳ sự bất đồng về khối đại diện sau cùng của chuỗi thì điều này sẽ dẫn đến khả năng gian lận. Nếu một giao dịch xảy ra trong 1 khối thuộc về đuôi ngắn hơn khi khối tiếp theo được giải quyết, giao dịch đó sẽ trở lại thành giao dịch chưa được xác nhận vì tất cả các giao dịch khác được nhóm vào trong khối kia.
Mỗi block chứa một tham chiếu đến khối trước đó, và tham chiếu đó là một phần của vấn đề toán học cần được giải quyết để truyền khối sau tới mạng lưới. Vì vậy, rất khó để tính toán trước một loạt các block bởi nó cần tính ra một số lượng lớn các số ngẫu nhiên cần thiết để giải quyết một khối và đặt nó trên blockchain.
Các giao dịch trong mạng lưới blockchain của bitcoin được bảo vệ bởi một cuộc chạy đua tính toán toán học: với bất kỳ kẻ tấn công nào muốn cạnh tranh với toàn bộ mạng lưới.
* Do đó, giao dịch ngày càng an toàn hơn theo thời gian. Và những khối đã được thêm vào chuỗi trong quá khứ bao giờ cũng an toàn hơn so với những khối mới được thêm vào. Bởi một block được thêm vào chuỗi trung bình cứ 10p một lần cho nên trong khoảng 1h kể từ khi giao dịch được nhóm vào trong khối đầu tiên của nó sẽ tạo ra một xác suất khá cao rằng giao dịch đã được xử lý và không thể đảo ngược.
### IV. Công việc của một lập trình viên Blockchain
* Tạo, kiểm tra và triển khai sản phẩm blockchain mới.
* Cập nhật sản phẩm.
* Nghiên cứu công nghệ mới để ứng dụng vào các dự án… là các công việc chung của một lập trình viên blockchain.
Các đơn vị tuyển dung lập trình viên blockchain, kỹ sư blockchain khá đa dạng từ các công ty công nghệ, công ty truyền thông, các công ty cung cấp giải pháp blockchain hay các công ty, ngân hàng, tổ chức tài chính có nhu cầu xây dựng hệ thống trên nền tảng blockchain.
### V. Ứng dụng thực tiễn của công nghệ Blockchain trong cuộc sống
* **Một số ngành công nghiệp mà công nghệ Blockchain có thể tác động đến như**:
- Công nghệ ô tô Automotive (Automotive)
- Chế tạo (Manufacturing)
- Công nghệ, truyền thông và viễn thông (Tech, media & Telecommunications)
- Dịch vụ tài chính (Financial Services)
- Nghệ thuật & Giải trí (Art & Recreation)
- Chăm sóc sức khỏe (Healthcare)
- Bảo hiểm (Insurance)
- Bán lẻ (Retail)
- Khu vực công (Public Sector)
- Bất động sản (Property)
- Nông nghiệp (Agricultural)
- Khai thác (Mining)
