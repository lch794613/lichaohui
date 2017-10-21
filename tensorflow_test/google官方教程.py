import tensorflow as tf
#官方教程
node1=tf.constant(20.0,dtype=tf.float32)#定义常量为20
node2=tf.constant(2.0)
print(node1,node2)#如果不在session中run的话 初始值只会是0
sess=tf.Session()
print(sess.run([node1,node2]))
#使用指定cup或者gpu来运算 因为不指定 默认1个gpu
# with tf.Session() as sess:
#   with tf.device("/gpu:1"):
#     matrix1 = tf.constant([[3., 3.]])
#     matrix2 = tf.constant([[2.],[2.]])
#     product = tf.matmul(matrix1, matrix2)

state=tf.Variable(0)
one = tf.constant(1)
new_value = tf.add(state, one)
update = tf.assign(state, new_value)
print(sess.run(new_value))
print(sess.run((update)))

a=tf.placeholder(tf.float32)
b=tf.placeholder(tf.float32)
adder_node=a+b
print(sess.run(adder_node, {a: 3, b: 4.5}))
print(sess.run(adder_node, {a: [1, 3], b: [2, 4]}))

w=tf.Variable([.3],tf.float32)
b=tf.Variable([-.3],tf.float32)
x=tf.placeholder(tf.float32)
linear_model=w*x+b

init=tf.global_variables_initializer()#将变量初始化 防止之前的变量对结果产生影响
sess.run(init)
print(sess.run(linear_model,{x:[1,2,3,4]}))#将占位符x传入4个值 得到4个结果

y=tf.placeholder(tf.float32)#定义y 为line模型外的一个偏移值
squared_detail=tf.square(linear_model-y)#将line模型和y进行平方差的计算 返回的是一个list
loss=tf.reduce_sum(squared_detail)#对所有的list进行求和 notice：括号里第二个参数没写就是0维度得到是个变量
#  写0就是一维度就是竖向求和 写1就是2维就是横向求和 得到的结果就是list
print(sess.run(loss,{x:[1,2,3,4],y:[0,-1,-2,-3]}))#producing the loss value

