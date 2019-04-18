FROM node:8.10.0

RUN useradd --user-group --create-home --shell /bin/false app

ENV HOME=/home/app

COPY package.json webpack.config.js $HOME/md-prev/
COPY client $HOME/md-prev/client

RUN chown -R app:app $HOME/*

USER app
WORKDIR $HOME/md-prev
RUN npm install
RUN npm run build

USER root
COPY . $HOME/md-prev
RUN chown -R app:app $HOME/*
USER app




