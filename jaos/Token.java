package jaos;

/**
 * @Type One of the possible TokenTypes (IDENTIFIER, SEMICOLON, CLASS, ...)
 * @Lexeme The actual word or symbol (var, while, !=, while...)
 * @Literal Stored values ()
 * @Line The line of the file where the Token is located
 */
class Token {
    final TokenType type;
    final String lexeme;
    final Object literal;
    final int line;

    Token(TokenType type, String lexeme, Object literal, int line) {
        this.type = type;
        this.lexeme = lexeme;
        this.literal = literal;
        this.line = line;
    }

    public String toString() {
        return type + " " + lexeme + " " + literal;
    }
}