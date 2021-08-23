// AC，这是最简单的解决方案，数值不太好。
// https://leetcode.com/problems/integer-to-roman/

// Symbol       Value
// I             1
// V             5
// X             10
// L             50
// C             100
// D             500
// M             1000
//
//     I can be placed before V (5) and X (10) to make 4 and 9.
//     X can be placed before L (50) and C (100) to make 40 and 90.
//     C can be placed before D (500) and M (1000) to make 400 and 900.

const symbolMap = new Map([
    [1, "I"],
    [4, "IV"],
    [5, "V"],
    [9, "IX"],
    [10, "X"],
    [40, "XL"],
    [50, "L"],
    [90, "XC"],
    [100, "C"],
    [400, "CD"],
    [500, "D"],
    [900, "CM"],
    [1000, "M"]
]);

/**
 * @param {number} num
 * @return {string}
 */
var intToRoman = function (num) {
    var textPart, minus;
    symbolMap.forEach((value, key) => {
        if (num >= key) {
            textPart = value;
            minus = key;
        }
    });

    let remain = num - minus;
    if (remain === 0) {
        return textPart;
    } else {
        return "".concat(textPart, intToRoman(remain));
    }
};

module.exports = {
    intToRoman: intToRoman
}