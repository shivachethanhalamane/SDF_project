# 🧮 Infinite Precision Arithmetic Engine (Java)

## 🔍 Introduction

This project delivers a Java library that performs arithmetic on numbers of unlimited size and precision. By representing numbers as strings and implementing custom algorithms, it avoids the overflow and rounding errors inherent in Java’s primitive types. Supported operations include addition, subtraction, multiplication, and division for both integers and floats, with up to **30 decimal digits** of precision.

## 🧰 Capabilities

- ✅ **Infinite‐length** integer and floating‐point arithmetic  
- ➕ Supports **add**, **subtract**, **multiply**, **divide**  
- ⚙️ Packaged as a `.jar` for easy integration  
- 🖥️ **CLI tool**: `MyInfArith.java` (Java) and optional Python wrapper `run.py`  
- 📦 Maven‐ready project structure  
- 🧪 Comprehensive edge-case handling and error reporting  

---

## 💡 Usage

### Option 1: Java CLI

```bash
# Compile
javac -d bin src/arbitraryarithmetic/*.java src/MyInfArith.java

# Run integer multiplication
java -cp bin MyInfArith int mul \
  9999999999999999999999 8888888888888888888888

# Run float division
java -cp bin MyInfArith float div \
  8792726365283060579833950521677211.0 \
  493835253617089647454998358
```

### Option 2: Python Wrapper

python run.py float mul 1.2345 6.7890

### Option 3: Library Import

```bash
# Package with Maven
mvn clean package

# In another Java project
import arbitraryarithmetic.*;

public class Demo {
  public static void main(String[] args) {
    AFloat a = new AFloat("3.1415926535897932384626");
    AFloat b = new AFloat("2.7182818284590452353602");
    System.out.println(a.multiply(b));
  }
}
```

---

## 🗂️ Project Structure

```
infinite-arithmetic/
│
├── src/
│   └── arbitraryarithmetic/
│       ├── AInteger.java
│       └── AFloat.java
│
├── MyInfArith.java
├── run.py
├── pom.xml
└── README.md
```

---

## ⚙️ Implementation Details

### Core Classes

- **AInteger**  
  - Constructors: `AInteger()`, `AInteger(String)`, `AInteger(AInteger)`  
  - Static parser: `parse(String)`  
  - Methods: `add()`, `subtract()`, `multiply()`, `divide()`

- **AFloat**  
  - Constructors: `AFloat()`, `AFloat(String)`, `AFloat(AFloat)`  
  - Static parser: `parse(String)`  
  - Methods: `add()`, `subtract()`, `multiply()`, `divide()`  
  - Decimal precision truncated to 30 digits

### Algorithms

1. **Addition/Subtraction**  
   - Digit-by-digit processing with carry/borrow  
   - Signs handled separately for clean logic  

2. **Multiplication**  
   - Schoolbook multiplication: multiply each digit and sum with positional shifts  

3. **Division**  
   - Long division: subtract multiples, track remainder, extend decimal up to 30 places  

### Edge-Case Handling

- **Division by zero** → throws `ArithmeticException`  
- **Normalization:** strips leading/trailing zeros, converts “-0.000” → “0.0”  
- **Invalid input** → throws descriptive `NumberFormatException`

---

## 🧪 Test Cases & Examples

```bash
# Integer add
java MyInfArith int add \
  23650078224912949497310933240250 \
  42939783262467113798386384401498
# → 66589861487380063295697317641748

# Integer divide by zero
java MyInfArith int div 25 0
# → ArithmeticException: Division by zero

# Float multiply
java MyInfArith float mul \
  6400251.9377695 2326541.6827934
# → 14890452913599.9717457253213

# Float divide with 30-digit precision
java MyInfArith float div 1 3
# → 0.333333333333333333333333333333
```

---

## ⚖️ Limitations

- Decimal precision capped at **30 digits** (per `BigDecimal` rules)  
- No support for scientific/exponential notation  
- Input must be a well-formed numeric string  

---

## 🚀 Future Improvements

- **Scientific Notation** support (e.g. “1.23e-4”)  
- **Configurable Precision** beyond 30 digits  
- **Performance Optimizations** (Karatsuba, FFT-based multiply)  
- **Thread‐safe API** for concurrent operations  

---

## 📝 Key Learnings

- Implementing low-level digit-wise algorithms in Java  
- Mastering `BigDecimal`‐style precision handling  
- Structuring a Maven-based Java project  
- Writing robust unit tests and handling edge cases  

---

## 👨‍💻 Authors

- Shiva Chethan — CS24BTECH11057  
- (Add other team members here)
