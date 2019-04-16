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
            use: [
                {
                    loader: 'babel-loader',
                    options: {
                      presets: ['env', 'react']
                    }
                }
            ],
            exclude: /node_modules/
        },
    ],
 },
};

