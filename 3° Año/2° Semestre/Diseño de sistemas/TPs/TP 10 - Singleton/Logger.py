from datetime import datetime


class Logger:

    __logs = ""
    __logger = None

    def __init__(self):
        if Logger.__logger is None:
            self.clear()
            Logger.__logger = self

    def get_logger(self):
        return Logger.__logger

    def get_logs(self):
        return Logger.__logs

    def add_log(self, log):
        Logger.__logs += ">>> " + log + "\n"

    def clear(self):
        Logger.__logs = datetime.ctime(datetime.now()) + "\n"
