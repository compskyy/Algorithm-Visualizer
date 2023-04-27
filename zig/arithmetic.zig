import std.stdio;

pub fn main() {
  // Create a new scanner.
  var scanner = std.io.Scanner(std.io.Stdin);

  // Print a prompt and read the first term.
  writefln("Enter the first term: ");
  var firstTerm = scanner.next_double();

  // Print a prompt and read the common difference.
  writefln("Enter the common difference: ");
  var commonDiff = scanner.next_double();

  // Print a prompt and read the number of terms.
  writefln("Enter the number of terms: ");
  var numOfTerms = scanner.next_int();

  // Calculate the sum of the series.
  var sum = sum_of_series(firstTerm, commonDiff, numOfTerms);

  // Print the sum of the series.
  writefln("The sum of the arithmetic series is: %f", sum);

  // Close the scanner.
  scanner.close();
}

fn sum_of_series(
  firstTerm: double,
  commonDiff: double,
  numOfTerms: int
) -> double {
  return (numOfTerms / 2.0) * (2 * firstTerm + (numOfTerms - 1) * commonDiff);
}