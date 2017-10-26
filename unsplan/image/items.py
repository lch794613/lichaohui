# -*- coding: utf-8 -*-

# Define here the models for your scraped items
#
# See documentation in:
# http://doc.scrapy.org/en/latest/topics/items.html

import scrapy


class ImageItem(scrapy.Item):
    id = scrapy.Field()
    name = scrapy.Field()
    bio = scrapy.Field()
    image_urls = scrapy.Field()
    location = scrapy.Field()
    port_url=scrapy.Field()
