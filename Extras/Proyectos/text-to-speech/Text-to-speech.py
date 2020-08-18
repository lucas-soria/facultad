#!/usr/bin/python3
from google_speech import Speech
import sys


"""

    How to install on Ubuntu and other Debian derivatives:
        - install pip for Python 3
        - pip3 install google_speech
        - sudo apt-get install sox libsox-fmt-mp3.


    Recomendation:
        Create output mp3 file. its faster

"""

file = open("./{}".format(sys.argv[1]), "r")
text = file.read()
lang = sys.argv[2]
speech = Speech(text, lang)
# speech.play()

'''
    you can also apply audio effects while playing (using SoX)
see http://sox.sourceforge.net/sox.html#EFFECTS for full effect documentation
'''
# sox_effects = ("speed", "1.15")
# speech.play(sox_effects)

# save the speech to an MP3 file (no effect is applied)
speech.save("{}.mp3".format(sys.argv[3]))
