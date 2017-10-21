from scrapy import cmdline
cmdline.execute('scrapy crawl doubanbook -o douban2.csv -t csv'.split())