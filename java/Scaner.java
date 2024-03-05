package java;

import java.util.ArrayList;
import java.util.List;

import static java.TokenType.*;

/**
 * Object with functions to convert a String contaning the code into a list of Tokens
 */
class Scanner {
    private final String source;
    private final List<Token> tokens = new ArrayList<>();

    // Track the scanners current position
    private int start = 0;
    private int current = 0;
    private int line = 1;

    Scanner(String source) {
        this.source = source;
    }

    /**
     * 
     * @return List of parsed Tokens
     */
    List<Token> scanTokens() {
        while (!isAtEnd()) {
            // We are at the beginning of the next lexeme.
            start = current;
            scanToken();
        }

        tokens.add(new Token(EOF, "", null, line));
        return tokens;
    }

    /**
     * 
     * @return True if the source has been consumed in its entirety, False otherwise
     */
    private boolean isAtEnd() {
        return current >= source.length();
    }

    /**
     * Move the current scanner position and add a token to the list if a Token is recognized
     */
    private void scanToken() {
        char c = advance();
        switch (c) {
            case '(':
                addToken(LEFT_PAREN);
                break;
            case ')':
                addToken(RIGHT_PAREN);
                break;
            case '{':
                addToken(LEFT_BRACE);
                break;
            case '}':
                addToken(RIGHT_BRACE);
                break;
            case ',':
                addToken(COMMA);
                break;
            case '.':
                addToken(DOT);
                break;
            case '-':
                addToken(MINUS);
                break;
            case '+':
                addToken(PLUS);
                break;
            case ';':
                addToken(SEMICOLON);
                break;
            case '*':
                addToken(STAR);
                break;
        }
    }

    /**
     * read the next character
     * @return
     */
    private char advance() {
        return source.charAt(current++);
    }

    /**
     * Add a Token of the specified type to the list
     * @param type one of the possible values of the TokenTypes enum
     */
    private void addToken(TokenType type) {
        addToken(type, null);
    }

    /**
     * Add a Token of the specified type to the list, indicating a variable
     * @param type one of the possible values of the TokenTypes enum
     * @param literal value to store on the token
     */
    private void addToken(TokenType type, Object literal) {
        String text = source.substring(start, current);
        tokens.add(new Token(type, text, literal, line));
    }
}