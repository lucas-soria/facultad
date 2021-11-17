import tensorflow.compat.v1 as tf
tf.disable_v2_behavior()


a = tf.constant(2, name="a")
b = tf.constant(3, name="b")
c = tf.add(a, b, name="addition")

with tf.compat.v1.Session() as sess:
	print(sess.run(c))
