import tensorflow as tf
import numpy as np
import matplotlib.pyplot as plt
import seaborn as sns
import pandas as pd


numb=1000
conjunto_num=[]
for i in range(numb):
    if np.random.random()>0.5:
        conjunto_num.append([np.random.normal(0.0,0.9),np.random.normal(0.0,0.9)])
    else:
        conjunto_num.append([np.random.normal(3,0.5),np.random.normal(1,0.5)])

df=pd.DataFrame({"x":[v[0] for v in conjunto_num],"y":[v[1]for v in conjunto_num]})
sns.lmplot('x','y',data=df,fit_reg=False,size=6)
plt.show()

#定义常量
vectors=tf.constant(conjunto_num)
k=4   #4个基点
#定义变量 从vectors中随机取出整数 然后从0，0开始 切片k=4 到倒数第1个 即随机取出4个
centroides=tf.Variable(tf.slice(tf.random_shuffle(vectors),[0,0],[k,-1]))
#slice（t，【x，y】【a，b】）表示从t中坐标为xy的值开始横a 竖b 切片取值

#在vectors上面增加一个维度 在基点k上加1个维度 由于长度不一样 不能计算 加一个维度长度为可延伸 于是可以让他们进行计算
expanded_vectors=tf.expand_dims(vectors,0)
expanded_centroides=tf.expand_dims(centroides,1)

#公式为 2点的距离为sqrt（sq(x0-x1)+sq(y0-y1)）最简单的勾股定理啦
#reduce_sum进行加法之后会减少一个维度 注意
#argmin 返回值是一个维度的 从各个维度中找到那个最小的值 然后返回那个维度
assignments=tf.argmin(tf.reduce_sum(tf.square(tf.subtract(expanded_vectors,expanded_centroides)),2),0)

means = tf.concat(0, [tf.reduce_mean(tf.gather(vectors, tf.reshape(tf.where( tf.equal(assignments, c)),[1,-1])), reduction_indices=[1]) for c in range(k)])
update_centroides=tf.assign(centroides,means)
init_op=tf.initialize_all_variables()


sess=tf.Session()
sess.run(init_op)

for step in range(100):
   _, centroid_values, assignment_values = sess.run([update_centroides, centroides, assignments])


#
# data={'x':[],'y':[],'cluster':[]}
#
# for i in range(len(assignments_values)):
#     data['x'].append(conjunto_num[i][0])
#     data['y'].append(conjunto_num[i][1])
#     data['cluster'].append(assignments_valuse[i])
#
# df=pd.DataFrame(data)
# sns.lmplot("x","y",data=df,fit_reg=False,legend=False,size=6,hue='cluster')
# plt.show()
















