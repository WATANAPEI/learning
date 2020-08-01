#coding: utf-8

# TestCase
# invoke method to be test
class TestCase:
    def __init__(self, name):
        self.name = name
    def setUp(self):
        pass
    def tearDown(self):
        pass
    def run(self):
        result = TestResult()
        result.testStarted()
        self.setUp()
        method = getattr(self, self.name)
        method()
        self.tearDown()
        return result

# WasRun
# Flag to check if test case is invoked
class WasRun(TestCase):
    # override
    def setUp(self):
        self.log = "setUp "
    def testMethod(self):
        self.log = self.log + "testMethod "
    def testBrokenMethod(self):
        raise Exception
    def tearDown(self):
        self.log = self.log + "tearDown "

# TestResult
# return result message
class TestResult:
    def __init__(self):
        self.runCount = 0
    def testStarted(self):
        self.runCount = self.runCount + 1
    def summary(self):
        return "%d run, 0 failed" % self.runCount


# test case method
class TestCaseTest(TestCase):
    # override
    def testTemplateMethod(self):
        test = WasRun("testMethod")
        test.run()
        assert("setUp testMethod tearDown " == test.log)
    def testResult(self):
        test = WasRun("testMethod")
        result = test.run()
        assert("1 run, 0 failed" == result.summary())
    def testFailedResult(self):
        test = WasRun("testBrokenMethod")
        test.run()
        assert("1 run, 1 failed" == result.summary())

TestCaseTest("testTemplateMethod").run()
TestCaseTest("testResult").run()
# TestCaseTest("testFailedResult").run()
