// AC，数值很好，90%多。
// https://leetcode.com/problems/minimum-time-difference/
const ONE_DAY_IN_MINUTE = 24 * 60;

/**
 * @param {string[]} timePoints
 * @return {number}
 */
var findMinDifference = function (timePoints) {
    let sorted = timePoints.map(toNumber).sort(compareNumber);
    let headTail = ONE_DAY_IN_MINUTE - (sorted[sorted.length - 1] - sorted[0]);
    let min = Math.min(headTail);
    for (let i = 0; i < timePoints.length - 1; i++) {
        min = Math.min(min, sorted[i + 1] - sorted[i]);
    }
    return min;
};

function compareNumber(a, b) {
    return a - b;
}

/**
 *
 * @param {String} timeStr
 * @returns {*}
 */
function toNumber(timeStr) {
    return parseInt(timeStr.slice(0, 2)) * 60 + parseInt(timeStr.slice(3, 5));
}

module.exports = {
    findMinDifference: findMinDifference
}