import tensorflow as tf
from tensorflow.examples.tutorials.mnist import input_data

mnist=input_data.read_data_sets("MNIST_data/", one_hot=True)



#设置占位符 none表示其中一个维度是可以任意长度
x=tf.placeholder(tf.float32,[None,784])
#设置权重 这样的设置是为了方便计算 初始化都是0
W=tf.Variable(tf.zeros([784,10]))
#设置偏置值
B=tf.Variable(tf.zeros([10]))

#调用函数softmax 使y=wx+b 线性的函数弯曲
y = tf.nn.softmax(tf.matmul(x, W) + B)

#定义一个损失值loss

y_=tf.placeholder(tf.float32,[None,10])

#计算交叉熵 用来衡量预测用于描述真相的低效性。 y是预测的 y_是正确的

cross_entroy=tf.reduce_mean(-tf.reduce_sum(y_*tf.log(y)))

#tensorflow 会自动的使用 反向传播算法

#使用梯度下降法

train=tf.train.GradientDescentOptimizer(0.1).minimize(cross_entroy)

init=tf.initialize_all_variables()
with tf.Session() as sess:
    sess.run(init)
    for i in  range(1000):
        #训练集 x轴为x y轴为y_
        batch_xs,batch_ys=mnist.train.next_batch(100)
        sess.run(train,feed_dict={x:batch_xs,y_:batch_ys})

#评估训练结果
 #对比y轴上和y_上的正确值的索引 返回的是一组boolean值
    correct_prediction = tf.equal(tf.argmax(y,1), tf.argmax(y_,1))
    #将这组boolean值转化为浮点数 求平均值就可以得到结果了
    accuracy = tf.reduce_mean(tf.cast(correct_prediction, tf.float32))
    print(sess.run(accuracy, feed_dict={x: mnist.test.images, y_: mnist.test.labels}))
    print(sess.run(W))
    print(sess.run(B))
