i = 0;
name = "John Wick";

while i < 5 {
    Write("Iteration " + i);
    i = i + 1;
}

if i == 5 {
    Write("i is now 5");
} else {
    Write("i is not 5");
}

result = 3 + 7;
Write("Result of 3 + 7: " + result);

product = 4 * 6;
Write("Result of 4 * 6: " + product);

// Set i to an even value
i = 6;

isEven = i % 2 == 0;
Write("Is i even? " + isEven);

Write("End of script");