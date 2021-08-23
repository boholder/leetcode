const main = require("../Medium/Integer to Roman-12");
const intToRoman = main.intToRoman;

describe("main", () => {
    test("example1", () => {
        let num = 3;
        expect(intToRoman(num)).toBe("III");
    });
    test("example2", () => {
        let num = 4;
        expect(intToRoman(num)).toBe("IV");
    });
    test("example3", () => {
        let num = 9;
        expect(intToRoman(num)).toBe("IX");
    });
    test("example4", () => {
        let num = 58;
        expect(intToRoman(num)).toBe("LVIII");
    });
    test("example5", () => {
        let num = 1994;
        expect(intToRoman(num)).toBe("MCMXCIV");
    });
})