import unittest
from count_letters import count_letters


class TestLetterCounter(unittest.TestCase):
    def test_count_(self):
        result = count_letters('hello world')
        self.assertEqual(result, {
            ' ': 1,
            'e': 1,
            'd': 1,
            'h': 1,
            'l': 3,
            'o': 2,
            'r': 1,
            'w': 1,
        })


if __name__ == '__main__':
    unittest.main()