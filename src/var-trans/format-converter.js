const config = require('config');

const lowerWordRegExp = new RegExp(config.get('regexp.lowerWord'));
const firstUpperWordRegExp = new RegExp(config.get('regexp.firstUpperWord'), 'g');
const invalidNamingCaseRegExp = new RegExp(config.get('regexp.invalidNamingCase'));

function toScreamingSnakeCase(words) {
    return words.map(toUpperCase).join('_');
}

function toSnakeCase(words) {
    return words.map(toLowerCase).join('_');
}

function toKebabCase(words) {
    return words.map(toLowerCase).join('-');
}

function toPascalCase(words) {
    return words.map(firstLetterToUpperCase).join('');
}

function toCamelCase(words) {
    return words[0].toLowerCase().concat(words.slice(1).map(firstLetterToUpperCase).join(''));
}

function toLowerCase(word) {
    return word.toLowerCase();
}

function toUpperCase(word) {
    return word.toUpperCase();
}

function firstLetterToUpperCase(word) {
    return word[0].toUpperCase() + word.slice(1).toLowerCase();
}

/**
 *
 * @param {string} variableName
 * @returns {Array} raw words for converting to different naming cases.
 */
function extractWordsFrom(variableName) {
    if (variableName.match(invalidNamingCaseRegExp)) {
        throw TypeError(`Can not recognize this variable name: ${variableName}`);
    } else {
        let hasUnderscoreFlag = variableName.includes('_');
        let hasHyphenFlag = variableName.includes('-');
        let firstCharacter = variableName[0];
        let firstLetterIsUpperCaseFlag = firstCharacter === firstCharacter.toUpperCase();

        if (hasUnderscoreFlag) {
            return variableName.split('_');
        } else if (hasHyphenFlag) {
            return variableName.split('-');
        } else if (firstLetterIsUpperCaseFlag) {
            return extractWordsFromPascalCaseName(variableName);
        } else {
            return extractWordsFromCamelCaseName(variableName);
        }
    }
}

function extractWordsFromCamelCaseName(variableName) {
    let headWord = variableName.match(lowerWordRegExp)[0];
    return [headWord, ...extractWordsFromPascalCaseName(variableName.slice(headWord.length))];
}

function extractWordsFromPascalCaseName(variableName) {
    return [...variableName.match(firstUpperWordRegExp)];
}

function toNamingCaseMap(variableName) {
    try {
        let words = extractWordsFrom(variableName);
        return new Map([
            ['origin', variableName],
            ['camelCase', toCamelCase(words)],
            ['pascalCase', toPascalCase(words)],
            ['kebabCase', toKebabCase(words)],
            ['snakeCase', toSnakeCase(words)],
            ['screamingSnakeCase', toScreamingSnakeCase(words)],
        ]);
    } catch (e) {
        console.error("Naming case converter only return origin name due to: " + e);
        return new Map([['origin', variableName]]);
    }
}

module.exports = {
    toCamelCase: toCamelCase,
    toPascalCase: toPascalCase,
    toKebabCase: toKebabCase,
    toSnakeCase: toSnakeCase,
    toScreamingSnakeCase: toScreamingSnakeCase,
    extractWordsFrom: extractWordsFrom,
    toNamingCaseMap: toNamingCaseMap,
}