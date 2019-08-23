#include <stdio.h>
#include <stdlib.h>
#define _GNU_SOURCE
#include <getopt.h>


static void do_cat(const char *path);

static struct option longopts[] = {
    {"tab", no_argument, NULL, 't'},
    {"new", no_argument, NULL, 'n'},
    {0, 0, 0, 0,}
};

int
main(int argc, char *argv[])
{
    int i;
    while((getopt_long(argc, argv, "tn", longopts, NULL)) != -1){

    }
    for (i = 1; i < argc; i++) {
        do_cat(argv[i]);
    }
    exit(0);
}

static void do_cat(const char *path)
{
    FILE *f;
    int c;

    f = fopen(path, "r");
    if (!f) {
        perror(path);
        exit(1);
    }
    while((c = fgetc(f)) != EOF) {
        switch(c) {
            case '\t':
                if (fputs("\\t", stdout) == EOF) exit(1);
                break;
            case '\n':
                if (fputs("$\n", stdout) == EOF) exit(1);
                break;
            default:
                if (putchar(c) < 0) exit(1);
                break;
        }
    }
    fclose(f);
}
