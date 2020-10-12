from Clazz1 import Clazz1
from Clazz2 import Clazz2
from Logger import Logger


class LoggerMain:

    def main(self, args):
        LoggerMain.run()

    def run(self):
        c1 = Clazz1()
        c1.method1()
        c2 = Clazz2()
        c2.method1()
        c2.method2()
        print(Logger().get_logger().get_logs())


LoggerMain().run()
