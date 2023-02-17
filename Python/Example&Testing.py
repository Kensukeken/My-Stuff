import unittest

def multiply(x, y):
    return x * y

class TestMultiply(unittest.TestCase):
    
    def test_multiply_positive_numbers(self):
        self.assertEqual(multiply(2, 3), 6)
    
    def test_multiply_negative_numbers(self):
        self.assertEqual(multiply(-2, -3), 6)
    
    def test_multiply_zero(self):
        self.assertEqual(multiply(2, 0), 0)
        self.assertEqual(multiply(0, 2), 0)
    
    def test_multiply_floats(self):
        self.assertAlmostEqual(multiply(0.1, 0.2), 0.02, places=2)

if __name__ == '__main__':
    unittest.main()
