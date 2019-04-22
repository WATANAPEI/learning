const HtmlWebpackPlugin = require('html-webpack-plugin');
const webpack = require('webpack');
const path = require('path');

module.exports = {
    entry: {
        js: path.resolve(__dirname, './client/index.js')
    },
    output: {
        path: path.resolve(__dirname, './out'),
        filename: 'bundle.js'
    },
    devServer: {
      contentBase: path.resolve(__dirname, './client'),
      port: 8080,
      inline: true,
      disableHostCheck: true,
      host: "0.0.0.0"
    },
    resolve: {
      modules: [path.resolve(__dirname, "client"), "node_modules"],
      extensions: ['.js', '.jsx']
    },
    module: {
            rules: [
            {
                test: /\.js$/,
                use: [
                    {
                        loader: 'babel-loader',
                        options: {
                          presets: ['env', 'react']
                        }
                    },
                ],
                exclude: /node_modules/
            },
            {
                test: /\.txt$/,
                use: [
                    {
                        loader: "raw-loader"
                    }
                   ],
            },
            ],
    },
    plugins: [
      new HtmlWebpackPlugin({template: path.resolve(__dirname, './client/index.html')})
    ]
};

