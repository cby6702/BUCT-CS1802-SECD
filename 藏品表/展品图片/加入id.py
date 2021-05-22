#coding:utf-8
 
readDir1 = "./list1.txt"
readDir2 = "./collection.txt"
writeDir = "./conclusion.txt"
outfile=open(writeDir,"w",encoding='utf-8')
f1 = open(readDir1,"r",encoding='utf-8')
f2 = open(readDir2,"r",encoding='utf-8')

lines_f1=f1.readlines()
lines_f2=f2.readlines()


for i in range(0,len(lines_f1)):
    column_1= lines_f1[i].strip().split(' ')
    i=i+1
    for j in range(i-1,len(lines_f2)):
        column_2=lines_f2[j].strip().split(' ')
        j=j+1
        try:
            if column_1[0]==column_2[1] and column_1[1]==column_2[2]:   
                outfile=open(writeDir,"a",encoding='utf-8')
                outfile.write(column_2[0]+' '+lines_f1[i])         
                print('成功加入第'+str(i)+'条数据')
                outfile.close()
                break
        except BaseException:
            print("错误，当前文件错误")
            continue
        

print('成功写入'+str(i)+'条数据')
f1.close()
f2.close()

