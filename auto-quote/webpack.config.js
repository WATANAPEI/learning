module.exports = {
    entry: {
        js: "./client/index.js",
    },
    output: {
        path: __dirname + "/out",
        filename: 'bundle.js',
    },
    module: {
        loaders: [
        {
            test: /\.js$/,
            loader: 'babel-loader',
            exclude: /node_modules/,
            query: {
                presets: ['env', 'react'],
            },
        },
    ],
 },
};

