#Makefile pre proj2 do IVS#

PROJECT_NAME = xmikla10
#JAVA_FILES = $(shell find ./src/Kalkulacka/app/src/ -type f -name '*.java')
#CLASSES_FILES = $(JAVA_FILES:./src/Kalkulacka/app/src/%.java=./classes/%.class)

#Projektova struktura na vysledne zabalenie
ALL_FILES = doc install repo

doc:
	cd ..  && cd .. && doxygen ./repo/src/Doxyfile

pack:
	cd .. && cd .. && zip -r $(PROJECT_NAME).zip $(ALL_FILES)
