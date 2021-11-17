# xor_muy_simple
import tensorflow.compat.v1 as tf
tf.disable_v2_behavior()
import time
import numpy as np
import matplotlib.pyplot as plt

errors = []
capa_oculta = int(input("capa_oculta: ") or 3)
learning_rate = float(input("learning_rate: ") or 0.1)
iterations = int(input("iterations: ") or 10000)    

XOR_X = [[0,0],[0,1],[1,0],[1,1]]
XOR_Y = [[0],[1],[1],[0]]

# XOR_X = [[0, 0], [0, 1], [1, 0], [1, 1]]
# XOR_Y = [[0], [0], [0], [1]]

x_ = tf.placeholder(tf.float32, shape=[4, 2], name='x-input')
y_ = tf.placeholder(tf.float32, shape=[4, 1], name='y-input')

# 2,3: 2 entradas para cada una de las tres neuronas en capa oculta
# 3,1: 3 entradas para una neurona en capa de salida

Pesos1 = tf.Variable(tf.random_uniform([2, capa_oculta], -1, 1), name="Pesos1")
Bias1 = tf.Variable(tf.random_uniform([capa_oculta], -1, 1), name="Bias1")
Pesos2 = tf.Variable(tf.random_uniform([capa_oculta, 1], -1, 1), name="Pesos2")
Bias2 = tf.Variable(tf.random_uniform([1], -1, 1), name="Bias2")

A = tf.sigmoid(tf.matmul(x_, Pesos1) + Bias1)
Salida = tf.sigmoid(tf.matmul(A, Pesos2) + Bias2)

Costo=tf.reduce_mean(abs(y_ - Salida))
# Costo = tf.reduce_mean((y_*tf.log(Salida)+((1 - y_)* tf.log(1.0-Salida)))*-1)

train_step = tf.train.GradientDescentOptimizer(learning_rate).minimize(Costo)

init = tf.global_variables_initializer()
sess = tf.Session()
sess.run(init)

t_start = time.time()
for i in range(iterations):
    sess.run(train_step, feed_dict={x_: XOR_X, y_: XOR_Y})
    errors.append(sess.run(Costo, feed_dict={x_: XOR_X, y_: XOR_Y}))
t_end = time.time()

print ("Serie:", i + 1)
print ("Salida:", sess.run(Salida, feed_dict={x_: XOR_X, y_: XOR_Y}))
print('Pesos1:', sess.run(Pesos1))
print ('Bias1:', sess.run(Bias1))
print('Pesos2:', sess.run(Pesos2))
print('Bias2:', sess.run(Bias2))
print('Presicion:', (1 - sess.run(Costo, feed_dict={x_: XOR_X, y_: XOR_Y})) * 100)
print('Tiempo transcurrido:', t_end - t_start, 's')
plt.plot([np.mean(errors[i-50:i]) for i in range(len(errors))])
plt.show()