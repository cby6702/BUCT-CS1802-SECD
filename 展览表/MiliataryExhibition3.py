import requests
import bs4
from bs4 import BeautifulSoup
f = open("exhibition.txt","rt",encoding='utf-8')
for line in f.readlines():pass
i = eval(line[0:3])
f = open("exhibition.txt","a+",encoding='utf-8')
r = requests.get("http://www.jb.mil.cn/zlcl/jbcl/")
r.encoding = r.apparent_encoding
demo = r.text
soup = BeautifulSoup(demo,"html.parser")
for link in soup.find_all('div','basicDes'):
    if isinstance(link, bs4.element.Tag):
        i+=1
        f.write("{}|".format(i))
        f.write("军事博物馆|")
        f.write("{}|".format(link.find('h3').string))
        f.write("{}\n".format(link.find('p').string))
r = requests.get("http://www.jb.mil.cn/zlcl/jdcl/")
r.encoding = r.apparent_encoding
demo = r.text
soup = BeautifulSoup(demo,"html.parser")
for link in soup.find('div','ywzc_leftWrap').find_all('div','ywzc_box'):
    if isinstance(link, bs4.element.Tag):
        i+=1
        f.write("{}|".format(i))
        f.write("军事博物馆|")
        f.write("{}|".format(link.find('h3').string))
        brief = link.find('p').string
        f.write("{}\n".format(brief))
for link in soup.find('div','ywzc_rightWrap').find_all('div','ywzc_box'):
    if isinstance(link, bs4.element.Tag):
        i+=1
        f.write("{}|".format(i))
        f.write("军事博物馆|")
        f.write("{}|".format(link.find('h3').string))
        brief = link.find('p').string
        f.write("{}\n".format(brief))
f.close()