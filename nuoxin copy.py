import requests
from bs4 import BeautifulSoup
import pandas as pd
import time
import re

""" 
    id = 1636 是 1.31号
    id = 1630 是 1.30号
    id = 1000 是 3.26号格式未变
    id = 692 开始 5.16号 格式变了
"""

id = '1636'
url = 'http://www.sznuoxin.cn/?bj-%s.html'%id


def create_data_frame():
    df = pd.DataFrame([], columns=['type', 'name', 'model', 'color', 'price', 'dealer', 'time', 'ctime'])

    t = time.time()

    data = {'type': '', 'name': '', 'model': '', 'color': '', 'price': '', 'dealer': '诺信', 'time': '', 'ctime': t}

    return df,data

def get_data_by_url(url,df,data):

    res = requests.get(url)
    soup = BeautifulSoup(res.text, 'html.parser')

    date = soup.find('font', attrs={'size': '5'}).get_text()
    index = date.find('日')
    date = date[:index+1]
    data['time'] = date
    bj_content = soup.find('div', id='bj-content')
    divs = bj_content.children
    for _, div in enumerate(divs):
        if _ == 2:
            data['type'] = '国行'
            tds = div.find_all('td')
            for a, td in enumerate(tds):
                if (a+1) % 3 == 1:
                    data['name'] = td.get_text()
                if (a+1) % 3 == 2:
                    data['model'], color = split_model(td.get_text())
                if (a+1) % 3 == 0:
                    price = td.get_text().split('/')
                    muti_price(df,data,color,price)
        if _ == 5:
            data['type'] = '三星'
            data['name'] = '三星水货'
            tds = div.find_all('td')
            for a, td in enumerate(tds):
                if (a + 1) % 3 == 1:
                    text = td.get_text()
                if (a + 1) % 3 == 2:
                    data['model'], color = split_model2(text,td.get_text())
                if (a + 1) % 3 == 0:
                    price = td.get_text().split('/')
                    muti_price(df, data, color, price)
        if _ == 8:
            for i, divv in enumerate(div.children):
                if i == 0:
                    data['type'] = '索尼'
                    data['name'] = '索尼水货'
                    tds = divv.find_all('td')
                    for a, td in enumerate(tds):
                        if (a + 1) % 3 == 1:
                            text = td.get_text()
                        if (a + 1) % 3 == 2:
                            data['model'], color = split_model2(text, td.get_text())
                        if (a + 1) % 3 == 0:
                            price = td.get_text().split('/')
                            muti_price(df, data, color, price)
                if i == 3:
                    data['type'] = 'apple国行'
                    data['name'] = '官换国行'
                    tds = divv.find_all('td')
                    for a, td in enumerate(tds):
                        if (a + 1) % 3 == 1:
                            text = td.get_text()
                        if (a + 1) % 3 == 2:
                            data['model'], color = split_model2(text, td.get_text())
                        if (a + 1) % 3 == 0:
                            price = td.get_text().split('/')
                            muti_price(df, data, color, price)

                if i == 6:
                    data['type'] = '苹果国行'
                    data['name'] = '苹果国行'
                    tds = divv.find_all('td')
                    for a, td in enumerate(tds):
                        if (a + 1) % 3 == 1:
                            text = td.get_text()
                        if (a + 1) % 3 == 2:
                            data['model'], color = split_model2(text, td.get_text())
                        if (a + 1) % 3 == 0:
                            price = td.get_text().split('/')
                            muti_price(df, data, color, price)
                if i == 8:
                    data['type'] = '港行apple'
                    data['name'] = 'ipad'
                    tds = divv.find_all('td')
                    for a, td in enumerate(tds):
                        if (a + 1) % 3 == 1:
                            text = td.get_text()
                        if (a + 1) % 3 == 2:
                            data['model'], color = split_model2(text, td.get_text())
                        if (a + 1) % 3 == 0:
                            price = td.get_text().split('/')
                            muti_price(df, data, color, price)
                if i == 9:
                    data['type'] = '港行apple'
                    data['name'] = 'ipad'
                    tds = divv.find_all('td')
                    for a, td in enumerate(tds):
                        if (a + 1) % 3 == 1:
                            text = td.get_text()
                        if (a + 1) % 3 == 2:
                            data['model'], color = split_model2(text, td.get_text())
                        if (a + 1) % 3 == 0:
                            price = td.get_text().split('/')
                            muti_price(df, data, color, price)
                if i == 11:
                    data['type'] = '港行apple'
                    data['name'] = 'iphone'
                    tds = divv.find_all('td')
                    for a, td in enumerate(tds):
                        if (a + 1) % 3 == 1:
                            text = td.get_text()
                        if (a + 1) % 3 == 2:
                            data['model'], color = split_model2(text, td.get_text())
                        if (a + 1) % 3 == 0:
                            price = td.get_text().split('/')
                            muti_price(df, data, color, price)

                if i == 12:
                    data['type'] = '港行apple'
                    data['name'] = 'iphone'
                    tds = divv.find_all('td')
                    for a, td in enumerate(tds):
                        if (a + 1) % 3 == 1:
                            text = td.get_text()
                        if (a + 1) % 3 == 2:
                            data['model'], color = split_model2(text, td.get_text())
                        if (a + 1) % 3 == 0:
                            price = td.get_text().split('/')
                            muti_price(df, data, color, price)




def split_model(name):
    name = name.replace(' ', ' ')
    name = name.replace('\n', '')
    if '色' in name:
        if 'G' in name:  #有色 有G
            pos = name.rfind('G')
            model = name[:pos+1]
            color = name[pos+1:]
            if hasNumbers(color): #color 分隔不正确
                model = model +color[:-2]
                color = color[-2:]
            return model, color
        if ' ' in name:
            model_colors = name.split(' ')
            color = model_colors.pop()
            if hasNumbers(color):
                a = re.findall(r'\d|\W', color)
                r = a[-1]
                pos = color.rfind(r)
                model = color[:pos + 2]
                color = color[pos + 2:]
                model = ''.join(model_colors[:-1]) + model
                return model, color
            return ' '.join(model_colors), color
        else:     #有色 没G
            return name[:-2], name[-2:]

    if '/' in name:
        if " " in name:
            model_colors = name.split(' ')
            if '全网' == model_colors[-1]:
                model_colors.pop()
                if '' == model_colors[-1]:
                    model_colors.pop()
            for i, _ in enumerate(reversed(model_colors)):
                if '/' in _:
                    if i != 0:
                        i = -(i+1)
                        colors = model_colors.pop(i)
                        if hasNumbers(colors):
                            model_colors = name.split(' ')
                            if hasNumbers(color):
                                a = re.findall(r'\d|\W', color)
                                r = a[-1]
                                pos = color.rfind(r)
                                model = color[:pos +2]
                                color = color[pos +2:]
                                model = ''.join(model2) + model
                                return model, color
                            return ' '.join(model_colors[:-1]), ' '.join(model_colors[-1:])
                        else:
                            color = colors.split('/')
                            return model_colors, color
                    i = -(i + 1)
                    colors = model_colors.pop(i)
                    model = model_colors
                    color = colors.split('/')
                    models, color = is_eq_len(model, color)
                    if hasNumbers(color[0]):
                        a = re.findall(r'\d|\W', color[0])
                        r = a[-1]
                        pos = color[0].rfind(r)
                        model = color[0][:pos +2]
                        color = color[0][pos +2:]
                        model = ''.join(models) + model
                        return model, color
                    return ' '.join(models), color
        if '）' in name:
            pos = name.rfind('）')
            model = name[:pos + 1]
            colors = name[pos + 1:]
            color = colors.split('/')
            model, color = is_eq_len(model, color)
            return model, color
        if ')' in name:
            pos = name.rfind(')')
            model = name[:pos + 1]
            colors = name[pos + 1:]
            color = colors.split('/')
            model, color = is_eq_len(model, color)
            return model, color
        if 'G' in name: #有G 没色
            pos = name.rfind('G')
            model = name[:pos+1]
            colors = name[pos+1:]
            color = colors.split('/')
            model, colors = is_eq_len(model, color)

            if hasNumbers(colors[0]): #color 分隔不正确
                 model = model +colors[0][:-2]
                 color = colors[0][-2:]

            return model, color
        else:
            pos = name.find('/')
            model = name[:pos - 1]
            colors = name[pos - 1:]
            color = colors.split('/')
            return model, color
    if ' ' in name:
        model_colors = name.split(' ')
        color = model_colors.pop()
        if '）' in color:
            pos = name.rfind('）')
            model = name[:pos + 1]
            color = name[pos + 1:]
            if 'G' in color:
                pos = name.rfind('G')
                model = name[:pos + 1]
                color = name[pos + 1:]
                return model, color
            return model, color
        if ')' in color:
            pos = name.rfind(')')
            model = name[:pos + 1]
            color = name[pos + 1:]
            if 'G' in color:
                pos = name.rfind('G')
                model = name[:pos + 1]
                color = name[pos + 1:]
                return model, color
            return model, color
        if 'G' in color:
            pos = name.rfind('G')
            model = name[:pos + 1]
            color = name[pos + 1:]
            return model, color
        if hasNumbers(color):
            a = re.findall(r'\d|\W', color)
            r = a[-1]
            pos = color.rfind(r)
            model = color[:pos + 2]
            color = color[pos + 2:]
            model = ''.join(model_colors[:-1]) + model
            return model, color
        return ' '.join(model_colors), color
    if '）' in name:
        pos = name.rfind('）')
        model = name[:pos + 1]
        color = name[pos + 1:]
        return model, color
    if ')' in name:
        pos = name.rfind(')')
        model = name[:pos + 1]
        color = name[pos + 1:]
        return model, color
    if 'G' in name:  # 有G 没色
        pos = name.rfind('G')
        model = name[:pos + 1]
        color = name[pos + 1:]
        return model, color
    else:
        return name, ''

def split_model2(name,name2):

    if '】' in name2:
        colors = re.findall(r'【.*】',name2)
        color = colors[0][1:-1]
        if '/' in color:
            color = color.split('/')
        name2 = name2.replace(colors[0],' ')
        model  = name +name2

        return model, color
    if "/" in name2:
        colors = name2.split('/')
        return name,colors
    else:
        return name+name2, ''


def is_eq_len(model, color):

    if len(color[0]) != len(color[1]):
        pre = color[0][:-len(color[1])]
        last = color[0][-len(color[1]):]
        if len(last) == 0:
            return model, color
        model = ' '.join(model) + pre
        color[0] = last
        return model, color
    return model, color


def hasNumbers(inputString):
    return bool(re.search(r'\d', inputString))

def muti_price(df,data,colors,price):

    if type(colors) != list:
        colors = colors.split("//")

    """ 单颜色"""
    if len(colors) == 1:

        data['color'] = colors[0]
        data['price'] = price[0]
        df.loc[df.shape[0] + 1] = data
        return
    """ 多颜色 不对称"""
    if len(colors) == len(price):

        for i,color in enumerate(colors):
            data['color'] = color
            data['price'] = price[i]
            df.loc[df.shape[0] + 1] = data

        return
    else:
        for i, color in enumerate(colors):
            data['color'] = color
            data['price'] = price[0]
            df.loc[df.shape[0] + 1] = data



if __name__ == '__main__':
    id = 1636
    for _ in reversed(range(id-1000,id)):
        url = 'http://www.sznuoxin.cn/?bj-%s.html' % _
        df, data = create_data_frame()
        get_data_by_url(url, df, data)
        df.to_excel('诺亚通信'+data['time']+'.xlsx', index=True, header=True, sheet_name=data['time'])
        print('完成爬取'+data['time'])