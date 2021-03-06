#include <ctype.h>
#include <stdarg.h>
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef enum {
    TK_RESERVED,
    TK_IDENT,
    TK_NUM,
    TK_EOF,
} TokenKind;

typedef enum {
    ND_ADD,
    ND_SUB,
    ND_MUL,
    ND_DIV,
    ND_EQ,
    ND_NE,
    ND_LT,
    ND_LE,
    ND_NUM,
    ND_ASSIGN,
    ND_LVAR,
} NodeKind;

typedef struct Token Token;
typedef struct Node Node;

struct Token {
    TokenKind kind;
    Token *next;
    int val;
    char *str;
    int len;
};

struct Node {
    NodeKind kind;
    Node *lhs;
    Node *rhs;
    int val;
    int offset;
};

Token *token;
char *user_input;
Node *expr();
Node *equality();
Node *relational();
Node *add();
Node *mul();
Node *primary();
Node *unary();
void gen(Node *node);
Token *tokenize(char *p);
void error_at(char *loc, char *fmt, ...);
void error(char *fmt, ...);
bool at_eof();
