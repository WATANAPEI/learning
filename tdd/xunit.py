#coding: utf-8

# TestCase
# invoke method to be test
class TestCase:
    def __init__(self, name):
        self.name = name
    def run(self):
        method = getattr(self, self.name)
        method()

# WasRun
# Flag to check if test case is invoked
class WasRun(TestCase):
    def __init__(self, name):
        self.wasRun = None
        super().__init__(name)
    def testMethod(self):
        self.wasRun = 1

# test case method
class TestCaseTest(TestCase):
    def testRunning(self):
        test = WasRun("testMethod")
        assert(not test.wasRun)
        test.run()
        assert(test.wasRun)

TestCaseTest("testRunning").run()

