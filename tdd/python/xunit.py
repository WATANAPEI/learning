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
        self.setUp()
        method = getattr(self, self.name)
        method()
        self.tearDown()

# WasRun
# Flag to check if test case is invoked
class WasRun(TestCase):
    # override
    def setUp(self):
        self.log = "setUp "
    def testMethod(self):
        self.log = self.log + "testMethod "
    def tearDown(self):
        self.log = self.log + "tearDown "


# test case method
class TestCaseTest(TestCase):
    # override
    def testTemplateMethod(self):
        test = WasRun("testMethod")
        test.run()
        assert("setUp testMethod tearDown " == test.log)

TestCaseTest("testTemplateMethod").run()

