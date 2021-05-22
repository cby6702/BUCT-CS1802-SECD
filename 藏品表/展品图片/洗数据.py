#coding:utf-8
 
readDir = "./list.txt"
writeDir = "./list1.txt"
outfile=open(writeDir,"a",encoding='utf-8')
f = open(readDir,"r",encoding='utf-8')
 
lines_seen = set() # Build an unordered collection of unique elements.
 
for line in f:
    line = line.strip('\n').replace('\t',' ')  
    if line not in lines_seen:              
        outfile.write(line+ '\n')
        lines_seen.add(line)

print('这是洗数据')
f.close()
outfile.close()