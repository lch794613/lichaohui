import tensorflow as tf

#assign 这个函数是两者取大
#reduce_mean求平均值
#cast 转化
#argmax（x，x2）求平均值
#equal 是否相等 返回布尔值
#nn.softmax 一种segment函数
#熵 公式在文字识别教程
#mutmul（）矩阵相乘


state=tf.Variable(2)
one = tf.constant(1)
three=tf.constant(5)
new_value = tf.add(state, one)
update = tf.nn.softmax(tf.multiply(state, three)+new_value)

init=tf.global_variables_initializer()

with tf.Session() as sess:
    sess.run(init)
    print(sess.run(update))

