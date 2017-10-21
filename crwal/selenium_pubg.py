from selenium import webdriver
from selenium.webdriver.support.wait import WebDriverWait
from selenium.webdriver.support import expected_conditions as ec
from selenium.webdriver.common.by import By
from selenium.common.exceptions import TimeoutException as te
from bs4 import BeautifulSoup as bs

wd=webdriver.PhantomJS()
wait=WebDriverWait(wd,10)
wd.set_page_load_timeout(20)

serv=['eu','as','na','oc','sa','sea']
url='https://pubg.me/players/rating?season=2017-pre4&region=%s&match=all'
name_url='https://pubg.me/player/%s/squad?season=2017-pre4&region=%s'
file= open('pubg.csv', 'w')
file.write('name,point,rank,Win Rate,Wins,Win Rank,Kill / Death Ratio,Kills,'
           'Kill Rank,macth played,daliy logins,avg distance travelled,'
           'Total Distance Travelled,AVG Distance On Foot,AVG Distance In Vehicle,'
           'Weapons Acquired,Vehicles Destroyed,Total Damage Dealt,Top 10 Rate,'
           'Top 10s,AVG Survival Time,Longest Time Survived,Total Heals,Total Boosts,'
           'Team Kills,Suicides,Revives,AVG Damage Per Match,Most Kills In Match,'
           'Assists,KDA,Headshots,Headshot Kill Ratio,Longest Kill,Road Kills,Best Kill Streak,'
           'DBNO')
#'\n' 换行
for _ in serv:
    new_url=url%_
    try:
        wd.get(new_url)
    except Exception:
        print('time out after 30 seconds when loading page')
        wd.execute_script('window.stop()')

    source=wd.page_source
    soup=bs(source,'html.parser')
    names=soup.find_all(class_ = "sidebar-user-name")#name的合集 需要.sting来提取内容
    for name in names:
        new_name_url=name_url%(name.string,_)
        try:
            wd.get(new_name_url)
        except Exception:
            print('time out after 30 seconds when loading page')
            wd.execute_script('window.stop()')
        source = wd.page_source
        soup = bs(source, 'html.parser')
        datas=soup.findAll(class_="value")

        i = 1
        data = ''
        for info in datas:
            str = datas[i].string
            if ',' in str:
                str = str.replace(',', '')
            data = data + str + ','
            i += 1
            if i > len(datas) - 1:
                break
        data=name.string+","+data
        file.write('\n')
        file.write(data)
        print(data)





file.close()










