from tensorflow.examples.tutorials.mnist import input_data
import tensorflow as tf
import numpy as np
import matplotlib.pyplot as plt

mnist = input_data.read_data_sets('MNIST_data', one_hot=True)
#
# sess=tf.InteractiveSession()
#
# #使用relu 即卷积神经网络来训练
# def weight_variable(shape):
#   initial = tf.truncated_normal(shape, stddev=0.1)
#   return tf.Variable(initial)
#
# def bias_variable(shape):
#   initial = tf.constant(0.1, shape=shape)
#   return tf.Variable(initial)

#添加层
def add_layer(input,in_size,out_size,activation_function=None):
    Weight=tf.Variable(tf.random_normal([in_size,out_size]))
    biases=tf.Variable(tf.zeros([1,out_size])+0.1)
    y=tf.matmul(input,Weight)+biases
    if activation_function is None:
        out_put=y
    else:
        out_put=activation_function(y)
    return out_put

# Make up some real data
x_data = np.linspace(-1,1,300)[:, np.newaxis]
noise = np.random.normal(0, 0.05, x_data.shape)
y_data = np.square(x_data) - 0.5 + noise

# define placeholder for inputs to network
xs = tf.placeholder(tf.float32, [None, 1])
ys = tf.placeholder(tf.float32, [None, 1])

l1=add_layer(xs,1,10,activation_function=tf.nn.relu)
#
prediction=add_layer(l1,10,1,activation_function=None)

loss=tf.reduce_mean(tf.reduce_sum(tf.square(ys-prediction),reduction_indices=[1]))

train=tf.train.GradientDescentOptimizer(0.2).minimize(loss)

init=tf.global_variables_initializer()

fig=plt.figure()
ax=fig.add_subplot(1,1,1)
ax.scatter(x_data,y_data)
plt.ion()
plt.show()

sess=tf.Session()
sess.run(init)
for _ in range(1000):
    sess.run(train,feed_dict={xs:x_data,ys:y_data})
    if _ %50:
        try:
            ax.lines.remove(lines[0])
        except Exception:
            pass
        prediction_value = sess.run(prediction, feed_dict={xs: x_data})
        # plot the prediction
        lines = ax.plot(x_data, prediction_value, 'r-', lw=5)
        plt.pause(0.1)
        print(_)
print("over")
sess.close()