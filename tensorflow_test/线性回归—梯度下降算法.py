import numpy as np
import matplotlib.pyplot as plt
import tensorflow as tf

num_point=1000
vector_set=[]
for i in range(num_point):
    x1=np.random.normal(0.0,0.5)
    y1=x1*0.3+0.1+np.random.normal(0.0,0.03)
    vector_set.append([x1,y1])

x_data=[v[0] for v in vector_set]
y_data=[v[1] for v in vector_set]

plt.plot(x_data,y_data,"ro",label='Origin data')


W=tf.Variable(tf.random_uniform([1],-1.0,1.0))#定义变量w 即权重w 随机生产 服从1维 -1到1的值
B=tf.Variable(tf.zeros([1]))#定义变量B 即basis 偏移值 初始化为0
y=W*x_data+B #设定一个回归参数
#设定损失值loss 先算出平方差 然后将它们求它们的平均值
loss=tf.reduce_mean(tf.square(y-y_data))
#实例化一个优化器 优化度为0。5  优化的名字叫 梯度下降算法
optimizer=tf.train.GradientDescentOptimizer(0.5)
#将损失值传入优化器进行优化
train=optimizer.minimize(loss)

init=tf.initialize_all_variables()
sess=tf.Session()
sess.run(init)
for step in range(100):
    sess.run(train)
    print(sess.run(W),sess.run(B))

plt.plot(x_data,sess.run(W)*x_data+sess.run(B))
plt.show()