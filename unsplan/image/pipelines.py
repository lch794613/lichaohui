# -*- coding: utf-8 -*-

# Define your item pipelines here
#
# Don't forget to add your pipeline to the ITEM_PIPELINES setting
# See: http://doc.scrapy.org/en/latest/topics/item-pipeline.html
import requests
import os
import csv


class ImagePipeline(object):
    def __init__(self):
        dir_path='./unsplan'
        if not os.path.exists(dir_path):
            os.makedirs(dir_path)

        f=open('unsplan.csv','w')
        f.write('id,name,location,port,bio')
        f.write('\n\r')
        f.close()

    def process_item(self, item, spider):

        image_urls=item['image_urls']
        image=requests.get(image_urls)
        name=str(item['name'])
        id = str(item['id'])
        bio = str(item['bio'])
        bio=bio.replace(',','')
        loc=str(item['location'])
        loc=loc.replace(',','')
        pot=str(item['port_url'])
        pot=pot.replace(',','')

        strings='%s,%s,%s,%s,%s'%(id,name,loc,pot,image_urls)
        print(strings)
        with open('unsplan.csv','a')as f:

            f.write(strings+'\n')

        with open('./unsplan/'+name+'.png','wb')as im:
            im.write(image.content)
            print('下载'+name)
        return item

