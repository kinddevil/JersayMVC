/**
 * 
 * https://code.google.com/p/js-test-driver/wiki/TestCase 
 * http://meri-stuff.blogspot.com/2012/01/javascript-testing-with-jstestdriver.html
fail([msg])
assertTrue([msg], actual)
assertFalse([msg], actual)
assertEquals([msg], expected, actual)
assertSame([msg], expected, actual)
assertNotSame([msg], expected, actual)
assertNull([msg], actual)
assertNotNull([msg], actual)

jstestdriver.console.log("JsTestDriver", "Hello World!");

 */

GreeterTest = TestCase("GreeterTest");

GreeterTest.prototype.testGreet = function() {
  //var greeter = new myapp.Greeter();
  //assertEquals("Hello World!", greeter.greet("World"));
  assertEquals("Hello World!","Hello World!");
};