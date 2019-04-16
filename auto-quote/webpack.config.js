module.exports = {
    entry: {
        js: "./client/index.js",
        html: "./client/index.html"
    },
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
                },
                {
                    test: /\.html$/,
                    loader: 'file?name=[name].[ext]'
                }
            ],
            exclude: /node_modules/
        },
    ],
 },
};

