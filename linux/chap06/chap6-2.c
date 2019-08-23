#include <stdio.h>
#include <stdlib.h>

int
main(int argc, char *argv[])
{
    int i;
    int line_num = 0;
    for (i = 1; i < argc; i++) {
        FILE *f;
        int c;

        if (argc < 2) {
            fprintf(stderr, "%s: invalid argument number\n", argv[0]);
            exit(1);
        }
        f = fopen(argv[i], "r");
        if (!f) {
            perror(argv[i]);
            exit(1);
        }
        while((c = fgetc(f)) != EOF) {
            if (c == '\n') {
                    line_num++;
            }
        }
        line_num++;
        printf("line number is %d\n", line_num);
        fclose(f);
    }
    exit(0);
}
