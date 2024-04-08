# add comments
CC=g++
# Flags to be executed such as -Wall which gives more warnings
CFLAGS= -Wall
TARGET=out

# This section finds all .c files and sets S_SRCS = to them
C_SRCS := \
	$(wildcard *.cpp)

# This section finds all .h files and sets HDRS = to them
HDRS := \
	$(wildcard *.h)

# This compiles the object files into the target executable with the LFLAGS which in this case connects libraries.
build: $(C_SRCS)
	$(CC) $(C_SRCS) $(CFLAGS) -o $(TARGET)

# This removes any built files leaving just the original source files
clean:
	rm -f $(TARGET)

# runs build and then executes the target executable with the command line argument of "out.txt"
run: build
	./$(TARGET)

# This make execute will show the C source files and header files that the makefile finds.
which:
	@echo "FOUND SOURCES: $(C_SRCS)"
	@echo "FOUND HEADERS: $(HDRS)"
