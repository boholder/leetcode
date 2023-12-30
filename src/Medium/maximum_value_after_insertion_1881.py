class Solution:
    """
    AC

    https://leetcode.cn/problems/maximum-value-after-insertion/
    给你一个非常大的整数 n 和一个整数数字 x ，大整数 n 用一个字符串表示。n 中每一位数字和数字 x 都处于闭区间 [1, 9] 中，且 n 可能表示一个 负数 。

    你打算通过在 n 的十进制表示的任意位置插入 x 来 最大化 n 的 数值 。但 不能 在负号的左边插入 x 。

    例如，如果 n = 73 且 x = 6 ，那么最佳方案是将 6 插入 7 和 3 之间，使 n = 763 。
    如果 n = -55 且 x = 2 ，那么最佳方案是将 2 插在第一个 5 之前，使 n = -255 。

    返回插入操作后，用字符串表示的 n 的最大值。
    """

    def maxValue(self, n: str, x: int) -> str:
        # 正数从左到右，放在第一个比x小的数字前
        # 负数是放在第一个比x大的数字前
        positive_stop = lambda r: r == 1
        negative_stop = lambda r: r == -1

        negative = True if n[0] == '-' else False
        predicate = negative_stop if negative else positive_stop

        if negative:
            n = n[1:]

        head = 0
        while head < len(n):
            if predicate(self.compare(x, n[head])):
                break
            head += 1

        num = n[:head] + str(x) + n[head:]
        return "-" + num if negative else num

    @staticmethod
    def compare(a: int, b: str) -> int:
        """
        ascii "0" = 48(dec)
        https://www.ascii-code.com/

        :param a: 一位数字，1-9
        :param b: 单个字符，1-9
        :return: -1 0 1
        """
        ascii_a = a + 48
        ascii_b = ord(b)

        if ascii_a < ascii_b:
            return -1
        elif ascii_a > ascii_b:
            return 1
        else:
            return 0


# def test_0():
#     assert Solution.compare(1, "2", ) == -1
#     assert Solution.compare(2, "1") == 1
#     assert Solution.compare(1, "1") == 0
#
#
# def test_11():
#     assert Solution().maxValue("13", 2) == "213"
#
#
# def test_1():
#     assert Solution().maxValue("-13", 2) == "-123"
#
#
# def test_2():
#     assert Solution().maxValue("-132", 3) == "-1323"
#
#
# def test_3():
#     assert Solution().maxValue("-648468153646", 5) == "-5648468153646"
