i = 0;
final (swallow) name = "John Wick";

name = "Wick John";

Write("Did the final modifier work: " + name);

def test() {
    Write("Method call works");

    def thisWorksSurprisingly() {
        Write("Holy moly!");
    }
}

test();

thisWorksSurprisingly();

j = 0;

def recursion() {
    Write("Recursion of j is now: " + j);
    if j != 5 {
        j = j + 1;
        recursion();
    }
}

recursion();

def argTest(x) {

}

argTest();

while i < 6 {
    Write("Iteration " + i);
    i = i + 1;
} else {
    Write("i is " + i);
}

if i == 6 {
    Write("i is now 6");
} else {
    Write("i is not 6");
}

result = 3 + 7;
Write("Result of 3 + 7: " + result);

product = 4 * 6;
Write("Result of 4 * 6: " + product);

isEven = i % 2 == 0;
Write("Is i even? " + isEven);

Write("End of script");