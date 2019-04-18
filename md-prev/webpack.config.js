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
      contentBase: path.resolve(__dirname, './index.html'),
      port: 3000
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
            }
        ],
     },
    plugins: [
      new HtmlWebpackPlugin({template: path.resolve(__dirname, './client/index.html')})
    ]
};

