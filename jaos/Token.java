package jaos;

/**
 * {@code @Type} One of the possible TokenTypes (IDENTIFIER, SEMICOLON, CLASS, ...)<br>
 * {@code @Lexeme} The actual word or symbol (var, while, !=, while, ...)<br>
 * {@code @Literal} Stored values (5.4, "string", True, ...)<br>
 * {@code @Line} The line of the file where the Token is located<br>
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