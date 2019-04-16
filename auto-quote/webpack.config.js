module.exports = {
    entry: "./client/index.js",
    output: {
        path: __dirname + "/out",
        filename: 'bundle.js'
    },
    module: {
        rules: [
        {
            test: /\.js$/,
            use: ['babel-loader'],
            exclude: /node_modules/,
            query: {
                presets: ['env', 'react'],
            },
        },
    ],
 },
};

