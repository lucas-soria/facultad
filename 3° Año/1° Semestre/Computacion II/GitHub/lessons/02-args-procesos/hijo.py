import os

pid = os.fork()
print "pid: " + str(os.getpid()) + " " + str (pid)
print "ppid: " + str(os.getppid()) + " " + str (pid) 

if pid == 0:
    print " soy el hijo"




else :
    print "soy  tu padre "

