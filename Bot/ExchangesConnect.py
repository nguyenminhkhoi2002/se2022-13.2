import ccxt as cx
import time

class Test:
    
    def __init__(self,api,secret):

        self.binance = cx.binance({'apiKey':api,'secret':secret})
        self.binance.set_sandbox_mode(True)
    #xem gia thi truong
    def getPrice(self,nameCoin,timeAgo=0,timefame='1m',lim=10):
        nameCoin=nameCoin+'/USDT'
        responseAgo=self.binance.fetch_ohlcv(nameCoin, timefame,since=(int(time.time()*1000-timeAgo*24*60*60*1000-600000)),limit=lim)#lay gia cua n ngay truoc
        for i in responseAgo:
            i[0]=time.ctime(i[0]/1000)
        return responseAgo

    #xem tien trong vi
    def getBalance(self,nameCoin):
        balance=self.binance.fetch_balance()
        total=balance['total']
        return total[nameCoin]
    #Time: ','Open_price: ','Highest_price: ','lowest_price: ','close_price: ','Volume: '
    def average(self,nameCoin,timeago=0,limit=24): #gia trung binh cua ngay hom truoc
        res=self.getPrice(nameCoin,timeago,timefame='1h',lim=limit)
        sum=0
        for i in res:
                sum=sum+i[4]
        avera=sum/limit
        return avera
    def getPriceNow(self,NameCoin):#lay gia tri hien tai
        res=self.getPrice(NameCoin,0,timefame='1m',lim=1)
        return res[-1][4] 


    #order=binance.create_market_order('ETH/USDT',direc,5)
    def specify(self,coinName):
        if self.getPriceNow(coinName)>self.average(coinName,1):
            return 'buy'
        else:
            return 'sell'
    #hàm dùng để giao dịch
    def orderCoin(self,coinName):
        currentPrice=self.getPriceNow(coinName)
        coinName2=coinName+'/USDT'
        amount=self.getBalance(coinName)/200
        
        if currentPrice>self.average(coinName,1):
            self.binance.create_market_order(coinName2,'buy',amount)
            return time.ctime()+" "+'buy'+" "+str(amount)+" "+coinName+" at "+str(currentPrice) +"$"
        else:
            self.binance.create_market_order(coinName2,'sell',amount)
            return time.ctime()+" "+'sell'+" "+str(amount)+" "+coinName+" at "+str(currentPrice) +"$"

       
        

    

