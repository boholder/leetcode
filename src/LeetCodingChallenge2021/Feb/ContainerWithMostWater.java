package LeetCodingChallenge2021.Feb;

// leetcode 11 这个题的思路是，约束面积的是较短板的长度和距离，
// 可人为控制距离，贪婪地从大到小。故两个指针从两头开始。
// 距离已确定，也顺便确定了两个板的索引和长度。
// 为了获取更大的面积（且移动指针），我们放弃较短板，
// 向中间移动短板对应的指针。两指针交会结束，记录最大面积
public class ContainerWithMostWater {
}

class Solution6 {
    public int maxArea(int[] height) {
        int headCursor = 0;
        int tailCursor = height.length;

        return 0;
    }
}