import request
import time
import hashlib 
import json
import db

headers={
    "Accept":"application/json, text/javascript, */*; q=0.01",
    "Accept-Language":"zh-CN,zh;q=0.9",
    "X-Requested-With":"XMLHttpRequest",
    "Content-Type":"application/x-www-form-urlencoded; charset=UTF-8",
    "Origin":"http://app.gjzwfw.gov.cn",
    "User-Agent": "",
    "Cookie":"",
    "Referer": "http://app.gjzwfw.gov.cn/jmopen/webapp/html5/gjwwjqgbwgmlcx/index.html"
}
times=str(int(time.time()*1000))
string='gjwwjqgbwgmlcx'+times
time_key=
# print(times)
# print(time_key)
page_num=1
save_file=open("数据1.txt","wt+",encoding='utf-8')
# for i in range(1,172):
#     print(i)
museum_num=1

db_opera=db.sql_sql()

for i in range(1,172):
    print("当前页码",page_num)
    bodys={
        "param":'''{"from":"1","key":"","requestTime":"'''+times+'''","sign":"'''+time_key+'''"\
    ,"tableName":"InstBaseCollection","currentPageNumber":'''+str(page_num)+''',"pagesize":10,"instName":"","province":"","instType":"","qualityLevel":" "}'''
    }
    # print(bodys)
    s=request.post("",headers=headers,data=bodys,verify=False)

    sp= json.loads(s.text)
    page_num=page_num+1
    
    for sps in sp["data"]["data"]:
        # print(sps)
        sql='''INSERT INTO `museums`(`mid`, `name`, `introduction`, `opentime`, `city`) \
            VALUES ('''+str(museum_num)+''',\''''+sps["instName"]+'''\',\''''+sps["summary"]+'''\',\''''+sps["openTime"]+'''\',\''''+sps["areaCode"]+'''\')'''
        db_opera.set_sql(sql.encode("utf8"))
        museum_num+=1
        # save_file.write(str(sp))
    time.sleep(1)
db_opera.set_close()
# save_file.close()