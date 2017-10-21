from sklearn.feature_extraction import DictVectorizer#用于将特征转化为0，1
from sklearn import preprocessing #用于将标签转化为01
from sklearn import tree
from sklearn.externals.six import StringIO
import csv
import copy


electronicsData=open(r'/Users/dly_lee/Documents/02_深度学习基础/03_代码与素材/代码与素材(1)/01DTree/AllElectronics.csv',"r")
read = csv.reader(electronicsData)
header=next(read)
print(header)

featurelist=[]
labellist=[]


for row in read:#横向遍历
    labellist.append(row[len(row)-1])#把每行的最后一个属性给标签数组
    rowdict={}
    for i in range(1,len(row)-1):#范围从第二个到最后一个 即竖遍历
        rowdict[header[i]]=row[i]#将key-value对放入字典中
    featurelist.append(rowdict)

print(featurelist) #特征数组 包含rowdic字典的数组
print(labellist)#标签数组

dv=DictVectorizer()
dummyX=dv.fit_transform(featurelist).toarray()
print("X"+str(dummyX))#特征二值化

lb=preprocessing.label
zz=lb.LabelBinarizer()
dummyY=zz.fit_transform(labellist)
print("Y"+str(dummyY))  #标签二值化

#
dtc=tree.DecisionTreeClassifier(criterion="entropy")#决策树 传入信息熵
dtc=dtc.fit(dummyX,dummyY)

print(dtc)

with open("decisiontree.doc","w") as f:
    f=tree.export_graphviz(dtc,feature_names=dv.get_feature_names(),out_file=f)

onerow=dummyX[0,:]
print("onerow"+str(onerow))

newrow=copy.deepcopy(onerow)
newrow[0]=1
newrow[2]=0
print("newrow"+str(newrow))

predictY=dtc.predict(newrow)
print(predictY)

