import json
import request
import time

headers={
    "User-Agent": "",
    "Referer": "http://www.chnppmuseum.com/web/index.html"
}

s=request.get("http://www.chnppmuseum.com/admin/webInterface/getInformationWebList.do?currentPage=1&informationType=3&size=5&_t="+str(time.time()),headers=headers)
p=json.loads(s.text)
for content in p["data"]["data"]:
    print("标题：",content["informationTheme"])
    print("内容：",content["informationContent"]+"\n")