const HtmlWebPackPlugin = require("html-webpack-plugin");

const path = require("path");

module.exports = {
    entry: './src/index.js',
    output: {
        path: path.resolve(__dirname, 'dist'),
        filename: 'index.js'
    },
    resolve: {
        modules: [path.resolve(__dirname, '../build/dist'), 'node_modules']
    },
    watch: true,
    plugins: [new HtmlWebPackPlugin({template: 'src/index.ejs'})]
};