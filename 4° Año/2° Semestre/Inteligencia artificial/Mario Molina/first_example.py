import tensorflow as tf
tf.compat.v1.disable_eager_execution() # need to disable eager in TF2.x


a = tf.constant(2, name="a")
b = tf.constant(3, name="b")
c = tf.add(a, b, name="addition")

with tf.compat.v1.Session() as sess:

	print(sess.run(c))
