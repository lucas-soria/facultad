import input_data

mnist = input_data.read_data_sets("/tmp/data", one_hot=True)

import tensorflow as tf

learning_rate = 0.01
training_iteration = 30
batch_size = 100
display_step = 2

