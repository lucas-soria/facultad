import tensorflow as tf
tf.compat.v1.disable_eager_execution() # need to disable eager in TF2.x

x1 = tf.constant([1,2,3,4])
x2 = tf.constant([5,6,7,8])

result = tf.multiply(x1, x2)

with tf.compat.v1.Session() as sess:

	print(sess.run(result))
