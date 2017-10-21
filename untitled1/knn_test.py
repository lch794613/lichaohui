from sklearn.neighbors import  classification
from sklearn import datasets

knn=classification.KNeighborsClassifier()#实例化模型knn 临近选择模型

iris=datasets.load_iris()#python自带的内置数据集

knn.fit(iris.data,iris.target)#传入数据建立模型

predictedlabel=knn.predict([[4.3,3.5,3.3,0.2]])#预测传入的数据是那个类型
print(predictedlabel)