import requests
import bs4
import re
from bs4 import BeautifulSoup
f = open("exhibitionnew.txt","rt",encoding='utf-8')
for line in f.readlines():pass
i = eval(line[0:3])
f = open("exhibitionnewnew.txt","a+",encoding='utf-8')
r = requests.get("http://www.namoc.org/zsjs/zlzx/")
r.encoding = r.apparent_encoding
demo = r.text
soup = BeautifulSoup(demo,"html.parser")
for link in soup.find_all('li','clearfix'):
   if isinstance(link, bs4.element.Tag):
      i+=1
      f.write("{}|".format(i))
      f.write("中国美术馆|")
      inlink=link.find('h3').a
      f.write("{}|".format(inlink.string))
      s = requests.get("http://www.namoc.org/zsjs/zlzx/{}".format(inlink.get('href')))
      s.encoding = s.apparent_encoding
      childsoup = BeautifulSoup(s.text, "html.parser")
      if childsoup.find('div', 'Custom_UnionStyle') != None:
         brieflink = childsoup.find('div', 'Custom_UnionStyle')
         if(brieflink.string!=None):
            if(len(brieflink.string)>10):
               f.write("{}\n".format(brieflink.string))
               continue
         for blink in brieflink.find_all(['p','span','div']):
            if blink.string!=None:
               f.write("{}\n".format(brieflink.find(['p','span','div']).string))
               break
      else:f.write("\n")
for j in range(4):
   r = requests.get("http://www.namoc.org/zsjs/zlzx/zlhg/20{}/index.html".format(20-j))
   r.encoding = r.apparent_encoding
   demo = r.text
   soup = BeautifulSoup(demo, "html.parser")
   for link in soup.find_all('li','clearfix'):
      i += 1
      f.write("{}|".format(i))
      f.write("中国美术馆|")
      inlink = link.find('h3').a
      f.write("{}|".format(inlink.string))
      s = requests.get("http://www.namoc.org/zsjs/zlzx/{}".format(inlink.get('href')[6:-1]+inlink.get('href')[-1]))
      s.encoding = s.apparent_encoding
      childsoup = BeautifulSoup(s.text, "html.parser")
      if childsoup.find('div', 'Custom_UnionStyle') != None:
         brieflink = childsoup.find('div', 'Custom_UnionStyle')
         if(brieflink.string!=None):
            if(len(brieflink.string)>10):
               f.write("{}\n".format(brieflink.string))
               continue
         for blink in brieflink.find_all(['p','span','div']):
            if blink.string!=None:
               f.write("{}\n".format(brieflink.find(['p','span','div']).string))
               break
      else:f.write("\n")
