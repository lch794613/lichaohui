import tensorflow as tf
import numpy as np

x_data=np.random.rand(100).astype(np.float32)
y_data=x_data*0.1+0.3


#初始化2个值 分别是权重和偏移值
Wight=tf.Variable(tf.zeros([1]))
Basis=tf.Variable(tf.zeros([1]))


y=Wight*x_data+Basis
#将得到的y与真实的y 进行求差平方取平均值
loss=tf.reduce_mean(tf.square(y-y_data))
optimizer=tf.train.GradientDescentOptimizer(0.5)#创建优化器 设置训练度为0。5
#将差值放入优化器中优化得到训练样本
train=optimizer.minimize(loss)
#初始化所有的变量
init=tf.initialize_all_variables()


#建立一个session
sess=tf.Session()
#进行神经元初始化
sess.run(init)

#进行训练 
for step in range(201):
    sess.run(train)
    if step %20==0:
        print(step,sess.run(Wight),sess.run(Basis))