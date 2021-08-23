const main = require("../Medium/Minimum Time Difference-539")

describe("main", () => {
    test("example1", () => {
        let timePoints = ["23:59", "00:00"];
        expect(main.findMinDifference(timePoints)).toBe(1);
    });
    test("example2", () => {
        let timePoints = ["00:00", "23:59", "00:00"];
        expect(main.findMinDifference(timePoints)).toBe(0);
    });
    test("wrong1", () => {
        let times = ["01:01", "02:01"];
        expect(main.findMinDifference(times)).toBe(60);
    })
});