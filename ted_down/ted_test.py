import requests
from bs4 import BeautifulSoup
import re
import urllib.request

def find_ted():
    num=input("输入要查找的ted的页码 1-72 因为官网只有72页 可能以后会增加")
    url_page='https://www.ted.com/talks?page=%s'%num
    ted_page=requests.get(url_page)
    conts=ted_page.content
    soup=BeautifulSoup(conts,"html.parser")
    cont=soup.findAll(attrs={"data-ga-context":"talks"})
    i=0
    for _ in cont:
        i=i+1
        if i%2==0:
            names = _['href'].split(r"/")
            name = names[2].split("_")
            real_name = name[2:]
            fullname = ""
            for _ in real_name:
                fullname = fullname + _ + "_"
            print("page:"+str(int(i/2))+" is "+fullname)
    print("一共"+str(int(i/2))+"个视频")
    return cont


def choice():
    try:
        num = input("请输入1表示单个下载 输入2表示批量下载 输入3表示返回上一级重新选择")
        if int(num)==1:
            num=input("请输入要下载的页面 如有多个请用逗号分隔")
            pages=num.split(",")
            return pages

        elif int(num)==2:
            num=input("请输入要下载的页面 格式如下a-b,c-d")
            new_page=[]
            pages =num.split(",")
            for page in pages:
                page2=page.split("-")
                for _ in range(int(page2[0]),int(page2[1])+1):
                    new_page.append(_)
            return new_page
        elif int(num) == 3:
            find_ted()
            choice()
        else:
            print("输入错误请重新输入")
            choice()
    except:
        print("输入错误请重新输入")
        choice()

donwload_url=''
def download(conts,page):
    global donwload_url
    try:
        page=int(page)
    except:
        print("发现输入的编号有问题 请重新输入")
        choice()
    raw_url=conts[page*2]['href']
    print("你要下载的页面是: "+raw_url)
    names=raw_url.split(r"/")
    name=names[2].split("_")
    real_name=name[2:]
    fullname=""
    for _ in real_name:
        fullname=fullname+_+"_"
    print("要下载的视频是: "+fullname)
    url='https://www.ted.com%s'%(raw_url)
    response=requests.get(url)
    cont=response.content
    soup=BeautifulSoup(cont,"html.parser")
    element=soup.findAll("script")
    patter=re.compile('http.*?mp4.apikey=.*?"')
    stre=patter.findall(str(element))
    for _ in stre:
        if "1500k" in _:
            _=_.split('"')
            donwload_url=_[0]
            print("获得下载地址: "+donwload_url)

    print("开始下载")
    try:
        urllib.request.urlretrieve(donwload_url, filename=fullname + ".mp4", reporthook=Schedule)
        print("下载完成")
    except:
        print("下载出现问题 重新下载")
        download(cont,page)


pre=0
def Schedule(a,b,c):
    global pre
    per = 100.0 * a * b / c
    if int(per)-pre>0:
        print('%.2f%%' % per)
        pre=int(per)
    if pre=>100:
        pre=0



if __name__ == '__main__':
    conts=find_ted()
    pages=choice()
    print("你需要下载的内容编号一共是:")
    print(pages)
    for page in pages:
        download(conts,page)
