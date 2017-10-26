# -*- coding: utf-8 -*-
import scrapy
import json
import requests
from  image.items import ImageItem
class ImagecrawlSpider(scrapy.Spider):
    name = 'imagecrawl'

    element=['men','girl','nature','love','space','woman','face','city','couple','people','work','male','guy',
             'person','shirt','hand','caucasian','art','lady']


    fist_url = 'https://unsplash.com/napi/search/photos?query=caucasian&xp=&per_page=20&page=2'
    page=2
    id=0
    index=16

    head = {'authorization': 'Client-ID c94869b36aa272dd62dfaeefed769d4115fb3189a9d1ec88ed457207747be626'}

    def start_requests(self):

        return [scrapy.http.FormRequest(self.fist_url,headers=self.head,method='GET',callback=self.parse)]

    def parse(self, response):
        items=ImageItem()
        dir=json.loads(response.body)
        total_pages=dir['total_pages']
        results=dir['results']
        for _ in range(0,len(results)):
            self.id+=1
            index=results[_]
            urls=index['urls']
            user=index['user']
            items['bio']=user['bio']
            items['name']=user['name']
            items['id']=self.id
            items['image_urls']=urls['regular']
            items['location']=user['location']
            items['port_url']=user['portfolio_url']
            yield items


        url='https://unsplash.com/napi/search/photos?query=%s&xp=&per_page=20&page=%s'
        if self.page < total_pages:
            self.page+=1
            next_page=url%(self.element[self.index],self.page)
            yield scrapy.http.FormRequest(next_page,headers=self.head,method='GET',callback=self.parse)
        else:
            if self.index<len(self.element):
                self.index+=1
                self.page=2
                next_page = url % (self.element[self.index], self.page)
                yield scrapy.http.FormRequest(next_page,headers=self.head,method='GET',callback=self.parse)

