import os 

class MyInfArith :
    def __init__(self, mode):
        self.file_path = ""
        self.mode = mode

    def compile(self) :
        os.system(f"javac -cp .:{self.file_path}target/aarithmetic.jar {self.file_path}myinfarith/MyInfArith.java")
    def run(self, op, x, y) :
        os.system(f"java -cp .:{self.file_path}target/aarithmetic.jar {self.file_path}myinfarith.MyInfArith {self.mode} {op} {x} {y}")   


def main() :

    mode = input("Enter the mode of operation: <int/float> \n")
    s = MyInfArith(mode)
    s.compile()
    print("Enter the operation to be performed: ")
    print("1. for ADDITION")
    print("2. for SUBTRACTION")
    print("3. for MULTIPLICATION")
    print("4. for DIVISION")
    op = int(input())
    if op == 1 :
        op = "add"
    elif op == 2 :
        op = "sub"
    elif op == 3 :
        op = "mul"
    elif op == 4 :
        op = "div"
    else :
        print("Invalid operation")
        return
    x = input("Enter the first number: ")
    y = input("Enter the second number: ")
    print("\nResult: ")
    s.run(op, x, y)

if __name__ == "__main__":
    main()

