# -*- coding: utf-8 -*-
import scrapy
from douban.items import DoubanItem

import re


class DoubanbookSpider(scrapy.Spider):
    name = 'doubanbook'
    #allowed_domains = ['douban.com']
    start_urls = ['https://book.douban.com/top250']


    def parse(self, response):
        datas=response.xpath('//tr[@class="item"]')
        item=DoubanItem()
        sum=0
        print(sum)
        for items in datas:
            sum+=1
            item['rank']=sum
            item['title']=items.xpath('td[@valign="top"]/div[@class="pl2"]/a/text()').extract()[0].strip()
            #item['title']= title.replace(' ', '').replace('\n', '')
            item['rate'] = items.xpath('td[@valign="top"]/div[@class="star clearfix"]/span[@class="rating_nums"]/text()').extract()
            print(item['rate'])
            item['quote'] = items.xpath('td[@valign="top"]/p[@class="quote"]/span[@class="inq"]/text()').extract()[0].strip()
            print(item['quote'])
            authors= items.xpath('td[@valign="top"]/p[@class="pl"]/text()').extract()
            author=authors[0].split('/')
            item['author']=author[0]
            yield item

            next_page=response.xpath('//span[@class="next"]/a/@href').extract()
            if next_page:
                print(next_page[0])
            yield scrapy.Request(next_page[0],self.parse)







        pass
