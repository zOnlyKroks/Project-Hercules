i = 0;
final (swallow) name = "John Wick";

name = "Wick John";

Write("Did the final modifier work: " + name);
j = 0;

def recursion() {
    Write("Recursion of j is now: " + j);
    if j != 5 {
        j = j + 1;
        recursion();
    }
}

recursion();

Write(true xor true);

while i < 6 {
    Write("While Iteration " + i);
    i = i + 1;
} else {
    Write("i is " + i);
}

until i < 0 {
    Write("Unless Iteration " + i);
    i = i - 1;
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

result2 = 6 / 2;
Write("Result of 6 / 2: " + result2);

isEven = i % 2 == 0;
Write("Is i even? " + isEven);

Write("Testing da scope depths");

j = 3;

def pleaseWork() {
    z = 2;
    Write(z);
    Write(j);
}

pleaseWork();

Write("------");
Write(z);
Write(j);

Write("Test 1 succeeded");
Write("------");

if j == 3 {
    z = 5;
    Write(z);
}

Write(z);

Write("-------");

Write("Test 2 succeeded");

Write("------");

Write("Arg test");

def argTest(x,y) {
    Write(x + ":" + y);
}

argTest(1,3);

Write("End of script");