#!/usr/bin/python

import csv
titulo = True
with open('archivo.csv') as csv_file:
    csv_reader = csv.reader(csv_file, delimiter=',')
    for renglon in csv_reader:
        if titulo == True:
            titulo = False
        elif float(renglon[4]) > 1000:
            print renglon[1]
