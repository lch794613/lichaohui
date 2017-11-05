import requests
from bs4 import BeautifulSoup
import re

url2='https://www.lagou.com/jobs/list_java?city=%E6%9D%AD%E5%B7%9E&cl=false&fromSearch=true&labelWords=&suginput='
data2={'User-Agent':'Mozilla/5.0 (Macintosh; Intel Mac OS X 10.12; rv:56.0) Gecko/20100101 Firefox/56.0'}
headers={'User-Agent':'Mozilla/5.0 (Macintosh; Intel Mac OS X 10.12; rv:56.0) Gecko/20100101 Firefox/56.0'}
cookie = '_ga=GA1.2.887930916.1508681739; Hm_lvt_4233e74dff0ae5bd0a3d81c6ccf756e6=1508681739,1509879365; user_trace_token=20171022221541-7c82227c-b733-11e7-a468-525400f775ce; LGUID=20171022221541-7c822566-b733-11e7-a468-525400f775ce; index_location_city=%E6%9D%AD%E5%B7%9E; JSESSIONID=ABAAABAAAFCAAEG1E655B24DF124850EA6D66C27979192F; _gid=GA1.2.1191998157.1509879365; Hm_lpvt_4233e74dff0ae5bd0a3d81c6ccf756e6=1509885394; LGRID=20171105203635-f5c2204c-c225-11e7-9793-5254005c3644; TG-TRACK-CODE=index_search; SEARCH_ID=6b9f86b86fe049bda6570656ee66dd2e; LGSID=20171105202546-730bf095-c224-11e7-9793-5254005c3644; PRE_UTM=; PRE_HOST=; PRE_SITE=https%3A%2F%2Fwww.lagou.com%2F; PRE_LAND=https%3A%2F%2Fwww.lagou.com%2Fjobs%2Flist_python%3FlabelWords%3D%26fromSearch%3Dtrue%26suginput%3D; X_HTTP_TOKEN=3cd16d0817ae31f2f8d024e19e2cc22a; _putrc=7FC8459CB863B87B; login=true; unick=%E6%9D%8E%E6%9C%9D%E8%BE%89; showExpriedIndex=1; showExpriedCompanyHome=1; showExpriedMyPublish=1; hasDeliver=0; _gat=1'
cookie_dict = dict((line.split('=') for line in cookie.strip().split(";")))

url=[]
name=[]
salary=[]
years=[]
school=[]

page_max=30
page=1
first='true'
params={'first':first,
        'pn':str(page),
        'kd	':'java'}
def crawl():
    res2=requests.post(url2,params=params,headers=headers,cookies=cookie_dict)
    conts=res2.text
    soup=BeautifulSoup(conts,'html.parser')

    cont=soup.findAll("a",class_="position_link")
    p=re.compile('https.*?html')
    urls=p.findall(str(cont))
    for _ in urls:
        url.append(_)

    company_name = soup.findAll(attrs={'data-company':True})
    for _ in company_name:
        name.append(_['data-company'])

    company_name = soup.findAll(attrs={'data-company':True})

    p=re.compile('<span class="salary fr">.*?</span>')
    salarys=p.findall(str(company_name))

    for _ in salarys:
        salary.append(_[24:-7])

    p=re.compile('<span>.*?</span>')
    salarys=p.findall(str(company_name))
    p=re.compile('>.*?<')

    for _ in salarys[::5]:
        years.append(str(p.findall(str(_)))[3:-3])

    for _ in salarys[1::5]:
        school.append(str(p.findall(str(_)))[3:-3])


for _ in range(page_max):
    crawl()
    print("爬完一页:" + str(page))
    page+=1
    first ='flase'



with open("lagou_java.csv",'a') as f:
    f.write('index,name,salary,years,school,url')
    f.write('\n')
    for _ in range(len(url)):
        f.write('%s,%s,%s,%s,%s,%s'%(_+1,name[_],salary[_],years[_],school[_],url[_]))
        f.write('\n')

