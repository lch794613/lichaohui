from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.wait import WebDriverWait as wds
from selenium.webdriver.support import expected_conditions as ec
from bs4 import BeautifulSoup
import time


soup=BeautifulSoup(open('/Users/dly_lee/Downloads/pubg_.html'),'html.parser',from_encoding='utf-8')
point=soup.find(class_ = "value value-blue")
win_rate=soup.find(class_ = "value value-green")
kda=soup.find(class_ = "value value-red")
all=soup.findAll(class_ = "value")
print(all[1].string)


i=1
data=''
print(len(all))
for info in all:
    str=all[i].string
    if ',' in str:
        str=str.replace(',','')
    data=data+str+','
    i+=1
    if i>len(all)-1:
        break
data='lch'+","+data
print(data)