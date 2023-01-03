import sys
import pygame
import webbrowser
from ExchangesConnect import Test
import time
def main(binance):
    filelog=open("TradeLog.txt","at",encoding="utf-8")
    pygame.init()
    screen=pygame.display.set_mode((646,431))
    pygame.display.set_caption('Bot Client')
    btc=round(binance.getBalance('BTC'),2)
    eth=round(binance.getBalance('ETH'),3)
    bnb=round(binance.getBalance('BNB'),3)
    ltc=round(binance.getBalance('LTC'),1)
    usdt=round(binance.getBalance('USDT'),3)
    ex=pygame.image.load('surfer.png')
    bitcoin=pygame.image.load('reshape/bitcoin.png')
    ethereum=pygame.image.load('reshape/ethereum.png')
    binimg=pygame.image.load('reshape/binance.png')
    litecoin=pygame.image.load('reshape/litecoin.png')
    urlBTC='https://testnet.binancefuture.com/en/futures/BTCUSDT'
    urlETH='https://testnet.binancefuture.com/en/futures/ETHUSDT'
    urlBNB='https://testnet.binancefuture.com/en/futures/BNBUSDT'
    urlLTC='https://testnet.binancefuture.com/en/futures/LTCUSDT'
    WHITE=(255,255,255)
    YELLOW=(220,222,37)
    running=True
    option1=False
    option2=False
    option3=False
    option4=False
    font=pygame.font.SysFont('Arial',20)
    font2=pygame.font.SysFont('Arial',30)
            
    def renderPrice(nameCoin): # lay gia hien tai cua dong coin va do thay doi gia tri cua no
        price=binance.getPriceNow(nameCoin)
        xt=round((price-binance.average(nameCoin,1))/binance.average(nameCoin,1)*100,2)
        textprice=font2.render(str(price),True,WHITE)
        if xt>0:
            text_22=font2.render(str(xt),True,(11,255,16))
        else:
            text_22=font2.render(str(xt),True,(255,0,0))
        return textprice,text_22

    
    balanceUSDT=font.render('USDT: '+str(usdt),True,WHITE)
    
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
    #lay gia cua dong coin quy doi sang USDT + so USDT hien co (nham ve do thi the hien tuong quan)
    def getUSDT(coinName):
        return binance.getPriceNow(coinName)*binance.getBalance(coinName) + binance.getBalance('USDT')
    #ve do thi
    def mychart(chartList):
        for i in range(0,len(chartList)-2):
            pygame.draw.line(screen,(0,0,0),(chartList[i][0],chartList[i][1]),(chartList[i+1][0],chartList[i+1][1]),width=3)
    x1=0 #gia tri ban dau cua toa do x cua bieu do
    yreal=215 #gia tri ban dau cua toa do y cua bieu do
    chartList=[]
    firstBTC=getUSDT('BTC')
    firstETH=getUSDT('ETH')   #tong gia tri ban dau cua dong tien
    firstBNB=getUSDT('BNB')
    firstLTC=getUSDT('LTC')
    lct=5 #lct la gia tri bien thien cua toa do x 
    while running:
        
        tick=0
        while option1:
            for event in pygame.event.get():
                if event.type==pygame.QUIT:
                    running=False
                    pygame.quit()
                    sys.exit()
        
        while option2:
            for event in pygame.event.get():
                if event.type==pygame.QUIT:
                    running=False
                    pygame.quit()
                    sys.exit()
        while option3:
            for event in pygame.event.get():
                if event.type==pygame.QUIT:
                    running=False
                    pygame.quit()
                    sys.exit()
        while option4:
            for event in pygame.event.get():
                if event.type==pygame.QUIT:
                    running=False
                    pygame.quit()
                    sys.exit()
        screen.fill(WHITE)
        screen.blit(ex,(0,0))
        x_mount,y_mount=pygame.mouse.get_pos()
        if 69<x_mount<159 and 77<y_mount<131:
            pygame.draw.rect(screen,(255,255,255),((69,74,115,55)),width=2,border_radius=10)
            screen.blit(text_11,(134,91))
            screen.blit(bitcoin,(324,37))
            screen.blit(text_12,(480,323))
            screen.blit(text_13,(360,323))
            
        else:
            pygame.draw.rect(screen,(255,255,255),((69,74,89,55)),width=2,border_radius=10)
            screen.blit(detail_price_text,(200,89.3))
        if 69<x_mount<159 and 160<y_mount<209:
            pygame.draw.rect(screen,(255,255,255),((69,160,135,55)),width=2,border_radius=10)
            screen.blit(text_21,(134,178))
            screen.blit(ethereum,(324,37))
            screen.blit(text_22,(480,323))
            screen.blit(text_23,(360,323))
        else:
            pygame.draw.rect(screen,(255,255,255),((69,160,89,55)),width=2,border_radius=10)
            screen.blit(detail_price_text,(200,174))
        if 69<x_mount<159 and 261<y_mount<316:
            pygame.draw.rect(screen,(255,255,255),((69,246,125,55)),width=2,border_radius=10)
            screen.blit(text_31,(134,265))
            screen.blit(binimg,(324,37))
            screen.blit(text_32,(480,323))
            screen.blit(text_33,(360,323))
        else:
            pygame.draw.rect(screen,(255,255,255),((69,246,89,55)),width=2,border_radius=10)
            screen.blit(detail_price_text,(200,259.3))
        if 69<x_mount<159 and 332<y_mount<387:
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
                if 69<x_mount<159 and 77<y_mount<131:           
                    if event.button==1 :
                        option1=True
                        
                if 69<x_mount<159 and 160<y_mount<209:
                    if event.button==1:
                        option2=True
                    
                if 69<x_mount<159 and 261<y_mount<316:
                    if event.button==1:
                        option3=True
                        
                if 69<x_mount<159 and 332<y_mount<387:
                    if event.button==1:
                        option4=True
                if 200<x_mount<294 and 90<y_mount<110:
                    if event.button==1:
                        webbrowser.open(urlBTC,1)
                if 200<x_mount<294 and 175<y_mount<195:
                    if event.button==1:
                        webbrowser.open(urlETH,1)
                if 200<x_mount<294 and 259<y_mount<280:
                    if event.button==1:
                        webbrowser.open(urlBNB,1)
                if 200<x_mount<294 and 350 <y_mount<370:
                    if event.button==1:
                        webbrowser.open(urlLTC,1)
                    

        pygame.display.flip()

    pygame.quit()
if __name__=='__main__':
    binance=Test('kRRAig0cXzJBznFUHNDpKoXwn3Qrv7NKq6yRYhin4ULsCpE9NLJoMU9vWSirDCRb','1Yz7D2Q2eDWx2fX1W1BsVoUpmDWOoYWSsnPTjuTn5Pf91PiCaIUEAFxqWkkIEhHW')
    main(binance)
        