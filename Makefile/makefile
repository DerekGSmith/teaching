# This is a very basic makefile (they can get incredibly complicated)
# Creating makefiles for preexisting project and tinkering with them is a great way to learn

# Creates CC variable representing compile command
CC=g++
# Compile Flags such as -Wall which gives more warnings
CFLAGS= -Wall
# Name of output(target) file
TARGET=out

# This code assumes all source files and headers are in the same directory.
# This often will not be the case and should not be the case.
# This section finds all .c files and sets S_SRCS = to them
C_SRCS := \
	$(wildcard *.cpp)

# This section finds all .h files and sets HDRS = to them
HDRS := \
	$(wildcard *.h)

# Makefile Commands
# This compiles the object files into the target executable with the LFLAGS which in this case connects libraries.
build: $(C_SRCS)
	$(CC) $(C_SRCS) $(CFLAGS) -o $(TARGET)

# This removes any built files leaving just the original source files
clean:
	rm -f $(TARGET)

# executes the target executable with the command line argument of "out.txt"
run: 
	./$(TARGET)

# This make execute will show the C source files and header files that the makefile finds.
which:
	@echo "FOUND SOURCES: $(C_SRCS)"
	@echo "FOUND HEADERS: $(HDRS)"
