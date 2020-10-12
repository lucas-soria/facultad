from Logger import Logger
from Clazz3 import Clazz3


class Clazz2:

    def method1(self):
        Logger().get_logger().add_log("Clazz2:method1")

    def method2(self):
        c3 = Clazz3()
        c3.method1()
        Logger().get_logger().add_log("Clazz2:method2")
