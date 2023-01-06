from tkinter import*
import Client
from ExchangesConnect import *
screen=Tk() #khởi tạo màn hình đăng nhập    
screen.geometry("450x200") #cài đặt kích thước cửa sổ
screen.title("Login Screen") #cài đặt tiêu đề cho cửa sổ
str=StringVar(screen) #khai báo dữ liệu nhập vào
strPass=StringVar(screen)
def changescr(): #hàm chuyển màn hình và khởi tạo đối tượng binance để kết nối với sàn giao dịch
   try:
     screen.destroy()
     binance = Test(str.get(),strPass.get())
     Client.main(binance)
   except Exception:
        ex="Check you infor or connection"
b=Button(screen,text="Login",activebackground="#83838B",command=changescr).place(x=173,y=127) #vẽ nút bấm
apiEntry=Entry(screen,width=40,textvariable=str).place(x=73,y=32) # vẽ hợp thoại nhận dữ liệu
passEntry=Entry(screen,width=40,textvariable=strPass,show="*",highlightbackground="black").place(x=73,y=62)
apiKey=Label(screen,text="ApiKeys",).place(x=24,y=32) #vẽ label hướng dẫn nhập dữ liệu
apiSecret=Label(screen,text="Secret").place(x=24,y=62)
screen.mainloop()