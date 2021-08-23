// AC，但双数值不怎么样。有意思的是，ESlint提醒不能在loop中造function，
// 所有箭头函数都算造function。
// https://leetcode.com/problems/valid-sudoku/
/**
 * @param {character[][]} board
 * @return {boolean}
 */
var isValidSudoku = function (board) {
    // row
    for (let array of board) {
        if (!isValidArray(array)) {
            return false;
        }
    }

    // column
    for (let i = 0; i < 9; i++) {
        var column = board.map(take(i));
        if (!isValidArray(column)) {
            return false;
        }
    }

    // 3*3 block
    for (let i = 0; i < 9; i += 3) {
        for (let j = 0; j < 9; j += 3) {
            let block = board.slice(i, i + 3)
                .flatMap(getArraySlice(j));
            if (!isValidArray(block)) {
                return false;
            }
        }
    }

    return true;
};

function take(colIndex) {
    return array => array[colIndex];
}

function getArraySlice(j) {
    return array => array.slice(j, j + 3);
}

/**
 * @param {Array} array
 */
function isValidArray(array) {
    let numberArray = array.filter(char => {
        return char !== ".";
    });
    let distinct = (value, index, self) => {
        return self.indexOf(value) === index;
    };
    return numberArray.length === numberArray.filter(distinct).length;
}

module.exports = {
    isValidSudoku: isValidSudoku,
    isValidArray: isValidArray
};