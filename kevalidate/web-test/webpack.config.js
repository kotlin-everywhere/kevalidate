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
    module: {
        rules: []
    },
    // this will create a development server to host our application
    // and will also provide live reload functionality
    devServer: {
        contentBase: path.join(__dirname, "dist"),
        compress: true,
        port: 5001
    },

    watch: true,
    plugins: [new HtmlWebPackPlugin({template: 'src/index.ejs'})]
};