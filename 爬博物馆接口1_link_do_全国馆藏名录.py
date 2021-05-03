import request
import time
import hashlib 
import json
import threading
import _thread
from multiprocessing import Process



def scrapy(file_name,start,end):
    headers={
        "Accept":"application/json, text/javascript, */*; q=0.01",
        "Accept-Language":"zh-CN,zh;q=0.9",
        "X-Requested-With":"XMLHttpRequest",
        "Content-Type":"application/x-www-form-urlencoded; charset=UTF-8",
        "Origin":"http://app.gjzwfw.gov.cn",
        "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.114 Safari/537.36",
        "Cookie":"HWWAFSESID=10fadb513ed28c135b; HWWAFSESTIME=1619620435531; JMOPENSESSIONID=f0e79e98-a47a-4fd2-9601-4d0c091a5e42",
        "Referer": "http://app.gjzwfw.gov.cn/jmopen/webapp/html5/gjwwjqgbwgmlcx/index.html"
    }

    times=str(int(time.time()*1000))
    string='gjwwjqgbwgmlcx'+times
    time_key=hashlib.md5(string.encode('utf8')).hexdigest()
    # print(times)
    # print(time_key)
    page_num=start
    save_file=open(file_name,"wt+",encoding='utf-8')
    save_log=open("log_"+file_name,"wt+",encoding='utf-8')
    lsp=end
    # for i in range(1,172):
    #     print(i)
    try:
        while(page_num<end):  
            try:
                # page_num=i
                print("当前页码",page_num)
                save_log.write(str(page_num))
                bodys={
                    "param":'''{"from":"1","key":"b8d515ed3de145d487830b4d21ef377d","requestTime":"'''+times+'''","sign":"'''+time_key+'''"\
                ,"tableName":"BaseCollectionCr","currentPageNumber":'''+str(page_num)+''',"pagesize":10,"instName":"","province":"","instType":"","qualityLevel":" "}'''
                }
                # print(bodys)
                s=request.post("http://app.gjzwfw.gov.cn/jimps/link.do",headers=headers,data=bodys,verify=False)

                sp= json.loads(s.text)
                page_num=page_num+1
                for sp in sp["data"]["data"]:
                    # print(sp)
                    save_file.write(str(sp))
                    # time.sleep(0.1)
                time.sleep(1)
            except:
                # print("出问题了")
                # fps=open("error.txt","wt+")
                # fps.write(str(page_num))
                # fps.close()
                print("出错了",sp.text)
                page_num=page_num-1
                time.sleep(30)
                pass
    except:
        pass
    save_log.close()
    save_file.close()
    

if __name__ == '__main__': 
    try:
        ts=[]
        for i_num in range(1,235439,10000):
            print(i_num)
            if((235439/i_num) >1):
                print("创建进程")
                # t = threading.Thread(target=scrapy,args=("数据3_"+str(i_num)+".txt",i_num,i_num+100))  #gil锁问题 无法并行
                # ts.append(t)
                # t.setDaemon(True)
                # _thread.start_new_thread( scrapy,("数据3_"+str(i_num)+".txt",i_num,i_num+100))
                p = Process(target=scrapy,args=("数据3_"+str(i_num)+".txt",i_num,i_num+10000))
                p.daemon=True
                # p.join()
                ts.append(p)
                time.sleep(0.6)
                p.start()
                
            else:
                # t = threading.Thread(target=scrapy,args=("数据3_"+str(235401)+".txt",i_num,235439))
                # ts.append(t)
                # t.setDaemon(True)
                # _thread.start_new_thread( scrapy,("数据3_"+str(235401)+".txt",i_num,235439))
                p = Process(target=scrapy,args=("数据3_"+str(i_num)+".txt",i_num,235439))
                p.daemon=True
                # p.join()
                ts.append(p)
                time.sleep(0.6)
                p.start()
                
        for td in ts:
            td.join()
            
    except Exception as e:
        print (e)
   
