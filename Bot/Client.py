import sys
import pygame
import webbrowser
from ExchangesConnect import Test
import time
def main(binance):
    filelog=open("TradeLog.txt","at",encoding="utf-8") #mở file TradeLog.txt để ghi lại quá trình trade
    pygame.init() #khởi tạo pygame
    screen=pygame.display.set_mode((646,431)) # khởi tạo màn hình
    pygame.display.set_caption('Bot Client') # đặt tiêu đề cho màn hình
    btc=round(binance.getBalance('BTC'),2) #lấy số dư các loại crypto
    eth=round(binance.getBalance('ETH'),3)
    bnb=round(binance.getBalance('BNB'),3)
    ltc=round(binance.getBalance('LTC'),1)
    usdt=round(binance.getBalance('USDT'),3)
    ex=pygame.image.load('surfer.png') #tải các hình ảnh có sẵn
    bitcoin=pygame.image.load('reshape/bitcoin.png')
    ethereum=pygame.image.load('reshape/ethereum.png')
    binimg=pygame.image.load('reshape/binance.png')
    litecoin=pygame.image.load('reshape/litecoin.png')
    urlBTC='https://testnet.binancefuture.com/en/futures/BTCUSDT' #liên kết đến các sàn giao dịch của từng loại crypto
    urlETH='https://testnet.binancefuture.com/en/futures/ETHUSDT'
    urlBNB='https://testnet.binancefuture.com/en/futures/BNBUSDT'
    urlLTC='https://testnet.binancefuture.com/en/futures/LTCUSDT'
    WHITE=(255,255,255) #đặt màu sắc
    YELLOW=(220,222,37)
    running=True #đăt các điều kiện chạy ( do pygame cần vòng lặp để vẽ lên màn hình)
    option1=False
    option2=False
    option3=False
    option4=False
    font=pygame.font.SysFont('Arial',20)#cài đặt font chữ 
    font2=pygame.font.SysFont('Arial',30)
            
    def renderPrice(nameCoin): # lấy giá trị của crypto hiện tại và độ biến thiên của nó so với ngày hôm qua
        price=binance.getPriceNow(nameCoin)
        xt=round((price-binance.average(nameCoin,1))/binance.average(nameCoin,1)*100,2)
        textprice=font2.render(str(price),True,WHITE)
        if xt>0:
            text_22=font2.render(str(xt),True,(11,255,16)) #nếu tăng thì độ biến thiên được hiển thị là màu xanh theo %
        else:
            text_22=font2.render(str(xt),True,(255,0,0)) # nếu giảm gì là màu đỏ
        return textprice,text_22
    def updateBalance(coinName):
        cryto=round(binance.getBalance(coinName),2)
        newusdt=round(binance.getBalance('USDT'),3)
        text1=font.render(str(cryto),True,WHITE)
        text2=font.render('USDT: '+str(newusdt),True,WHITE)
        return text1,text2
    
    balanceUSDT=font.render('USDT: '+str(usdt),True,WHITE) # lấy số dư USDT trong ví
    #bắt đầu render các thành phần bên trên để có thể hiển thị trên màn hình
    text_1=font.render('BTC',True,WHITE) 
    text_11=font.render(str(btc),True,WHITE)
    text_13,text_12=renderPrice('BTC')
    text_2=font.render('ETH',True,WHITE)
    text_21=font.render(str(eth),True,WHITE)
    text_23,text_22=renderPrice('ETH')
    text_3=font.render('BNB',True,WHITE)
    text_31=font.render(str(bnb),True,WHITE)
    text_33,text_32=renderPrice('BNB')
    text_4=font.render('LTC',True,WHITE)
    text_41=font.render(str(ltc),True,WHITE)
    text_43,text_42=renderPrice('LTC')
    back_text=font.render('BACK',True,(0,0,0))
    back_text2=font.render('Back',True,WHITE)
    trading_text=font.render('Trading is in progress....',True,(0,0,0))
    detail_price_text=font.render("Detail Price",True,YELLOW)

    #lay gia cua dong coin quy doi sang USDT + so USDT hien co (nhằm vẽ đồ thị the hien tuong quan)
    def getUSDT(coinName):
        return binance.getPriceNow(coinName)*binance.getBalance(coinName) + binance.getBalance('USDT')
    #ve do thi
    def mychart(chartList):
        for i in range(0,len(chartList)-2):
            pygame.draw.line(screen,(0,0,0),(chartList[i][0],chartList[i][1]),(chartList[i+1][0],chartList[i+1][1]),width=3)
   
    firstBTC=getUSDT('BTC')
    firstETH=getUSDT('ETH')   #tong gia tri ban dau cua dong tien
    firstBNB=getUSDT('BNB')
    firstLTC=getUSDT('LTC')
    
    while running:
        x1=33 #gia tri ban dau cua toa do x cua bieu do
        yreal=215 #gia tri ban dau cua toa do y cua bieu do
        chartList=[[33,215]]
        tick=0
        lct=10 #lct la gia tri bien thien cua toa do x 
        while option1:
            time.sleep(0.1)
            tick+=1
            if tick % 600==0: # cu moi 60s se thuc hien giao dich               
                filelog.write(binance.orderCoin('BTC')+'\n') #giao dịch
                x1_change=lct
                y1=round(2.15*(getUSDT('BTC')-firstBTC),3)
                x1+=x1_change
                yreal=215-y1
                charHead=[]
                charHead.append(x1)
                charHead.append(yreal)
                chartList.append(charHead) #cập nhật biểu đồ sau khi giao dịch
                if chartList[0][0]<=30: # nếu biểu đồ đã chạm đến biên trái thì xóa phần đã chạm 
                    del chartList[0]
                if x1>520:# nếu gần chạm đến biên phải thì biểu đồ không tiến về bên phải nữa mà cập nhật mỗi biến động 
                    lct=0
                    for i in range(0,len(chartList)-2):
                        chartList[i][0]-=10
                        chartList[i][1]=chartList[i+1][1]
                
            screen.fill(WHITE) #xóa trắng màn hình
            #xây dựng các nút bấm trong màn hình trade
            screen.blit(trading_text,(228,366)) 
            x_mount,y_mount=pygame.mouse.get_pos()
            screen.blit(back_text,(72,12))
            if 69 <x_mount<150 and 10<y_mount<39:
                pygame.draw.rect(screen,(0,0,0),(59,10,81,29),border_radius=10) 
                screen.blit(back_text2,(72,12))
                
            
            mychart(chartList)#ve bieu do
            #xử lí sự kiện
            for event in pygame.event.get():
                if event.type==pygame.QUIT:
                    pygame.quit()
                    sys.exit()
                if event.type==pygame.MOUSEBUTTONDOWN:
                    if 69 <x_mount<150 and 10<y_mount<39:
                        if event.button==1:
                            option1=False
                            text_11,balanceUSDT=updateBalance('BTC')
                         
                    
            pygame.display.flip()
        
        while option2: # được xây dựng tương tự như option1 nhưng đối với ETH
            screen.fill(WHITE)
            screen.blit(trading_text,(228,366))
            x_mount,y_mount=pygame.mouse.get_pos()
            screen.blit(back_text,(72,12))
            if 69 <x_mount<150 and 10<y_mount<39:
                pygame.draw.rect(screen,(0,0,0),(59,10,81,29),border_radius=10)
                screen.blit(back_text2,(72,12))
            time.sleep(0.1)
            tick+=1
            if tick % 600==0:              
                filelog.write(binance.orderCoin('ETH')+'\n')
                x1_change=lct
                y1=round(1.15*(getUSDT('ETH')-firstETH),3)
                x1+=x1_change
                yreal=215-y1
                charHead=[]
                charHead.append(x1)
                charHead.append(yreal)
                chartList.append(charHead)
                if chartList[0][0]<=30:
                    del chartList[0]
                if x1>520:
                    lct=0
                    for i in range(0,len(chartList)-2):
                        chartList[i][0]-=10
                        chartList[i][1]=chartList[i+1][1]

            mychart(chartList)
            for event in pygame.event.get():
                if event.type==pygame.QUIT:
                    pygame.quit()
                    sys.exit()
                if event.type==pygame.MOUSEBUTTONDOWN:
                    if 69 <x_mount<150 and 10<y_mount<39:
                        if event.button==1:
                            option2=False
                            text_21,balanceUSDT=updateBalance('ETH')
            pygame.display.flip()
        while option3:# được xây dựng tương tự như option1 nhưng đối với BNB
            screen.fill(WHITE)
            screen.blit(trading_text,(228,366))
            x_mount,y_mount=pygame.mouse.get_pos()
            screen.blit(back_text,(72,12))
            if 69 <x_mount<150 and 10<y_mount<39:
                pygame.draw.rect(screen,(0,0,0),(59,10,81,29),border_radius=10)
                screen.blit(back_text2,(72,12))
            tick+=1
            time.sleep(0.1)
            if tick % 600==0:               
                filelog.write(binance.orderCoin('BNB')+'\n')              
                x1_change=lct
                y1=round(1.15*(getUSDT('BNB')-firstBNB),3)
                x1+=x1_change
                yreal=215-y1
                charHead=[]
                charHead.append(x1)
                charHead.append(yreal)
                chartList.append(charHead)
                if chartList[0][0]<=30:
                    del chartList[0]
                if x1>520:
                    lct=0
                    for i in range(0,len(chartList)-2):
                        chartList[i][0]-=10
                        chartList[i][1]=chartList[i+1][1]
            mychart(chartList)
            for event in pygame.event.get():
                if event.type==pygame.QUIT:
                    pygame.quit()
                    sys.exit()
                if event.type==pygame.MOUSEBUTTONDOWN:
                    if 69 <x_mount<150 and 10<y_mount<39:
                        if event.button==1:
                            option3=False
                            text_31,balanceUSDT=updateBalance('BNB')
            pygame.display.flip()
        while option4: # được xây dựng tương tự như option1 nhưng đối với LTC
            screen.fill(WHITE)
            screen.blit(trading_text,(228,366))
            x_mount,y_mount=pygame.mouse.get_pos()
            screen.blit(back_text,(72,12))
            if 69 <x_mount<150 and 10<y_mount<39:
                pygame.draw.rect(screen,(0,0,0),(59,10,81,29),border_radius=10)
                screen.blit(back_text2,(72,12))
            tick+=1
            time.sleep(0.1)
            if tick % 600==0:
                filelog.write(binance.orderCoin('LTC')+'\n')             
                x1_change=lct
                y1=round(1.15*(getUSDT('LTC')-firstLTC),3)
                
                x1+=x1_change
                yreal=215-y1
                charHead=[]
                charHead.append(x1)
                charHead.append(yreal)
                chartList.append(charHead)
                if chartList[0][0]<=30:
                    del chartList[0]
                if x1>520: 
                    lct=0
                    for i in range(0,len(chartList)-2):
                        chartList[i][0]-=10
                        chartList[i][1]=chartList[i+1][1]
            mychart(chartList)
            for event in pygame.event.get():
                if event.type==pygame.QUIT:
                    pygame.quit()
                    sys.exit()
                if event.type==pygame.MOUSEBUTTONDOWN:
                    if 69 <x_mount<150 and 10<y_mount<39:
                        if event.button==1:
                            option4=False
                            text_41,balanceUSDT=updateBalance('LTC')

            pygame.display.flip()

        #đây là màn hình chính
        screen.fill(WHITE)
        screen.blit(ex,(0,0))
        x_mount,y_mount=pygame.mouse.get_pos()
        if 69<x_mount<159 and 77<y_mount<131: #vẽ button để vào option1
            pygame.draw.rect(screen,(255,255,255),((69,74,115,55)),width=2,border_radius=10)
            screen.blit(text_11,(134,91))
            screen.blit(bitcoin,(324,37))
            screen.blit(text_12,(480,323))
            screen.blit(text_13,(360,323))
            
        else:
            pygame.draw.rect(screen,(255,255,255),((69,74,89,55)),width=2,border_radius=10)
            screen.blit(detail_price_text,(200,89.3))
        if 69<x_mount<159 and 160<y_mount<209:#vẽ button để vào option2
            pygame.draw.rect(screen,(255,255,255),((69,160,135,55)),width=2,border_radius=10)
            screen.blit(text_21,(134,178))
            screen.blit(ethereum,(324,37))
            screen.blit(text_22,(480,323))
            screen.blit(text_23,(360,323))
        else:
            pygame.draw.rect(screen,(255,255,255),((69,160,89,55)),width=2,border_radius=10)
            screen.blit(detail_price_text,(200,174))
        if 69<x_mount<159 and 261<y_mount<316:#vẽ button để vào option3
            pygame.draw.rect(screen,(255,255,255),((69,246,125,55)),width=2,border_radius=10)
            screen.blit(text_31,(134,265))
            screen.blit(binimg,(324,37))
            screen.blit(text_32,(480,323))
            screen.blit(text_33,(360,323))
        else:
            pygame.draw.rect(screen,(255,255,255),((69,246,89,55)),width=2,border_radius=10)
            screen.blit(detail_price_text,(200,259.3))
        if 69<x_mount<159 and 332<y_mount<387: #vẽ button để vào option4
            pygame.draw.rect(screen,(255,255,255),((69,332,115,55)),width=2,border_radius=10)
            screen.blit(text_41,(134,350))
            screen.blit(litecoin,(324,37))
            screen.blit(text_42,(480,323))
            screen.blit(text_43,(360,323))
        else:
            pygame.draw.rect(screen,(255,255,255),((69,332,89,55)),width=2,border_radius=10)
            screen.blit(detail_price_text,(200,350))
            
        screen.blit(text_1,(95,91))
        screen.blit(text_2,(95,178))
        screen.blit(text_3,(95,265))
        screen.blit(text_4,(95,350))
        screen.blit(balanceUSDT,(265,17))
        
        
        for event in pygame.event.get():
            if event.type==pygame.QUIT:
                running=False
                pygame.quit()
                sys.exit()
            if event.type == pygame.MOUSEBUTTONDOWN:
                if 69<x_mount<159 and 77<y_mount<131:    #sự kiện click chuột để vào option1       
                    if event.button==1 :
                        option1=True
                        
                if 69<x_mount<159 and 160<y_mount<209:  #sự kiện click chuột để vào option2     
                    if event.button==1:
                        option2=True
                    
                if 69<x_mount<159 and 261<y_mount<316: #sự kiện click chuột để vào option3     
                    if event.button==1:
                        option3=True
                        
                if 69<x_mount<159 and 332<y_mount<387: #sự kiện click chuột để vào option4    
                    if event.button==1:
                        option4=True
                if 200<x_mount<294 and 90<y_mount<110:  #sự kiện click chuột để vào mở liên kết đến sàn giao dịch BTC  
                    if event.button==1:
                        webbrowser.open(urlBTC,1)
                if 200<x_mount<294 and 175<y_mount<195:  #sự kiện click chuột để vào mở liên kết đến sàn giao dịch ETH
                    if event.button==1:
                        webbrowser.open(urlETH,1)
                if 200<x_mount<294 and 259<y_mount<280: #sự kiện click chuột để vào mở liên kết đến sàn giao dịch BNB
                    if event.button==1:
                        webbrowser.open(urlBNB,1)
                if 200<x_mount<294 and 350 <y_mount<370: #sự kiện click chuột để vào mở liên kết đến sàn giao dịch LTC
                    if event.button==1:
                        webbrowser.open(urlLTC,1)
                    

        pygame.display.flip()

    pygame.quit()

        