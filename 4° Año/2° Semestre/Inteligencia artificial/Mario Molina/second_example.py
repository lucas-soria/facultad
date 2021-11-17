import tensorflow.compat.v1 as tf
tf.disable_v2_behavior()

x1 = tf.constant([1,2,3,4])
x2 = tf.constant([5,6,7,8])

result = tf.multiply(x1, x2)

with tf.compat.v1.Session() as sess:
	print(sess.run(result))
