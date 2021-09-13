const converter = require("../format-converter");

describe('word array -> naming case string converters should work', () => {
    const testWords = ['uSER1', 'pHONE2', "nUMBER3"];
    test('toCamelCase', () => {
        expect(converter.toCamelCase(testWords)).toBe('user1Phone2Number3');
    })
    test('toPascalCase', () => {
        expect(converter.toPascalCase(testWords)).toBe('User1Phone2Number3');
    })
    test('toKebabCase', () => {
        expect(converter.toKebabCase(testWords)).toBe('user1-phone2-number3');
    })
    test('toSnakeCase', () => {
        expect(converter.toSnakeCase(testWords)).toBe('user1_phone2_number3');
    })
    test('toScreamingSnakeCase', () => {
        let words = ['user1', 'phone2', 'number3'];
        expect(converter.toScreamingSnakeCase(words)).toBe('USER1_PHONE2_NUMBER3');
    })
})

describe('naming string -> word array should work with valid inputs', () => {
    let extractWordsFrom = converter.extractWordsFrom;
    test('camelCase inputs', () => {
        expect(extractWordsFrom('userPhoneNumber'))
            .toEqual(['user', 'Phone', 'Number']);
        expect(extractWordsFrom('user1Phone2Number3'))
            .toEqual(['user1', 'Phone2', 'Number3']);
        expect(extractWordsFrom('uPN'))
            .toEqual(['u', 'P', 'N']);
    })
    test('PascalCase inputs', () => {
        expect(extractWordsFrom('UserPhoneNumber'))
            .toEqual(['User', 'Phone', 'Number']);
        expect(extractWordsFrom('User1Phone2Number3'))
            .toEqual(['User1', 'Phone2', 'Number3']);
        expect(extractWordsFrom('UPN'))
            .toEqual(['U', 'P', 'N']);
    })
    test('kebabCase inputs', () => {
        expect(extractWordsFrom('user-phone-number'))
            .toEqual(['user', 'phone', 'number']);
        expect(extractWordsFrom('user1-phone2-number3'))
            .toEqual(['user1', 'phone2', 'number3']);
        expect(extractWordsFrom('u-p-n'))
            .toEqual(['u', 'p', 'n']);
    })
    test('snakeCase inputs', () => {
        expect(extractWordsFrom('user_phone_number'))
            .toEqual(['user', 'phone', 'number']);
        expect(extractWordsFrom('user1_phone2_number3'))
            .toEqual(['user1', 'phone2', 'number3']);
        expect(extractWordsFrom('u_p_n'))
            .toEqual(['u', 'p', 'n']);
    })
    test('ScreamingSnakeCase inputs', () => {
        expect(extractWordsFrom('USER_PHONE_NUMBER'))
            .toEqual(['USER', 'PHONE', 'NUMBER']);
        expect(extractWordsFrom('USER1_PHONE2_NUMBER3'))
            .toEqual(['USER1', 'PHONE2', 'NUMBER3']);
        expect(extractWordsFrom('U_P_N'))
            .toEqual(['U', 'P', 'N']);
    })
})

describe('naming string -> word array should throw Error with invalid inputs', () => {
    test('invalid inputs', () => {
        expect(() => {
            converter.extractWordsFrom('uSER1_pHONE2_nUMBER3')
        }).toThrow();
        expect(() => {
            converter.extractWordsFrom('非英语字母')
        }).toThrow();
        expect(() => {
            converter.extractWordsFrom('-user')
        }).toThrow();
        expect(() => {
            converter.extractWordsFrom('_user')
        }).toThrow();
        expect(() => {
            converter.extractWordsFrom('uABCu')
        }).toThrow();
    })
})

test('name string -> naming case Map', () => {
    expect(converter.toNamingCaseMap('user1Phone2Number3'))
        .toEqual(new Map([
            ["origin", 'user1Phone2Number3'],
            ["camelCase", 'user1Phone2Number3'],
            ["pascalCase", 'User1Phone2Number3'],
            ["kebabCase", 'user1-phone2-number3'],
            ["snakeCase", 'user1_phone2_number3'],
            ["screamingSnakeCase", 'USER1_PHONE2_NUMBER3'],
        ]))
})