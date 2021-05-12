import requests
import re

headers={
    "User-Agent": "",
}

s=requests.get("http://www.lvshunmuseum.org/news/?SortID=1",headers=headers)
a=s.text
b=re.findall("<h1>(.*?)</",a)
for i in b:
    print(i)