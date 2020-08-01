#coding: utf-8

# TestCase
# invoke method to be test
class TestCase:
    def __init__(self, name):
        self.name = name
    # def setUp(self):
        # pass
    def run(self):
        self.setUp()
        method = getattr(self, self.name)
        method()

# WasRun
# Flag to check if test case is invoked
class WasRun(TestCase):
    # override
    def setUp(self):
        self.wasRun = None
        self.wasSetUp = 1
    def testMethod(self):
        self.wasRun = 1

# test case method
class TestCaseTest(TestCase):
    # override
    def setUp(self):
        self.test = WasRun("testMethod")
    def testRunning(self):
        self.test.run()
        assert(self.test.wasRun)
    def testSetUp(self):
        self.test.run()
        assert(self.test.wasSetUp)

TestCaseTest("testRunning").run()
TestCaseTest("testSetUp").run()

