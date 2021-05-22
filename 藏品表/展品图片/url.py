import re
import requests
from urllib import error
from bs4 import BeautifulSoup
import os
import time
import codecs

num = 0
numPicture = 0
List = []

def dowmloadPicture(html, keyword):
    global num   
    pic_url = re.findall('"thumbURL":"(.*?)",', html, re.S) 
    print("找到关键词:" + keyword + "的图片，开始爬取图片url...")
    for temp in pic_url:
        print("正在爬取第" + str(num + 1) + "张图片的url:" + str(temp))
        try:
            if temp is not None:
                pic = requests.get(temp, timeout=7)
            else:
                continue
        except BaseException:
            print("错误，当前图片url爬取错误")
            continue
        else:
            m_list=open("list.txt","a",encoding="utf-8")                
            m_list.write(word+"\t"+str(temp)+"\n")
            m_list.close()
            num += 1
           
        if num >= numPicture:
            return
 
if __name__ == "__main__":  # 主函数入口
    
    
    headers = {
        'Accept-Language': 'zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2',
        'Connection': 'keep-alive',
        'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.212 Safari/537.36 Edg/90.0.818.62',
        'Upgrade-Insecure-Requests': '1'
        }
 
    A = requests.Session()
    A.headers = headers
      
    
    tm = int(input("输入每个文物需要图片数 "))
    numPicture = tm
    line_list = []
    line_list1=[]
    line_list2=[]
    m_name=open("collection.txt","r",encoding="utf-8")
    content = m_name.readlines()
    m_name.close
    for temp in content:
        line_list1.append(temp.strip())
    #for i in line_list1:
       # line_list2.append(i.replace('\t',''))
    for i in line_list1:
         line_list.append(i.replace('"',""))
    j=0
    for word in line_list:
        url = "https://image.baidu.com/search/flip?tn=baiduimage&ie=utf-8&word=" + word + "&pn="       
        t = 0
        tmp = url
        time.sleep(0.5)
        try:
            url = tmp + str(t)                
            result = A.get(url, timeout=10, allow_redirects=False)
        except error.HTTPError as e:
            print("网络错误，请调整网络后重试")
        else:           
            dowmloadPicture(result.text, word)
            j=j+1
            print("成功爬取第"+str(j)+"张图片的url"+"\n")
    print("爬取结束")
